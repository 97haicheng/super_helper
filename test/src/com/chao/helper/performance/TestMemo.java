package com.chao.helper.performance;

/**
 * Created by think on 2017/4/6.
 */
public class TestMemo {

    public static void main(String[] args) {
        MemoConsumer arrayMemo=(MemoConsumer)Handler.newInstance(new MemoConsumerImpl ());
        arrayMemo.creatArray();
        arrayMemo.creatHashMap();
    }

}
