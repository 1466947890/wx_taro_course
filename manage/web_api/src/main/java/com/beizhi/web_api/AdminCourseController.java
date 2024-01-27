package com.beizhi.web_api;

import com.beizhi.common.Constants;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Course;
import com.beizhi.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/27 21:01
 * @describe
 */
@RestController
@RequestMapping("/web/admin/course")
public class AdminCourseController {
    @Resource
    private CourseService courseService;

    /**
     * 分页查询课程列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result selectPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "") String name){
        return courseService.selectPage(pageNum, pageSize, name);
    }

    @PutMapping
    public Result updateCourse(@RequestBody Course course){
        courseService.updateById(course);
        return Result.success(Constants.OPERATION_SUCCESS);
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("/{courseId}")
    public Result deleteCourse(@PathVariable Integer courseId){
        courseService.removeById(courseId);
        return Result.success(Constants.DELETE_SUCCESS);
    }

    /**
     * 获取单个课程的学生全部成绩
     * @param courseId
     * @return
     */
    @GetMapping("/grade/{courseId}")
    public Result gradeCourse(@PathVariable Integer courseId){
        return courseService.getGrade(courseId);
    }

    /**
     * 计算学生课程成绩
     * @param courseId
     * @return
     */
    @PostMapping("/grade/{courseId}")
    public Result calculateGrade(@PathVariable Integer courseId){
        return courseService.calculate(courseId);
    }


}
