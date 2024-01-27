package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.result.Result;
import com.beizhi.dao.DetailsMapper;
import com.beizhi.entity.Details;
import com.beizhi.service.DetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 14669
 * @date 2023/12/10 23:08
 * @describe
 */
@Service
public class DetailsServiceImpl extends ServiceImpl<DetailsMapper, Details> implements DetailsService {
    @Resource
    private DetailsMapper detailsMapper;

    /**
     * 通过课程ID获取课程资料
     * @param courseId 课程ID
     * @return
     */
    @Override
    public Result getDetailsByCourseId(Integer courseId) {
        LambdaQueryWrapper<Details> detailsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        detailsLambdaQueryWrapper.eq(Details::getCourseId, courseId);
        List<Details> detailsList = detailsMapper.selectList(detailsLambdaQueryWrapper);
        return Result.successData(detailsList);
    }

    /**
     * 分页查询资料列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public Result selectPage(Integer pageNum, Integer pageSize, String name) {
        LambdaQueryWrapper<Details> queryWrapper = new LambdaQueryWrapper<>();
        if(!name.isEmpty()){
            queryWrapper.like(Details::getName, name);
        }
        IPage<Details> page = new Page<>(pageNum, pageSize);
        return Result.successData(detailsMapper.selectPage(page, queryWrapper));
    }
}
