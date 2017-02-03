package com.chao.helper.spring.aop;

/**
 * Created by think on 2017/2/3.
 * 通过下面的代码测试拥有性能监视能力的ForumServiceImpl业务方法：
 */
public class TestForumService {

    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();
        forumService .removeForum(10);
        forumService .removeTopic(1012);
    }

}
