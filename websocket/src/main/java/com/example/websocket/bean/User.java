package com.example.websocket.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

/**
 * Created by dingyabin on 2018/4/23.
 * Time: 14:35
 * function:
 */
@Slf4j
@Getter
@Setter
public class User implements Principal {

    private String name;


    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


}
