package com.chao.helper.db.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Criteria
 * @Description: 查询条件构造器实体
 * @author zhangYuanHui
 */
public class Criteria {
	/**
	 * 条件字段
	 */
	private String name;
	
	/**
	 * sql逻辑运算符
	 */
	private String operator;
	
	/**
	 * 参数值
	 */
	private Object value;
	
	/**
	 * 	条件数组
	 */
	private List<Criteria> paramCriteriaList;
	
	public Criteria() {
		
	}

	public Criteria(String name, String operator, Object value) {
		this.name = name;
		this.operator = operator;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public List<Criteria> getParamCriteriaList() {
		return paramCriteriaList;
	}

	public void setParamMap(List<Criteria> paramCriteriaList) {
		this.paramCriteriaList = paramCriteriaList;
	}
	
	public void setParamCriteriaList(Criteria[] criteria) {
		this.paramCriteriaList = new ArrayList<>();
		for(Criteria cus : criteria){
			this.paramCriteriaList.add(cus);
		}
	}
}
