package com.chao.helper.service;

import com.chao.helper.beans.Account;
import com.chao.helper.db.dao.RoleDao;
import com.chao.helper.db.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 根据角色id 查询显示菜单
	 * @param roleId
	 * @throws Exception
	 */
	public List<Map<String, Object>> showMeun(long roleId) throws Exception{
		return roleDao.queryMeunByRoleId(roleId);
	}
	/**
	 * 显示代理菜单
	 * @param
	 * @throws Exception
	 */
	public List<Map<String, Object>> showMeun_agent() throws Exception{
		return roleDao.queryMeun_agent();
	}
	/**
	 * 显示代理菜单
	 * @param
	 * @throws Exception
	 */
	public List<Map<String, Object>> showMeun_ch() throws Exception{
		return roleDao.queryMeun_ch();
	}
	/** 
	 * 记录登录信息
	 * @throws Exception 
	 */
	public void updateLogin(Account user) throws Exception{
		user.setLoginCount(user.getLoginCount() + 1);
		userDao.updateLoginCount(user);
	}
	
	/**
	 * 通过用户名查找用户信息
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Account queryUserByUserName(String userName) throws Exception{
		return userDao.queryUserByUserName(userName);
	}
	/**
	 * 通过用户名查找用户信息-渠道
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Account queryUserByUserName_ch(String userName) throws Exception{
		return userDao.queryUserByUserName_ch(userName);
	}
}
