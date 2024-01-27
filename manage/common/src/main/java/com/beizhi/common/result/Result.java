package com.beizhi.common.result;

import com.beizhi.common.Constants;
import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result response(ResultEnum resultEnum){
        return new  Result(resultEnum.getCode(), resultEnum.getMessage());
    }

    public static Result success(String msg){
        return new Result(Constants.CODE_200, msg);
    }

    public static Result successData(String msg,Object data){
        return new Result(Constants.CODE_200, msg, data);
    }
    public static Result successData(Object data){
        return new Result(Constants.CODE_200, Constants.GET_SUCCESS, data);
    }

    public static Result error(Integer code, String msg){
        return new Result(code, msg);
    }
}
