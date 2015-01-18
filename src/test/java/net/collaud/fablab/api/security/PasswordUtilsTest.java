/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.collaud.fablab.api.security;

import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.data.UserEO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Slf4j
public class PasswordUtilsTest {

	public PasswordUtilsTest() {
	}

	private UserEO user;

	@Before
	public void setUp() {
		user = new UserEO();
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of isPasswordValid method, of class PasswordUtils.
	 */
	@Test
	public void testPasswordChange() {
		boolean result = PasswordUtils.isPasswordValid(user, "test");
		assertEquals(false, result);
		
		PasswordUtils.setUseEONewPassword(user, "test");

		result = PasswordUtils.isPasswordValid(user, "test");
		assertEquals(true, result);
		
		log.info("Passord is : {}", user.getPassword());
		log.info("Salt is : {}", user.getPasswordSalt());
		
	}
}
