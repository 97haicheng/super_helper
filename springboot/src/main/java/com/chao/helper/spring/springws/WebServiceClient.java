package com.chao.helper.spring.springws;

import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * Created by think on 2016/11/16.
 */
public class WebServiceClient {

    private static final String MESSAGE =
            "<message xmlns=\"http://tempuri.org\">Hello World</message>";

    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public void setDefaultUri(String defaultUri) {
        webServiceTemplate.setDefaultUri(defaultUri);
    }

    // send to the configured default URI
    public void simpleSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);
    }

    // send to an explicit URI
    public void customSendAndReceive() {
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/AnotherWebService",
                source, result);
    }

}
