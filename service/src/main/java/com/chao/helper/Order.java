package com.chao.helper;

import java.io.Serializable;


/**
 * 订单指令封装对象（VO）
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chOrderId;
	private String chId;
    private String agCodeProId;
    private String type;
	private String mobiles;
    private String timestamp;
    private String sign;

	public String getChOrderId() {
		return chOrderId;
	}
	public void setChOrderId(String chOrderId) {
		this.chOrderId = chOrderId;
	}
	public String getChId() {
		return chId;
	}
	public void setChId(String chId) {
		this.chId = chId;
	}
	public String getAgCodeProId() {
		return agCodeProId;
	}
	public void setAgCodeProId(String agCodeProId) {
		this.agCodeProId = agCodeProId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMobiles() {
		return mobiles;
	}
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}