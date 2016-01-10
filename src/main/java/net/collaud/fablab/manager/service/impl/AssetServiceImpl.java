package net.collaud.fablab.manager.service.impl;

import java.io.IOException;
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
	@Secured({Roles.ASSET_MANAGE})
	public void remove(Integer id) {
		//TODO check rights (ownership)
		//TODO check where it's used
		assetRepository.delete(id);
	}

	@Override
	public Optional<AssetEO> getById(Integer id) {
		return Optional.ofNullable(assetRepository.findOneWithoutContent(id));
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
