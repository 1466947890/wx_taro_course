package com.beizhi.service;

import com.beizhi.common.result.Result;

/**
 * @author 14669
 * @date 2024/3/3 15:39
 * @describe
 */
public interface GradeService {
    Result selectGradeByMajor(Integer majorId);
}
