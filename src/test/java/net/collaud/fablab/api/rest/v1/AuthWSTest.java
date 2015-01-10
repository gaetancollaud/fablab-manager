package net.collaud.fablab.api.rest.v1;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.AbstractRestTest;
import net.collaud.fablab.api.rest.v1.criteria.AuthCredential;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@DatabaseSetup({"/data/roles_users.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
@Ignore
public class AuthWSTest extends AbstractRestTest {

	public AuthWSTest() {
		super("api/v1/auth");
	}

	@Test
	public void testIsAuthenticated() {
		Boolean authenticated = callForObject(HttpMethod.GET, "isAuthenticated", Boolean.class);
		assertFalse("Should not be authenticated", authenticated);

		//FIXME remove hashed password
		loginAs("member1", "94ad0573ed0f4a7607f857e571a2d997f1208b868d7f580c646267b63fea25ec");
		authenticated = callForObject(HttpMethod.GET, "isAuthenticated", Boolean.class);
		assertTrue("Should be authenticated", authenticated);

		logout();
		authenticated = callForObject(HttpMethod.GET, "isAuthenticated", Boolean.class);
		assertFalse("Should not be authenticated", authenticated);
	}

	/**
	 * Test of login method, of class AuthWS.
	 */
	@Test
	public void testLogin() {
		AuthCredential credential = new AuthCredential("unknownUsername", "wrongPassword");
		LoginResult result = callForObject(HttpMethod.POST, "login", credential, LoginResult.class);
		assertEquals("Username should be unknown", LoginResult.UNKNOWN_USERNAME, result);
		
		credential.setLogin("member1");
		result = callForObject(HttpMethod.POST, "login", credential, LoginResult.class);
		assertEquals("Password should be wrong", LoginResult.WRONG_PASSWORD, result);
		
		credential.setPassword("fablab");
		//FIXME remove hashed password
		credential.setPassword("94ad0573ed0f4a7607f857e571a2d997f1208b868d7f580c646267b63fea25ec");
		result = callForObject(HttpMethod.POST, "login", credential, LoginResult.class);
		assertEquals("Login should be ok", LoginResult.OK, result);
		
		result = callForObject(HttpMethod.POST, "login", credential, LoginResult.class);
		assertEquals("User should be already connected", LoginResult.ALREADY_CONNECTED, result);
		
		logout();
	}

}
