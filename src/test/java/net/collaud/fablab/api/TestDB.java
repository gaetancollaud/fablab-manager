package net.collaud.fablab.api;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.List;
import java.util.Map;
import static junit.framework.TestCase.*;
import net.collaud.fablab.api.dao.RoleDao;
import net.collaud.fablab.api.data.RoleEO;
import net.collaud.fablab.api.rest.AbstractRestTest;
import net.collaud.fablab.api.rest.v1.criteria.AuthCredential;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gaétan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DatabaseSetup({"/data/basic_roles_users.xml"})
public class TestDB extends AbstractRestTest{
	private static final Logger LOG = Logger.getLogger(TestDB.class);

	@Autowired
	private RoleDao roleDao;

	public TestDB() {
		super("auth");
	}

	@Test
	public void testListRoles() {
		List<RoleEO> list = roleDao.getAllRoles();
		assertEquals("Wrong list of roles", 10, list.size());
	}
	
	@Test
	public void testLogin(){
		RestTemplate restTemplate = new TestRestTemplate();
		AuthCredential credential = new AuthCredential("animator1", "");
		Object result = restTemplate.postForObject("http://localhost:" + getPort() + "/api/v1/auth/login", credential, Object.class);
		assertNotNull(result);
	}

}
