package com.chao.helper.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.utils.HttpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by think on 2016/11/9.
 *
 * HttpClient封装实现
 */
public abstract class HttpProvider implements Provider {

    private static final Logger logger = LoggerFactory.getLogger(HttpProvider.class);
    //默认大小
    private static final int defaultMaxTotal = 10;

    public String orderFor(Map<String, Object> params, String url) {

        int httpPoolMaxTotal = 0;
        if(params.containsKey("HttpPoolMaxTotal")){
            httpPoolMaxTotal = Integer.parseInt((String)params.get("HttpPoolMaxTotal"));
        }

        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient(getProviderKey(),
                (httpPoolMaxTotal == 0) ? defaultMaxTotal : httpPoolMaxTotal);

        try {

            HttpRequestBase request = createRequest(params, url);

            logger.info("HttpProvider createRequest : {}, params : {}, url : {}", request.toString(),params,url);

            CloseableHttpResponse response = HttpClientUtil.sendRequest(httpClient, request);

            logger.info("HttpProvider sendRequest : {}", response);

            return processResponse(response);

        } catch (IOException e) {
            logger.error("Send Request error", e);
            throw new HelperException(e);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            throw new HelperException(e);
        }

    }

    protected abstract HttpRequestBase createRequest(Map<String, Object> params, String url);

    protected abstract String processResponse(CloseableHttpResponse response);


}
