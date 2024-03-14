package com.beizhi.service.impl;

import com.beizhi.common.dto.GradeDto;
import com.beizhi.common.result.Result;
import com.beizhi.dao.StudentCourseGradeMapper;
import com.beizhi.service.GradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 14669
 * @date 2024/3/3 15:40
 * @describe
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Resource
    private StudentCourseGradeMapper studentCourseGradeMapper;

    @Override
    public Result selectGradeByMajor(Integer majorId) {
        List<GradeDto> gradeDtoList = studentCourseGradeMapper.selectMajorGrade(majorId);
        return Result.successData(gradeDtoList);
    }
}
