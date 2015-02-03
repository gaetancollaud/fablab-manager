package net.collaud.fablab.api.rest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import junit.framework.TestCase;
import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.v1.criteria.AuthCredential;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.util.StatefulRestTemplate;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
abstract public class AbstractRestTest extends TestCase {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractRestTest.class);

	@Value("${local.server.port}")
	private int port;

	private final String resourcePath;

	private StatefulRestTemplate restTemplate;

	public AbstractRestTest(String resourcePath) {
		assert resourcePath != null : "Path should not be null";
		this.resourcePath = resourcePath;
	}

	@Before
	public void setUpRestTest() {
		//new statefull template for each test
		restTemplate = new StatefulRestTemplate();
	}

	public int getPort() {
		return port;
	}

	protected <T> List<T> callForList(HttpMethod method, String path, Class<T> clazz) {
		Class arr = Array.newInstance(clazz, 0).getClass();
		Object o = restTemplate.getObject(method, getCompletePath(path), arr);
		return Arrays.asList((T[]) o);
	}

	protected <T> List<T> callForList(HttpMethod method, String path, Object data, Class<T> clazz) {
		Class arr = Array.newInstance(clazz, 0).getClass();
		Object o = restTemplate.getObject(method, getCompletePath(path), data, arr);
		return Arrays.asList((T[]) o);
	}

	protected <T> T callForObject(HttpMethod method, String path, Class<T> clazz) {
		return restTemplate.getObject(method, getCompletePath(path), clazz);
	}

	protected <T> T callForObject(HttpMethod method, String path, Object data, Class<T> clazz) {
		return restTemplate.getObject(method, getCompletePath(path), data, clazz);
	}

	private String getCompletePath(String path) {
		if (path == null) {
			path = "";
		}
		if (!path.isEmpty()) {
			path = "/" + path;
		}
		return "http://localhost:" + port + "/" + resourcePath + path;
	}

	protected void testRestrictedAccess(HttpMethod method, String path, String... roles) {
		testRestrictedAccess(method, path, null, roles);
	}

	protected void testRestrictedAccess(HttpMethod method, String path, Object data, String... roles) {
		Set<String> rolesToTestSet = new TreeSet<>(Arrays.asList(roles));
		Set<String> allRolesSet = new TreeSet<>(Arrays.asList(Roles.LIST_ROLES));
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

		for (String role : Roles.LIST_ROLES) {
			//login as role to test
			loginAs(role);

			if (rolesToTestSet.contains(role)) {
				//this role should have access
				try {
					callForObject(method, path, data, Object.class);
				} catch (HttpClientErrorException ex) {
					fail("Role '" + role + " should have access to " + path + ". Error :" + ex.getMessage());
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

	protected void loginAs(String username, String password) {
		AuthCredential credential = new AuthCredential(username, password);
		LoginResult result = restTemplate.post("http://localhost:" + getPort() + "/api/v1/auth/login", credential, LoginResult.class);
		assertEquals("Login is not ok", LoginResult.OK.getName(), result.getName());
		LOG.debug("Logged as " + username);

	}

	protected void loginAs(String username) {
		loginAs(username, "");
	}

	protected void loginAsAdministrator() {
		//FIXME
		loginAs("administrator1", "94ad0573ed0f4a7607f857e571a2d997f1208b868d7f580c646267b63fea25ec");
	}

	protected void logout() {
		restTemplate.get("http://localhost:" + port + "/api/v1/auth/logout", Object.class);
		LOG.debug("Logout sucessful");
	}
	
	protected Date getDeltaDateMs(Date ref, long deltaMs){
		return new Date(ref.getTime()+deltaMs);
	}
	
	protected Date getDetlaDateSec(Date ref, long deltaSec){
		return getDeltaDateMs(ref, deltaSec*1000);
	}
	
	protected Date getDeltaDateMin(Date ref, long deltaMin){
		return getDetlaDateSec(ref, deltaMin*60);
	}
	
	protected Date getDeltaDateHour(Date ref, long deltaHour){
		return getDetlaDateSec(ref, deltaHour*60);
	}
}
