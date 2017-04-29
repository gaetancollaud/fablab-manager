package net.collaud.fablab.manager.service.impl;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.AssetRepository;
import net.collaud.fablab.manager.dao.UserRepository;
import net.collaud.fablab.manager.data.AssetEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.ConfigurationKey;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.exceptions.FileTypeNotAllowedException;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.AssetService;
import net.collaud.fablab.manager.service.ConfigurationService;
import net.collaud.fablab.manager.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaetan Collaud
 */
@Component
@Transactional
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private Environment env;

	@Override
	@Secured({Roles.ASSET_MANAGE, Roles.ASSET_MANAGE})
	public void remove(Long id) {
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
	public Optional<AssetEO> getById(Long id) {
		return Optional.ofNullable(assetRepository.findOneWithContent(id));
	}

	@Override
	@Secured({Roles.ASSET_UPLOAD})
	public AssetEO upload(MultipartFile file) {
		checkIfMimeIsAllowed(file.getContentType());

		UserEO currentUser = userRepository.findOne(securityService.getCurrentUserId());

		int extIndex = file.getOriginalFilename().lastIndexOf(".");
		String name;
		String ext;
		if (extIndex != -1) {
			name = file.getOriginalFilename().substring(0, extIndex);
			ext = file.getOriginalFilename().substring(extIndex + 1);
		} else {
			name = file.getOriginalFilename();
			ext = "";
		}

		try {
			AssetEO asset = AssetEO.builder()
					.data(file.getBytes())
					.mime(file.getContentType())
					.title(name)
					.extension(ext)
					.size((int) file.getSize())
					.dateUpload(Instant.now())
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

	private void checkIfMimeIsAllowed(String mime) {
		String mimeAllowed = configurationService.getValue(ConfigurationKey.UPLOAD_MIME_ALLOWED);
		if (!Arrays.stream(mimeAllowed.split(","))
				.map(String::trim)
				.anyMatch(s -> s.equalsIgnoreCase(mime))) {
			throw new FileTypeNotAllowedException("File type " + mime + " not allowed");
		}
	}

}
