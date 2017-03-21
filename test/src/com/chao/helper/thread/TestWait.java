package com.chao.helper.thread;

/**
 * Created by think on 2017/3/20.
 * Obj.wait()，与Obj.notify()必须要与synchronized(Obj)一起使用，
 * 也就是wait,与notify是针对已经获取了Obj锁进行操作，
 * 从语法角度来说就是Obj.wait(),Obj.notify必须在synchronized(Obj){...}语句块内。
 */
public class TestWait implements Runnable{

    private String name;

    private Object prev;

    private Object self;

    public TestWait(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0){
            synchronized (prev){
                synchronized (self){
                    System.out.println(name);
                    count--;

                    self.notify();
                }

                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        TestWait pa = new TestWait("A", c, a);
        TestWait pb = new TestWait("B", a, b);
        TestWait pc = new TestWait("C", b, c);


        new Thread(pa).start();
        Thread.sleep(100);  //确保按顺序A、B、C执行
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }
}
