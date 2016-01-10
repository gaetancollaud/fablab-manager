package net.collaud.fablab.manager.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.AssetRepository;
import net.collaud.fablab.manager.dao.UserRepository;
import net.collaud.fablab.manager.data.AssetEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.AssetService;
import net.collaud.fablab.manager.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaetan Collaud
 */
@Service
@Transactional
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Secured({Roles.ASSET_MANAGE, Roles.ASSET_MANAGE})
	public void remove(Integer id) {
		final AssetEO asset = assetRepository.findOneWithoutContent(id);
		if (asset == null) {
			throw new DataRetrievalFailureException("asset not found");
		}
		if (!securityService.hasRole(Roles.ASSET_MANAGE)
				&& !securityService.getCurrentUserId().equals(asset.getOwner().getId())) {
			throw new PermissionDeniedDataAccessException("Not the owner", null);
		}
		assetRepository.delete(id);
	}

	@Override
	public Optional<AssetEO> getById(Integer id) {
		return Optional.ofNullable(assetRepository.findOneWithContent(id));
	}

	@Override
	@Secured({Roles.ASSET_UPLOAD})
	public AssetEO upload(String name, MultipartFile file) {
		try {
			UserEO currentUser = userRepository.findOne(securityService.getCurrentUserId());
			AssetEO asset = AssetEO.builder()
					.data(file.getBytes())
					.mime(file.getContentType())
					.title(name)
					.size((int)file.getSize())
					.dateUpload(LocalDateTime.now())
					.owner(currentUser)
					.build();
			return assetRepository.save(asset);
		} catch (IOException ex) {
			throw new FablabException("Cannot upload file ", ex);
		}
	}

	@Override
	public List<AssetEO> findAll() {
		if (securityService.isAuthenticated()) {
			if (securityService.hasRole(Roles.ASSET_MANAGE)) {
				return assetRepository.findAll();
			} else {

				return assetRepository.findAllForOwner(securityService.getCurrentUserId());
			}
		}
		throw new AuthenticationCredentialsNotFoundException(null);
	}

}
