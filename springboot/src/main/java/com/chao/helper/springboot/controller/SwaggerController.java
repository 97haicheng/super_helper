package com.chao.helper.springboot.controller;

import com.chao.helper.springboot.model.User;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST 指的是一组架构约束条件和原则。满足这些约束条件和原则的应用程序或设计就是 RESTful。
 * 此外，有一款RESTFUL接口的文档在线自动生成+功能测试功能软件——Swagger UI，
 * 具体配置过程可移步《Spring Boot 利用 Swagger 实现restful测试》
 */
@RestController
@RequestMapping(value="/users")
public class SwaggerController {


    /*
     *  http://127.0.0.1:8080/h5/swagger/index.html
     */
    @ApiOperation(value="Get all users",notes="requires noting")
    @RequestMapping(method=RequestMethod.GET)
    public List<User> getUsers(){
        List<User> list=new ArrayList<User>();

        User user=new User();
        user.setName("hello");
        list.add(user);

        User user2=new User();
        user.setName("world");
        list.add(user2);
        return list;
    }

    @ApiOperation(value="Get user with id",notes="requires the id of user")
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    public User getUserById(@PathVariable String name){
        User user=new User();
        user.setName("hello world");
        return user;
    }
}
