package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("c_student_course")
public class StudentCourse {
    private Integer studentId;
    private Integer courseId;
    private BigDecimal grade;
}
