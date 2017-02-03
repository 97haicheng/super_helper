package com.chao.helper.spring.aop;

/**
 * Created by think on 2017/2/3.
 * ForumServiceImpl：移除性能监视横切代码
 */
public class ForumServiceImplNew implements ForumService {

    public void removeTopic(int topicId) {
        System.out.println("模拟删除Topic记录:"+topicId);
        try {
            Thread.currentThread().sleep(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void removeForum(int forumId) {
        System.out.println("模拟删除Forum记录:"+forumId);
        try {
            Thread.currentThread().sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}