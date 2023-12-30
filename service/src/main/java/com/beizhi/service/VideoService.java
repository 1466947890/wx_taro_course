package com.beizhi.service;

import com.beizhi.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
     Result uploadVideo(MultipartFile file,Integer chapterId, Integer userId);

     Result getPlayUrl(Integer videoId);
}
