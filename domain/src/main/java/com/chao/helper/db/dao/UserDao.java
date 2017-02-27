package com.chao.helper.db.dao;

import com.chao.helper.beans.Account;
import com.chao.helper.enumeration.PlatType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author wh
 * @DESC 用户操作的 dao 类
 */
@Repository
public class UserDao extends BaseDao<Account> {

	/**
	 * 新增用户信息使用sql
	 */
	private static final String ADD_SQL = "INSERT INTO _account( _userName, _pwd, _anotherName, "
			+ "_createTime, _lastLogin, _roleId, _userMobile, _userStatus, "
			+ "_loginCount, _salt, _userEmail, _platType, _chId, _realName, _optUserId, _remark ) "
			+ "VALUES(?, ?, ?, now(), now(), ?, ?, ?, 0, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * 修改用户状态使用sql
	 */
	private static final String UPDATE_STATE_SQL = "UPDATE _account SET _userStatus = ? WHERE _id = ? ";

	/**
	 * 记录用户登录次数和登录时间SQL
	 */
	private static final String UPDATE_LOGIN_SQL = "UPDATE _account SET _loginCount = ?, "
			+ "_lastLogin = now() WHERE _userName = ?";

	/**
	 * 用户重置sql
	 */
	private static final String UPDATE_PASSWORD_SQL = "UPDATE _account SET _pwd = ?  WHERE _id = ?";
	
	// 更新sql
	private static String UPDATE_SQL = "update _account set _createTime = now()";
	/**
	 * 查询用户信息
	 */
	private static final String QUERY_USER_SQL = " SELECT a._id  id, a._userName userName,"
			+ " a._anotherName anotherName, a._createTime createTime, "
			+ " a._lastLogin lastLogin, a._roleId roleId, r._roleName roleName, "
			+ " a._userMobile mobile, a._userStatus userStatus, a._loginCount loginCount, "
			+ " a._userEmail email, a._salt salt, a._createTime createTime "
			+ " FROM _account a LEFT JOIN _roles r ON a._roleId = r._id ";

	/**
	 * 通过id查询当个对象
	 */
	private static final String QUERY_USER_BYID_SQL = "SELECT  role._roleName roleName, user._userName userName, user._anotherName anotherName, user._createTime createTime,"
			+" user._lastLogin lastLogin, user._roleId roleId, user._userMobile userMobile, user._userStatus isDisable, user._loginCount loginCount, user._userEmail userEmail, user._salt salt, user._id id "
			+"FROM _account user LEFT JOIN _roles role "
			+" ON user._roleId = role._id WHERE user._id = ?  AND user._userStatus <> 0";

	/**
	 * 根据用户名查找用户
	 */
	private static final String QUERY_USER_BY_USERNAME = "SELECT role._roleName roleName, user._userName userName, user._agId agId, "
			+ " user._anotherName anotherName, user._createTime createTime,  user._lastLogin lastLogin, "
			+ " user._roleId roleId, user._userMobile userMobile, user._platType platType, user._agentType agentType, "
			+ "  user._loginCount loginCount, user._userEmail  userEmail,  user._salt salt, user._id id, "
			+ " user._pwd pwd, user._userStatus userStatus "
			+ "  FROM _account user LEFT JOIN  _roles role "
			+ "  ON  user._roleId = role._id WHERE user._userName = ? AND  user._platType in (1,2) AND user._userStatus <> 3 ";
	/**
	 * 根据用户名查找用户
	 */
	private static final String QUERY_USER_BY_USERNAME_CH = "SELECT  user._userName userName, "
			+ " user._anotherName anotherName, user._createTime createTime,  user._lastLogin lastLogin, "
			+ " user._roleId roleId, user._userMobile userMobile, user._platType platType, user._agentType agentType, "
			+ "  user._loginCount loginCount, user._userEmail  userEmail,  user._salt salt, user._id id, user._chId chId, "
			+ " user._pwd pwd, user._userStatus userStatus "
			+ "  FROM _account user "
			+ "  WHERE  user._userName = ? AND user._platType = 3 AND user._userStatus <> 3 ";
	public long validatorUserName(String username){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(0) FROM _account WHERE _userName = ? ");
		return dbUtilsTemplate.searchTotal(sql.toString(), username);
	}
	
