package com.beizhi.wx_api;

import com.beizhi.common.result.Result;
import com.beizhi.service.WxService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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


    /** 获取微信邀请二维码参数
     * @param scene 携带参数
     * @param page 页面路径
     * @return
     */
    @GetMapping("/qrImage")
    public Result getQrImage(@RequestParam String scene,@RequestParam String page){
        return wxService.getQrImage(scene, page);
    }

    /**
     * 通过课程ID加入课程
     * @param courseId 课程ID
     * @param request  来自请求
     * @return
     */
    @PostMapping("/join")
    public Result joinCourseById(@RequestParam Integer courseId, HttpServletRequest request){
        return wxService.joinCourseByCourseId(courseId, request);
    }

    /**
     * 获取课程信息，在加入课程前预览
     * @param courseId 课程ID
     * @return
     */
    @GetMapping("/courseInfo/{courseId}")
    public Result courseInfo(@PathVariable Integer courseId){
        return wxService.getCourseInfoById(courseId);
    }


}


