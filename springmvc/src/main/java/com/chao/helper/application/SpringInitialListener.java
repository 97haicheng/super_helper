package com.chao.helper.application;

import com.chao.helper.utils.SpringRegisterUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;


public class SpringInitialListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
        if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。

            ApplicationContext applicationContext = event.getApplicationContext();

            RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

            BoundHashOperations hashOperations = redisTemplate.boundHashOps("provider");

            Map<Object, Object> map = hashOperations.entries();

            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                SpringRegisterUtils.register(event.getApplicationContext(), (String) entry.getKey(), (byte[]) entry.getValue());
            }
        }
    }

}
