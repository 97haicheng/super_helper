package com.chao.helper.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.utils.HttpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * Created by think on 2017/1/20.
 *
 * HttpClient封装实现
 */
public abstract class HttpSSLProvider implements Provider {

    private static final Logger logger = LoggerFactory.getLogger(HttpSSLProvider.class);

    private static final CloseableHttpClient httpClient;

    static {
        httpClient = buildSSLCloseableHttpClient();
    }

    private static CloseableHttpClient buildSSLCloseableHttpClient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        //信任所有
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
            // ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。
            @SuppressWarnings("deprecation")
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
        } catch (NoSuchAlgorithmException e) {

        } catch (KeyStoreException e) {
        }
        return HttpClients.createDefault();
    }


    public String orderFor(Map<String, Object> params, String url) {

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
