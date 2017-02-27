package com.chao.helper.db.dao;

import com.chao.helper.beans.UserTable;
import com.chao.helper.cache.Cache;
import com.chao.helper.db.query.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称   ：lbDao 
 * 类名称       ：UserTableDao.java 
 * 类描述       ：UserTable数据库访问层  
 * 版本号       ：v1.0
 * 作者&时间 ：wh 2017-01-11 
 */ 
@Repository
public class UserTableDao extends BaseDao<UserTable> implements Cache {

	// 缓存名称
	private static String CACHE_NAME = "userTable";
	
	// 查询sql
	private static String QUERY_SQL = "select id id, usernum usernum, province province, city city, citycode citycode, provincecode provincecode, createdate createdate, synchroflag synchroflag, nettype nettype from _usertable";
	// 插入sql
	private static String INSERT_SQL = "insert into _usertable (id, usernum, province, city, citycode, provincecode, createdate, synchroflag, nettype) values (?, ?, ?, ?, ?, ?, ?, ?);";
	// 更新sql
	private static String UPDATE_SQL = "update _usertable set _createTime = now()";
	
	@Override
	public String getCacheName() {
		return CACHE_NAME;
	}

	/**
	 * 方法说明：通过主键ID查询记录
	 * @param	id 主键
	 * @return 实体对象
	 */ 
	public UserTable queryById(int id) {
		return load(QUERY_SQL + " where _id = ?", id);
	}

	/**
	 * 方法说明：分页查询数据结果集
	 * @param	page 包含参数等信息
	 * @return page对象
	 */ 
	public Page searchToPage(Page page) {
		return super.searchPageToBean(UserTable.class, QUERY_SQL, page);
	}

	/**
	 * 方法说明：插入记录
	 * @param	userTable 待插入对象
	 * @return 插入对象(包括主键ID)
	 */ 
	public UserTable insert(UserTable userTable) {
		Object[] params = new Object[] { userTable.getUsernum(), userTable.getProvince(), 
			userTable.getCity(), userTable.getCitycode(), userTable.getProvincecode(), userTable.getCreatedate(), 
			userTable.getSynchroflag(), userTable.getNettype()};

		userTable.setId(dbUtilsTemplate.insertForKeys(INSERT_SQL, params).intValue());
		return userTable;
	}

	/**
	 * 方法说明：更新记录
	 * @param	userTable 待更新对象
	 * @return 更新后的对象
	 */
	public UserTable update(UserTable userTable) {
		Assert.notNull(userTable);
		Assert.notNull(userTable.getId());

		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE_SQL);

		List<Object> paramList = new ArrayList<>();
		// 更新非空值属性
		if (userTable.getCity() != null) {
			sql.append(", city = ? ");
			paramList.add(userTable.getCity());
		}
		if (userTable.getCitycode() != null) {
			sql.append(", citycode = ? ");
			paramList.add(userTable.getCitycode());
		}
		if (userTable.getCreatedate() != null) {
			sql.append(", createdate = ? ");
			paramList.add(userTable.getCreatedate());
		}
		if (userTable.getNettype() != null) {
			sql.append(", nettype = ? ");
			paramList.add(userTable.getNettype());
		}
		if (userTable.getProvince() != null) {
			sql.append(", province = ? ");
			paramList.add(userTable.getProvince());
		}
		if (userTable.getProvincecode() != null) {
			sql.append(", provincecode = ? ");
			paramList.add(userTable.getProvincecode());
		}
		if (userTable.getSynchroflag() != null) {
			sql.append(", synchroflag = ? ");
			paramList.add(userTable.getSynchroflag());
		}
		if (userTable.getUsernum() != null) {
			sql.append(", usernum = ? ");
			paramList.add(userTable.getUsernum());
		}
		sql.append(" where ");
		if (userTable.getId() != null) {
			sql.append("_id = ? ");
			paramList.add(userTable.getId());
		}

		dbUtilsTemplate.update(sql.toString(), paramList.toArray());
		return queryById(userTable.getId());
	}
	/**
	 * 根据手机前七位确认省份信息
	 * @param mobile
	 * @return
	 * @author winner
	 * @time 2016-01-11
	 */
	public UserTable queryByUsernum(String mobile)
	{
		StringBuilder sql = new StringBuilder(); 
		sql.append(" WHERE usernum = ? ");
		return findOneBy(QUERY_SQL+sql.toString(), new Object[] { mobile });
	}

}

