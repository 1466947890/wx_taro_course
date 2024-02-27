package com.beizhi.wx_api;

import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 14669
 * @date 2024/2/3 17:51
 * @describe
 */
@RestController
@RequestMapping("/wx/course")
public class WxCourseController {
    @Resource
    private CourseService courseService;

    /**
     * 获取首页推荐课程
     * @param request
     * @return
     */
    @GetMapping("/index")
    private Result indexData(HttpServletRequest request){
        Integer userid = JwtUtils.getUserIdByRequest(request);
        return courseService.wxIndexData(userid);
    }
}
