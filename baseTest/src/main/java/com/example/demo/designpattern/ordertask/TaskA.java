package com.example.demo.designpattern.ordertask;

import org.springframework.stereotype.Component;

/**
 * Created by MrDing
 * Date: 2018/9/14.
 * Time:21:30
 */
@Component
public class TaskA extends Job {

    @Override
    protected void execute(Object obj) {
        System.out.println(this.getClass().getName() + "---->" + obj.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected boolean needAsyn() {
        return false;
    }


    @Override
    public int getOrder() {
        return 1;
    }


}
