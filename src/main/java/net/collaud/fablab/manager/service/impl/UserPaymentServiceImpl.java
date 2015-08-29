package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.UserPaymentRepository;
import net.collaud.fablab.manager.data.UserPaymentEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>UserPayment</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.PAYMENT_VIEW})
public class UserPaymentServiceImpl implements UserPaymentService {

    @Autowired
    private UserPaymentRepository userPaymentDAO;

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public List<UserPaymentEO> findAll() {
        return new ArrayList(new HashSet(userPaymentDAO.findAll()));
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public Optional<UserPaymentEO> getById(Integer id) {
        return userPaymentDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public UserPaymentEO save(UserPaymentEO userPayment) {
        if (userPayment.getId() == null) {
            userPayment.setId(0);
        }
        if (userPayment.getId() > 0) {
            UserPaymentEO old = userPaymentDAO.findOne(userPayment.getId());
            old.setTotal(userPayment.getTotal());
            old.setDatePayment(userPayment.getDatePayment());
            old.setDiscount(userPayment.getDiscount());
            old.setDiscountPercent(userPayment.isDiscountPercent());
            old.setAmount(userPayment.getAmount());
            old.setLabel(userPayment.getLabel());
            old.setNote(userPayment.getNote());
            old.setUser(userPayment.getUser());
            old.setCashier(userPayment.getCashier());
            old.setRefund(userPayment.getRefund());
            old.setEvent(userPayment.isEvent());
            old.setPayedForFabLab(userPayment.isPayedForFabLab());
            old.setAccountCredit(userPayment.getAccountCredit());
            old.setAccountDebit(userPayment.getAccountDebit());
            old.setActive(userPayment.isActive());
            return userPaymentDAO.saveAndFlush(old);
        } else {
            return userPaymentDAO.saveAndFlush(userPayment);
        }
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public void remove(Integer id) {
        UserPaymentEO current = userPaymentDAO.findOne(id);
        current.setActive(false);
        userPaymentDAO.saveAndFlush(current);
    }
}
