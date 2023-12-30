package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("c_student")
public class Student {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer userid;
}
