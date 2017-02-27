package com.chao.helper.enumeration;
/**
 * 项目名称   ：lnService
 * 类名称       ：ErrorCodeType.java
 * 类描述       ：
 * 版本号       ：v1.0
 * 作者&时间 ：changjian  2016年8月9日
 */
public enum ErrorCodeType {

	// 说明：下行短信成功、订购成功、退订成功
	SUCCESS(100000, "成功类"),
	// 说明：参数校验、验证码校验
	VALIDATE(110000, "校验错误类"),	// 重点：此类错误允许再次支付
	// 说明：业务数据不正确、数据关联关系不正确、异常错误
	FORMAL(120000, "一般错误类"),
	// 说明：风控类
	RISK(130000, "风险控制类"),
	// 说明：vac提供或返回的错误信息
	SP(140000, "vac错误类");
	
	
    private int code;
    private String name;

    ErrorCodeType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static ErrorCodeType getType(int code) {
        for (ErrorCodeType c : ErrorCodeType.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (ErrorCodeType c : ErrorCodeType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
