package net.collaud.fablab.api;

import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */

@ContextConfiguration(locations = {
			"classpath:/spring-context-test.xml",//test env
			"classpath:/spring-database-test.xml",//test env
			//"classpath:/spring-security.xml",//standard env
		})
public class BaseTest {
	
}
