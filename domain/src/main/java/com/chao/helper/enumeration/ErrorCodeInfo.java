package com.chao.helper.enumeration;


import com.chao.helper.application.PlatformCodingHandler;

import java.text.MessageFormat;

/**
 * 项目名称   ：lnService
 * 类名称       ：ErrorCodeInfo.java
 * 类描述       ：
 * 版本号       ：v1.0
 * 作者&时间 ：changjian  2016年8月9日
 */
public enum ErrorCodeInfo implements PlatformCodingHandler {
	
//--------------------成功类--------------------
	ERROR_100100(ErrorCodeType.SUCCESS.getCode() + 100, "下行短信成功"),
	ERROR_100200(ErrorCodeType.SUCCESS.getCode() + 200, "支付成功"),
	ERROR_100300(ErrorCodeType.SUCCESS.getCode() + 300, "退订成功"),
	
	
//--------------------校验错误类（允许以相同订单号再次支付）--------------------
	ERROR_110001(ErrorCodeType.VALIDATE.getCode() + 1, "请求参数不完整"),
	ERROR_110002(ErrorCodeType.VALIDATE.getCode() + 2, "请求参数值不符合要求"),
	ERROR_110003(ErrorCodeType.VALIDATE.getCode() + 3, "数据签名验证失败"),
	ERROR_110004(ErrorCodeType.VALIDATE.getCode() + 4, "时间戳与当前时间相差超过5分钟"),
    ERROR_110005(ErrorCodeType.VALIDATE.getCode() + 5, "-订单与厂商信息不匹配"),
    ERROR_110006(ErrorCodeType.VALIDATE.getCode() + 6, "订单与商品信息不匹配"),
    ERROR_110007(ErrorCodeType.VALIDATE.getCode() + 7, "待确认支付的订单不存在"),
    ERROR_110008(ErrorCodeType.VALIDATE.getCode() + 8, "订单的验证码已过期"),
    ERROR_110009(ErrorCodeType.VALIDATE.getCode() + 9, "来源类型不正确"),
    ERROR_110010(ErrorCodeType.VALIDATE.getCode() + 10, "下发验证码短信失败"),
    ERROR_110011(ErrorCodeType.VALIDATE.getCode() + 11, "订购类型错误"),
    ERROR_110012(ErrorCodeType.VALIDATE.getCode() + 12, "超过验证码验证次数上限"),
    ERROR_110013(ErrorCodeType.VALIDATE.getCode() + 13, "验证码输入错误"),
    ERROR_110014(ErrorCodeType.VALIDATE.getCode() + 14, "生成签名出错"),
    ERROR_110015(ErrorCodeType.VALIDATE.getCode() + 15, "请求参数转换出错"),
    ERROR_110016(ErrorCodeType.VALIDATE.getCode() + 16, "订单与应用信息不匹配"),
    //ERROR_110017(ErrorCodeType.VALIDATE.getCode() + 17, "验证码输入错误"),
    
//--------------------一般错误类--------------------
	ERROR_120001(ErrorCodeType.FORMAL.getCode() + 1, ""),
	ERROR_120002(ErrorCodeType.FORMAL.getCode() + 2, ""),
	ERROR_120003(ErrorCodeType.FORMAL.getCode() + 3, "渠道不存在"),
	ERROR_120004(ErrorCodeType.FORMAL.getCode() + 4, "渠道状态不正确"),
	ERROR_120005(ErrorCodeType.FORMAL.getCode() + 5, "代理商产品不存在或者代理商状态不正确"),
	ERROR_120006(ErrorCodeType.FORMAL.getCode() + 6, "代理商产品状态不正确"),
	ERROR_120008(ErrorCodeType.FORMAL.getCode() + 8, "渠道绑定代理商商品校验失败"),
	ERROR_120009(ErrorCodeType.FORMAL.getCode() + 9, "渠道订单号重复"),
	ERROR_120010(ErrorCodeType.FORMAL.getCode() + 10, "创建订单工单失败"),
	ERROR_120011(ErrorCodeType.FORMAL.getCode() + 11, "未查询到渠道订单信息"),
	ERROR_120012(ErrorCodeType.FORMAL.getCode() + 12, "未查询到渠道资金池信息"),
	ERROR_120013(ErrorCodeType.FORMAL.getCode() + 13, "-未查询到订单记录"),
	ERROR_120091(ErrorCodeType.FORMAL.getCode() + 91, "-未查询到代理商信息"),
	ERROR_120092(ErrorCodeType.FORMAL.getCode() + 92, "-代理商已经停用"),
	//新增订单有效期(默认2小时)
	ERROR_120014(ErrorCodeType.FORMAL.getCode() + 14, "订单已过期"),
	ERROR_120018(ErrorCodeType.FORMAL.getCode() + 18, "回调CP异常"),
	//新增cp访问量控制
	ERROR_120019(ErrorCodeType.FORMAL.getCode() + 19, "cp访问量超过限制最大值"),
	ERROR_120020(ErrorCodeType.FORMAL.getCode() + 20, "未查询到工单记录"),
	ERROR_120021(ErrorCodeType.FORMAL.getCode() + 21, "渠道合同商品余额不足"),
	ERROR_120022(ErrorCodeType.FORMAL.getCode() + 22, "对应供应商产品报错"),
	ERROR_120023(ErrorCodeType.FORMAL.getCode() + 23, "渠道商品合同错误"),
	ERROR_120024(ErrorCodeType.FORMAL.getCode() + 24, "代理商商品合同错误"),
	ERROR_120025(ErrorCodeType.FORMAL.getCode() + 25, "查找计费方案错误"),
	ERROR_120026(ErrorCodeType.FORMAL.getCode() + 26, "订购失败"),
	ERROR_120027(ErrorCodeType.FORMAL.getCode() + 27, "平台商品合同错误"),
	ERROR_120028(ErrorCodeType.FORMAL.getCode() + 28, "合同方案错误"),
	ERROR_120029(ErrorCodeType.FORMAL.getCode() + 29, "配置供应商产品错误"),
	ERROR_129999(ErrorCodeType.FORMAL.getCode() + 9999, "系统异常错误"),

	
//--------------------风险控制类--------------------
	ERROR_130001(ErrorCodeType.RISK.getCode() + 1, "支付手机号是黑名单用户"),


//--------------------vac错误类--------------------
	// vac定义：通常错误
	ERROR_140001(ErrorCodeType.SP.getCode() + 1, "请求供应商连接超时"),
	ERROR_140002(ErrorCodeType.SP.getCode() + 2, "请求供应商响应超时"),
	ERROR_140003(ErrorCodeType.SP.getCode() + 3, "请求供应商异常"),
    ;
	
    private int code;
    private String message;

    ErrorCodeInfo(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public String getMessage() {
		return message;
	}

	public String getMessage(Object... parameterName) {
        if (parameterName != null) {
            return MessageFormat.format(message, parameterName);
        }

        return message;
    }
    
	/**
	 * 根据错误码获取错误描述
	 * @param errorCode
	 * @return String
	 */
    public static String getErrorMsg(int errorCode) {
    	ErrorCodeInfo[] errorArray = ErrorCodeInfo.values();
    	for(ErrorCodeInfo c : errorArray){
    		if(c.getCode() == errorCode){
    			return c.getMessage();
    		}
    	}
    	return null;
    }
    /**
	 * 根据错误码获取错误描述
	 * @param errorCode
	 * @return String
	 */
    public static int getErrorCode(String errorMsg) {
    	ErrorCodeInfo[] errorArray = ErrorCodeInfo.values();
    	for(ErrorCodeInfo c : errorArray){
    		if(c.getMessage().equals(errorMsg)){
    			return c.getCode();
    		}
    	}
    	return -1;
    }
    
    public static void main(String[] agrs){
    	ErrorCodeInfo[] errorArray = ErrorCodeInfo.values();
    	System.out.println(errorArray.length);
    }
    
}
