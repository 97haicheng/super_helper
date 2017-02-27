package com.chao.helper.db.dao;

import com.chao.helper.beans.RequestLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称   ：domain
 * 类名称       ：RequestLogDao.java
 * 类描述       ：记录请求日志功能
 * 版本号       ：v1.0
 * 作者&时间 ：changjian  2016年3月30日
 */
@Repository
public class RequestLogDao extends BaseDao<RequestLog> {

	/**
	 * 保存请求信息使用sql
	 */
	private static final String ADD_SQL = "INSERT INTO _request_log(_cpOrderId, _orderPaymentId, _workOrderId, "
			+ "_remoteAddr, _type, _content, _createDate, _state, _refenceId, _admin, _description) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	/**
	 * 查询操作日志
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param type 日志类型
	 * @param desc 日志的描述
	 * @return
	 */
	public List<RequestLog> queryAllRequestLog(Timestamp startDate, Timestamp endDate, Integer type, String desc, int pageNo, int pageSize){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT _id id, _cpOrderId cpOrderId, _orderPaymentId orderPaymentId, _workOrderId workOrderId, _remoteAddr remoteAddr, ");
		sql.append(" _type type, _content content, _createDate createDate, _state state, _refenceId refenceId, _admin admin, _description description ");
		sql.append(" FROM  _request_log");
		sql.append(" WHERE _createDate >=  ? AND _createDate <= ? ");
		List<Object> params = new ArrayList<>();
		params.add(startDate);
		params.add(endDate);
		if (null != type){
			sql.append(" AND  _type = ? ");
			params.add(type);
		}
		
		if(StringUtils.isNotEmpty(desc)){
			sql.append(" AND  _description LIKE '%" + desc.trim() + "%' ");
		}
		sql.append(" LIMIT ?, ? ");
		params.add((pageNo - 1) * pageSize);
		params.add(pageSize);
		
		return dbUtilsTemplate.searchToBeanList(RequestLog.class, sql.toString(), params.toArray());
	}

	/**
	 * 查询操作日志
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param type 日志类型
	 * @param desc 日志的描述
	 * @return
	 */
	public Number queryAllRequestLogCount(Timestamp startDate, Timestamp endDate, Integer type, String desc){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(0) ");
		sql.append(" FROM  _request_log");
		sql.append(" WHERE _createDate >=  ? AND _createDate <= ? ");
		List<Object> params = new ArrayList<>();
		params.add(startDate);
		params.add(endDate);
		if (null != type){
			sql.append(" AND  _type = ? ");
			params.add(type);
		}
		
		if(StringUtils.isNotEmpty(desc)){
			sql.append(" AND  _description LIKE ? ");
			params.add(desc);
		}
		
		return dbUtilsTemplate.searchTotal(sql.toString(), params.toArray());
	}
	
	
	/**
	 * 保存请求信息
	 */
	public void addLog(RequestLog requestLog) {
		dbUtilsTemplate.update(ADD_SQL,
				new Object[] {requestLog.getCpOrderId(), requestLog.getOrderPaymentId(),
						requestLog.getWorkOrderId(), requestLog.getRemoteAddr(),
						requestLog.getType(), requestLog.getContent(),
						requestLog.getCreateDate(), requestLog.getState(), requestLog.getRefenceId(), requestLog.getAdmin(), requestLog.getDescription()});
	}
	
	
}
