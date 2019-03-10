package net.collaud.fablab.manager.boot;

import net.collaud.fablab.manager.security.FablabAuthentificationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Gaetan Collaud
 */
@Order(101)
@Configuration
public class SecurityBasicAuth extends WebSecurityConfigurerAdapter {

	@Autowired
	private FablabAuthentificationProvider authentificationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.regexMatcher("/api/v1/system/door/.*")
				.httpBasic();

	}

}
