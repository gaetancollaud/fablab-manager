package net.collaud.fablab.api.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.audit.AuditUtils;
import net.collaud.fablab.api.dao.MachineRepository;
import net.collaud.fablab.api.dao.PaymentRepository;
import net.collaud.fablab.api.dao.SubscriptionRepository;
import net.collaud.fablab.api.dao.UsageRepository;
import net.collaud.fablab.api.dao.UserRepository;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.data.PaymentEO;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.AuditAction;
import net.collaud.fablab.api.data.type.AuditObject;
import static net.collaud.fablab.api.data.type.AuditObject.PAYMENT;
import static net.collaud.fablab.api.data.type.AuditObject.SUBSCRIPTION;
import static net.collaud.fablab.api.data.type.AuditObject.USAGE;
import net.collaud.fablab.api.data.virtual.HistoryEntry;
import net.collaud.fablab.api.data.virtual.HistoryEntryId;
import net.collaud.fablab.api.data.virtual.UserPaymentHistory;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.criteria.PeriodSearchCriteria;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.AuditService;
import net.collaud.fablab.api.service.PaymentService;
import net.collaud.fablab.api.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Slf4j
public class PaymentServiceImpl extends AbstractServiceImpl implements PaymentService {

	@Autowired
	private AuditService audtiService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UsageRepository usageRepository;

	@Autowired
	private MachineRepository machineRepository;

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public PaymentEO addPayment(Integer userId, Date datePayment, double amount, String comment) {
		UserEO user = userRepository.findOneDetails(userId).orElseThrow(() -> new RuntimeException("Cannot find user with id " + userId));
		PaymentEO payment = new PaymentEO(datePayment, amount, user, securityService.getCurrentUser().get(), comment);
		payment = paymentRepository.save(payment);
		return payment;
	}

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public UsageEO useMachine(Integer userId, Integer machineId, Date startDate, int minutes, double additionalCost, String comment, boolean paidDirectly) {
		UserEO user = userRepository.findOneDetails(userId).orElseThrow(() -> new RuntimeException("Cannot find user with id " + userId));
		MachineEO machine = machineRepository.findOne(machineId);
		double hourPrice = machine.getMachineType().getPriceList().stream()
				.filter(p -> p.getMembershipTypeId()==user.getMembershipType().getId())
				.findFirst()
				.map(pm -> pm.getPrice())
				.orElseThrow(() -> new RuntimeException("Cannot find price for usage"));
		double amount = hourPrice*minutes/60+additionalCost;
		UsageEO usage = new UsageEO(startDate, hourPrice, minutes, additionalCost, comment, user, machine, user.getMembershipType());
		usage = usageRepository.save(usage);
		
		if(paidDirectly){
			//the user paid directly
			addPayment(userId, startDate, amount, comment);
		}
		
		return usage;
	}

	@Override
	@Secured({Roles.ACCOUNTING_VIEW})
	public List<HistoryEntry> getPaymentEntries(PeriodSearchCriteria search) {
		if (search.isOneDateNull()) {
			throw new FablabException("Dates cannot be null");
		}
		List<UsageEO> listUsage = usageRepository.getAllBetween(search.getDateFrom(), search.getDateTo());
		List<PaymentEO> listPayment = paymentRepository.getAllBetween(search.getDateFrom(), search.getDateTo());
		List<SubscriptionEO> listSubscription = subscriptionRepository.getAllBetween(search.getDateFrom(), search.getDateTo());
		return convertToHistoryEntry(listUsage, listPayment, listSubscription);
	}

	@Override
	public UserPaymentHistory getLastPaymentEntries(Integer userId) {
		if(!securityService.getCurrentUserId().equals(userId)){
			securityService.checkRoles(Roles.PAYMENT_MANAGE);
		}
		
		List<HistoryEntry> listHistory = getHistoryEntriesForuser(userId);
		double balance = userRepository.getUserBalanceFromUserId(userId)
				.map(ub -> ub.getValue())
				.orElse(0d);
		return new UserPaymentHistory(listHistory, balance);
	}

	protected List<HistoryEntry> getHistoryEntriesForuser(Integer userId) {
		List<UsageEO> listUsage = usageRepository.getByUser(userId);
		List<PaymentEO> listPayment = paymentRepository.getByUser(userId);
		List<SubscriptionEO> listSubscription = subscriptionRepository.getByUser(userId);

		return convertToHistoryEntry(listUsage, listPayment, listSubscription);
	}

