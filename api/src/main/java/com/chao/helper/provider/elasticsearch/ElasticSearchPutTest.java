package com.chao.helper.provider.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.HttpProvider;
import com.chao.helper.utils.JacksonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/12/20.
 * Description :
 *
 * @author : QuChao
 */
public class ElasticSearchPutTest extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchPostTest.class);
    private static final String URL = "http://192.168.11.202:9200/accounts";

    @Override
    public String getProviderKey() {
        return "ElasticSearchPostTest";
    }

    @Override
    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPut put = new HttpPut(url);

        String json = JSONObject.toJSONString(params);

        logger.info("ElasticSearchPostTest request : {} , URL : {}", json.toString(), url);

        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        put.setEntity(entity);
        return put;
    }

    @Override
    protected String processResponse(CloseableHttpResponse response) {
        HttpEntity entityRsp = response.getEntity();
        String result  = "";
        try {
            result = EntityUtils.toString(entityRsp);

            String fotmatStr = JacksonUtils.format(result);

            logger.info("ElasticSearchPostTest response : {}", "\n" + fotmatStr);

        } catch (IOException e) {
            throw new HelperException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        /**首先新建一个名称为accounts的 Index，里面有一个名称为person的 Type。person有三个字段
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

        /**向指定的 /Index/Type 发送 PUT 请求，就可以在 Index 里面新增一条记录。比如，向/accounts/person发送请求，就可以新增一条人员记录。*/
        Map<String, Object> person = new HashMap<String, Object>();
        person.put("user", "张三");
        person.put("title", "工程师");
        person.put("desc", "数据库管理，软件开发");
        ElasticSearchPutTest http_client = new ElasticSearchPutTest();
        http_client.orderFor(person, "http://192.168.11.202:9200/accounts/person/2");

    }

}