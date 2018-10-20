package com.example.demo.test;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by MrDing
 * Date: 2018/11/12.
 * Time:21:55
 */
public class Test4 {


    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        Listener listener = new Listener();
        eventBus.register(listener);
        eventBus.post("cccccc");
    }




    public static class Listener{

        @Subscribe
        @AllowConcurrentEvents
        private void handle(String msg){
            System.out.println("Listener-------------------"+msg);
        }

    }







}
