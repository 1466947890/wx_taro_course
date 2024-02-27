package com.beizhi.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.result.Result;
import com.beizhi.common.result.ResultEnum;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.common.utils.RedisCache;
import com.beizhi.common.utils.WxUtils;
import com.beizhi.dao.CourseMapper;
import com.beizhi.dao.StudentCourseGradeMapper;
import com.beizhi.dao.StudentMapper;
import com.beizhi.entity.Course;
import com.beizhi.entity.Student;
import com.beizhi.entity.StudentCourse;
import com.beizhi.service.WxService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 14669
 * @date 2024/1/29 22:26
 * @describe
 */
@Service
public class WxServiceImpl implements WxService {
    @Resource
    private RedisCache redisCache;

    @Value("${files.upload.path}")
    private String fileUploadPath;
    @Value("${wx.appid}")
    private  String appid;
    @Value("${wx.secret}")
    private  String secret;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentCourseGradeMapper studentCourseGradeMapper;
    @Resource
    private WxUtils wxUtils;

    @Override
    public Result getQrImage(String scene, String page) {

        //获取小程序access_token
        String accessToken = wxUtils.getAccessToken();

        String result = null;

        // 获取小程序二维码
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("scene", scene);
        params.put("page",page);
        params.put("check_path",false);
        params.put("env_version","develop");

        // 注意这里byte是小写
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(url, params, byte[].class);
        // 二维码图片转base64
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            InputStream inputStream = null;
            ByteArrayOutputStream swapStream = null;
            try {
                byte[] body = responseEntity.getBody();
                inputStream = new ByteArrayInputStream(body);
                // 将获取流转为base64格式
                byte[] data;
                swapStream = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int rc;
                while ((rc = inputStream.read(buff, 0, 1024)) > 0) {
                    swapStream.write(buff, 0, rc);
                }

//                System.out.println(swapStream);
                data = swapStream.toByteArray();
                File dir = new File(fileUploadPath + "/invite");
                if(!dir.isDirectory()){
                    dir.mkdir();
                }

                String uuid = IdUtil.fastSimpleUUID();
                String fileUuid = uuid + StrUtil.DOT + "jpg";
                File uploadFile = new File((fileUploadPath + fileUuid));

                FileOutputStream fileOutputStream = new FileOutputStream(uploadFile);
                fileOutputStream.write(data);

                // todo 解决获取本地化自动获取域名
                String path = "http://localhost:9092/file/" + fileUuid;
                fileOutputStream.flush();
                fileOutputStream.close();
                return Result.successData(path);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (swapStream != null) {
                        swapStream.close();
                    }
                } catch (Exception e) {

                }
            }
        }
        return null;

    }

    @Override
    public Result joinCourseByCourseId(Integer courseId, HttpServletRequest request) {
        Integer userId = JwtUtils.getUserIdByRequest(request);
        //        判断课程存不存在
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseLambdaQueryWrapper.eq(Course::getId, courseId);
        Course course = courseMapper.selectOne(courseLambdaQueryWrapper);
        if(Objects.isNull(course)){
            throw new BusinessException(BaseErrorEnum.COURSE_NOT_EXIST);
        }

        // 查询用户的学号
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getUserid, userId);
        Student student = studentMapper.selectOne(studentLambdaQueryWrapper);
        if(Objects.isNull(student)){
            student = new Student();
            student.setUserid(userId);
            studentMapper.insert(student);
        }
//        判断学生是否已经在课程中
        LambdaQueryWrapper<StudentCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentCourse::getCourseId, courseId);
        queryWrapper.eq(StudentCourse::getStudentId, student.getId());
        List<StudentCourse> studentCourseGradeList = studentCourseGradeMapper.selectList(queryWrapper);
        if(!studentCourseGradeList.isEmpty()){
            throw new BusinessException(BaseErrorEnum.STUDENT_EXIST);
        }
//        先插入学生表
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentId(student.getId());
        studentCourse.setCourseId(courseId);

//        插入
        studentCourseGradeMapper.insert(studentCourse);
        return Result.response(ResultEnum.JOIN_COURSE_SUCCESS);
    }

    @Override
    public Result getCourseInfoById(Integer courseId) {
        return Result.successData(courseMapper.selectById(courseId));
    }

    public String getAccessToken() {
        String apiUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        if(Objects.nonNull(redisCache.getCacheObject("accessToken"))){
            return redisCache.getCacheObject("accessToken");
        }
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                byte[] buffer = new byte[inputStream.available()];
                while ((inputStream.read(buffer)) != -1){
                    stream.write(buffer);
                }
                JSONObject resJSONObj = JSONObject.parse(stream.toString("UTF-8"));
                String accessToken = resJSONObj.getString("access_token");
                inputStream.close();
                redisCache.setCacheObject("accessToken", accessToken, 2, TimeUnit.HOURS);
                return accessToken;


                // 处理返回的 access token 数据
                // ...

            } else {
                throw new IOException("HTTP response code: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }
}


@Data
class AccessToken {
    private String accessToken;//获取到的凭证
    private Integer expiresIn;//获取有效时间，单位：秒。目前是7200秒内值
    private Integer errCode;//错误码
    private String errMsg;//错误信息
}
