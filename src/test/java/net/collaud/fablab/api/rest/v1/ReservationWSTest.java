package net.collaud.fablab.api.rest.v1;

import net.collaud.fablab.api.rest.AbstractRestTest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import net.collaud.fablab.api.ApplicationTest;
import net.collaud.fablab.api.security.RolesHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gaétan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ReservationWSTest extends AbstractRestTest{
	
	public ReservationWSTest() {
		super("api/v1");
	}
	

	/**
	 * Test of list method, of class ReservationWS.
	 */
	@Test
	public void testAccess() throws Exception {
		testRestrictedAccess(RolesHelper.ROLE_MANAGE_USER, "user");
	}

	
}
