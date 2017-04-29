package net.collaud.fablab.manager.boot;

import net.collaud.fablab.manager.security.FablabAuthentificationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Gaetan Collaud
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Security extends WebSecurityConfigurerAdapter{

	@Autowired
	private FablabAuthentificationProvider authentificationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.anonymous().disable()
				.csrf().disable()
//				.httpBasic().and()
				.authenticationProvider(authentificationProvider)
				.authorizeRequests()
				.antMatchers("*").permitAll();
	}
	
//	@Configuration
//	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
//		@Override
//		public void init(AuthenticationManagerBuilder auth) throws Exception {
//			  auth.inMemoryAuthentication().withUser("1").password("emf123").roles("USER");
//		}
//	} 
	
}
