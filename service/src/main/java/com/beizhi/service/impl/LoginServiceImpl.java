package com.beizhi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beizhi.common.Constants;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.dto.CodeDto;
import com.beizhi.common.dto.user.UserInfoDto;
import com.beizhi.common.dto.user.UserLoginDto;
import com.beizhi.common.result.Result;
import com.beizhi.common.result.ResultEnum;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.common.utils.RedisCache;
import com.beizhi.common.utils.RequestUtils;
import com.beizhi.dao.MajorMapper;
import com.beizhi.dao.UserMapper;
import com.beizhi.entity.*;
import com.beizhi.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Resource
    private RedisCache redisCache;

    @Resource
    private UserMapper userMapper;
    @Resource
    private MajorMapper majorMapper;

    @Resource
    private AuthenticationManager authenticationManager;
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    @Override
    public Result getOpenId(CodeDto codeDto) {
        String urlStr = LOGIN_URL + "?js_code=" + codeDto.getCode();
        String params = "appid=" + appid + "&" + "secret=" + secret;
        String result = RequestUtils.RequestPost(urlStr, params).getData();
        JSONObject userOpenId = JSONObject.parseObject(result);

//        查询是否存在用户的openId
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenId, userOpenId.getString(Constants.openid));
        User userdata = userMapper.selectOne(queryWrapper);
        // 没有则插入，有则返回除了密匙以外的信息即可
        User user = new User();
        user.setSessionKey(userOpenId.getString(Constants.sessionKey));
        if(!(codeDto.getAvatar() ==null) &&!codeDto.getAvatar().isEmpty() ){
            user.setAvatar(codeDto.getAvatar());
        }
        if(!(codeDto.getNickName() ==null) &&!codeDto.getNickName().isEmpty()){
            user.setNickname(codeDto.getNickName());
        }

        if(Objects.isNull(userdata)){
            user.setOpenId(userOpenId.getString(Constants.openid));
            userMapper.insert(user);
        }else{
            userMapper.update(user, queryWrapper);
            user.setId(userdata.getId());
        }

        /**
         * 返回userid和token信息，最后再请求用户信息
         */
        Map<String, String> data = new HashMap<>();
        log.info(String.format("当前登录的用户为【%s】", user.getId()));
        data.put("token", JwtUtils.sign(user));
        return Result.successData(Constants.LOGIN_SUCCESS, data);
    }

    @Override
    public Result updateUser(UserInfoDto userInfoDto) {
        if(Objects.isNull(userInfoDto.getId())){
            throw new BusinessException(BaseErrorEnum.USER_NOT_EXIST);
        }
//        判断有没有这个专业
        LambdaQueryWrapper<Major> majorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        majorLambdaQueryWrapper.eq(Major::getName, userInfoDto.getMajor());
        Major major = majorMapper.selectOne(majorLambdaQueryWrapper);
        if(Objects.isNull(major)){
            major = new Major();
            major.setName(userInfoDto.getMajor());
            majorMapper.insert(major);
        }

        User user = new User();
        BeanUtils.copyProperties(userInfoDto, user);

        user.setIsReal(Constants.inReview);
        user.setMajorId(major.getId());
        if(userMapper.updateById(user) == 0){
            throw new BusinessException(BaseErrorEnum.OPERATION_SQL_ERROR);
        }
        return Result.response(ResultEnum.UPDATE_SUCCESS);
    }

    /**
     * 网页端登录网站获取信息
     * @param user
     * @return
     */
    @Override
    public Result login(UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getPhone(), userLoginDto.getPassword());
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new BusinessException(BaseErrorEnum.USER_PASSWORD_ERROR);
        }


        if(Objects.isNull(authenticate)){
            throw new BusinessException(BaseErrorEnum.USER_PASSWORD_ERROR);
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user = loginUser.getUser();
        String token = JwtUtils.sign(user);
        redisCache.setCacheObject("login:" + user.getId(), loginUser);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        data.put("role", user.getRole());

        return Result.successData("登录成功", data);
    }

}
