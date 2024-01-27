package com.beizhi.web_api;

import com.beizhi.common.Constants;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Major;
import com.beizhi.service.MajorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/4 17:25
 * @describe 用户专业接口
 */

@RestController
@RequestMapping("/web/admin/major")
public class AdminMajorController {
    @Resource
    private MajorService majorService;


    /**
     * 获取全部专业
     * @return
     */
    @GetMapping
    public Result majorList(){
        return Result.successData(majorService.list());
    }

    /**
     * 分页查询专业列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result majorPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String name){

        return majorService.selectPage(pageNum, pageSize, name);
    }

    /**
     * 通过专业ID更新专业名称
     * @param major
     * @return
     */
    @PutMapping
    public Result majorUpdate(@RequestBody Major major){
        majorService.updateById(major);
        return Result.success(Constants.OPERATION_SUCCESS );
    }

    /**
     * 通过专业ID删除专业
     * @param majorId
     * @return
     */
    @DeleteMapping("/majorId")
    public Result majorDelete(@PathVariable Integer majorId){
        majorService.removeById(majorId);
        return Result.success(Constants.DELETE_SUCCESS);
    }

    /**
     * 保存专业
     * @param major
     * @return
     */
    @PostMapping
    public Result majorSave(@RequestBody Major major){
        majorService.save(major);
        return Result.success(Constants.DELETE_SUCCESS);
    }


}
