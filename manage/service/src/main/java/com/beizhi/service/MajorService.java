package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Major;

/**
 * @author 14669
 * @date 2024/1/4 17:27
 * @describe
 */
public interface MajorService extends IService<Major> {
    Result selectPage(Integer pageNum, Integer pageSize, String name);
}
