package com.beizhi.service;

import com.beizhi.common.dto.chapterTask.VideoInfo;
import com.beizhi.common.eunm.IsRealEunm;
import com.beizhi.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 14669
 */
public interface VideoService {
     Result uploadVideo(MultipartFile file,Integer chapterId, Integer userId);

     Result getPlayUrl(Integer videoId);

     VideoInfo getVodUrl(String videoId);

     Result selectPage(Integer pageNum, Integer pageSize, Integer isReal);

     Result changeIsRealByVideoId(Integer videoId, Integer isReal);
}
