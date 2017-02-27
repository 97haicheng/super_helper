package com.chao.helper.db.query;

import java.util.List;

/**
 * 
 * @ClassName: Query
 * @Description: 分页数据查询器
 * @author zhangYuanHui
 */
@SuppressWarnings("rawtypes")
public interface Query {
	/**
	 * 设置分页查询起始行
	 * @Description: 设置分页查询起始行 
	 * @author zhangYuanHui
	 * @param first 表记录起始索引
	 */
	public void setFirstResult(int first);
	
	/**
	 * 设置分页查询结束行
	 * @Description: 设置分页查询结束行 
	 * @author zhangYuanHui
	 * @param maxResults 表记录结束索引
	 */
	public void setMaxResults(int maxResults);
	
	/**
	 * 查询列表数据
	 * @Description: 查询列表数据
	 * @author zhangYuanHui
	 * @return List
	 */
	public List searchToBeanList();
	
	/**
	 * 根据指定单个条件查询列表数据
	 * @Description: 根据指定单个条件查询列表数据
	 * @author zhangYuanHui
	 * @param param 参数值
	 * @return List
	 * @throws Exception
	 */
	public List searchToBeanList(Object param);
	
	/**
	 * 根据指定多个条件查询列表数据
	 * @Description: 根据指定多个条件查询列表数据
	 * @author zhangYuanHui
	 * @param params 参数值数组
	 * @return List
	 * @throws Exception
	 */
	public List searchToBeanList(Object... params);
	
	/**
	 * 获取分页sql
	 * @return String
	 */
	public String getPageSQL();
}
