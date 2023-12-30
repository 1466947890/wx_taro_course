package com.beizhi.controller;

import com.beizhi.common.Constants;
import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.common.utils.RedisCache;
import com.beizhi.service.VideoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Resource
    private VideoService videoService;
    @Resource
    private RedisCache redisCache;

    /**
     * 上传章节视频
     * @param file
     * @param chapterId
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadVideo/interAspect/{chapterId}",headers = "content-type=multipart/form-data")
    public Result uploadVideo(@RequestParam("file") MultipartFile file ,@PathVariable Integer chapterId,HttpServletRequest request) {
        Integer userId = JwtUtils.getUserIdByRequest(request);
        return videoService.uploadVideo(file, chapterId, userId);
    }

    /**
     * 获取上传视频进度
     * @param request
     * @return
     */
    @GetMapping("/process")
    public Result process(HttpServletRequest request){
        Integer userid = JwtUtils.getUserIdByRequest(request);
        return Result.successData(redisCache.getCacheObject(Constants.video_process_pre + userid));
    }

    /**
     * 获取视频播放地址
     * @param videoId
     * @return
     */
    @GetMapping("/videoId")
    public Result getPlayUrl(@PathVariable Integer videoId){
        return videoService.getPlayUrl(videoId);
    }
}
