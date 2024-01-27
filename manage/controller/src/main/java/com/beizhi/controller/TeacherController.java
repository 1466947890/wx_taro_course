package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.entity.Teacher;
import com.beizhi.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 14669
 * @date 2023/12/12 16:33
 * @describe
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    /*
    更新教师资料
     */

    /**
     * 通过个人登录状态保存教师资料
     * @param teacher
     * @param request
     * @return
     */
    @PutMapping
    public Result saveTeacherDetails(@RequestBody Teacher teacher, HttpServletRequest request){
        Integer userId = JwtUtils.getUserIdByRequest(request);
        return teacherService.saveTeacherByUserId(teacher, userId);
    }

    /**
     * 通过个人登录状态获取教师资料
     * @param request
     * @return
     */
    @GetMapping
    public Result getTeacherDetails(HttpServletRequest request){
        Integer userId = JwtUtils.getUserIdByRequest(request);
        return   teacherService.getTeacherDetails(userId);
    }
}
