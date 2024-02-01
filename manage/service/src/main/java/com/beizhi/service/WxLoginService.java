package com.beizhi.service;

import com.beizhi.common.dto.WxLoginDto;
import com.beizhi.common.result.Result;

/**
 * @author 14669
 * @date 2024/1/31 22:51
 * @describe
 */
public interface WxLoginService {
    Result login(WxLoginDto wxLoginDto);
}
