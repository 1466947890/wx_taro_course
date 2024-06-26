package com.beizhi.controller;

import com.beizhi.common.dto.user.UserInfoDto;
import com.beizhi.common.result.Result;
import com.beizhi.service.LoginService;
import com.beizhi.common.dto.CodeDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 1466947890
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;


    /**
     * 微信小程序登录
     * @param codeDto
     * @return 如果是新用户则插入用户信息并返回用户信息，如果是旧用户则返回用户信息，并更新secret
     */
    @PostMapping("/login")
    public Result login(@RequestBody CodeDto codeDto){
        return loginService.getOpenId(codeDto);
    }

    /**
     * 更新用户信息资料
     * @param userInfoDto
     * @return
     */
    @PutMapping("/login")
    public Result updateUserInfo(@RequestBody UserInfoDto userInfoDto){
        return loginService.updateUser(userInfoDto);
    }



}
