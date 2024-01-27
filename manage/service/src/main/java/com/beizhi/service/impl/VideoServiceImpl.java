package com.beizhi.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beizhi.common.Constants;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.config.AliyunVodSDKUtils;
import com.beizhi.common.config.ConstantPropertiesUtil;
import com.beizhi.common.dto.chapterTask.VideoInfo;
import com.beizhi.common.eunm.IsRealEunm;
import com.beizhi.common.listener.PutObjectProgressListener;
import com.beizhi.common.result.Result;
import com.beizhi.dao.ChapterTaskMapper;
import com.beizhi.dao.VideoMapper;
import com.beizhi.entity.Video;
import com.beizhi.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {
    @Resource
    private PutObjectProgressListener putObjectProgressListener;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private ChapterTaskMapper chapterTaskMapper;

    @Value("${aliyun.vod.file.keyid}")
    private String keyId;

    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;

    @Transactional
    @Override
    public Result uploadVideo(MultipartFile file,Integer chapterId, Integer userId) {
        InputStream inputStream = null;
        String originalFilename = null;
        String title = null;

        System.out.println(keyId + " " + keySecret);
        long totalBytes;
        try {
            inputStream = file.getInputStream();
            //原始文件名
            originalFilename = file.getOriginalFilename();
            //只获取MP4之前的字符串作为文件名  lastIndexOf 从后面开始第一个“.”  因为文件名可以为12.12.12.MP4
            title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            totalBytes = file.getSize();
            putObjectProgressListener.setTotalBytes(totalBytes);
            putObjectProgressListener.setUserId(userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UploadStreamRequest request = new UploadStreamRequest(
                keyId,
                keySecret,
                title, originalFilename, inputStream);
        request.setPrintProgress(true);
        request.setProgressListener(putObjectProgressListener);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        Map<String, String> map = new HashMap<>();
        //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
        // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            map.put("videoId", response.getVideoId());
            LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
            videoLambdaQueryWrapper.eq(Video::getChapterId, chapterId);

            Video video = videoMapper.selectOne(videoLambdaQueryWrapper);
            video.setFlag(response.getVideoId());
            if(Objects.isNull(video)){
                video.setChapterId(chapterId);
                videoMapper.insert(video);
            }else{
                videoMapper.update(video, videoLambdaQueryWrapper);
            }
            map.put("id", String.valueOf(video.getId()));
            return Result.successData(map);
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.println(response.getVideoId());
            System.out.println(response.getCode());
            throw new BusinessException(BaseErrorEnum.UPLOAD_VIDEO_ERROR);
        }
    }

    @Override
    public Result getPlayUrl(Integer videoId) {
        //1.初始化client对象
        DefaultAcsClient client = null;
        client = AliyunVodSDKUtils.initVodClient(keyId, keySecret);
        //2.创建 request response对象 给request对象设置视频id
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(String.valueOf(videoId));
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try{
            //3.再利用request对象得到response对象
            response =  client.getAcsResponse(request);
            //4. 利用response对象得到视频的相关信息
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                return Result.successData(playInfo.getPlayURL());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public VideoInfo getVodUrl(String videoId) {//main是静态方法 静态方法不能调用成员方法 这个方法需要也是static
        //1.初始化client对象
        if (Objects.isNull(videoId)){
            return null;
        }
        DefaultAcsClient client = null;
        client = AliyunVodSDKUtils.initVodClient(keyId, keySecret);
        //2.创建 request response对象 给request对象设置视频id
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(videoId);
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try{
            //3.再利用request对象得到response对象
            response =  client.getAcsResponse(request);
            //4. 利用response对象得到视频的相关信息
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            Map<String, String> data = new HashMap<>();
            VideoInfo videoInfo = new VideoInfo();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                videoInfo.setUrl(playInfo.getPlayURL());
                videoInfo.setDuration(playInfo.getDuration());
                return videoInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result selectPage(Integer pageNum, Integer pageSize, Integer isReal) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        if(isReal != 0){
            queryWrapper.eq(Video::getIsReal, isReal);
        }
        IPage<Video> videoIPage = new Page<>(pageNum, pageSize);


        return Result.successData(videoMapper.selectPage(videoIPage, queryWrapper));

    }

    @Override
    public Result changeIsRealByVideoId(Integer videoId, Integer isReal) {
        if(!(isReal == 1 || isReal == -1)){
            throw new BusinessException(BaseErrorEnum.PARAMETER_ERROR);
        }
        Video video = new Video();
        video.setId(videoId);
        video.setIsReal(isReal);
        videoMapper.updateById(video);
        return Result.success(Constants.OPERATION_SUCCESS);
    }
}
