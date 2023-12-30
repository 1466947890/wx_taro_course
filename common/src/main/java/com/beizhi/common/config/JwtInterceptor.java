package com.beizhi.common.config;

import com.alibaba.fastjson.JSONObject;
import com.beizhi.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info(String.format("访问的域名为【%s】", request.getRequestURL()));
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String token = request.getHeader("token");
        if(token != null){
            boolean result = JwtUtils.verify(token);
            if(result){
                return true;
            }
        }

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "token认证失败");
            log.info("token：" + token + " 未通过认证");
            response.getWriter().append(jsonObject.toJSONString());
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
