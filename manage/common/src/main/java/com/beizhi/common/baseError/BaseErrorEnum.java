package com.beizhi.common.baseError;

import com.beizhi.common.Constants;

public enum BaseErrorEnum implements BaseErrorInfoInterface{
    BODY_NOT_MATCH(400, "数据格式不匹配"),
    OPERATION_SQL_ERROR(500, "操作失败，请检查数据"),

    USER_PASSWORD_ERROR(500, "用户名或者密码错误"),
    USER_NOT_EXIST(400, "用户不存在"),
    TOKEN_ERROR(500, "token非法"),

    CHAPTER_EXIST(500, "章节名称已存在"),
    STUDENT_EXIST(500, "学生已存在"),

    // 课程不存在
    COURSE_NOT_EXIST(Constants.CODE_500, "课程不存在"),
    UPLOAD_VIDEO_ERROR(Constants.CODE_500, "上传视频失败"),
    SAVE_ERROR(Constants.CODE_500, "保存失败"),

    SYSTEM_ERROR(Constants.CODE_500, "系统错误"),
    PARAMETER_ERROR(Constants.CODE_500, "参数错误")
    ;
    private Integer code;

    private String message;
    BaseErrorEnum(Integer code, String message) {
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
