package com.beizhi.wx_api;

import com.beizhi.common.result.Result;
import com.beizhi.service.WxLoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    public Result login(String code, String phoneCode){
        return wxLoginService.login(code, phoneCode);
    }
}
