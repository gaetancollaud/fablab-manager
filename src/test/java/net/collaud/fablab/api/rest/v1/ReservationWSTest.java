package net.collaud.fablab.api.rest.v1;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import net.collaud.fablab.api.rest.AbstractRestTest;
import net.collaud.fablab.api.ApplicationTest;
import net.collaud.fablab.api.security.RolesHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author Gaétan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DatabaseSetup({"/data/basic_roles_users.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class ReservationWSTest extends AbstractRestTest{
	
	public ReservationWSTest() {
		super("api/v1");
	}
	

	/**
	 * Test of list method, of class ReservationWS.
	 */
	@Test
	public void testAccess() throws Exception {
		testRestrictedAccess("user", RolesHelper.ROLE_MANAGE_USER);
	}

	
}
