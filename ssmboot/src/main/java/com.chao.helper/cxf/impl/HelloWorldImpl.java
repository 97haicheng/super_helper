package com.chao.helper.cxf.impl;

import com.chao.helper.cxf.HelloWorld;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component("helloWorld")
@WebService
public class HelloWorldImpl implements HelloWorld {

	public String say(String str) {
		return "Hello"+str;
	}

}
