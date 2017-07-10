package net.collaud.fablab.manager.dao.impl;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.dao.AssetRepositoryCustom;
import net.collaud.fablab.manager.dao.projection.AssetProjection;
import net.collaud.fablab.manager.data.AssetEO;
import net.collaud.fablab.manager.data.QAssetEO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
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
	public AssetEO findOneWithoutContent(Long id) {
		return new JPAQuery<>(entityManager)
				.select(AssetProjection.projectionWithoutContent(asset))
				.from(asset)
				.innerJoin(asset.owner)
				.where(asset.id.eq(id))
				.fetchOne();
	}

	@Override
	public AssetEO findOneWithContent(Long id) {
		return new JPAQuery<>(entityManager)
				.select(AssetProjection.projectionWithContent(asset))
				.from(asset)
				.innerJoin(asset.owner)
				.where(asset.id.eq(id))
				.fetchOne();
	}

	@Override
	public List<AssetEO> findAll() {
		return new JPAQuery<>(entityManager)
				.select(AssetProjection.projectionWithoutContent(asset))
				.from(asset)
				.innerJoin(asset.owner)
				.orderBy(asset.id.desc())
				.fetch();
	}

	@Override
	public List<AssetEO> findAllForOwner(Long ownerId) {
		return new JPAQuery<>(entityManager)
				.select(AssetProjection.projectionWithoutContent(asset))
				.from(asset)
				.innerJoin(asset.owner)
				.where(asset.owner.id.eq(ownerId))
				.fetch();
	}
}
