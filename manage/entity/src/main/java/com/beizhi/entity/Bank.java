package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("c_bank")
public class Bank {
    @TableId(type = IdType.AUTO)
    private Integer BankId;
    private String BankName;
    private String BankTime;
}
