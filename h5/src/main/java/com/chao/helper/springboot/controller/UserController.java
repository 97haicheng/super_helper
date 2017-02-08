package com.chao.helper.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.chao.helper.springboot.model.User;
import com.chao.helper.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 返回Json格式数据，多用于Ajax请求。
 */
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /*
     * http://127.0.0.1:8080/h5/addUser?name=n&age=20&password=p
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public void addUser(@RequestParam(value="name", required=false, defaultValue="World") String name,
                        @RequestParam(value="age", required=false, defaultValue="20") int age,
                        @RequestParam(value="password", required=false, defaultValue="Hello") String password){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setPassword(password);
        userService.addUser(user);
    }

    /*
     * http://127.0.0.1:8080/h5/updateUser?id=3&name=n&age=20&password=p
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public void updateUser(@RequestParam(value="id", required=false, defaultValue="1") int id,
                           @RequestParam(value="name", required=false, defaultValue="World") String name,
                           @RequestParam(value="age", required=false, defaultValue="20") int age,
                           @RequestParam(value="password", required=false, defaultValue="Hello") String password){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setPassword(password);
        userService.updateUser(user);
    }

    /*
     * http://127.0.0.1:8080/h5/deleteUserById
     */
    @RequestMapping("/deleteUserById")
    @ResponseBody
    public void deleteUserById(@RequestParam(value="id", required=false, defaultValue="1") int id){
        userService.deleteUserById(id);
    }

    /*
     * http://127.0.0.1:8080/h5/getUserInfo
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public String getUserInfo() {
        List<User> listUser = userService.getUserInfo();
        if(listUser!=null){
            for(int i=0; i<listUser.size(); i++){
                logger.info("userName:"+listUser.get(i).getName()+
                        " userAge:"+listUser.get(i).getAge()+" userPassword:"+listUser.get(i).getPassword());
            }
        }

        String json = JSON.toJSONString(listUser);
        logger.info("JSON:"+json);
        return json;
    }

    /*
     * http://127.0.0.1:8080/h5/selectUserByID
     */
    @RequestMapping("/selectUserByID")
    @ResponseBody
    public User selectUserByID(@RequestParam(value="id", required=false, defaultValue="1") int id){
        return userService.selectUserByID(id);
    }

    /*
     * http://127.0.0.1:8080/h5/selectUsersByName?name=liuxiao
     */
    @RequestMapping("/selectUsersByName")
    @ResponseBody
    public User selectUsersByName(@RequestParam(value="name", required=false, defaultValue="World") String name){
        return userService.selectUsersByName(name);
    }

    /*
     * http://127.0.0.1:8080/h5/selectUsersByParam
     */
    @RequestMapping("/selectUsersByParam")
    @ResponseBody
    public User selectUsersByParam(@RequestParam(value="id", required=false, defaultValue="1") int id,
                                   @RequestParam(value="name", required=false, defaultValue="World") String name,
                                   @RequestParam(value="age", required=false, defaultValue="20") int age,
                                   @RequestParam(value="password", required=false, defaultValue="Hello") String password){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setPassword(password);
        return userService.selectUsersByParam(user);
    }

    /*
    * http://127.0.0.1:8080/h5/deleteUser?name=n&age=20&password=p
    */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public void deleteUser(@RequestParam(value="id", required=false, defaultValue="1") int id,
                           @RequestParam(value="name", required=false, defaultValue="World") String name,
                           @RequestParam(value="age", required=false, defaultValue="20") int age,
                           @RequestParam(value="password", required=false, defaultValue="Hello") String password){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setPassword(password);
        userService.deleteUser(user);
    }


}
