package net.collaud.fablab.api.rest.v1;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.rest.AbstractRestTest;
import net.collaud.fablab.api.ApplicationTest;
import net.collaud.fablab.api.dao.ReservationDAO;
import net.collaud.fablab.api.rest.v1.criteria.ReservationSearchCriteria;
import net.collaud.fablab.api.rest.v1.data.ReservationTO;
import net.collaud.fablab.api.security.RolesHelper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpServerErrorException;

/**
 *
 * @author Gaétan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DatabaseSetup({"/data/roles_users.xml", "/data/reservations.xml"})
@DatabaseTearDown(value = {"/data/roles_users.xml", "/data/reservations.xml"}, type = DatabaseOperation.DELETE)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
public class ReservationWSTest extends AbstractRestTest {

	@Autowired
	private ReservationDAO reservationDao;

	public ReservationWSTest() {
		super("api/v1/reservation");
	}

	@After
	public void tearDownReservation() {
		reservationDao.removeAllReservations();
	}

	/**
	 * Test of list method, of class ReservationWS.
	 *
	 * @throws java.lang.Exception
	 */
	@Test
	public void testAccess() throws Exception {
		ReservationSearchCriteria criteria = new ReservationSearchCriteria(new Date(), new Date(), new ArrayList<>());
		testRestrictedAccess(HttpMethod.POST, "search", criteria, RolesHelper.ROLE_VIEW_RESERVATION);

		ReservationTO reservation = new ReservationTO(0, new Date(), new Date(), 1, 1);
		testRestrictedAccess(HttpMethod.POST, "", reservation, RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);
		testRestrictedAccess(HttpMethod.PUT, "1", reservation, RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);
		testRestrictedAccess(HttpMethod.DELETE, "1", RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);
	}

	@Test
	public void testWrongReservationSearch() throws Exception {
		loginAsAdministrator();

		//null dates
		ReservationSearchCriteria criteria = new ReservationSearchCriteria(null, null, new ArrayList<>());
		try {
			callForObject(HttpMethod.POST, "search", criteria, Object.class);
			fail("This call should fail");
		} catch (HttpServerErrorException ex) {
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
		}

		//date end null
		criteria.setDateFrom(new Date());
		try {
			callForObject(HttpMethod.POST, "search", criteria, Object.class);
			fail("This call should fail");
		} catch (HttpServerErrorException ex) {
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
		}

		//date end before date start
		criteria.setDateTo(new Date(criteria.getDateFrom().getTime() - 1));
		try {
			callForObject(HttpMethod.POST, "search", criteria, Object.class);
			fail("This call should fail");
		} catch (HttpServerErrorException ex) {
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
		}

		logout();
	}

	@Test
	public void testCorrectWorkflow() {
		loginAsAdministrator();
		
		Date ref = new Date();
		ReservationSearchCriteria criteria = new ReservationSearchCriteria(getDeltaDateHour(ref, -10), ref, Arrays.asList(new Integer[]{1, 2, 3}));
		
		//verify that list is empty
		List<ReservationTO> list = callForList(HttpMethod.POST, "search", criteria, ReservationTO.class);
		assertTrue("List should be empty", list.isEmpty());
		
		//add one reservation
		ReservationTO reservation = new ReservationTO(0, getDeltaDateHour(ref, -2), getDeltaDateHour(ref, -1), 0, 1);
		callForObject(HttpMethod.POST, "", reservation, Object.class);
		
		//check the list
		list = callForList(HttpMethod.POST, "search", criteria, ReservationTO.class);
		assertEquals("List should be empty", 1, list.size());
		ReservationTO reservation2 = list.get(0);
		assertEquals("Date start should be the same", reservation.getDateStart(), reservation2.getDateStart());
		assertEquals("Date end should be the same", reservation.getDateEnd(), reservation2.getDateEnd());
		assertEquals("Machine id should be the same", reservation.getMachineId(), reservation2.getMachineId());
		
		//edit the reservation
		reservation=reservation2;
		reservation.setDateStart(getDeltaDateHour(ref, -3));
		callForObject(HttpMethod.PUT, String.valueOf(reservation.getReservationId()), reservation, Object.class);
		
		//check the list again
		list = callForList(HttpMethod.POST, "search", criteria, ReservationTO.class);
		assertEquals("List should be empty", 1, list.size());
		reservation2 = list.get(0);
		assertEquals("Reservation id should be the same", reservation.getReservationId(), reservation2.getReservationId());
		assertEquals("Date start should be the same", reservation.getDateStart(), reservation2.getDateStart());
		assertEquals("Date end should be the same", reservation.getDateEnd(), reservation2.getDateEnd());
		assertEquals("Machine id should be the same", reservation.getMachineId(), reservation2.getMachineId());
		
		//delete the reservation
		callForObject(HttpMethod.DELETE, String.valueOf(reservation.getReservationId()), Object.class);
		
		//check the list again
		list = callForList(HttpMethod.POST, "search", criteria, ReservationTO.class);
		assertTrue("List should be empty", list.isEmpty());
		
		logout();
	}

}
