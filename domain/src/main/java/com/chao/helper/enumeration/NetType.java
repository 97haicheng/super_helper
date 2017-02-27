package com.chao.helper.enumeration;

/**
 * 
*   
* 项目名称：lbDao  
* 类名称：PlatType  
* 类描述： 运营商类型
* 创建人：高广斌  
* 创建时间：2016年12月13日15:59:13  
*
 */
public enum NetType {
	
	LT(1, "联通"),
	YD(2, "移动"),
	DX(3, "电信");
    

    
    private int code;
    private String name;

    NetType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static NetType getType(int code) {
        for (NetType c : NetType.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (NetType c : NetType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
