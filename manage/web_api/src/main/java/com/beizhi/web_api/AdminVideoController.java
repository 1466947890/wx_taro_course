package com.beizhi.web_api;

import com.beizhi.common.eunm.IsRealEunm;
import com.beizhi.common.result.Result;
import com.beizhi.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2024/1/27 10:15
 * @describe
 */
@RestController
@RequestMapping("/web/admin/video")
public class AdminVideoController {
    @Resource
    private VideoService videoService;

    /**
     * 分页查看视频
     * @param pageNum 页码
     * @param pageSize 页数
     * @param isReal 是否已审核
     * @return
     */
    @GetMapping("/page")
    public Result selectPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam Integer isReal){
        return videoService.selectPage(pageNum, pageSize, isReal);
    }

    /**
     * 更新视频认证状态
     * @param videoId 视频Id
     * @param isReal -1代表未审核，1代表已审核
     * @return
     */
    @PutMapping("/{videoId}/{isReal}")
    public Result changeIsReal(@PathVariable Integer videoId, @PathVariable Integer isReal){
        return videoService.changeIsRealByVideoId(videoId, isReal);
    }


}
