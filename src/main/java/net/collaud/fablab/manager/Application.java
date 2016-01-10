package net.collaud.fablab.manager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author Gaetan Collaud
 */
//@EnableAutoConfiguration
@SpringBootApplication
//@ImportResource({
//	"classpath:/spring-context.xml",//test env
//	"classpath:/spring-database.xml",//test env
//	"classpath:/spring-security.xml",//standard env
//})

public class Application {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(Application.class).run(args);
	}

	
}
