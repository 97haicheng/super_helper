package com.chao.helper.spring.springredis;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by think on 2016/11/16.
 */
public class RedisApp {

    public static void main(String[] args) {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("springRedis/spring-redis.xml");
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

        redisTemplate.boundListOps("KEY").leftPush("VALUETEST");

        System.out.println(redisTemplate.opsForList().rightPop("KEY"));

        redisTemplate.boundValueOps("KEY").set("15840043215", 15 * 60, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.destroy();
    }
}
