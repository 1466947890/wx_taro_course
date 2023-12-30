package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Examine;

import java.util.List;

/**
 * @author 14669
 * @date 2023/12/13 17:42
 * @describe
 */
public interface ExamineService extends IService<Examine> {
    Result gradeByChapterId(List<Examine> examineList, Integer chapterId);
}
