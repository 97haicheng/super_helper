package com.chao.helper.thread.synch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by think on 2017/4/26.
 * 之后创建一个模拟售票的类TicketRunnable,该类的构造器接收一个Ticket类
 */

public class TicketRunnable implements Runnable{
    private Ticket ticket;
    private Random random;
    public TicketRunnable(Ticket ticket) {
        this.ticket = ticket;
        random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i ++){
            synchronized (ticket) {
                try {
                    int count = random.nextInt(10) + 1;
                    boolean success = ticket.buyTicket(count);
                    System.out.println(String.format("%s打算买%d张票,买票%s了,还剩下%d张票,总共卖掉%d张票, 总票数%d",
                            Thread.currentThread().getName(), count, success ? "成功" : "失败",
                            ticket.getCount(), ticket.getBuyedCount(), ticket.getAllCount()));
                    if (!success) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Ticket ticket = new Ticket();
        for (int i = 0; i < 20; i ++){
            threads.add(new Thread(new TicketRunnable(ticket)));
        }

        for (Thread thread : threads){
            thread.start();
        }
    }
}
