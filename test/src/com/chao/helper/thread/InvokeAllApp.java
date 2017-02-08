package com.chao.helper.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by think on 2017/2/7.
 */
public class InvokeAllApp {

    public static void main(String[] args) throws InterruptedException,
            ExecutionException
    {
        ExecutorService exec = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        for (int i = 0; i < 10; i++)
        {
            task = new Callable<Integer>()
            {
                @Override
                public Integer call() throws Exception
                {
                    int ran = new Random().nextInt(1000);
                    Thread.sleep(ran);
                    System.out.println(Thread.currentThread().getName()+" 休息了 " + ran );
                    return ran;
                }
            };

            tasks.add(task);
        }

        long s = System.currentTimeMillis();


        List<Future<Integer>> results = exec.invokeAll(tasks);

        System.out.println("执行任务消耗了 ：" + (System.currentTimeMillis() - s) +"毫秒");

        for (int i = 0; i < results.size(); i++)
        {
            try
            {
                System.out.println(results.get(i).get());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }


        exec.shutdown();

    }

}
