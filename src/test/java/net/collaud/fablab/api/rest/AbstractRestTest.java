package net.collaud.fablab.api.rest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import junit.framework.TestCase;
import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.v1.criteria.AuthCredential;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.util.StatefulRestTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
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

	protected <T> List<T> callForList(HttpMethod method, String path, Class<T> clazz) {
		Class arr = Array.newInstance(clazz, 0).getClass();
		String completePath = "http://localhost:" + port + "/" + resourcePath + "/" + path;
		Object o = restTemplate.getObject(method, completePath, arr);
		return Arrays.asList((T[]) o);
	}

	protected <T> T callForObject(HttpMethod method, String path, Class<T> clazz) {
		String completePath = "http://localhost:" + port + "/" + resourcePath + "/" + path;
		return restTemplate.getObject(method, completePath, clazz);
	}

	protected <T> T callForObject(HttpMethod method, String path, Object data, Class<T> clazz) {
		String completePath = "http://localhost:" + port + "/" + resourcePath + "/" + path;
		return restTemplate.getObject(method, completePath, data, clazz);
	}

	protected void testRestrictedAccess(HttpMethod method, String path, String... roles) {
		testRestrictedAccess(method, path, null, roles);
	}

	protected void testRestrictedAccess(HttpMethod method, String path, Object data, String... roles) {
		Set<String> rolesToTestSet = new TreeSet<>(Arrays.asList(roles));
		Set<String> allRolesSet = new TreeSet<>(Arrays.asList(RolesHelper.LIST_ROLES));
		LOG.debug("Testing path '" + path + "' for roles " + rolesToTestSet.toString());

		//check if all roles to test exist.
		for (String role : roles) {
			if (!allRolesSet.contains(role)) {
				throw new RuntimeException("Role '" + role + "' unknown. Use RolesHelper for roles list");
			}
		}

		try {
			//should trow 401 because not logged yet
			callForObject(method, path, data, Object.class);
			fail("Exception should be throwed");
		} catch (HttpClientErrorException ex) {
			assertEquals("Status should be 401 (Unauthorized)", HttpStatus.UNAUTHORIZED, ex.getStatusCode());
		}

		for (String role : RolesHelper.LIST_ROLES) {
			//login as role to test
			loginAs(role);

			if (rolesToTestSet.contains(role)) {
				//this role should have access
				try {
					callForObject(method, path, data, Object.class);
				} catch (HttpClientErrorException ex) {
					fail("Role '" + role + " should have access to " + path+". Error :"+ex.getMessage());
				}
			} else {
				//this role should not have access
				try {
					callForObject(method, path, data, Object.class);
					fail("Role '" + role + " should not have access to " + path);
				} catch (HttpClientErrorException ex) {
					assertEquals("Status should be 403 (Forbidden)", HttpStatus.FORBIDDEN, ex.getStatusCode());

				}
			}

			//logout
			logout();
		}
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
