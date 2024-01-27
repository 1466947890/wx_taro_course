package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取个人信息
     * @param request
     * @return
     */
    @GetMapping
    public Result getUserInfo(HttpServletRequest request){
        Integer userid = JwtUtils.getUserIdByRequest(request);
        return userService.getUserInfo(userid);
    }


}
