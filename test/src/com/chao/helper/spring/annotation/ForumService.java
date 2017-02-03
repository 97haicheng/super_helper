package com.chao.helper.spring.annotation;

/**
 * Created by think on 2017/2/3.
 */
public class ForumService {
    @NeedTest(value=true)
    public void deleteForum(int forumId){
        System.out.println("删除论坛模块："+forumId);
    }
    @NeedTest(value=false)
    public void deleteTopic(int postId){
        System.out.println("删除论坛主题："+postId);
    }
}
