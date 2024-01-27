package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 课程资料表
 */
@Data
@TableName("c_details")
public class Details {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String url;
    @JsonIgnore
    private Integer courseId;
}
