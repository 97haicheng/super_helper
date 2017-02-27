package com.chao.helper.beans;

import java.sql.Timestamp;

/**
 * @author zhangge
 *	@DESC 角色实体类
 */
public class Role {

	/**
	 * 主键
	 */
	private long id;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 创建时间
	 */
	private Timestamp createDate;

	/**
	 * 修改时间
	 */
	private Timestamp updateDate;
	
	/**
	 * 全局的状态，用来标识数据，1:正常，0：失效
	 */
	private long state;
	
	public enum State{
		STATE(1,"正常"),
		NOT_STATE(0,"失效");
		
		private Integer code;
		private String name;
		
		private State(Integer code, String name) {
			this.code = code;
			this.name = name;
		}
		
		public Integer getCode() {
			return code;
		}
		
		public String getName() {
			return name;
		}
		
	}

	/**
	 * @return 获取［state］的值 
	 */
	public long getState() {
		return state;
	}

	/**	
	 *	设置 属性［state］的值
	 * @param ［state］ : 属性［state］的值
	 */
	public void setState(long state) {
		this.state = state;
	}

	/**
	 * @return 获取［id］的值 
	 */
	public long getId() {
		return id;
	}

	/**	
	 *	设置 属性［id］的值
	 * @param ［id］ : 属性［id］的值
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return 获取［name］的值 
	 */
	public String getName() {
		return name;
	}

	/**	
	 *	设置 属性［name］的值
	 * @param ［name］ : 属性［name］的值
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 获取［createDate］的值 
	 */
	public Timestamp getCreateDate() {
		return createDate;
	}

	/**	
	 *	设置 属性［createDate］的值
	 * @param ［createDate］ : 属性［createDate］的值
	 */
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return 获取［updateDate］的值 
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**	
	 *	设置 属性［updateDate］的值
	 * @param ［updateDate］ : 属性［updateDate］的值
	 */
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
