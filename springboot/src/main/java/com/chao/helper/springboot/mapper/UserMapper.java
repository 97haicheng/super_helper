package com.chao.helper.springboot.mapper;


import com.chao.helper.springboot.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
@Repository
public interface UserMapper {

    void addUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    List<User> getUserInfo();

    User selectUserByID(int id);

    User selectUsersByName(String name);

    User selectUsersByParam(User user);

    void deleteUser(User user);
}
