package com.beizhi.service;

import com.beizhi.common.result.Result;

/**
 * @author 14669
 * @date 2024/1/29 22:26
 * @describe
 */
public interface WxService {
    Result getQrImage(String scene, String page);
}
