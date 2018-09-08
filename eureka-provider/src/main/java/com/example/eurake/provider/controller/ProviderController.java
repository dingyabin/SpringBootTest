package com.example.eurake.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dingyabin
 * @date 2018/4/10
 * Time: 14:58
 * function:
 */
@RestController
public class ProviderController {


    @RequestMapping("service/v1")
    public String provider(){
        return "hello";
    }


    @RequestMapping("info")
    public String info(){
        return "info";
    }



}
