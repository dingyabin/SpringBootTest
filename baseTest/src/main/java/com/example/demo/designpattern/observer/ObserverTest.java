package com.example.demo.designpattern.observer;

/**
 *
 * @author dingyabin
 * @date 2018/4/8
 * Time: 16:39
 * function:
 */
public class ObserverTest {

    public static void main(String[] args) {
        MySubject observable = new MySubject();

        MyObserver myObserver1 = new MyObserver(observable);
        MyObserver myObserver2 = new MyObserver(observable);
        MyObserver myObserver3 = new MyObserver(observable);

        observable.setChanged();
        observable.notifyObservers("8888888");
    }
}
