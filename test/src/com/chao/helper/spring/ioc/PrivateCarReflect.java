package com.chao.helper.spring.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by think on 2017/2/3.
 * color变量和drive()方法都是私有的，
 * 通过类实例变量无法在外部访问私有变量、
 * 调用私有方法的，但通过反射机制却可以绕过这个限制：
 */
public class PrivateCarReflect {
    public static void main(String[] args) throws Throwable{
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.chao.helper.spring.ioc.PrivateCar");
        PrivateCar pcar = (PrivateCar)clazz.newInstance();

        Field colorFld = clazz.getDeclaredField("color");
        //①取消Java语言访问检查以访问private变量
        colorFld.setAccessible(true);
        colorFld.set(pcar,"红色");

        Method driveMtd = clazz.getDeclaredMethod("drive",(Class[])null);
        //Method driveMtd = clazz.getDeclaredMethod("drive"); JDK5.0下使用

        //②取消Java语言访问检查以访问protected方法
        driveMtd.setAccessible(true);
        driveMtd.invoke(pcar,(Object[])null);
    }
}
