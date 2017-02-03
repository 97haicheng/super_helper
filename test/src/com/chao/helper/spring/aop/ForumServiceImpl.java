package com.chao.helper.spring.aop;

/**
 * Created by think on 2017/2/3.
 * ForumService：包含性能监视横切代码
 */
public class ForumServiceImpl implements ForumService {
    public void removeTopic(int topicId) {

        //①-1开始对该方法进行性能监视
        PerformanceMonitor.begin(
                "com.chao.helper.spring.aop.ForumServiceImpl.removeTopic");
        System.out.println("模拟删除Topic记录:"+topicId);
        try {
            Thread.currentThread().sleep(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //①-2结束对该方法进行性能监视
        PerformanceMonitor.end();
    }

    public void removeForum(int forumId) {
        //②-1开始对该方法进行性能监视
        PerformanceMonitor.begin(
                "com.chao.helper.spring.aop.ForumServiceImpl.removeForum");
        System.out.println("模拟删除Forum记录:"+forumId);
        try {
            Thread.currentThread().sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //②-2结束对该方法进行性能监视
        PerformanceMonitor.end();
    }
}
