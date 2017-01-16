package com.chao.helper.provider;

import java.util.Map;

/**
 * Created by think on 2016/11/9.
 *
 * HttpClient封装接口
 */
public interface Provider {

    String getProviderKey();

    String orderFor(Map<String, Object> params, String url);
}
