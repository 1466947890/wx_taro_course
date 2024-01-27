package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("c_examine")
public class Examine {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String eOptions;
    private Integer type;
    private String answer;
//    所属题库ID
    private Integer chapterId;
}
