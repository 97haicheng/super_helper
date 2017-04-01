package com.chao.springhbn.test;


import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by think on 2017/3/27.
 *
 * 测试日志插入到mongo
 */
public class TestMongo {

    private static Logger logger = Logger.getLogger(TestMongo.class);

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                logger.info("username=quchao, pay money="+10);
            }
        }, 5, 5, TimeUnit.SECONDS);

    }
}
