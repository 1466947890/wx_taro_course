package com.beizhi.common.config;

import com.beizhi.common.Constants;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public Result sqlException(SQLException e){
        e.printStackTrace();
        return Result.error(Constants.CODE_500, "mysql执行语句异常");
    }
    @ExceptionHandler(AccessDeniedException.class)
    public Result accessDeniedException(AccessDeniedException e) {
        return Result.error(Constants.CODE_403, e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result authenticationException(AuthenticationException e) {
        return Result.error(Constants.CODE_401, e.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result onBusinessException(BusinessException e, HttpServletRequest request){
        log.info(String.format("业务异常：请求地址【%s】,异常信息【%s】", request.getRequestURI(), e.getMessage()));
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result onException(Exception e, HttpServletRequest request){
        log.info(String.format("系统异常：请求地址【%s】,异常信息【%s】", request.getRequestURI(), e.getStackTrace()));
        log.info("详细信息如下：");
        e.printStackTrace();
        return Result.error(500, e.getMessage());
    }
}
