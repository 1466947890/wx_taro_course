package com.beizhi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beizhi.entity.TeacherCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProfessorMapper extends BaseMapper<TeacherCourse> {

    @Select("select tc.* from c_course c join c_teacher_course tc on c.id = tc.course_id join c_teacher t on t.id = tc.teacher_id where t.userid = #{userId}")
    List<TeacherCourse> selectTeacherCourseByUserId(Integer userId);
}
