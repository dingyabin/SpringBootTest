package com.example.dingyabin.data.bean;

import java.io.Serializable;

public class Common implements Serializable {
    private Long id;

    private String msg;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Common{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }
}