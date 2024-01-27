package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.ChapterTask;

public interface ChapterTaskService extends IService<ChapterTask> {
    Result saveChapterTask(ChapterTask chapterTask);

    Result getChapterTask(Integer courseId,Integer chapterId);
}
