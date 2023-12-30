package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.service.DetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2023/12/10 23:19
 * @describe
 */
@RestController
@RequestMapping("/details")
public class DetailsController {
    @Resource
    private DetailsService detailsService;

    /**
     * 获取课程资料
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}")
    public Result getDetails(@PathVariable Integer courseId){
        return detailsService.getDetailsByCourseId(courseId);
    }
}
