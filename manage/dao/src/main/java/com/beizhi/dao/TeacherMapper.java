package com.beizhi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beizhi.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    @Select("select t.avatar, t.information , t.experience  from c_teacher_course tc join c_teacher t on t.id = tc.teacher_id where tc.course_id = #{courseId}")
    List<Teacher> selectTeacherListByCourseId(Integer courseId);
}
