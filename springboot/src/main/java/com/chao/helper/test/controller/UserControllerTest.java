package com.chao.helper.test.controller;

import com.chao.helper.springboot.Application;
import com.chao.helper.springboot.controller.UserController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by think on 2017/2/7.
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)// 指定spring-boot的启动类

public class UserControllerTest {

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
