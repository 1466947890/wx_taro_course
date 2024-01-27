package com.beizhi.web_api;

import com.beizhi.common.dto.user.UserLoginDto;
import com.beizhi.common.result.Result;
import com.beizhi.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/4 13:13
 * @describe
 */
@RestController
public class WebLoginController {

    @Resource
    private LoginService loginService;


    /**
     * 网页端登录
     * @param userLoginDto
     * @return
     */
    @PostMapping("/web/login")
    public Result teacherLogin(@RequestBody UserLoginDto userLoginDto){
        return loginService.login(userLoginDto);
    }
}
