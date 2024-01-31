package com.beizhi.service;

import com.beizhi.common.dto.CodeDto;
import com.beizhi.common.dto.user.UserInfoDto;
import com.beizhi.common.dto.user.UserLoginDto;
import com.beizhi.common.result.Result;
import com.beizhi.entity.User;

public interface LoginService {
    Result getOpenId(CodeDto codeDto);

    Result updateUser(UserInfoDto userInfoDto);

    Result login(UserLoginDto userLoginDto);

    Result Wxlogin(String phone, String password);
}
