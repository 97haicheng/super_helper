package com.chao.helper.springboot.service;

import com.chao.helper.springboot.mapper.UserMapper;
import com.chao.helper.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.addUser(user);
    }

    @CacheEvict(value = "updateUser", keyGenerator = "wiselyKeyGenerator")
    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public void deleteUser(int id){
        userMapper.deleteUser(id);
    }

    @Cacheable(value = "getUserInfo", keyGenerator = "wiselyKeyGenerator")
    public List<User> getUserInfo(){
        List<User> listUser=userMapper.getUserInfo();
        return listUser;
    }

    @Cacheable(value = "selectUserByID", keyGenerator = "wiselyKeyGenerator")
    public User selectUserByID(int id){
        return userMapper.selectUserByID(id);
    }

    @Cacheable(value = "selectUsersByName", keyGenerator = "wiselyKeyGenerator")
    public User selectUsersByName(String name){
        return userMapper.selectUsersByName(name);
    }

    @Cacheable(value = "selectUsersByParam", keyGenerator = "wiselyKeyGenerator")
    public User selectUsersByParam(User user){
        return userMapper.selectUsersByParam(user);
    }


}
