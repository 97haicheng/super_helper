package com.chao.helper.provider.elasticsearch;

import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.HttpProvider;
import com.chao.helper.utils.JacksonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuChao on 2017/12/20.
 * Description :
 *
 * @author : QuChao
 */
public class ElasticSearchDeleteTest extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchGetTest.class);

    @Override
    public String getProviderKey() {
        return "ELASTICSEARCH";
    }

    @Override
    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        String pretty = (String) params.get("pretty");

        //创建参数列表
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("pretty", pretty));

        String data = URLEncodedUtils.format(list, "UTF-8");

        logger.info("ElasticSearchPostTest request : {} , URL : {}", data, url);

        HttpDelete delete = new HttpDelete(url + "?" + data);
        delete.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());

        return delete;
    }

    @Override
    protected String processResponse(CloseableHttpResponse response) {
        String result = "";
        try {
            HttpEntity entityRsp = response.getEntity();

            result = EntityUtils.toString(entityRsp);

            String fotmatStr = JacksonUtils.format(result);

            logger.info("ElasticSearchGetTest response : {}", "\n" + fotmatStr);

        } catch (Exception e) {
            throw new HelperException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        /**
         Map<String, Object> params = new HashMap<String, Object>();
         Map<String, Object> mappings = new HashMap<String, Object>();
         Map<String, Object> person = new HashMap<String, Object>();
         Map<String, Object> properties = new HashMap<String, Object>();
         Map<String, Object> user = new HashMap<String, Object>();
         user.put("type", "text");
         user.put("analyzer", "ik_max_word");
         user.put("search_analyzer", "ik_max_word");
         Map<String, Object> title = new HashMap<String, Object>();
         title.put("type", "text");
         title.put("analyzer", "ik_max_word");
         title.put("search_analyzer", "ik_max_word");
         Map<String, Object> desc = new HashMap<String, Object>();
         desc.put("type", "text");
         desc.put("analyzer", "ik_max_word");
         desc.put("search_analyzer", "ik_max_word");
         properties.put("user", user);
         properties.put("title", title);
         properties.put("desc", desc);
         person.put("properties", properties);
         mappings.put("person", person);
         params.put("mappings", mappings);
         String json = JSONObject.toJSONString(params);
         System.out.println(json.toString());
         */

        /**向/Index/Type/Id发出 GET 请求，就可以查看这条记录。*/
        Map<String, Object> params = new HashMap<String, Object>();
        ElasticSearchDeleteTest http_client = new ElasticSearchDeleteTest();
        http_client.orderFor(params, "http://192.168.11.202:9200/accounts/person/2");
    }
}
