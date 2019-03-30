package com.eshop.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.eshop.common.ServerResponse;
import com.eshop.service.ICartService;
import com.eshop.vo.CartVo;

/**
 * Created by Paula
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CartServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ICartService iCartService;
	
	@Test
	@Rollback(true)
	public void test() {
		//ServerResponse<CartVo> re = iCartService.add(35336, 35336, 35336);
		//Assert.assertNotNull(re);
	}

}
