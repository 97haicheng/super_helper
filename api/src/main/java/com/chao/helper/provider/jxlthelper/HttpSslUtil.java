package com.chao.helper.provider.jxlthelper;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpSslUtil {
	protected static final Logger logger = LoggerFactory.getLogger(HttpSslUtil.class);// log日志类
    public static final String CHARSET = "UTF-8";
    private static final CloseableHttpClient httpClient;
    /**
     * 加载连接
     */
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
    
    /**
     * HTTP Get 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param charset    编码格式
     * @return    页面内容
     */
    public static String doGet(String url,Map<String,String> params,String charset){
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String doGet(String url, Map<String, String> params){
        return doGet(url, params,CHARSET);
    }
    public static String doPost(String url, Map<String, String> params){
        return doPost(url, params,CHARSET);
    }

	/**
	 * HTTP Post 获取内容
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static String doPost(String url, Map<String, String> params,
			String charset) {
		String result = null;
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);

			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :"+ statusCode);
			}
			HttpEntity entity = response.getEntity();
			System.out.println("ContentType:" + entity.getContentType());
			System.out.println("ContentTypeLength:" + entity.getContentLength());
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			System.out.println("result:" + result);

			EntityUtils.consume(entity);
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
    /**
     * 单元测试方法
     * @param args
     * @throws ClientProtocolException
     */
	public static void main(String[] args) throws ClientProtocolException {
		String secretKey = "RhMr3WdYYx0twU3Lqz8A8eAPge2yyKArIRX4masR";
		// 按次验证码
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("companyno", "bjybk");
		sParaTemp.put("productcode", "F0010");
		sParaTemp.put("mobilephone", "15607917527");
		sParaTemp.put("service", "payment");
		sParaTemp.put("tradeno","1");
		//doPost请求参数
		Map<String, String> paramMap = SignUtils.buildRequestPara(sParaTemp,secretKey);
		//doPost请求参数
		String doPostUrl = "https://inf.mewifi.cn/fdp/openAPIAction_payment";
		//doGet请求参数
		String doGetUrl = "https://inf.mewifi.cn/fdp/openAPIAction_payment?"+ SignUtils.buildInfo(sParaTemp, secretKey);
		try {
			//post请求
			String doPostResult = HttpSslUtil.doPost(doPostUrl,paramMap);
			
			logger.info("doPostResult:" + doPostResult);
			
			//get请求
			//String doGetresult = HttpSslUtil.doGet(doGetUrl, null);
			//logger.info("doGetresult:" + doGetresult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
