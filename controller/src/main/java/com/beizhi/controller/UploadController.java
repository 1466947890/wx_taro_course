package com.beizhi.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.beizhi.entity.Details;
import com.beizhi.service.DetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class UploadController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private DetailsService detailsService;

    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String type = FileUtil.extName(originalFileName);
        // 先存储到磁盘
        File upload = new File(fileUploadPath);
        if(!upload.isDirectory()){
            upload.mkdir();
        }
        // 定义一个文件作为唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + type;
        File uploadFile = new File((fileUploadPath + fileUuid));
        // 把获取到的文件存到磁盘里面去
        file.transferTo(uploadFile);
        String url = String.valueOf(request.getRequestURL()).replace("upload/", "");
        return url + fileUuid;
    }

    /**
     * 上传资料到课程
     * @param file
     * @param courseId 课程ID
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/upload/{courseId}")
    public String upload(@RequestParam MultipartFile file, @PathVariable Integer courseId, HttpServletRequest request) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String type = FileUtil.extName(originalFileName);
        // 先存储到磁盘
        File upload = new File(fileUploadPath);
        if(!upload.isDirectory()){
            upload.mkdir();
        }
        // 定义一个文件作为唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + type;
        File uploadFile = new File((fileUploadPath + fileUuid));
        // 把获取到的文件存到磁盘里面去
        file.transferTo(uploadFile);
        String url = String.valueOf(request.getRequestURL()).replace("upload/" + courseId, "");
        Details details = new Details();
        details.setName(originalFileName);
        details.setUrl(url + fileUuid);
        details.setCourseId(courseId);
        detailsService.save(details);
        return url + fileUuid;
    }

    /**
     * 获取文件地址
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath  + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
}
