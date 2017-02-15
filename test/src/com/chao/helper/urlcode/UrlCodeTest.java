package com.chao.helper.urlcode;

import com.chao.helper.utils.CRequest;
import com.chao.helper.utils.JacksonUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by think on 2017/2/15.
 */
public class UrlCodeTest {

    public static void main(String[] args) {
        String str = "ts=20170214173323&hash=9284AF529C3A7E61961683D1FC33079B&cpno=XY063&service=resultNotify&params=%7B%22cporderno%22%3A%222017021417322756696740%22%2C%22status%22%3A%221%22%2C%22ztorderno%22%3A%22201702140001314090%22%7D";
        String jsonArr = null;
        try {
//            System.out.println(URLEncoder.encode(str, "UTF-8"));
            System.out.println(URLDecoder.decode(str, "UTF-8"));
            jsonArr = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String, String> mapRequest = CRequest.URLRequest(jsonArr);

        String ts = (String) mapRequest.get("ts");
        String params = (String) mapRequest.get("params");
        Map mapParams = JacksonUtil.fromJson(params, Map.class);
        String cporderno = (String) mapParams.get("cporderno");
        String status = String.valueOf(mapParams.get("status"));
        String ztorderno = (String) mapParams.get("ztorderno");//时科订单号

        if (StringUtils.isBlank(cporderno)) {
//            out.print(RESULT_FAIL);
        }else{
            //此处取得工单ID  前14位是时间戳
            cporderno = cporderno.substring(14,cporderno.length());
        }

        String msg = null;
        if("1".equals(status)){
            msg = "充值失败";
        }else {
            msg = "充值成功";
        }

        System.out.println(cporderno+" "+status+" "+msg+" "+ts+" "+ztorderno);
    }
}
