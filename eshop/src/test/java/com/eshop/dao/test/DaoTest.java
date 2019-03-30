package com.eshop.dao.test;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eshop.dao.UserMapper;
import com.eshop.pojo.User;
import com.eshop.test.TestBase;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Paula on shop.
 */
public class DaoTest extends TestBase {

    private UserMapper userMapper;

    public void testDao(){
        User a = new User();
        a.setPassword("111");
        a.setUsername("aaaaaPaula");
        a.setRole(0);
        a.setCreateTime(new Date());	
        a.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(userMapper.insertUser(a));
    }


}
