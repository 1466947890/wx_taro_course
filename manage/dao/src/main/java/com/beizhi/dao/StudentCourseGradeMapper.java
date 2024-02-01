package com.beizhi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beizhi.entity.StudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentCourseGradeMapper extends BaseMapper<StudentCourse> {
    @Select("select sc.course_id from c_student_course sc join c_student s on sc.student_id = s.id join c_user u on u.id = #{userId} group by sc.course_id")
    List<StudentCourse> selectCourseByUserId(Integer userId);
}
