package net.collaud.fablab.manager.rest.v1;

import net.collaud.fablab.manager.service.SystemDoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Gaétan on 25/01/2017.
 */
@RestController()
@RequestMapping("/api/v1/system/door")
public class SystemDoorWS {

	@Autowired
	private SystemDoorService systemDoorService;

	@RequestMapping(value = "rfids", method = RequestMethod.GET)
	public Map<String, String> getAllWithRfid() {
		return systemDoorService.getRfids();
	}
}
