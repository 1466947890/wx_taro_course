package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.dao.UserMapper;
import com.beizhi.entity.LoginUser;
import com.beizhi.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 14669
 * @date 2023/12/6 21:44
 * @describe
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> userLambdaQueryWrapper =
                new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone, phone);
        User user = userMapper.selectOne(userLambdaQueryWrapper);

        if(Objects.isNull(user)){
            throw new BusinessException(BaseErrorEnum.USER_PASSWORD_ERROR);
        }

        return new LoginUser(user);
    }
}
