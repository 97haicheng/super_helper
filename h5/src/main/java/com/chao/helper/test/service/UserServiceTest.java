package com.chao.helper.test.service;

import com.chao.helper.springboot.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by think on 2017/2/7.
 *
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes=Application.class)// 指定spring-boot的启动类

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserInfo() {
		Assert.assertTrue(userService.getUserInfo().size()==4);
	}

	@Test
	public void testSelectUsersByName(){
		String name = "liuxiao";
		System.out.println(userService.selectUsersByName(name));
	}

//	@Test
//	public void test1() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void test2() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void test3() {
//		fail("Not yet implemented");
//	}

}
