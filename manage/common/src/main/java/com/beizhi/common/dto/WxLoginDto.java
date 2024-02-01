package com.beizhi.common.dto;

import lombok.Data;

/**
 * @author 14669
 * @date 2024/2/1 19:26
 * @describe
 */
@Data
public class WxLoginDto {
    private String code;
    private String phoneCode;
}
