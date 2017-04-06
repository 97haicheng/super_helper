package com.chao.helper.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by think on 2017/4/6.
 */
public class FooImpl implements Foo {

    private List link=new LinkedList();
    private List array=new ArrayList();

    public FooImpl() {
        for (int i = 0; i < 10000; i++) {
            array.add(new Integer(i));
            link.add(new Integer(i));
        }
    }

    @Override
    public void testArrayList() {
        for(int i=0;i<10000;i++)
            array.get(i);
    }

    @Override
    public void testLinkedList() {
        for(int i=0;i<10000;i++)
            link.get(i);
    }
}
