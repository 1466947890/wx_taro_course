package com.beizhi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beizhi.common.dto.user.UserInfoDto;
import com.beizhi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.id,u.name ,u.nickname, u.avatar ,u.open_id ,u.phone ,u.sex ,u.register_time,u.is_real , m.name as major  from c_user u join c_major m on u.major_id  = m.id where u.id = #{userid}")
    UserInfoDto getUserInfo(Integer userid);

    Page<User> getPage(Page<User> userPage, String name, Integer isReal);

    @Update("update c_user u set u.is_real = #{isReal} where u.id = #{userid}")
    Integer updateIsRealByUserid(Integer userid, Integer isReal);
}
