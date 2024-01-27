package com.beizhi.common.dto;

import com.beizhi.entity.Course;
import lombok.Data;

@Data
public class CourseDto {
    private String courseName;
    private String courseImage;
    //    课程学分
    private String courseCredit;
    //    课程标识
    private Integer courseNumber;
    private String professorTime;
}
