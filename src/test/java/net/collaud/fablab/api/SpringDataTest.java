package net.collaud.fablab.api;

import java.util.List;
import net.collaud.fablab.api.dao.GroupDao;
import net.collaud.fablab.api.data.GroupEO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataTest extends BaseTest {

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private PlatformTransactionManager txManager;

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testGroupDao() {
		GroupEO group = new GroupEO();
		group.setTechnicalname("g1");
		group.setName("group 1");

		groupDao.saveAndFlush(group);

		List<GroupEO> all = groupDao.findAll();
		Assert.assertEquals(4, all.size());
	}
}
