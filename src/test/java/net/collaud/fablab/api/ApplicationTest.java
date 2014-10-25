package net.collaud.fablab.api;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Gaétan
 */
@Configuration
@EnableAutoConfiguration
@ImportResource({
	"classpath:/spring-context.xml",
	"classpath:/spring-database.xml",
	"classpath:/spring-security.xml"
})
public class ApplicationTest {
	
}
