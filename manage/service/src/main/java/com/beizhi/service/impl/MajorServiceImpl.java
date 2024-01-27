package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.result.Result;
import com.beizhi.dao.MajorMapper;
import com.beizhi.entity.Major;
import com.beizhi.service.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/4 17:27
 * @describe
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    @Resource
    private MajorMapper majorMapper;

    /**
     * 分页查询专业
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public Result selectPage(Integer pageNum, Integer pageSize, String name) {
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        IPage<Major> majorIpage = new Page<>(pageNum, pageSize);
        if(!"".equals(name)){
            queryWrapper.eq(Major::getName, name);
        }
        return Result.successData( majorMapper.selectPage(majorIpage, queryWrapper));
    }
}
