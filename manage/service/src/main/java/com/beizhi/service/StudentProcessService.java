package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.StudentProcess;

/**
 * @author 14669
 * @date 2023/12/13 19:27
 * @describe
 */
public interface StudentProcessService extends IService<StudentProcess> {
    Result recordVideoProcess(Integer chapterId, Integer duration, Integer current, Integer userid);
}
