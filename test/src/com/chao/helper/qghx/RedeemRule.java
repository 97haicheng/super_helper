package com.chao.helper.qghx;

/**
 * Created by think on 2016/11/26.
 *
 * 兑换码规则实体类
 */
public class RedeemRule {
    private String netType;//网别
    private Integer goodsId;//销售品ID
    private String goodsName;//销售品名称
    private Integer exchange;//是否兑换

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getExchange() {
        return exchange;
    }

    public void setExchange(Integer exchange) {
        this.exchange = exchange;
    }
}
