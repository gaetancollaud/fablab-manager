package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import net.collaud.fablab.manager.dao.MotionStockRepository;
import net.collaud.fablab.manager.dao.PurchaseRepository;
import net.collaud.fablab.manager.dao.RevisionRepository;
import net.collaud.fablab.manager.dao.SubscriptionRepository;
import net.collaud.fablab.manager.dao.UsageRepository;
import net.collaud.fablab.manager.dao.UserPaymentRepository;
import net.collaud.fablab.manager.data.MotionStockEO;
import net.collaud.fablab.manager.data.PurchaseEO;
import net.collaud.fablab.manager.data.RevisionEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.UserPaymentEO;
import static net.collaud.fablab.manager.data.type.RefundAction.REFUND;
import net.collaud.fablab.manager.data.virtual.HistoryEntry;
import net.collaud.fablab.manager.data.virtual.UserAccountEntry;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.rest.v1.criteria.PeriodSearchCriteria;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.AccountingService;
import net.collaud.fablab.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.PAYMENT_VIEW})
public class AccountingServiceImpl implements AccountingService {

    @Autowired
    private UserPaymentRepository userPaymentDao;

    @Autowired
    private SubscriptionRepository subscriptionDao;

    @Autowired
    private UsageRepository usageDao;

    @Autowired
    private MotionStockRepository motionStockDao;

    @Autowired
    private RevisionRepository revisionDao;

    @Autowired
    private PurchaseRepository purchaseDao;
    @Autowired
    private UserService userService;

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public List<HistoryEntry> getAccountingEntries(PeriodSearchCriteria search) {
        if (search.isOneDateNull()) {
            throw new FablabException("Dates cannot be null");
        }
        List<UsageEO> listUsage = usageDao.getAllBetween(search.getDateFrom(), search.getDateTo());
        List<UserPaymentEO> listPayment = userPaymentDao.getAllBetween(search.getDateFrom(), search.getDateTo());
        List<SubscriptionEO> listSubscription = subscriptionDao.getAllBetween(search.getDateFrom(), search.getDateTo());
        List<RevisionEO> listRevision = revisionDao.getAllBetween(search.getDateFrom(), search.getDateTo());
        List<PurchaseEO> listPurchase = purchaseDao.getAllBetween(search.getDateFrom(), search.getDateTo());
        List<MotionStockEO> listMotionStock = motionStockDao.getAllBetween(search.getDateFrom(), search.getDateTo());
        return convertToHistoryEntry(listUsage, listPayment, listSubscription, listRevision, listPurchase, listMotionStock);
    }

