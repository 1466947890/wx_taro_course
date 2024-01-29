package com.beizhi.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.beizhi.common.result.Result;
import com.beizhi.common.utils.RedisCache;
import com.beizhi.service.WxService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    @Value("${wx.appid}")
    private  String appid;
    @Value("${wx.secret}")
    private  String secret;

    @Override
    public Result getQrImage(String scene, String page) {

        //获取小程序access_token
        String accessToken=getAccessToken();
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
                data = swapStream.toByteArray();
                result = new String(Base64.getEncoder().encode(data));
                result = "data:image/jpeg;base64," + result;

                return Result.successData(result);
            } catch (Exception e) {

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
//                System.out.println(stream.toString("UTF-8"));

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
