package com.eshop.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.common.ServerResponse;
import com.eshop.pojo.User;

/**
 * Created by Paula
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) 
public class UserServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserServiceImpl iUserService;
	
	@Test
	public void testCheckAnswer() {
		
		ServerResponse<String> result = iUserService.checkAnswer("", "", "");
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testLogin() {
		ServerResponse<User> result = iUserService.login("username", "password");
		Assert.assertNotNull(result);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testRegister() {
		User user = new User();
		user.setUsername("userName");
		ServerResponse<String> result = iUserService.register(user);
		Assert.assertNotNull(result);
	}

}
