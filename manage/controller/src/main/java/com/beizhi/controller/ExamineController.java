package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.entity.Examine;
import com.beizhi.service.ExamineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 14669
 * @date 2023/12/13 17:37
 * @describe
 */
@RestController
@RequestMapping("/examine")
public class ExamineController {
    @Resource
    private ExamineService examineService;


    /*
    1、根据传过来的题目打分（未指定分数）
     */

    /**
     * 章节测验打分
     * @param chapterId
     * @param examineList
     * @return
     */
    @PostMapping("/grade/{chapterId}")
    public Result getGradeByChapterId(@PathVariable Integer chapterId, @RequestBody List<Examine> examineList){
        return examineService.gradeByChapterId(examineList, chapterId);
    }
}
