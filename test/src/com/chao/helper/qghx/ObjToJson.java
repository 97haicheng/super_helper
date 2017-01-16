package com.chao.helper.qghx;

import com.chao.helper.utils.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2016/11/26.
 *
 * 全国后向兑换码实体类与json换转测试类
 */
public class ObjToJson {
    public static void main(String[] args) {
        List<RedeemRule> list = new ArrayList<RedeemRule>();

        RedeemRule redeemRule1 = new RedeemRule();
        redeemRule1.setNetType("yd");
        redeemRule1.setGoodsId(93);
        redeemRule1.setGoodsName("移动全国70M");
        redeemRule1.setExchange(1);
        list.add(redeemRule1);
        RedeemRule redeemRule2 = new RedeemRule();
        redeemRule2.setNetType("lt");
        redeemRule2.setGoodsId(95);
        redeemRule2.setGoodsName("联通全国20M");
        redeemRule2.setExchange(1);
        list.add(redeemRule2);
        RedeemRule redeemRule3 = new RedeemRule();
        redeemRule3.setNetType("dx");
        redeemRule3.setGoodsId(97);
        redeemRule3.setGoodsName("电信全国30M");
        redeemRule3.setExchange(1);
        list.add(redeemRule3);

        String json = JacksonUtil.toJSon(list);
        System.out.println(JacksonUtil.toJSon(list));

        List<RedeemRule> listCopy = JacksonUtil.readValue(json,new TypeReference<List<RedeemRule>>(){});
        System.out.println(listCopy.get(0).getNetType());
    }
}
