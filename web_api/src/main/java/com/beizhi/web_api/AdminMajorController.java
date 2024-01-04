package com.beizhi.web_api;

import com.beizhi.common.result.Result;
import com.beizhi.service.MajorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/4 17:25
 * @describe
 */

@RestController
@RequestMapping("/web/major")
public class AdminMajorController {
    @Resource
    private MajorService majorService;

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping
    public Result majorList(){
        return Result.successData(majorService.list());
    }
}
