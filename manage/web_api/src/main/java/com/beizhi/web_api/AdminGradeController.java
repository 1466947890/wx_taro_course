package com.beizhi.web_api;

import com.beizhi.common.result.Result;
import com.beizhi.service.GradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/3/3 15:34
 * @describe
 */
@RestController
@RequestMapping("/web/admin/grade")
public class AdminGradeController {
    @Resource
    private GradeService gradeService;

    @GetMapping("/major/{majorId}")
    public Result majorGrade(@PathVariable Integer majorId){
        return gradeService.selectGradeByMajor(majorId);
    }
}
