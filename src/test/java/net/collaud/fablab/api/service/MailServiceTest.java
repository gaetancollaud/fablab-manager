package net.collaud.fablab.api.service;

import java.util.HashMap;
import java.util.Map;
import net.collaud.fablab.api.BaseTest;
import net.collaud.fablab.api.service.impl.MailServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceTest extends BaseTest {

	@Autowired
	private MailService mailService;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testSendMail() {
		Map<String, Object> scopes = new HashMap<>();
		String content = mailService.buildMailTemplate(
				MailServiceImpl.Template.TEMPLATE_FORGOT_PASSWORD,
				scopes);
		mailService.sendMail("no-reply@fablab-fribourg.ch", "test", content, "gaetancollaud@gmail.com");
	}

}
