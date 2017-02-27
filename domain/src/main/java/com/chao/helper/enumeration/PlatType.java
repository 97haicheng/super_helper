package com.chao.helper.enumeration;

/**
 * 
*   
* 项目名称：lbDao  
* 类名称：PlatType  
* 类描述：  平台类型
* 创建人：高广斌  
* 创建时间：2016年12月13日15:59:13  
*
 */
public enum PlatType {
	
	PLAT(1, "平台"),
	AGENT(2, "代理"),
	CHANNEL(3, "渠道");
    

    
    private int code;
    private String name;

    PlatType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static PlatType getType(int code) {
        for (PlatType c : PlatType.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (PlatType c : PlatType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
