package net.collaud.fablab.api.dao.specifications;

import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import net.collaud.fablab.api.data.MachineEO_;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.ReservationEO_;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class ReservationSpecifications {

	public static Specification<ReservationEO> from(Date from) {
		return new Specification<ReservationEO>() {
			@Override
			public Predicate toPredicate(Root<ReservationEO> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.greaterThanOrEqualTo(root.get(ReservationEO_.dateStart), from);
			}
		};
	}

	public static Specification<ReservationEO> to(Date to) {
		return new Specification<ReservationEO>() {
			@Override
			public Predicate toPredicate(Root<ReservationEO> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.lessThanOrEqualTo(root.get(ReservationEO_.dateEnd), to);
			}
		};
	}

	public static Specification<ReservationEO> machines(List<Integer> machineIds) {
		return new Specification<ReservationEO>() {
			@Override
			public Predicate toPredicate(Root<ReservationEO> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return root.get(ReservationEO_.machine).get(MachineEO_.id).in(machineIds);
			}
		};
	}
	
	public static Specification<ReservationEO> joins(){
		return new Specification<ReservationEO>() {
			@Override
			public Predicate toPredicate(Root<ReservationEO> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				root.fetch(ReservationEO_.machine);
				root.fetch(ReservationEO_.user);
				return null;
			}
		};
	}

}
