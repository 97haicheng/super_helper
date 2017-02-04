package com.chao.helper.memorycache;

import com.sun.deploy.cache.MemoryCache;

/**
 * Created by think on 2017/2/4.
 */
public class MemoryCacheApp {
    public static void main(String[] args) {
        User user = new User();//读到一个用户列表
        user.setName("quchao");
        MemoryCache.addLoadedResource("capqueen:users", user);//放入内存

        //读取
        User getUsers = (User) MemoryCache.getLoadedResource("capqueen:users");
        System.out.println(getUsers.getName());
    }
}
