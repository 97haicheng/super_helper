package com.chao.helper.aop.aspect;

import org.aspectj.lang.JoinPoint;

import java.util.logging.Logger;

/**
 * Created by think on 2017/4/24.
 */
public class XmlAspect {

    Logger LOG = Logger.getLogger(String.valueOf(XmlAspect.class));

    //抽象切点，没有方法提，主要为了接下来解耦
    public void aspect(){}

    //这是我们的前置通知
    public void before(JoinPoint joinPoint){
        LOG.info("xml before " + joinPoint);
    }

    //这是我们的后置通知
    public void after(JoinPoint joinPoint){
        LOG.info("xml after " + joinPoint);
    }

    //这是我们的环绕通知
    public void around(JoinPoint joinPoint){
        LOG.info("xml around " + joinPoint);
    }

    //这是我们的后置返回通知
    public void afterReturn(JoinPoint joinPoint){
        LOG.info("xml afterReturn " + joinPoint);
    }

    //这是我们的异常通知
    public void afterThrow(JoinPoint joinPoint, Exception ex){
        LOG.info("xml afterThrow " + joinPoint + ex.getMessage());
    }
}
