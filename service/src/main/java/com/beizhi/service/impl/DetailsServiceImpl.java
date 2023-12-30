package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    @Override
    public Result getDetailsByCourseId(Integer courseId) {
        LambdaQueryWrapper<Details> detailsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        detailsLambdaQueryWrapper.eq(Details::getCourseId, courseId);
        List<Details> detailsList = detailsMapper.selectList(detailsLambdaQueryWrapper);
        return Result.successData(detailsList);
    }
}
