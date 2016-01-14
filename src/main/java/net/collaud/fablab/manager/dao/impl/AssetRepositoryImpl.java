package net.collaud.fablab.manager.dao.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.dao.AssetRepositoryCustom;
import net.collaud.fablab.manager.dao.projection.AssetProjection;
import net.collaud.fablab.manager.data.AssetEO;
import net.collaud.fablab.manager.data.QAssetEO;
import net.collaud.fablab.manager.data.UserEO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
@Transactional
@Slf4j
public class AssetRepositoryImpl implements AssetRepositoryCustom {

	public static final int MAX_LIMIT = 1000;
	public static final int DEFAULT_LIMIT = 100;

	private final QAssetEO asset = QAssetEO.assetEO;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AssetEO findOneWithoutContent(Integer id) {
		return new JPAQuery(entityManager)
				.from(asset)
				.innerJoin(asset.owner)
				.where(asset.id.eq(id))
				.singleResult(AssetProjection.projectionWithoutContent(asset));
	}

	@Override
	public AssetEO findOneWithContent(Integer id) {
		return new JPAQuery(entityManager)
				.from(asset)
				.innerJoin(asset.owner)
				.where(asset.id.eq(id))
				.singleResult(AssetProjection.projectionWithContent(asset));
	}

	@Override
	public List<AssetEO> findAll() {
		return new JPAQuery(entityManager)
				.from(asset)
				.innerJoin(asset.owner)
				.orderBy(asset.id.desc())
				.list(AssetProjection.projectionWithoutContent(asset));
	}

	@Override
	public List<AssetEO> findAllForOwner(Integer ownerId) {
		return new JPAQuery(entityManager)
				.from(asset)
				.innerJoin(asset.owner)
				.where(asset.owner.id.eq(ownerId))
				.list(AssetProjection.projectionWithoutContent(asset));
	}
}
