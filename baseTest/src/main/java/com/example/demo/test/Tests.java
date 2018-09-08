package com.example.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by MrDing
 * Date: 2017/12/31.
 * Time:23:42
 */
@RunWith(Parameterized.class)
public class Tests {

    private String str1;

    private String str2;

    public Tests(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    @Parameters
    public static Collection<String[]> data() {
        return Arrays.asList(new String[][]{{"1","2"},{"a","v"},{"d","2"}});

    }


    @Test
    public void test1() {
        System.out.println(str1 + "-------------" + str2);

    }
}
