package com.chao.helper.spring.aop;

/**
 * Created by think on 2017/2/3.
 */
public class TestForumServiceCglib {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl )proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }
}
