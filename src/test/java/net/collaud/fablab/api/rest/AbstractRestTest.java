package net.collaud.fablab.api.rest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.v1.criteria.AuthCredential;
import net.collaud.fablab.api.rest.v1.data.UserTO;
import net.collaud.fablab.api.util.StatefulRestTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author GaÃ©tan
 */
abstract public class AbstractRestTest extends TestCase {

	private static final Logger LOG = Logger.getLogger(AbstractRestTest.class);

	@Value("${local.server.port}")
	private int port;

	private final String resourcePath;

	private final StatefulRestTemplate restTemplate;

	public AbstractRestTest(String resourcePath) {
		assert resourcePath != null : "Path should not be null";
		this.resourcePath = resourcePath;
		restTemplate = new StatefulRestTemplate();
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
			Object resNoLogin = getObject(path, Object.class);
			fail("Exception should be throwed");
		} catch (HttpClientErrorException ex) {
			assertEquals("Status should be 401 (Unauthorized)", HttpStatus.UNAUTHORIZED, ex.getStatusCode());

		}

		loginAs("member1");
		try {
			Object resLoginNoRole = getObject(path, Object.class);
		} catch (HttpClientErrorException ex) {
			assertEquals("Status should be 403 (Forbidden)", HttpStatus.FORBIDDEN, ex.getStatusCode());

		}
		logout();

		loginAs("administrator1");
		Object resLoginRole = getObject(path, Object.class);
		assertNotNull("resut is null", resLoginRole);
		logout();
	}

	protected void loginAs(String username) {
		AuthCredential credential = new AuthCredential(username, "");

		LoginResult result = restTemplate.post("http://localhost:" + getPort() + "/api/v1/auth/login", credential, LoginResult.class);
		assertEquals("Login is not ok", result.getName(), LoginResult.OK.getName());
		LOG.debug("Logged as " + username);
	}

	protected void logout() {
		Object result = restTemplate.get("http://localhost:" + port + "/api/v1/auth/logout", Object.class);
		LOG.debug("Logout sucessful");
	}
}