	protected List<HistoryEntry> convertToHistoryEntry(List<UsageEO> listUsage, List<PaymentEO> listPayment, List<SubscriptionEO> listSubscription) {
		final List<HistoryEntry> listHistory = Stream.concat(
				Stream.concat(
						listUsage.stream()
						.map(u -> new HistoryEntry(u)),
						listPayment.stream()
						.map(p -> new HistoryEntry(p))),
				listSubscription.stream()
				.map(s -> new HistoryEntry(s)))
				.sorted()
				.collect(Collectors.toList());
		return listHistory;
	}

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public SubscriptionEO addSubscriptionConfirmation(Integer userId) {
		return addSubscriptionConfirmationIntern(userId);
	}

	@Override
	public SubscriptionEO addSubscriptionConfirmationForCurrentUser() {
		return addSubscriptionConfirmationIntern(securityService.getCurrentUserId());
	}

	private SubscriptionEO addSubscriptionConfirmationIntern(Integer userId) {
		Date now = new Date();

		//save user last subscription date
		UserEO user = userRepository.findOneDetails(userId)
				.orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));

		//Check if there is a current subscription
		user.getSubscriptions().stream()
				.sorted(Comparator.comparing(SubscriptionEO::getDateSubscription).reversed())
				.findFirst()
				.ifPresent(s -> {
					Instant end = s.getDateSubscription().toInstant().plus(s.getDuration(), ChronoUnit.DAYS);
					if (end.isAfter(Instant.now())) {
						throw new RuntimeException("Last subscription has not ended yet !");
					}
				});

		//insert subscription
		SubscriptionEO subscription = new SubscriptionEO();
		subscription.setUser(user);
		subscription.setDateSubscription(now);
		subscription.setDuration(user.getMembershipType().getDuration());
		subscription.setPrice(user.getMembershipType().getPrice());
		subscription.setMembershipType(user.getMembershipType());
		return subscriptionRepository.save(subscription);
	}

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public HistoryEntryId removeHistoryEntry(HistoryEntryId historyId) {
		final int id = historyId.getId();
		switch (historyId.getType()) {
			case PAYMENT:
				PaymentEO payment = Optional.ofNullable(paymentRepository.getOne(id))
						.orElseThrow(() -> new RuntimeException("Cannot find payment with id " + id));
				checkDateAccounting(payment.getDatePayment());
				paymentRepository.delete(payment);
				AuditUtils.addAudit(audtiService, securityService.getCurrentUser().get(), AuditObject.PAYMENT, AuditAction.DELETE, true,
						"Payment (amount " + payment.getTotal() + ") removed for user " + payment.getUser().getFirstLastName());
				break;
			case USAGE:
				UsageEO usage = Optional.ofNullable(usageRepository.getOne(id))
						.orElseThrow(() -> new RuntimeException("Cannot find usage with id " + id));
				checkDateAccounting(usage.getDateStart());
				usageRepository.delete(usage);
				AuditUtils.addAudit(audtiService, securityService.getCurrentUser().get(), AuditObject.PAYMENT, AuditAction.DELETE, true,
						"Machine usage (amount " + (-usage.getTotalPrice()) + ") removed for user " + usage.getUser().getFirstLastName());
				break;
			case SUBSCRIPTION:
				SubscriptionEO subscription = Optional.ofNullable(subscriptionRepository.getOne(id))
						.orElseThrow(() -> new RuntimeException("Cannot find usage with id " + id));
				checkDateAccounting(subscription.getDateSubscription());
				subscriptionRepository.delete(subscription);
				AuditUtils.addAudit(audtiService, securityService.getCurrentUser().get(), AuditObject.PAYMENT, AuditAction.DELETE, true,
						"Subscription (type: "+subscription.getMembershipType().getName()+", amount:" + (-subscription.getPrice()) + ") removed for user " + subscription.getUser().getFirstLastName());
				break;
			default:
				log.error("Cannot remove {} history entry", historyId.getType());
				return null;
		}
		return historyId;
	}

	private void checkDateAccounting(Date date) {
		Duration duration = Duration.between(date.toInstant(), Instant.now());
		long limit = 7;
		final long days = duration.toDays();
		if (days > limit) {
			throw new RuntimeException("Cannot edit accounting information of more than " + limit + " days (current was " + days + " day old)");
		}
	}

}
