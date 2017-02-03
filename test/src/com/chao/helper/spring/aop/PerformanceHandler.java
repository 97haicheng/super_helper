package com.chao.helper.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by think on 2017/2/3.
 */
public class PerformanceHandler implements InvocationHandler {//①实现InvocationHandler

    private Object target;
    public PerformanceHandler(Object target){ //②target为目标的业务类
        this.target = target;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName()+"."+ method. getName());
        Object obj = method.invoke(target, args);// ③-2通过反射方法调用业务类的目标方法
        PerformanceMonitor.end();
        return obj;
    }

}
