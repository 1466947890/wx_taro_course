package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 14669
 */
@Data
@TableName("c_major")
public class Major {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
}
