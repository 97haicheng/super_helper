package com.chao.helper.spring.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by think on 2017/2/3.
 * CGLib采用非常底层的字节码技术，可以为一个类创建子类，
 * 并在子类中采用方法拦截的技术拦截所有父类方法的调用，并顺势织入横切逻辑。
 */
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz); //① 设置需要创建子类的类
        enhancer.setCallback(this);
        return enhancer.create(); //②通过字节码技术动态创建子类实例

    }

    //③拦截父类所有方法的调用
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        PerformanceMonitor.begin(obj.getClass().getName()+"."+method. getName());//③-1
        Object result=proxy.invokeSuper(obj, args);
        PerformanceMonitor.end();//③-1通过代理类调用父类中的方法
        return result;
    }
}
