package com.beizhi.common.result;


import com.beizhi.common.Constants;

public enum ResultEnum implements ResultInfoInterface {
    DELETE_SUCCESS(Constants.CODE_200, "删除成功"),
    SAVE_SUCCESS(Constants.CODE_200, "保存成功"),
    UPDATE_SUCCESS(Constants.CODE_200, "更新成功"),
    JOIN_COURSE_SUCCESS(Constants.CODE_200, "加入课程成功")


    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
