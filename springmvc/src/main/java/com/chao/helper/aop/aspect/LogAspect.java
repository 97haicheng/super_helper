package com.chao.helper.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by think on 2017/4/24.
 */
@Component
@Aspect
public class LogAspect {

    Logger LOG = Logger.getLogger(String.valueOf(LogAspect.class));

    //抽象切点，没有方法提，主要为了接下来解耦
    @Pointcut("execution(* com.chao.helper.aop.service..*(..))")
    public void aspect(){}

    //这是我们的前置通知
    @Before("aspect()")
    public void before(JoinPoint joinPoint){
        LOG.info("before " + joinPoint);
    }

    //这是我们的后置通知
    @After("aspect()")
    public void after(JoinPoint joinPoint){
        LOG.info("after " + joinPoint);
    }

    //这是我们的环绕通知
    @Around("aspect()")
    public void around(JoinPoint joinPoint){
        LOG.info("around " + joinPoint);
    }

    //这是我们的后置返回通知
    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint){
        LOG.info("afterReturn " + joinPoint);
    }

    //这是我们的异常通知
    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex){
        LOG.info("afterThrow " + joinPoint + ex.getMessage());
    }
}
