package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Chapter;

public interface ChapterService extends IService<Chapter> {

    Result saveChapterByCourseId(Chapter chapter);

    Result getChapterListByCourseId(Integer courseId);

    Result deleteChapterByCourseId(Integer courseId);
}
