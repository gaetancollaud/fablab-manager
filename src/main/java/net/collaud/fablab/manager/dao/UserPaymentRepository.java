package net.collaud.fablab.manager.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.UserPaymentEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import net.collaud.fablab.manager.data.UserEO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>UserPayment</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface UserPaymentRepository extends JpaRepository<UserPaymentEO, Integer> {

    @Query("SELECT u "
            + " FROM UserPaymentEO u  "
            + " LEFT JOIN FETCH u.user  "
            + " LEFT JOIN FETCH u.cashier "
            + " WHERE u.active = true ")
    @Override
    List<UserPaymentEO> findAll();

    @Query("SELECT u "
            + " FROM UserPaymentEO u "
            + " LEFT JOIN FETCH u.user "
            + " LEFT JOIN FETCH u.cashier "
            + " WHERE u.id=:id AND u.active = true")
    Optional<UserPaymentEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT u "
            + " FROM UserPaymentEO u "
            + " LEFT JOIN FETCH u.user "
            + " LEFT JOIN FETCH u.cashier "
            + " WHERE u.user.id=:userId AND u.active = true")
    List<UserPaymentEO> findByUser(@Param("userId") Integer userId);

    @Query(" SELECT u "
            + " FROM UserPaymentEO u "
            + " LEFT JOIN FETCH u.user "
            + " LEFT JOIN FETCH u.cashier "
            + " WHERE u.datePayment>=:dateAfter AND u.datePayment <=:dateBefore")
    public List<UserPaymentEO> getAllBetween(@Param("dateAfter") Date dateAfter, @Param("dateBefore") Date dateBefore);

    @Query("SELECT u "
            + " FROM UserPaymentEO u  "
            + " LEFT JOIN FETCH u.user  "
            + " LEFT JOIN FETCH u.cashier "
            + " WHERE u.user.id = :id")
    public List<UserPaymentEO> findAllWithActive(@Param("id")Integer id);
}
