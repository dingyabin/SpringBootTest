package com.example.demo.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by dingyabin on 2018/4/8.
 * Time: 16:38
 * function:
 */
public class MyObserver implements Observer {

    public MyObserver(Observable observable) {
        observable.addObserver(this);
    }

    public MyObserver() {
    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("发生变化了，" + this.getClass().getName() + "收到"+o.getClass().getName()+"的通知了,参数：" + arg.toString());
    }


}
