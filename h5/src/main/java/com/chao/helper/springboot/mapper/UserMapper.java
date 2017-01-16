package com.chao.helper.springboot.mapper;


import com.chao.helper.springboot.model.User;

import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
public interface UserMapper {

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

    public List<User> getUserInfo();

    public User selectUserByID(int id);

    public User selectUsersByName(String name);

    public User selectUsersByParam(User user);
}
