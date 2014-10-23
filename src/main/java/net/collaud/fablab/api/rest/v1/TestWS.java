package net.collaud.fablab.api.rest.v1;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaétan
 */
@RestController()
@RequestMapping("/api/v1/test")
public class TestWS {
	
	@RequestMapping()
	public List<String> listModulesRegistered() {
		return Arrays.asList(new String[]{"hello", "world"});
	}
	
}
