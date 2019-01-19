package com.example.demo.aoptest;

import org.springframework.stereotype.Service;

/**
 * Created by MrDing
 * Date: 2019/1/15.
 * Time:20:24
 */
@Service
public class AopTestImpl implements AopTest {

    @Override
    public void test() {
        System.out.println("................test..............");
    }
}
