package com.beizhi.controller;

import com.beizhi.common.dto.CourseDto;
import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.entity.StudentCourse;
import com.beizhi.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    /**
     * 保存课程
     * @param courseDto
     * @param request
     * @return
     */
    @PostMapping
    public Result saveCourse(@RequestBody CourseDto courseDto, HttpServletRequest request){
        Integer userId = JwtUtils.getUserIdByRequest(request);
        return courseService.saveCourse(courseDto, userId);
    }

    /**
     * 获取课程信息列表
     * @param type
     * @param request
     * @return
     */
    @GetMapping("/{type}")
    public Result getCourseList(@PathVariable Integer type, HttpServletRequest request){
        Integer userId = JwtUtils.getUserIdByRequest(request);
        return courseService.getCourseListByType(type, userId);
    }

    /**
     * 学生添加课程
     * @param courseGrade
     * @param request
     * @return
     */
    @PostMapping("/studentCourse")
    public Result joinCourse(@RequestBody StudentCourse courseGrade, HttpServletRequest request){
        Integer userId = JwtUtils.getUserIdByRequest(request);
        return courseService.saveCourseStudent(courseGrade, userId);
    }
}
