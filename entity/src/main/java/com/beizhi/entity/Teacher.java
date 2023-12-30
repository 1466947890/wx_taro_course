package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("c_teacher")
public class Teacher {
    @TableId(type = IdType.AUTO)
    @JsonIgnore
    private Integer id;
    private String avatar;
    private String information;
    private String experience;
    @JsonIgnore
    private Integer userid;
}
