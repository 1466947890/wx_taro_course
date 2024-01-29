package com.beizhi.wx_api;

import com.beizhi.common.result.Result;
import com.beizhi.service.WxService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/29 21:32
 * @describe 微信邀请二维码
 */
@RestController
@RequestMapping("/wx/invitation")
public class WxInvitationController {
    @Resource
    private WxService wxService;

    /*
    1. 邀请二维码生成
     */


    /** 获取微信二维码参数
     * @param scene 携带参数
     * @param page 页面路径
     * @return
     */
    @GetMapping("/qrImage")
    public Result getQrImage(String scene,String page){
        return wxService.getQrImage(scene, page);
    }
}