	/**
	 * 通过用户名查找用户信息
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Account queryUserByUserName(String userName) throws Exception {
		return findOneBy(QUERY_USER_BY_USERNAME, new Object[] { userName });
	}
	/**
	 * 通过用户名查找用户信息-渠道
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Account queryUserByUserName_ch(String userName) throws Exception {
		return findOneBy(QUERY_USER_BY_USERNAME_CH, new Object[] { userName });
	}

	/**
	 * 新增用户信息
	 * 
	 * @param user
	 * @return
	 */
	public Account addUser(Account user) {
		user.setId(dbUtilsTemplate.insertForKeys(ADD_SQL,
				new Object[] { user.getUserName().trim(), user.getPwd().trim(), user.getAnotherName()==null?"":user.getAnotherName().trim(),
						user.getRoleId(),
						user.getUserMobile().trim(), user.getUserStatus(), user.getSalt().trim(), 
						user.getUserEmail()==null?"":user.getUserEmail().trim(), user.getPlatType(), 
								user.getChId(), user.getRealName()==null?"":user.getRealName(), user.getOptUserId(), user.getRemark()
						}).intValue());
		return user;
	}

	/**
	 * 修改指定用户的状态
	 * 
	 * @param id
	 */
	public void updateUserState(long id, long isDisable) {
		dbUtilsTemplate.update(UPDATE_STATE_SQL, new Object[] { isDisable, id });
	}

	/**
	 * 方法说明：更新记录
	 * @param	Account 待更新对象
	 * @return 更新后的对象
	 */
	public Account update(Account Account) {
		Assert.notNull(Account);
		Assert.notNull(Account.getId());

		StringBuilder sql = new StringBuilder();
		sql.append(UPDATE_SQL);

		List<Object> paramList = new ArrayList<>();
		// 更新非空值属性
		if (Account.getAgId() != null) {
			sql.append(", _agId = ? ");
			paramList.add(Account.getAgId());
		}
		if (Account.getAgentType() != null) {
			sql.append(", _agentType = ? ");
			paramList.add(Account.getAgentType());
		}
		if (Account.getAnotherName() != null) {
			sql.append(", _anotherName = ? ");
			paramList.add(Account.getAnotherName());
		}
		if (Account.getChId() != null) {
			sql.append(", _chId = ? ");
			paramList.add(Account.getChId());
		}
		if (Account.getCreateTime() != null) {
			sql.append(", _createTime = ? ");
			paramList.add(Account.getCreateTime());
		}
		if (Account.getLastLogin() != null) {
			sql.append(", _lastLogin = ? ");
			paramList.add(Account.getLastLogin());
		}
		if (Account.getLoginCount() != null) {
			sql.append(", _loginCount = ? ");
			paramList.add(Account.getLoginCount());
		}
		if (Account.getOptUserId() != null) {
			sql.append(", _optUserId = ? ");
			paramList.add(Account.getOptUserId());
		}
		if (Account.getPlatType() != null) {
			sql.append(", _platType = ? ");
			paramList.add(Account.getPlatType());
		}
		if (Account.getPwd() != null) {
			sql.append(", _pwd = ? ");
			paramList.add(Account.getPwd());
		}
		if (Account.getRealName() != null) {
			sql.append(", _realName = ? ");
			paramList.add(Account.getRealName());
		}
		if (Account.getRemark() != null) {
			sql.append(", _remark = ? ");
			paramList.add(Account.getRemark());
		}
		if (Account.getRoleId() != null) {
			sql.append(", _roleId = ? ");
			paramList.add(Account.getRoleId());
		}
		if (Account.getSalt() != null) {
			sql.append(", _salt = ? ");
			paramList.add(Account.getSalt());
		}
		if (Account.getUserEmail() != null) {
			sql.append(", _userEmail = ? ");
			paramList.add(Account.getUserEmail());
		}
		if (Account.getUserMobile() != null) {
			sql.append(", _userMobile = ? ");
			paramList.add(Account.getUserMobile());
		}
		if (Account.getUserName() != null) {
			sql.append(", _userName = ? ");
			paramList.add(Account.getUserName());
		}
		if (Account.getUserStatus() != null) {
			sql.append(", _userStatus = ? ");
			paramList.add(Account.getUserStatus());
		}
		sql.append(" where ");
		if (Account.getId() != null) {
			sql.append("_id = ? ");
			paramList.add(Account.getId());
		}

		dbUtilsTemplate.update(sql.toString(), paramList.toArray());
		return queryById(Account.getId());
	}

