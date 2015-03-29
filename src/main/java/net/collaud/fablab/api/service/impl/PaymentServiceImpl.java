package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.audit.AuditUtils;
import net.collaud.fablab.api.dao.PaymentRepository;
import net.collaud.fablab.api.dao.PriceRepository;
import net.collaud.fablab.api.dao.SubscriptionRepository;
import net.collaud.fablab.api.dao.UsageRepository;
import net.collaud.fablab.api.dao.UserRepository;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.data.PaymentEO;
import net.collaud.fablab.api.data.PriceCotisationEO;
import net.collaud.fablab.api.data.PriceRevisionEO;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.AuditAction;
import net.collaud.fablab.api.data.type.AuditObject;
import net.collaud.fablab.api.data.virtual.HistoryEntry;
import net.collaud.fablab.api.exceptions.FablabException;
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
@Secured({Roles.ADMIN})
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
	private PriceRepository priceRepository;

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public PaymentEO addPayment(UserEO user, Date datePayment, float amount, String comment) {
		PaymentEO payment = new PaymentEO(datePayment, amount, user, securityService.getCurrentUser().get(), comment);
		payment = paymentRepository.save(payment);
		return payment;
	}

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public UsageEO useMachine(UserEO user, MachineEO machine, Date startDate, int minutes, float additionalCost, String comment) {
		PriceRevisionEO priceRev = priceRepository.findAll().get(0);
		//FIXME find price per hour
		double price = -1;
		UsageEO usage = new UsageEO(0, startDate, price, minutes, additionalCost, comment, user, machine, user.getMembershipType(), priceRev);
		usage = usageRepository.save(usage);
		return usage;
	}

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public List<HistoryEntry> getPaymentEntries(Date dateBefore, Date dateAfter) {
		if (dateBefore == null || dateAfter == null) {
			throw new FablabException("Dates cannot be null");
		}
		List<UsageEO> listUsage = usageRepository.getAllBetween(dateBefore, dateAfter);
		List<PaymentEO> listPayment = paymentRepository.getAllBetween(dateBefore, dateAfter);
		List<SubscriptionEO> listSubscription = subscriptionRepository.getAllBetween(dateBefore, dateAfter);
		return convertToHistoryEntry(listUsage, listPayment, listSubscription);
	}

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public List<HistoryEntry> getLastPaymentEntries(Integer userId) {
		List<UsageEO> listUsage = usageRepository.getByUser(userId);
		List<PaymentEO> listPayment = paymentRepository.getByUser(userId);
		List<SubscriptionEO> listSubscription = subscriptionRepository.getByUser(userId);

		List<HistoryEntry> listHistory = convertToHistoryEntry(listUsage, listPayment, listSubscription);
		return listHistory;
	}

	protected List<HistoryEntry> convertToHistoryEntry(List<UsageEO> listUsage, List<PaymentEO> listPayment, List<SubscriptionEO> listSubscription) {
		TreeSet<HistoryEntry> setHistory = new TreeSet<>();

		for (UsageEO usage : listUsage) {
			setHistory.add(new HistoryEntry(usage));
		}

		for (PaymentEO payment : listPayment) {
			setHistory.add(new HistoryEntry(payment));
		}

		for (SubscriptionEO payment : listSubscription) {
			setHistory.add(new HistoryEntry(payment));
		}

		List<HistoryEntry> listHistory = new ArrayList<>(setHistory);

		return listHistory;
	}

	@Override
	@Secured({Roles.PAYMENT_MANAGE})
	public UserEO addSubscriptionConfirmation(UserEO user) {
		return addSubscriptionConfirmationIntern(user);
	}

	@Override
	public UserEO addSubscriptionConfirmationForCurrentUser() {
		UserEO user = securityService.getCurrentUser().get();
		addSubscriptionConfirmationIntern(user);
		return user;
	}

	private UserEO addSubscriptionConfirmationIntern(UserEO userParam) {
		Date now = new Date();

		//save user last subscription date
		UserEO user = userRepository.findOne(userParam.getId());

		//insert subscription
		SubscriptionEO subscription = new SubscriptionEO();
		subscription.setUser(user);
		subscription.setDateSubscription(now);
		final PriceCotisationEO pc = priceRepository.findAll().get(0).getPriceCotisationList()
				.stream()
				.filter(v -> v.getMembershipType().equals(user.getMembershipType()))
				.findFirst().get();
		subscription.setPriceCotisation(pc);
		subscriptionRepository.save(subscription);

		return userRepository.save(user);
	}

	@Override
	public List<HistoryEntry> getPaymentEntriesForCurrentUser() {
		final Optional<UserEO> currentUser = securityService.getCurrentUser();
		if (currentUser.isPresent()) {
			return getLastPaymentEntries(currentUser.get().getId());
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public void removeHistoryEntry(UserEO user, HistoryEntry entry) {
		switch (entry.getType()) {
			case PAYMENT:
				paymentRepository.delete(entry.getId());
				AuditUtils.addAudit(audtiService, securityService.getCurrentUser().get(), AuditObject.PAYMENT, AuditAction.DELETE, true,
						"Payment (amount " + entry.getAmount() + ") removed for user " + user.getFirstLastName());
				break;
			case USAGE:
				usageRepository.delete(entry.getId());
				AuditUtils.addAudit(audtiService, securityService.getCurrentUser().get(), AuditObject.PAYMENT, AuditAction.DELETE, true,
						"Machine usage (amount " + (-entry.getAmount()) + ") removed for user " + user.getFirstLastName());
				break;
		}

	}

}
