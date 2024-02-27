package com.beizhi.wx_api;

import com.beizhi.common.dto.WxLoginDto;
import com.beizhi.common.result.Result;
import com.beizhi.service.WxLoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/31 22:47
 * @describe
 */
@RestController
@RequestMapping("/wx/user")
public class WxUserController {
    @Resource
    private WxLoginService wxLoginService;


    /**
     * 微信登录接口
     * @param wxLoginDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody WxLoginDto wxLoginDto){
        return wxLoginService.login(wxLoginDto);
    }
}
