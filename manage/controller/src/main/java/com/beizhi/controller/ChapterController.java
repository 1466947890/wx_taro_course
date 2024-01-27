package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.entity.Chapter;
import com.beizhi.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    /**
     * 通过课程ID保存章节
     * @param chapter
     * @return
     */
    @PostMapping
    public Result saveChapter(@RequestBody Chapter chapter){
        return chapterService.saveChapterByCourseId(chapter);
    }

    /**
     * 通过课程ID获取章节列表
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}")
    public Result getChapter(@PathVariable Integer courseId){
        return chapterService.getChapterListByCourseId(courseId);
    }

    /**
     * 删除章节
     * @param courseId
     * @return
     */
    @DeleteMapping("/{courseId}")
    public Result deleteChapter(@PathVariable Integer courseId){
        return chapterService.deleteChapterByCourseId(courseId);
    }
}
