package com.beizhi.common.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserInfoDto {
    private Integer id;
    private String name;
    private String nickname;
    private String avatar;
    private String openId;
    private String phone;
    private String sex;
    private String registerTime;
    private Integer isReal;
    private String major;
}
