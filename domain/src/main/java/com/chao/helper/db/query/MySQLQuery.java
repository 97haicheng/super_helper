package com.chao.helper.db.query;


import com.chao.helper.utils.DbUtilsTemplate;

import java.util.List;


/**
 * 
 * @ClassName: MySQLQuery
 * @Description: 分页查询器实现类
 * @author zhangYuanHui
 */
@SuppressWarnings({"rawtypes"})
public class MySQLQuery implements Query {
	
	/**
	 * db工具类
	 */
	private DbUtilsTemplate dbUtilsTemplate;
	
	/**
	 * 执行的sql
	 */
	private String sql;
	
	private Class<?> entityClass;
	
	private int firstResult;
	
	private int maxResults;
	
	public MySQLQuery(DbUtilsTemplate dbUtilsTemplate, String sql, Class<?> clazz) {
		this.dbUtilsTemplate = dbUtilsTemplate;
		this.sql = sql;
		this.entityClass = clazz;
	}

	@Override
	public void setFirstResult(int first) {
		this.firstResult = first;
		
	}

	@Override
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	@Override
	public List searchToBeanList() {
		return dbUtilsTemplate.searchToBeanList(entityClass, getPageSQL());
	}
	
	@Override
	public List searchToBeanList(Object param) {
		return dbUtilsTemplate.searchToBeanList(entityClass, getPageSQL(), param);
	}

	@Override
	public List searchToBeanList(Object... params) {
		return dbUtilsTemplate.searchToBeanList(entityClass, getPageSQL(), params);
	}
	
	/**
	 *
	 * @Description: 拼接分页sql
	 * @author zhangYuanHui
	 * @return String
	 */
	@Override
	public String getPageSQL(){
		StringBuffer buf = new StringBuffer(sql);
		buf.append(" LIMIT ");
		buf.append(firstResult);
		buf.append(", ");
		buf.append(maxResults);
		return buf.toString();
	}

}
