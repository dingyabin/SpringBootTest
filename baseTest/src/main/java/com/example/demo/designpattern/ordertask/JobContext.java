package com.example.demo.designpattern.ordertask;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by MrDing
 * Date: 2018/9/14.
 * Time:21:09
 */
public class JobContext {

    private static final SortedSet<Job> jobs = new TreeSet<>((o1, o2) -> o1.getOrder() - o2.getOrder());

    private static final ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void addJob(Job job) {
        jobs.add(job);
    }

    public static void doTask(Object object) {
        for (Job job : jobs) {
            if (job.needAsyn()){
                pool.execute(()->{
                    System.out.println(job.getClass().getName()+"异步执行开始..");
                    job.execute(object);
                });
            }else {
                job.execute(object);
            }
        }
    }


}
