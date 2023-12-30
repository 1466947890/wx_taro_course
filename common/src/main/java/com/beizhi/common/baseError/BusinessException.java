package com.beizhi.common.baseError;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private Integer code;
    private String message;
    /**
     * 默认构造方法
     */
    public BusinessException(){
        super();
    }


    public BusinessException(BaseErrorEnum baseErrorEnum){
        this.code = baseErrorEnum.getCode();
        this.message = baseErrorEnum.getMessage();
    }
}
