package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.result.Result;
import com.beizhi.dao.TeacherMapper;
import com.beizhi.entity.Teacher;
import com.beizhi.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2023/12/12 16:28
 * @describe
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public Result saveTeacherByUserId(Teacher teacher, Integer userId) {
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getUserid, userId);

        if(teacherMapper.update(teacher, teacherLambdaQueryWrapper) > 0){
            return Result.success("保存成功");
        }else{
            throw new BusinessException(BaseErrorEnum.SAVE_ERROR);
        }
    }

    @Override
    public Result getTeacherDetails(Integer userId) {
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getUserid, userId);
        return Result.successData("获取成功", teacherMapper.selectOne(teacherLambdaQueryWrapper));
    }
}
