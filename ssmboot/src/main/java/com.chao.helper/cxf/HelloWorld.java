package com.chao.helper.cxf;

import javax.jws.WebService;

@WebService
public interface HelloWorld {

	public String say(String str);
}
