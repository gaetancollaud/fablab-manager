package net.collaud.fablab.api.dao.impl;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.dao.ReservationDAO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gaetan
 */
@Repository
public class ReservationDAOImpl  implements ReservationDAO {

	public ReservationDAOImpl() {
	}


//	@Override
//	public ReservationEO save(ReservationEO reservation) throws FablabException {
//		//FIXME check if no reservation before, use transaction
//		if (reservation.getReservationId() > 0) {
//			return edit(reservation);
//		} else {
//			return create(reservation);
//		}
//	}
//
//	@Override
//	public List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) {
//
//		if (dateStart != null && dateEnd != null && dateStart.after(dateEnd)) {
//			return new ArrayList<>();
//		}
//
//		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//		CriteriaQuery<ReservationEO> cq = cb.createQuery(ReservationEO.class);
//		Root<ReservationEO> reservation = cq.from(ReservationEO.class);
//
//		List<Predicate> listPredicate = new ArrayList<>();
//
//		listPredicate.add(cb.greaterThanOrEqualTo(reservation.get(ReservationEO_.dateStart), dateStart));
//		listPredicate.add(cb.lessThanOrEqualTo(reservation.get(ReservationEO_.dateEnd), dateEnd));
//		
//		if (machineIds != null) {
//			listPredicate.add(reservation.get(ReservationEO_.machine).in(machineIds));
//		}
//
//		cq.where(cb.and(listPredicate.toArray(new Predicate[]{})));
//
//		cq.orderBy(cb.desc(reservation.get(ReservationEO_.machine)));
//		TypedQuery<ReservationEO> query = getEntityManager().createQuery(cq);
//		return query.getResultList();
//	}

	@Override
	public ReservationEO save(ReservationEO current) throws FablabException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void remove(ReservationEO current) throws FablabException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) throws FablabException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
