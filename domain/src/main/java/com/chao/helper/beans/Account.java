package com.chao.helper.beans;
import java.sql.Timestamp;

/**
 * 项目名称   ：lbDao 
 * 类名称       ：Accounts 
 * 类描述       ：对应数据库表  _accounts 
 * 版本号       ：v1.0
 * 作者&时间 ：cj 2016-12-14 
 */ 
public class Account {

	/**
	 * 用户ID
	 */
	private Integer Id;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 代理商ID
	 */
	private Integer agId;

	/**
	 * 用户密码
	 */
	private String pwd;

	/**
	 * 系统角色/代理商/渠道商的ID
	 */
	private Integer roleId;

	/**
	 * 盐值
	 */
	private String salt;

	/**
	 * 1：平台；2：代理商；3：渠道
	 */
	private Integer platType;

	/**
	 * 邮箱地址
	 */
	private String userEmail;

	/**
	 * 手机号
	 */
	private String userMobile;

	/**
	 * 登录次数
	 */
	private Integer loginCount;

	/**
	 * 别名
	 */
	private String anotherName;

	/**
	 * 1：正常；2：停用；
	 */
	private Integer userStatus;

	/**
	 * 上一次登录时间,yyyy-mm-dd hh:mi:ss
	 */
	private Timestamp lastLogin;

	/**
	 * 创建时间，yyyy-mm-dd hh:mi:ss
	 */
	private Timestamp createTime;

	/**
	 * 账户类型，1主账号，2普通账号
	 */
	private Integer agentType;

	/**
	 * 创建人
	 */
	private String optUserId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/*
	 *  真实姓名
	 */
	private String realName;
	/*
	 *  备注
	 */
	private String remark;
	/**
	 * 渠道ID
	 */
	private Integer chId;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAgId() {
		return agId;
	}

	public void setAgId(Integer agId) {
		this.agId = agId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getPlatType() {
		return platType;
	}

	public void setPlatType(Integer platType) {
		this.platType = platType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public Integer getAgentType() {
		return agentType;
	}

	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}

	public String getOptUserId() {
		return optUserId;
	}

	public void setOptUserId(String optUserId) {
		this.optUserId = optUserId;
	}

	public Integer getChId() {
		return chId;
	}

	public void setChId(Integer chId) {
		this.chId = chId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
