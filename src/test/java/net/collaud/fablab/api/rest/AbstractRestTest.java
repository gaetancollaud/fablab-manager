package net.collaud.fablab.api.rest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gaétan
 */
public class AbstractRestTest extends TestCase {

	@Value("${local.server.port}")
	private int port;

	private final String resourcePath;

	public AbstractRestTest(String resourcePath) {
		assert resourcePath != null : "Path should not be null";
		this.resourcePath = resourcePath;
	}

	public int getPort() {
		return port;
	}

	protected <T> List<T> getObjectList(String path, Class<T> clazz) {
		RestTemplate restTemplate = new TestRestTemplate();
		Class arr = Array.newInstance(clazz, 0).getClass();
		Object o = restTemplate.getForObject("http://localhost:" + port + "/" + resourcePath + "/" + path, arr);
		return Arrays.asList((T[]) o);
	}

	protected <T> T getObject(String path, Class<T> clazz) {
		RestTemplate restTemplate = new TestRestTemplate();
		return restTemplate.getForObject("http://localhost:" + port + "/" + resourcePath + "/" + path, clazz);
	}

	protected void testRestrictedAccess(String path, String... roles) {
		Map resNoLogin = getObject(path, Map.class);
		assertEquals("Status should be 401 (Unauthorized)", 401, resNoLogin.get("status"));

		//FIXME implement access tests
//		loginWithRoleMock();
//		Map resLoginNoRole = getObject(path, Map.class);
//		assertEquals("Status should be 403 (Forbidden)", 403, resLoginNoRole.get("status"));
//		
//		loginWithRoleMock(roles);
//		Map resLoginRole = getObject(path, Map.class);
//		assertEquals("Status should be 403 (Forbidden)", 200, resLoginRole.get("status"));
	}

	protected void loginWithRoleMock(String... rolesStr) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String r : rolesStr) {
			authorities.add(new SimpleGrantedAuthority(r));
		}

		Authentication auth = new UsernamePasswordAuthenticationToken("testPrincipal", "testCredential", authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	protected void logoutMock(){
		SecurityContextHolder.clearContext();
	}
}
