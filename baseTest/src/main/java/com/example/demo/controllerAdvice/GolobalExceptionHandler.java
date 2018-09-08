package com.example.demo.controllerAdvice;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:18:58
 */
@ControllerAdvice
public class GolobalExceptionHandler {


    private Logger logger= LoggerFactory.getLogger(GolobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String,Object> catchExcdption(Exception exception){
        HashMap<String,Object> map = Maps.newHashMap();
        map.put("code","E00001");
        map.put("success",false);
        map.put("message",exception.getMessage());
        logger.error("发生异常了，{}",exception.getMessage());
        return map;
    }


}
