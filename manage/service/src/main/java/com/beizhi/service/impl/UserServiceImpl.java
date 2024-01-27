package com.beizhi.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.dto.user.UserInfoDto;
import com.beizhi.common.result.Result;
import com.beizhi.dao.MajorMapper;
import com.beizhi.dao.RoleMapper;
import com.beizhi.dao.UserMapper;
import com.beizhi.entity.User;
import com.beizhi.service.MajorService;
import com.beizhi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 1466947890
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result getUserInfo(Integer userid) {
        UserInfoDto userInfoDto =  userMapper.getUserInfo(userid);
        return Result.successData(userInfoDto);
    }

    @Override
    public Result selectUserList(Integer pageNum, Integer pageSize, String name, Integer isReal) {
        Page<User> userPage = new Page<User>(pageNum, pageSize);

        return Result.successData(userMapper.getPage(userPage, name, isReal));
    }

    @Override
    public Result updateIsReal(Integer userid, Integer isReal) {
        if(userMapper.updateIsRealByUserid(userid, isReal) == 0){
            throw new BusinessException(BaseErrorEnum.SYSTEM_ERROR);
        }
        return Result.success("更新成功");
    }

    @Override
    public Result updateUser(User user) {
        userMapper.updateById(user);
        return Result.success("操作成功");
    }

    @Override
    public Result deleteUserByUserid(Integer userid) {
        userMapper.deleteById(userid);
        return Result.success("操作成功");
    }

    @Override
    public Result majorRole() {
        Map<String, Object> majorRole = new HashMap<>();
        majorRole.put("majors", majorMapper.selectList(null));
        majorRole.put("roles", roleMapper.selectList(null));

        return Result.successData(majorRole);
    }
}