    protected List<HistoryEntry> convertToHistoryEntry(List<UsageEO> listUsage,
            List<UserPaymentEO> listPayment, List<SubscriptionEO> listSubscription,
            List<RevisionEO> listRevision, List<PurchaseEO> listPurchase, List<MotionStockEO> listMotionStock) {
        final List<HistoryEntry> listHistory = new ArrayList<>();
        for (UserPaymentEO p : listPayment) {
            if (!p.isActive()) {
                //copy the element to add it twice. first when was active and 
                //second when is cancelled (evite la copie profonde)
                p.setActive(true);
                listHistory.add(new HistoryEntry(p));
                p.setActive(false);
            }
            listHistory.add(new HistoryEntry(p));
        }
        for (SubscriptionEO s : listSubscription) {
            if (!s.isActive()) {
                //copy the element to add it twice. first when was active and 
                //second when is cancelled (evite la copie profonde)
                s.setActive(true);
                listHistory.add(new HistoryEntry(s));
                s.setActive(false);
            }
            listHistory.add(new HistoryEntry(s));
        }
        for (RevisionEO r : listRevision) {
            if (r.getCost() != 0) {
                if (!r.isActive()) {
                    //copy the element to add it twice. first when was active and 
                    //second when is cancelled (evite la copie profonde)
                    r.setActive(true);
                    listHistory.add(new HistoryEntry(r));
                    r.setActive(false);
                }
                listHistory.add(new HistoryEntry(r));
            }
        }
        for (MotionStockEO ms : listMotionStock) {
            if (!ms.getIo().equals("Sortie")) {
                if (!ms.isActive()) {
                    //copy the element to add it twice. first when was active and 
                    //second when is cancelled (evite la copie profonde)
                    ms.setActive(true);
                    listHistory.add(new HistoryEntry(ms));
                    ms.setActive(false);
                }
                listHistory.add(new HistoryEntry(ms));
            }
        }
        for (PurchaseEO p : listPurchase) {
            if (!p.isActive()) {
                //copy the element to add it twice. first when was active and 
                //second when is cancelled (evite la copie profonde)
                p.setActive(true);
                listHistory.add(new HistoryEntry(p));
                p.setActive(false);
            }
            listHistory.add(new HistoryEntry(p));
        }
        for (UsageEO u : listUsage) {
            if (!u.isActive()) {
                //copy the element to add it twice. first when was active and 
                //second when is cancelled (evite la copie profonde)
                u.setActive(true);
                listHistory.add(new HistoryEntry(u));
                u.setActive(false);
            }
            listHistory.add(new HistoryEntry(u));
        }
        Collections.sort(listHistory);
        return listHistory;
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public List<UserAccountEntry> getAccountingEntries(Integer userId) {
        UserEO user = userService.getById(userId).get();
        List<UsageEO> listUsage = usageDao.findAllWithActive(user.getId());
        List<UserPaymentEO> listPayment = userPaymentDao.findAllWithActive(user.getId());
        List<SubscriptionEO> listSubscription = subscriptionDao.findAllWithActive(user.getId());
        List<PurchaseEO> listPurchase = purchaseDao.findAllWithActive(user.getId());
        List<MotionStockEO> listMotionStock = motionStockDao.findAllWithActive(user.getId());
        return convertToHistoryEntryForUser(listUsage, listPayment, listSubscription, listPurchase,
                listMotionStock, user);
    }

    protected List<UserAccountEntry> convertToHistoryEntryForUser(List<UsageEO> listUsage,
            List<UserPaymentEO> listPayment, List<SubscriptionEO> listSubscription,
            List<PurchaseEO> listPurchase, List<MotionStockEO> listMotionStock, UserEO user) {
        final List<UserAccountEntry> userAccount = new ArrayList<>();
        for (UserPaymentEO p : listPayment) {
            if (Objects.equals(p.getUser().getId(), user.getId())) {
                if (p.getCashier() == null) {
                    if (!p.isPayedForFabLab()) {
                        if (!p.isActive()) {
                            //copy the element to add it twice. first when was active and 
                            //second when is cancelled (evite la copie profonde)
                            p.setActive(true);
                            userAccount.add(new UserAccountEntry(p, user));
                            p.setActive(false);
                        }
                        userAccount.add(new UserAccountEntry(p, user));
                    }
                } else {
                    if (p.getRefund().equals(REFUND)) {
                        if (!p.isActive()) {
                            //copy the element to add it twice. first when was active and 
                            //second when is cancelled (evite la copie profonde)
                            p.setActive(true);
                            userAccount.add(new UserAccountEntry(p, user));
                            p.setActive(false);
                        }
                        userAccount.add(new UserAccountEntry(p, user));
                    }
                }
            }
        }
        for (SubscriptionEO s : listSubscription) {
            if (Objects.equals(s.getUser().getId(), user.getId())) {
                if (!s.isActive()) {
                    //copy the element to add it twice. first when was active and 
                    //second when is cancelled (evite la copie profonde)
                    s.setActive(true);
                    userAccount.add(new UserAccountEntry(s, user));
                    s.setActive(false);
                }
                userAccount.add(new UserAccountEntry(s, user));
            }
        }
        for (MotionStockEO ms : listMotionStock) {
            if (Objects.equals(ms.getUser().getId(), user.getId())) {
                //PURCHASE CASE
                if (ms.getIo().equals("Correction")) {
                    if (!ms.getIo().equals("Sortie")) {
                        if (!ms.isActive()) {
                            //copy the element to add it twice. first when was active and 
                            //second when is cancelled (evite la copie profonde)
                            ms.setActive(true);
                            userAccount.add(new UserAccountEntry(ms, user));
                            ms.setActive(false);
                        }
                        userAccount.add(new UserAccountEntry(ms, user));
                    }
                }
            }
        }
        for (PurchaseEO p : listPurchase) {
            if (Objects.equals(p.getUser().getId(), user.getId())) {
                if (p.getCashier() == null) {
                    if (!p.isActive()) {
                        //copy the element to add it twice. first when was active and 
                        //second when is cancelled (evite la copie profonde)
                        p.setActive(true);
                        userAccount.add(new UserAccountEntry(p, user));
                        p.setActive(false);
                    }
                    userAccount.add(new UserAccountEntry(p, user));
                }
            }
        }
        for (UsageEO u : listUsage) {
            if (Objects.equals(u.getUser().getId(), user.getId())) {
                if (u.getCashier() == null) {
                    if (!u.isActive()) {
                        //copy the element to add it twice. first when was active and 
                        //second when is cancelled (evite la copie profonde)
                        u.setActive(true);
                        userAccount.add(new UserAccountEntry(u, user));
                        u.setActive(false);
                    }
                    userAccount.add(new UserAccountEntry(u, user));
                }
            }
        }
        Collections.sort(userAccount);
        return userAccount;
    }
}
