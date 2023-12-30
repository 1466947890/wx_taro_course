package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.service.StudentProcessService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 14669
 * @date 2023/12/13 19:30
 * @describe
 */
@RestController
@RequestMapping("/process")
public class StudentProcessController {
    @Resource
    private StudentProcessService studentProcessService;

    /**
     * 记录视频进度
     * @return
     */
    @PostMapping("/{chapterId}/{duration}/{current}")
    public Result recordVideoProcess(@PathVariable Integer chapterId, @PathVariable Integer duration,@PathVariable Integer current, HttpServletRequest request){
        Integer userid = JwtUtils.getUserIdByRequest(request);
        return studentProcessService.recordVideoProcess(chapterId, duration, current, userid);
    }
}
