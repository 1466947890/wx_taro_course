package com.beizhi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beizhi.entity.TeacherCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProfessorMapper extends BaseMapper<TeacherCourse> {

    @Select("select tc.* from c_teacher_course tc join c_teacher t on tc.teacher_id = t.id join c_user u on u.id = #{userId}")
    List<TeacherCourse> selectTeacherCourseByUserId(Integer userId);
}
