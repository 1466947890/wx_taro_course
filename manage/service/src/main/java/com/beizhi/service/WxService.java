package com.beizhi.service;

import com.beizhi.common.result.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 14669
 * @date 2024/1/29 22:26
 * @describe
 */
public interface WxService {
    Result getQrImage(String scene, String page, HttpServletRequest request);

    Result joinCourseByCourseId(Integer courseId, HttpServletRequest request);

    Result getCourseInfoById(Integer courseId);
}
