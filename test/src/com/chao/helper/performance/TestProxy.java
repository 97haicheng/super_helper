package com.chao.helper.performance;

/**
 * Created by think on 2017/4/6.
 */
public class TestProxy {
    public static void main(String[] args) {
        try {
            Foo foo = (Foo) Handler.newInstance(new FooImpl());
            foo.testArrayList();
            foo.testLinkedList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
