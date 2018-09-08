package com.example.demo.designpattern.observer;

import java.util.Observable;

/**
 * Created by dingyabin on 2018/4/8.
 * Time: 16:42
 * function:
 */
public class MySubject extends Observable {


    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

}
