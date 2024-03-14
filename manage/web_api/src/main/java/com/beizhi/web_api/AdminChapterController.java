package com.beizhi.web_api;

import com.beizhi.common.result.Result;
import com.beizhi.service.ChapterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/3/3 14:45
 * @describe 课程章节
 */
@RestController
@RequestMapping("/web/admin/chapter")
public class AdminChapterController {
    @Resource
    private ChapterService chapterService;

    @GetMapping("/{courseId}")
    public Result courseChapter(@PathVariable Integer courseId){
        return chapterService.getChapterListByCourseId(courseId);
    }
}
