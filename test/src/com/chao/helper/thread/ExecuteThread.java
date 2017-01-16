package com.chao.helper.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by think on 2016/12/7.
 */
public class ExecuteThread implements Runnable{

    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            Thread t = new Thread(new ExecuteThread(5));
            t.run();
        }
    }

    private int num;
    private CountDownLatch latch = new CountDownLatch(100);

    public ExecuteThread(int i){
        this.num = i;
        System.out.println(num);
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
