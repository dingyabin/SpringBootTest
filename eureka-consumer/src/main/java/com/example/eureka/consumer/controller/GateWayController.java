package com.example.eureka.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MrDing
 * Date: 2018/10/20.
 * Time:14:17
 */
@RestController
@RequestMapping("/consumer")
public class GateWayController {


    @RequestMapping("api/test")
    public String gateway(){
        return "ok";
    }


}
