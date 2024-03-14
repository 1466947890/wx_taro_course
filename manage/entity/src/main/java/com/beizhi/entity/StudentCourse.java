package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 14669
 */
@Data
@TableName("c_student_course")
public class StudentCourse {
    private Integer studentId;
    private Integer courseId;
    @JsonIgnore
    private BigDecimal grade;
}
