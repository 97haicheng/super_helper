package com.chao.helper.thread.synch;

/**
 * Created by think on 2017/4/26.
 * 首先创建一个Ticket类
 * 增加两个成员变量count-->表示剩余的票,buyedCount-->已经卖出的票,并提供getter方法
 * 增加一个buyTicket方法,用来模拟售票
 */
public class Ticket {

    private static final int DEFAULT_TICKET_COUNT = 1000;
    private int count = DEFAULT_TICKET_COUNT; //票的总数
    private int buyedCount = 0;

    public boolean buyTicket(int count) throws InterruptedException {
        if (this.count - count < 0){
            Thread.sleep(10);
            return false;
        }else{
            this.count = this.count - count;
            Thread.sleep(1);
            this.buyedCount = this.buyedCount + count;
            return true;
        }
    }

    public int getCount() {
        return count;
    }

    public int getBuyedCount() {
        return buyedCount;
    }

    public int getAllCount(){
        return count + buyedCount;
    }
}
