package com.example.log.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.AppenderBase;
import com.example.log.utils.AppendUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

import static net.logstash.logback.marker.Markers.appendEntries;

/**
 * Created by dingyabin001
 * Date: 2018/10/22.
 * Time:12:49
 */
@Slf4j
public class StashAppender extends AppenderBase<ILoggingEvent> {

    private static Properties properties;

    private static ExecutorService executorService;


    static {
        properties = new Properties();
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
                Runtime.getRuntime().availableProcessors() * 2,
                1, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(2048),
                Executors.defaultThreadFactory()
        );
        try {
            InputStream resourceAsStream;
            resourceAsStream = StashAppender.class.getResourceAsStream("/application.properties");
            if (resourceAsStream == null) {
                resourceAsStream = StashAppender.class.getResourceAsStream("/config/application.properties");
            }
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        executorService.submit(new AppendTask(iLoggingEvent));
    }


    private class AppendTask implements Runnable {

        private ILoggingEvent iLoggingEvent;
        private Map<String, String> map;

        AppendTask(ILoggingEvent iLoggingEvent) {
            this.iLoggingEvent = iLoggingEvent;
            map = MDC.getCopyOfContextMap();
        }

        @Override
        public void run() {
            if (map != null) {
                MDC.setContextMap(map);
            }
            Map<String, Object> myMap = new HashMap<>();
            myMap.put("ip", AppendUtil.getAddress());
            myMap.put("host", AppendUtil.getHost());
            myMap.put("appName", properties.getProperty("spring.application.name"));
            myMap.put("environment", properties.getProperty("spring.Environment"));
            myMap.put("loggerName", iLoggingEvent.getLoggerName());
            myMap.put("logLevel", iLoggingEvent.getLevel().levelStr);
            myMap.put("logLevelValue", iLoggingEvent.getLevel().levelInt);
            myMap.put("threadName", iLoggingEvent.getThreadName());
            if (iLoggingEvent.hasCallerData()) {
                AppendUtil.logCallerInfo(myMap, iLoggingEvent.getCallerData()[0]);
            }
            IThrowableProxy proxy = iLoggingEvent.getThrowableProxy();
            if (proxy != null) {
                AppendUtil.logThrowableInfo(myMap, proxy);
            }
            Object[] argumentArray = iLoggingEvent.getArgumentArray();
            if (argumentArray != null && argumentArray.length > 0) {
                AppendUtil.logKeyValueInfo(myMap, argumentArray);
            }
            log.info(appendEntries(myMap), iLoggingEvent.getFormattedMessage());
        }
    }

}
