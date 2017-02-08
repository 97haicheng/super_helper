package com.chao.helper.springboot.dao;

import com.chao.helper.springboot.mapper.UserMapper;
import com.chao.helper.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by think on 2017/2/7.
 */
@Component
public class UserDao implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public List<User> getUserInfo() {
        return userMapper.getUserInfo();
    }

    @Override
    public User selectUserByID(int id) {
        return userMapper.selectUserByID(id);
    }

    @Override
    public User selectUsersByName(String name) {
        return userMapper.selectUsersByName(name);
    }

    @Override
    public User selectUsersByParam(User user) {
        return userMapper.selectUsersByParam(user);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteUser(user);
    }
}
