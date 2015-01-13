package net.collaud.fablab.api.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.ReservationEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Transactional
public interface ReservationDAO extends JpaRepository<ReservationEO, Integer> {

	@Query("SELECT DISTINCT r "
			+ " FROM ReservationEO r "
			+ " JOIN FETCH r.user "
			+ " JOIN FETCH r.machine "
			+ " WHERE r.dateStart>=?1 AND r.dateEnd<= ?2")
	List<ReservationEO> findReservations(Date dateStart, Date dateEnd);

	@Query("DELETE FROM ReservationEO")
	void removeAllReservations();

	@Override
	@Query("SELECT r "
			+ " FROM ReservationEO r "
			+ " JOIN FETCH r.machine "
			+ " JOIN FETCH r.user "
			+ " WHERE r.reservationId=:id")
	ReservationEO findOne(@Param("id") Integer id);
}
