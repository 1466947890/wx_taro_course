package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.dto.CourseDto;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Course;
import com.beizhi.entity.StudentCourse;

public interface CourseService extends IService<Course> {
    Result saveCourse(CourseDto courseDto, Integer userId);

    Result getCourseListByType(Integer type, Integer userId);

    Result saveCourseStudent(StudentCourse courseGrade, Integer userId);

    Result selectPage(Integer pageNum, Integer pageSize, String name);

    Result getGrade(Integer courseId);

    Result calculate(Integer courseId);
}
