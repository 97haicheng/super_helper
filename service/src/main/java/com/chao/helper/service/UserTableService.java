package com.chao.helper.service;

import com.chao.helper.beans.UserTable;
import com.chao.helper.db.dao.UserTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTableService {

	@Autowired
	private UserTableDao userTableDao;
	
	/**
	 * 根据手机号前七位查询省份信息
	 * @param mobile
	 * @return
	 * @author winner
	 * @time 2017-01-11
	 */
	public UserTable queryUserTableByUsernum(String mobile)
	{
		return userTableDao.queryByUsernum(mobile);
	}
}
