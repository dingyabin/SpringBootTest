package com.example.demo.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.example.demo.bean.MyLog;
import com.example.demo.configration.MyBeanContainer;
import com.example.demo.service.MyLogService;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:12:53
 */
public class MyAppender extends AppenderBase<ILoggingEvent> {

    private String location;

    private MyLogService myLogService;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        Map<String, String> mdcPropertyMap = iLoggingEvent.getMDCPropertyMap();
        executorService.execute(() -> {
            if (myLogService == null) {
                myLogService = MyBeanContainer.getBean(MyLogService.class);
            }
            if (myLogService != null) {
                MyLog myLog = new MyLog();
                myLog.setTraceId(mdcPropertyMap.get("traceId") == null ?
                        UUID.randomUUID().toString().replaceAll("-","") : mdcPropertyMap.get("traceId"));
                myLog.setLevel(iLoggingEvent.getLevel().toString());
                myLog.setMessage(iLoggingEvent.getFormattedMessage());
                myLog.setThread(iLoggingEvent.getThreadName());
                myLog.setMethod(iLoggingEvent.getCallerData()[0].getClassName()
                        + "." + iLoggingEvent.getCallerData()[0].getMethodName());
                myLog.setLineNumber(iLoggingEvent.getCallerData()[0].getLineNumber());
                myLog.setCreateTime(new Date(iLoggingEvent.getTimeStamp()));
                myLogService.saveMyLog(myLog);
            }
        });
    }


    @PreDestroy
    public void shutrdown() {
        executorService.shutdown();
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
