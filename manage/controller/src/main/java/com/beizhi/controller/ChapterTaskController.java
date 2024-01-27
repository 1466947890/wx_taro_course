package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.entity.ChapterTask;
import com.beizhi.service.ChapterTaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chapterTask")
public class ChapterTaskController {
    @Resource
    private ChapterTaskService chapterTaskService;

    /**
     * 获取章节任务，视频、习题、教师资料
     * @param chapterId
     * @return
     */
    @GetMapping("/{courseId}/{chapterId}")
    public Result getChapterTask(@PathVariable Integer courseId, @PathVariable Integer chapterId){
        return chapterTaskService.getChapterTask(courseId ,chapterId);
    }

    /**
     * 上传章节任务，视频、习题
     * @param chapterTask
     * @return
     */
    @PostMapping
    public Result saveChapterTask(@RequestBody ChapterTask chapterTask){
        return chapterTaskService.saveChapterTask(chapterTask);
    }

}
