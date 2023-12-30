package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Teacher;

/**
 * @author 14669
 * @date 2023/12/12 11:24
 * @describe
 */

public interface TeacherService extends IService<Teacher> {
    Result saveTeacherByUserId(Teacher teacher, Integer userId);

    Result getTeacherDetails(Integer userId);
}
