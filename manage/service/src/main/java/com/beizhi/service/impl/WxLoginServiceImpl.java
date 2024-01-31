package com.beizhi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beizhi.common.Constants;
import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.common.utils.RequestUtils;
import com.beizhi.common.utils.WxUtils;
import com.beizhi.dao.UserMapper;
import com.beizhi.entity.User;
import com.beizhi.service.LoginService;
import com.beizhi.service.WxLoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private WxUtils wxUtils;

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Override
    public Result login(String code, String phoneCode) {
        // 获取手机号
//        String urlStr  =




        String phone = "";


        String urlStr = Constants.LOGIN_URL + "?js_code=" + code;
        String params = "appid=" + appid + "&" + "secret=" + secret;
        String result = RequestUtils.RequestPost(urlStr, params).getData();
        JSONObject userOpenId = JSONObject.parseObject(result);

        String PhoneUrlStr = Constants.PHONE_URL + "?access_token=" + wxUtils;
        String PhoneParams = "code=" + phoneCode;
        String PhoneResult = RequestUtils.RequestPost(PhoneUrlStr, PhoneParams).getData();


//        查询是否存在用户的openId
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenId, userOpenId.getString(Constants.openid));
        User userdata = userMapper.selectOne(queryWrapper);
        // 没有则插入，有则返回除了密匙以外的信息即可
        User user = new User();
        user.setSessionKey(userOpenId.getString(Constants.sessionKey));
        if(Objects.isNull(userdata)){
            user.setOpenId(userOpenId.getString(Constants.openid));
            user.setPhone(phone);
            user.setPassword("123456");
            userMapper.insert(user);
            return loginService.Wxlogin(phone, "123456");
        }else{
            userMapper.update(user, queryWrapper);
            user.setId(userdata.getId());
            return loginService.Wxlogin(userdata.getPhone(), userdata.getPassword());
        }

//        login(u)




        /**
         * 返回userid和token信息，最后再请求用户信息
         */
//        Map<String, String> data = new HashMap<>();
////        log.info(String.format("当前登录的用户为【%s】", user.getId()));
//        data.put("token", JwtUtils.sign(user));
//        return Result.successData(Constants.LOGIN_SUCCESS, data);
    }
}
