package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.PriceRevisionEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
public interface PriceRepository extends JpaRepository<PriceRevisionEO, Integer> {
}
