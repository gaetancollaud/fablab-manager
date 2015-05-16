package net.collaud.fablab.api.rest.v1;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import net.collaud.fablab.api.rest.AbstractRestTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
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
@DatabaseSetup({"/data/roles_users.xml", "/data/reservations.xml"})
@DatabaseTearDown(value = {"/data/roles_users.xml", "/data/reservations.xml"}, type = DatabaseOperation.DELETE)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
@Ignore
public class ReservationWSTest extends AbstractRestTest {

	public ReservationWSTest(String resourcePath) {
		super("api/v1/reservation");
	}

//	@Autowired
//	private ReservationDAO reservationDao;
//
//	@After
//	public void tearDownReservation() {
//		reservationDao.removeAllReservations();
//	}
//
//	/**
//	 * Test of list method, of class ReservationWS.
//	 *
//	 * @throws java.lang.Exception
//	 */
//	@Test
//	public void testAccess() throws Exception {
//		ReservationSearchCriteria criteria = new ReservationSearchCriteria(new Date(), new Date(), new ArrayList<>());
//		testRestrictedAccess(HttpMethod.POST, "search", criteria, RolesHelper.ROLE_VIEW_RESERVATION);
//
//		ReservationSimpleTO reservation = new ReservationSimpleTO(0, new Date(), new Date(), 1, 1);
//		testRestrictedAccess(HttpMethod.POST, "", reservation, RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);
//		testRestrictedAccess(HttpMethod.PUT, "1", reservation, RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);
//		testRestrictedAccess(HttpMethod.DELETE, "1", RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_RESERVATION);
//	}
//
//	@Test
//	public void testWrongReservationSearch() throws Exception {
//		loginAsAdministrator();
//
//		//null dates
//		ReservationSearchCriteria criteria = new ReservationSearchCriteria(null, null, new ArrayList<>());
//		try {
//			callForObject(HttpMethod.POST, "search", criteria, Object.class);
//			fail("This call should fail");
//		} catch (HttpServerErrorException ex) {
//			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
//		}
//
//		//date end null
//		criteria.setDateFrom(new Date());
//		try {
//			callForObject(HttpMethod.POST, "search", criteria, Object.class);
//			fail("This call should fail");
//		} catch (HttpServerErrorException ex) {
//			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
//		}
//
//		//date end before date start
//		criteria.setDateTo(new Date(criteria.getDateFrom().getTime() - 1));
//		try {
//			callForObject(HttpMethod.POST, "search", criteria, Object.class);
//			fail("This call should fail");
//		} catch (HttpServerErrorException ex) {
//			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
//		}
//
//		logout();
//	}
//
//	@Test
//	public void testCorrectWorkflow() {
//		loginAsAdministrator();
//		
//		Date ref = new Date();
//		ReservationSearchCriteria criteria = new ReservationSearchCriteria(getDeltaDateHour(ref, -10), ref, Arrays.asList(new Integer[]{1, 2, 3}));
//		
//		//verify that list is empty
//		List<ReservationSimpleTO> list = callForList(HttpMethod.POST, "search", criteria, ReservationSimpleTO.class);
//		assertTrue("List should be empty", list.isEmpty());
//		
//		//add one reservation
//		ReservationSimpleTO reservation = new ReservationSimpleTO(0, getDeltaDateHour(ref, -2), getDeltaDateHour(ref, -1), 0, 1);
//		callForObject(HttpMethod.POST, "", reservation, Object.class);
//		
//		//check the list
//		list = callForList(HttpMethod.POST, "search", criteria, ReservationSimpleTO.class);
//		assertEquals("List should be empty", 1, list.size());
//		ReservationSimpleTO reservation2 = list.get(0);
//		assertEquals("Date start should be the same", reservation.getDateStart(), reservation2.getDateStart());
//		assertEquals("Date end should be the same", reservation.getDateEnd(), reservation2.getDateEnd());
//		assertEquals("Machine id should be the same", reservation.getMachineId(), reservation2.getMachineId());
//		
//		//edit the reservation
//		reservation=reservation2;
//		reservation.setDateStart(getDeltaDateHour(ref, -3));
//		callForObject(HttpMethod.PUT, String.valueOf(reservation.getReservationId()), reservation, Object.class);
//		
//		//check the list again
//		list = callForList(HttpMethod.POST, "search", criteria, ReservationSimpleTO.class);
//		assertEquals("List should be empty", 1, list.size());
//		reservation2 = list.get(0);
//		assertEquals("Reservation id should be the same", reservation.getReservationId(), reservation2.getReservationId());
//		assertEquals("Date start should be the same", reservation.getDateStart(), reservation2.getDateStart());
//		assertEquals("Date end should be the same", reservation.getDateEnd(), reservation2.getDateEnd());
//		assertEquals("Machine id should be the same", reservation.getMachineId(), reservation2.getMachineId());
//		
//		//delete the reservation
//		callForObject(HttpMethod.DELETE, String.valueOf(reservation.getReservationId()), Object.class);
//		
//		//check the list again
//		list = callForList(HttpMethod.POST, "search", criteria, ReservationSimpleTO.class);
//		assertTrue("List should be empty", list.isEmpty());
//		
//		logout();
//	}

}
