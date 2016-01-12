package net.collaud.fablab.manager.rest.v1;

import java.util.Optional;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.AssetEO;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/api/v1/asset")
@JavascriptAPIConstant("ASSET_API")
public class AssetWS {

	@Autowired
	private AssetService assetService;

	@RequestMapping(value = "/{id}.{ext}", method = RequestMethod.GET)
	public ResponseEntity getByIdWithExt(@PathVariable Integer id) {
		return getById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable Integer id) {
		final Optional<AssetEO> optAsst = assetService.getById(id);
		if (optAsst.isPresent()) {
			AssetEO asset = optAsst.get();
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(asset.getMime()))
					.body(asset.getData());
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getList() {
		return ResponseEntity.ok(assetService.findAll());
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity create(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				final AssetEO asset = assetService.upload(file);
				asset.setData(null);
				return ResponseEntity.ok().body(asset);
			} catch (FablabException ex) {
				return ResponseEntity.badRequest().body(ex.getMessage());
			}
			
		}
		return ResponseEntity.badRequest().body("Empty file");
	}

}
