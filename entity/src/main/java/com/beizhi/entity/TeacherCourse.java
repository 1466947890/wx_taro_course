package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("c_teacher_course")
public class TeacherCourse {
    private Integer courseId;
    private Integer teacherId;
}
