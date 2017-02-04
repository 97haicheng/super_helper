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
 *
 * http://www.ibm.com/developerworks/cn/opensource/os-cn-spring-cache/
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void addUser(User user){
        userMapper.addUser(user);
    }


    @CacheEvict(cacheNames= {"selectUserByID", "selectUsersByName"}, allEntries=true)
//    @CacheEvict(value = "updateUser", keyGenerator = "wiselyKeyGenerator")
    public void updateUser(User user){
        userMapper.updateUser(user);
    }


    @CacheEvict(cacheNames = {"selectUserByID", "selectUsersByName"}, allEntries=true)
//    @CacheEvict(value = "deleteUserById", keyGenerator = "wiselyKeyGenerator")
    public void deleteUserById(int id){
        userMapper.deleteUserById(id);
    }


    @Cacheable(cacheNames = "selectUserByID", key = "'USER:ID:USER_ID_' + #id")
//    @Cacheable(value = "selectUserByID", keyGenerator = "wiselyKeyGenerator")
    public User selectUserByID(int id){
        return userMapper.selectUserByID(id);
    }


    @Cacheable(cacheNames = "selectUsersByName", key = "'USER:NAME:USER_NAME_' + #name")
//    @Cacheable(value = "selectUsersByName", keyGenerator = "wiselyKeyGenerator")
    public User selectUsersByName(String name){
        return userMapper.selectUsersByName(name);
    }


//    @Cacheable(cacheNames = "selectUsersByParam")
//    @Cacheable(value = "selectUsersByParam", keyGenerator = "wiselyKeyGenerator")
    public User selectUsersByParam(User user){
        return userMapper.selectUsersByParam(user);
    }


//    @Cacheable(cacheNames = "getUserInfo")
//    @Cacheable(value = "getUserInfo", keyGenerator = "wiselyKeyGenerator")
    public List<User> getUserInfo(){
        List<User> listUser=userMapper.getUserInfo();
        return listUser;
    }


    @CacheEvict(cacheNames = {"selectUserByID", "selectUsersByName"}, allEntries=true)
//    @CacheEvict(value = "deleteUser", keyGenerator = "wiselyKeyGenerator")
    public void deleteUser(User user) {
        userMapper.deleteUser(user);
    }


}
