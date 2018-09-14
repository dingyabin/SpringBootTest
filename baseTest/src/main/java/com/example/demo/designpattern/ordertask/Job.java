package com.example.demo.designpattern.ordertask;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;

/**
 * Created by MrDing
 * Date: 2018/9/14.
 * Time:21:06
 */
public abstract class Job implements InitializingBean, Ordered {

    protected abstract void execute(Object obj);

    protected abstract boolean needAsyn();


    @Override
    public void afterPropertiesSet(){
        JobContext.addJob(this);
    }


}
