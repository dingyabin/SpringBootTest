package com.example.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by MrDing
 * Date: 2018/1/18.
 * Time:22:25
 */

@RunWith(JUnit4.class)
public class Test3 {

    @Test
    public void test1() throws InterruptedException {
        int count = 5;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        System.out.println("主线程开始了..........");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "开始执行");
                sleep(3000);
                System.out.println(Thread.currentThread().getName() + "执行结束");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(1, TimeUnit.MINUTES);
        System.out.println("主线程结束1..........");
        System.out.println("准备执行后续任务..........");
        for (int i = 0; i < count; i++) {
            executorService.submit(() -> {
                sleep(2000);
                System.out.println(Thread.currentThread().getName() + "执行后续任务");
            });
        }
        System.out.println("主线程结束2..........");
        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.MINUTES);
    }


    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }




}
