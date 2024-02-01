package com.beizhi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beizhi.common.Constants;
import com.beizhi.common.dto.WxLoginDto;
import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.common.utils.RedisCache;
import com.beizhi.common.utils.RequestUtils;
import com.beizhi.common.utils.WxUtils;
import com.beizhi.dao.UserMapper;
import com.beizhi.entity.LoginUser;
import com.beizhi.entity.User;
import com.beizhi.service.LoginService;
import com.beizhi.service.WxLoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 14669
 * @date 2024/1/31 22:52
 * @describe
 */
@Service
public class WxLoginServiceImpl implements WxLoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginService loginService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private WxUtils wxUtils;

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Override
    public Result login(WxLoginDto wxLoginDto) {
        JSONObject userOpenId = getUserOpenId(wxLoginDto.getCode());
        String phoneNumber = getPhoneNumber(wxLoginDto.getPhoneCode());
//        查询是否存在用户的openId
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenId, userOpenId.getString(Constants.openid));
        User userdata = userMapper.selectOne(queryWrapper);
        // 没有则插入，有则返回除了密匙以外的信息即可
        User user = new User();
        user.setSessionKey(userOpenId.getString(Constants.sessionKey));
        if (Objects.isNull(userdata)) {
            user.setOpenId(userOpenId.getString(Constants.openid));
            user.setPhone(phoneNumber);
            userMapper.insert(user);
        } else {
            userMapper.update(user, queryWrapper);
            user.setId(userdata.getId());
        }
        user.setRole("ROLE_USER");

        LoginUser loginUser = new LoginUser(user);
        String token = JwtUtils.sign(user);
        redisCache.setCacheObject("login:" + user.getId(), loginUser);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        data.put("role", user.getRole());
        return Result.successData("登录成功", data);

    }

    private JSONObject getUserOpenId(String code){
        String urlStr = Constants.LOGIN_URL + "?js_code=" + code;
        String params = "appid=" + appid + "&" + "secret=" + secret;
        String result = RequestUtils.RequestPost(urlStr, params).getData();
        return JSONObject.parseObject(result);
    }

    private String getPhoneNumber(String code){
        String PhoneUrlStr = Constants.PHONE_URL + "?access_token=" + wxUtils.getAccessToken();
        Map<String, String> jsonParam = new HashMap<>();
        jsonParam.put("code",code);
        String PhoneResult = RequestUtils.postJson(PhoneUrlStr, jsonParam).getData();
        JSONObject resultJSON = JSONObject.parseObject(PhoneResult);
        return resultJSON.getJSONObject("phone_info").getString("phoneNumber");
    }
//        login(u)




}
