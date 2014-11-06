package net.collaud.fablab.api.rest.v1;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.ArrayList;
import java.util.Date;
import net.collaud.fablab.api.rest.AbstractRestTest;
import net.collaud.fablab.api.ApplicationTest;
import net.collaud.fablab.api.rest.v1.criteria.ReservationSearchCriteria;
import net.collaud.fablab.api.rest.v1.data.ReservationTO;
import net.collaud.fablab.api.security.RolesHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
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
@DatabaseSetup({"/data/roles_users.xml", "/data/reservations.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
public class ReservationWSTest extends AbstractRestTest {

	public ReservationWSTest() {
		super("api/v1");
	}

	/**
	 * Test of list method, of class ReservationWS.
	 */
	@Test
	public void testAccess() throws Exception {
		ReservationSearchCriteria criteria = new ReservationSearchCriteria(new Date(), new Date(), new ArrayList<Integer>());
		testRestrictedAccess(HttpMethod.POST, "reservation/search", criteria, RolesHelper.ROLE_VIEW_RESERVATION);

		ReservationTO reservation = new ReservationTO(0, new Date(), new Date(), 1, 1);
		testRestrictedAccess(HttpMethod.POST, "reservation", reservation, RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);
		testRestrictedAccess(HttpMethod.PUT, "reservation/1", reservation, RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);
		testRestrictedAccess(HttpMethod.DELETE, "reservation/1", RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);

	}

}
