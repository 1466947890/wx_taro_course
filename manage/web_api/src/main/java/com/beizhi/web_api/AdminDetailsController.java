package com.beizhi.web_api;

import com.beizhi.common.result.Result;
import com.beizhi.service.DetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/27 21:25
 * @describe 课程资料管理接口
 */
@RestController
@RequestMapping("/web/admin/details")
public class AdminDetailsController {
    @Resource
    private DetailsService detailsService;

    /**
     * 分页查询资料列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result selectPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam String name){
        return detailsService.selectPage(pageNum, pageSize, name);
    }


}
