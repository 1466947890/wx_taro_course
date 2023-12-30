package com.beizhi.common.dto.user;

import lombok.Data;

/**
 * @author 14669
 * @date 2023/12/22 22:48
 * @describe
 */
@Data
public class UserLoginDto {
    private String phone;
    private String password;
}
