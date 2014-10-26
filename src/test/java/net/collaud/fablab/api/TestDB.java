package net.collaud.fablab.api;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.List;
import static junit.framework.TestCase.*;
import net.collaud.fablab.api.dao.RoleDao;
import net.collaud.fablab.api.data.RoleEO;
import org.apache.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

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
@DatabaseSetup({"/data/roles.xml"})
public class TestDB {
	private static final Logger LOG = Logger.getLogger(TestDB.class);

	@Autowired
	private RoleDao roleDao;

	@Test
	public void testListRoles() {
		List<RoleEO> list = roleDao.getAllRoles();
		assertEquals("Wrong list of roles", 2, list.size());
	}

}
