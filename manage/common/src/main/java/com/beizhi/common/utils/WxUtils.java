package com.beizhi.common.utils;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 14669
 * @date 2024/1/31 23:34
 * @describe
 */
@Component
public class WxUtils {
    @Resource
    private RedisCache redisCache;
    @Value("${wx.appid}")
    private  String appid;
    @Value("${wx.secret}")
    private  String secret;
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
    }
}
