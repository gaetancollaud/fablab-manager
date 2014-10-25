package net.collaud.fablab.api.rest.v1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import net.collaud.fablab.api.ApplicationTest;
import net.collaud.fablab.api.rest.v1.data.UserTO;
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
public class ReservationWSTest extends TestCase {
	
	@Value("${local.server.port}")
    private  int port;

	protected <T> List<T> getObjectList(String path, Class<T> clazz) {
		RestTemplate restTemplate = new TestRestTemplate();
		Class arr = Array.newInstance(clazz, 0).getClass();
		Object o = restTemplate.getForObject("http://localhost:" + port + "/api/v1/" + path, arr);
		return Arrays.asList((T[]) o);
	}

	protected <T> T getObject(String path, Class<T> clazz) {
		RestTemplate restTemplate = new TestRestTemplate();
		return restTemplate.getForObject("http://localhost:" + port + "/api/v1/" + path, clazz);
	}
	
	public ReservationWSTest() {
	}
	

	/**
	 * Test of list method, of class ReservationWS.
	 */
	@Test
	public void testAccess() throws Exception {
		Object userList = getObject("user", Object.class);
		assertNotNull("list must be not null", userList);
		//assertTrue("List should be empty", userList.isEmpty());
	}

	
}
