package com.chao.helper.spring.springws;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * Created by think on 2016/11/16.
 */
public class WSApp {

    private static final String MESSAGE =
            "<message xmlns=\"http://tempuri.org\">Hello World</message>";


    public static void main(String[] args) {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("springWS/spring-ws.xml");
        WebServiceTemplate webServiceTemplate = ctx.getBean(WebServiceTemplate.class);
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.destroy();
    }
}
