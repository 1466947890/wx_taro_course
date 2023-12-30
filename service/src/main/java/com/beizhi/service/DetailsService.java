package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Details;

/**
 * @author 14669
 * @date 2023/12/10 23:08
 * @describe
 */
public interface DetailsService extends IService<Details> {
    Result getDetailsByCourseId(Integer courseId);
}
