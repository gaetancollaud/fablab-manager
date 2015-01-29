package net.collaud.fablab.api;

import java.util.List;
import net.collaud.fablab.api.dao.GroupRepository;
import net.collaud.fablab.api.data.GroupEO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataTest extends BaseTest {

	@Autowired
	private GroupRepository groupDao;

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
		Assert.assertEquals(1, all.size());
	}
}
