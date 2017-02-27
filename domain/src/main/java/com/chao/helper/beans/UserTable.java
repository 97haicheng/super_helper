package com.chao.helper.beans;

import java.util.Date;

/**
 * 项目名称   ：lbDao 
 * 类名称       ：UserTable 
 * 类描述       ：对应数据库表  _usertable 
 * 版本号       ：v1.0
 * 作者&时间 ：winner 2017-01-11 
 */ 
public class UserTable {

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 号段
	 */
	private String usernum;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 地市
	 */
	private String city;

	/**
	 * 地市编码
	 */
	private Integer citycode;

	/**
	 * 省份编码
	 */
	private Integer provincecode;

	/**
	 * 创建时间,yyyy-mm-dd hh:mi:ss
	 */
	private Date createdate;

	/**
	 * 
	 */
	private Integer synchroflag;

	/**
	 * 
	 */
	private Integer nettype;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsernum() {
		return usernum;
	}

	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

	public Integer getProvincecode() {
		return provincecode;
	}

	public void setProvincecode(Integer provincecode) {
		this.provincecode = provincecode;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getSynchroflag() {
		return synchroflag;
	}

	public void setSynchroflag(Integer synchroflag) {
		this.synchroflag = synchroflag;
	}

	public Integer getNettype() {
		return nettype;
	}

	public void setNettype(Integer nettype) {
		this.nettype = nettype;
	}
}
