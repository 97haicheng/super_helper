package com.chao.helper.thread;

/**
 * Created by think on 2017/3/20.
 * Thread.yield()方法作用是：暂停当前正在执行的线程对象，并执行其他线程。
 * yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
 * 因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。
 * 但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
 */
public class TestYield extends Thread{

    public TestYield(String name) {
        super(name);
    }

    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 30) {
//                System.out.println(this.getName());
                this.yield();
            }
        }
    }

    public static void main(String[] args) {

        TestYield yt1 = new TestYield("张三");
        TestYield yt2 = new TestYield("李四");
        yt1.start();
        yt2.start();
    }
}
