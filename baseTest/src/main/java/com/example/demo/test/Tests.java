package com.example.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by MrDing
 * Date: 2017/12/31.
 * Time:23:42
 */

public class Tests {


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i <10000000 ; i++) {
            int num =i;
            executorService.submit(()->{
                boolean sushu = Tests.isSushu(num);
                if (sushu){
                    System.out.println(num);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS );
        System.out.println("cost:" +(System.currentTimeMillis()-start));
    }








    public static  boolean isSushu(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
