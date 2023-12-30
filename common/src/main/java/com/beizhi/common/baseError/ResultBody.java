package com.beizhi.common.baseError;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBody<T> implements Serializable {
 
    private Integer code;
    private String message;
    private T data; //不确定的数据类型

    public ResultBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultBody(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
 
    public static <T> ResultBody Success(Integer code, String message,T t){
        return new ResultBody(code,message,t);
    }

    public static ResultBody Error(Integer code, String message){
        return new ResultBody(code,message);
    }
    public static <T> ResultBody Error(Integer code, String message,T t){
        return new ResultBody(code,message,t);
    }
}