package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("c_course")
public class Course {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String image;
//    课程学分
    private Integer credit;
//    课程标识
    @TableField(exist = false)
    private String teacherList;
}
