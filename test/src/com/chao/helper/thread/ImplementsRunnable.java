package com.chao.helper.thread;

/**
 * Created by think on 2017/3/20.
 * 实现java.lang.Runnable接口
 */
public class ImplementsRunnable implements Runnable{

    private int count=15;

//    private String name;
//
//    public ImplementsRunnable(String name) {
//        this.name = name;
//    }

    @Override
    public void run() {
        for(int i=0; i<5; i++){
//            System.out.println(name + "运行" + i);
            System.out.println(Thread.currentThread().getName() + "运行  count= " + count--);
            try {
                Thread.sleep((int) (Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        new Thread(new ImplementsRunnable("C")).start();
//        new Thread(new ImplementsRunnable("D")).start();

        ImplementsRunnable my = new ImplementsRunnable();
        new Thread(my, "C").start();//同一个mt，但是在Thread中就不可以，如果用同一个实例化对象mt，就会出现异常
        new Thread(my, "D").start();
        new Thread(my, "E").start();
    }
}
