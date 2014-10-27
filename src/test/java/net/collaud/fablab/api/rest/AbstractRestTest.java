package net.collaud.fablab.api.rest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import net.collaud.fablab.api.rest.v1.criteria.AuthCredential;
import net.collaud.fablab.api.util.StatefullRestTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gaétan
 */
abstract public class AbstractRestTest extends TestCase {

	private static final Logger LOG = Logger.getLogger(AbstractRestTest.class);

	@Value("${local.server.port}")
	private int port;

	private final String resourcePath;

	private final StatefullRestTemplate restTemplate;

	public AbstractRestTest(String resourcePath) {
		assert resourcePath != null : "Path should not be null";
		this.resourcePath = resourcePath;
		restTemplate = new StatefullRestTemplate();
	}

	public int getPort() {
		return port;
	}

	protected <T> List<T> getObjectList(String path, Class<T> clazz) {
		Class arr = Array.newInstance(clazz, 0).getClass();
		Object o = restTemplate.get("http://localhost:" + port + "/" + resourcePath + "/" + path, arr);
		return Arrays.asList((T[]) o);
	}

	protected <T> T getObject(String path, Class<T> clazz) {
		return restTemplate.get("http://localhost:" + port + "/" + resourcePath + "/" + path, clazz);
	}

	protected void testRestrictedAccess(String path, String... roles) {
		try {
			Map resNoLogin = getObject(path, Map.class);
			fail("Exception should be throwed");
		} catch (HttpClientErrorException ex) {
			assertEquals("Status should be 401 (Unauthorized)", HttpStatus.UNAUTHORIZED, ex.getStatusCode());

		}

		loginAs("member1");
		try {
			Map resLoginNoRole = getObject(path, Map.class);
		} catch (HttpClientErrorException ex) {
			assertEquals("Status should be 403 (Forbidden)", HttpStatus.FORBIDDEN, ex.getStatusCode());

		}
		logout();

		loginAs("administrator1");
		Map resLoginRole = getObject(path, Map.class);
		assertEquals("Status should be 403 (Forbidden)", 200, resLoginRole.get("status"));
		logout();
	}

	protected void loginAs(String username) {
		AuthCredential credential = new AuthCredential(username, "");

		Object result = restTemplate.post("http://localhost:" + getPort() + "/api/v1/auth/login", credential, Object.class);
		LOG.debug("Logged as " + username);
	}

	protected void logout() {
		Object result = restTemplate.get("http://localhost:" + port + "/api/v1/auth/logout", Object.class);
		LOG.debug("Logout sucessful");
	}
}
