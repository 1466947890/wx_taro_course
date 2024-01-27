package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("c_video")
public class Video {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String flag;
    private Integer isReal;
    private Integer chapterId;
}
