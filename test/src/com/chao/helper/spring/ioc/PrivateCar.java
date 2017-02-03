package com.chao.helper.spring.ioc;

/**
 * Created by think on 2017/2/3.
 */
public class PrivateCar {
    //①private成员变量：使用传统的类实例调用方式，只能在本类中访问
    private String color;

    //②protected方法：使用传统的类实例调用方式，只能在子类和本包中访问
    protected void drive() {

        System.out.println("drive private car! the color is:" + color);
    }
}
