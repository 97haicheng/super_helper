package com.chao.helper.test.controller;

import com.chao.helper.springboot.controller.UserController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by think on 2017/2/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class UserControllerApp {

    @Autowired
    private UserController userController;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetUserInfo() {
        String str = userController.getUserInfo();
        System.out.println("str:" + str);
    }

    @Test
    public void testSelectUsersByName(){
        String name = "liuxiao";
        System.out.println(userController.selectUsersByName(name));
    }

}