	/**
	 * 记录用户登录次数和登录时间
	 * 
	 * @param user
	 */
	public void updateLoginCount(Account user) {
		dbUtilsTemplate.update(UPDATE_LOGIN_SQL, new Object[] { user.getLoginCount(), user.getUserName() });
	}

	/**
	 * 重置用户密码
	 * 
	 * @param user
	 */
	public void updatePassword(Account user) {
		dbUtilsTemplate.update(UPDATE_PASSWORD_SQL, new Object[] { user.getPwd(), user.getId() });
	}

	/**
	 * * 查询分页用户信息
	 * 
	 * @param userName
	 *            查询条件（用户名）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryUser(String userName, String anotherName, Integer roleId, int state, 
			Integer pageNo, Integer pageSize) throws Exception {
		StringBuffer whereSql = new StringBuffer(" WHERE 1 = 1 and a._platType = " + PlatType.PLAT.getCode() + " ");
		if (StringUtils.isNotEmpty(userName)) {
			whereSql.append(" AND a._userName LIKE '%" + userName + "%' ");
		}

		if (null!=roleId) {
			whereSql.append(" AND a._roleId = " + roleId + " " );
		}
		
		if (StringUtils.isNotEmpty(anotherName)) {
			whereSql.append(" AND a._anotherName LIKE '%" + anotherName + "%' ");
		}
		
		if (1 == state){
			whereSql.append(" AND  a._userStatus = " + state);
		}else if (2 == state){
			whereSql.append(" AND  a._userStatus = " + state);
		}else{
			whereSql.append(" AND  a._userStatus IN( " + 1 + "," + 2 + ")");
		}
		//whereSql.append(" AND a._state = 1 ");
		
		whereSql.append(" LIMIT ?, ? ");
		return dbUtilsTemplate.searchToMapList(QUERY_USER_SQL + whereSql, new Object[]{(pageNo - 1) * pageSize, pageSize});
	}

	/**
	 * 查询分页数据条数
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Long queryUserCount(String userName, String anotherName, Integer roleId, int state) throws Exception {
		StringBuffer whereSql = new StringBuffer(" WHERE 1 = 1 and a._platType = " + PlatType.PLAT.getCode() + " ");
		if (StringUtils.isNotEmpty(userName)) {
			whereSql.append("AND _userName = '%" + userName + "%' ");
		}
		
		if (null!=roleId) {
			whereSql.append(" AND r._id = " + roleId + "%' ");
		}
		if (StringUtils.isNotEmpty(anotherName)) {
			whereSql.append(" AND a._anotherName LIKE '%" + anotherName + "%' ");
		}
		if (1 == state){
			whereSql.append(" AND  a._userStatus = " + state);
		}else if (2 == state){
			whereSql.append(" AND  a._userStatus = " + state);
		}else{
			whereSql.append(" AND  a._userStatus IN( " + 1 + "," + 2 + ")");
		}
		
		//whereSql.append(" AND a._state = 1 ");
		
		return dbUtilsTemplate.searchTotal("SELECT COUNT(1) FROM _account a LEFT JOIN _roles r ON a._roleId = r._id " + whereSql.toString());
	}

	/**
	 * 查询user对象
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Account queryById(long id){
		return findOneBy(QUERY_USER_BYID_SQL,  new Object[] { id });
	}
	
}
