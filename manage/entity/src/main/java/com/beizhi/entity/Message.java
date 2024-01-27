package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("c_message")
public class Message {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String value;
    private String m_time;
    private Integer pid;
    private Integer userid;
    private Integer chapterId;
}
