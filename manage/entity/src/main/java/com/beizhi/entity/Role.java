package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 14669
 * @date 2023/12/19 17:31
 * @describe
 */
@Data
@TableName("c_role")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String flag;
}
