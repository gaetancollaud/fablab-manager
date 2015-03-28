package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.PriceRevisionEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
public interface PriceRepository extends JpaRepository<PriceRevisionEO, Integer> {

	@Query(" SELECT t "
			+ " FROM PriceRevisionEO t "
			+ " ORDER BY t.dateRevision DESC ")
	public List<PriceRevisionEO> findAll();

}
