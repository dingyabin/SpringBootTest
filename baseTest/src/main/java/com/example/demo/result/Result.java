package com.example.demo.result;

import java.io.Serializable;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:20:04
 */
public class Result implements Serializable{

    private static final long serialVersionUID = -3602064487615997440L;

    private String code;
    private String messaage;
    private boolean success;
    private Object data;

    public Result() {
    }

    public Result(String code, String messaage, boolean success, Object data) {
        this.code = code;
        this.messaage = messaage;
        this.success = success;
        this.data = data;
    }

    public static Result init(){
        return new Result();
    }

    public String getCode() {
        return code;
    }

    public Result  setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessaage() {
        return messaage;
    }

    public Result setMessaage(String messaage) {
        this.messaage = messaage;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
