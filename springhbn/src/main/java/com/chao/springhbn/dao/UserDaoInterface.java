package com.chao.springhbn.dao;


import com.chao.springhbn.domain.User;

import java.util.List;

public interface UserDaoInterface {
	void addUser(User user);
	void updateUser(User user);
	User getUser(int id);
	List<User> findUserByName(String name);
	long getUserNum();
}
