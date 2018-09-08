package com.example.demo.banner;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * Created by MrDing
 * Date: 2017/8/31.
 * Time:23:05
 */
public class Mybanner implements Banner {

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        out.println("****************************************");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("               **                       ");
        out.println("          *    **                       ");
        out.println("           *   **                       ");
        out.println("               **                       ");
    }



}
