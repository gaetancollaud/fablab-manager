package net.collaud.fablab.api.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.ReservationEO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ReservationRepository extends JpaRepository<ReservationEO, Integer>, JpaSpecificationExecutor<ReservationEO> {

	@Query("SELECT DISTINCT r "
			+ " FROM ReservationEO r "
			+ " JOIN FETCH r.user "
			+ " JOIN FETCH r.machine ")
	List<ReservationEO> findReservations(Specification<ReservationEO> specification);

	@Query("DELETE FROM ReservationEO")
	void removeAllReservations();

	@Override
	@Query("SELECT r "
			+ " FROM ReservationEO r "
			+ " JOIN FETCH r.machine "
			+ " JOIN FETCH r.user "
			+ " WHERE r.id=:id")
	ReservationEO findOne(@Param("id") Integer id);
}
