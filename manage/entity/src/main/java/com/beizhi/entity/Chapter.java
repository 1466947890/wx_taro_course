package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 章节
 */
@Data
@TableName("c_chapter")
public class Chapter {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer courseId;
}
