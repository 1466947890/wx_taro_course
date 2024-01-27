package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("c_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String role;
    private String nickname;
    private String avatar;
    private String openId;
    @JsonIgnore
    private String sessionKey;
    private String phone;
    private String password;
    private String sex;

    @JsonIgnore
    private String registerTime;
    private Integer isReal;

    @TableField(exist = false)
    private String major;
    private Integer majorId;
}
