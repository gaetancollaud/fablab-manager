package net.collaud.fablab.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Gaétan
 */
@Configuration
@ImportResource({
	"classpath:/spring-context-test.xml",//test env
	"classpath:/spring-database-test.xml",//test env
	"classpath:/spring-security.xml",//standard env
})
public class ApplicationTest {
	
}
