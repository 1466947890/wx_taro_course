package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.User;

/**
 * @author 14669
 */
public interface UserService extends IService<User> {
    Result getUserInfo(Integer userid);

    Result selectUserList(Integer pageNum, Integer pageSize, String name, Integer isReal);

    Result updateIsReal(Integer userid, Integer isReal);

    Result updateUser(User user);

    Result deleteUserByUserid(Integer userid);

    Result majorRole();
}
