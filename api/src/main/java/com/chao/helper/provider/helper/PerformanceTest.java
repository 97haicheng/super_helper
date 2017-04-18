package com.chao.helper.provider.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by barry on 15/11/3.
 * 性能测试
 */
public class PerformanceTest {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static int NUM = 100;

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM);

    private static Random RANDOM = new Random();

    private static OrderResourceTest orderResourceTest = new OrderResourceTest();

    private static final String HOST = "http://218.60.136.202:8020";

    private static final String SECURE_KEY = "QQQQQCCCCCCCCCCCCCCCCCCCCCCCCCCC";


    public static void main(String[] args) {

        int count = NUM;

        List<Caller> callerList = new ArrayList<>();
        for (int i = 0; i< count; i++){
            callerList.add(new Caller(cyclicBarrier));
        }

        List<Future<Long>> futuresList = null;
        long start = System.currentTimeMillis();
        try {
            futuresList = executorService.invokeAll(callerList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total Cost Time All : " + (System.currentTimeMillis() - start));
        if(futuresList != null){
            long total = 0;
            for (Future<Long> future : futuresList){

//                while(!future.isDone()) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                try {

                    total += future.get().longValue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            BigDecimal s = new BigDecimal(total);
            System.out.println("Total Cost Time : " + total);

            BigDecimal average = s.divide(new BigDecimal(count),BigDecimal.ROUND_DOWN);
            System.out.println("Average cost time : " + average);

            BigDecimal request = new BigDecimal(NUM*1000);

            BigDecimal tps = request.divide(average, RoundingMode.HALF_UP);
            System.out.println("Api Tps : " + tps);

        }

    }

    private static class Caller implements Callable<Long> {

        private static boolean isCyclic = false;
        private CyclicBarrier cb;

        public Caller(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public Long call() throws Exception {

//            System.out.println(Thread.currentThread().getName() + ":in");

            if(!isCyclic){
                cb.await();
            }
            isCyclic = true;


            long start = System.currentTimeMillis();

            System.out.println(Thread.currentThread().getName() + ":" + start);

//            long random = RANDOM.nextInt(1000);

//            Thread.sleep(random);

            String orderId = orderResourceTest.getOrderId();

            orderResourceTest.testCreateOrder(HOST, SECURE_KEY, orderId);

            long cost = System.currentTimeMillis() - start;
            System.out.println(Thread.currentThread().getName() + ":out, cost time : " + cost);

            return cost;
        }
    }
}
