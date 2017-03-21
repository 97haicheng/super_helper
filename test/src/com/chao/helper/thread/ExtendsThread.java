package com.chao.helper.thread;

/**
 * Created by think on 2017/3/20.
 * 继续Thread类
 */
public class ExtendsThread extends Thread{

    private int count=5;

    private String name;

    public ExtendsThread(String name) {
        this.name = name;
    }

    public void run(){
        for(int i=0; i<5; i++){
//            System.out.println(name + "运行" + i);
            System.out.println(name + "运行  count= " + count--);
            try {
                sleep((int) (Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExtendsThread et1 = new ExtendsThread("A");
        ExtendsThread et2 = new ExtendsThread("B");

        et1.start();
        et2.start();
    }
}
