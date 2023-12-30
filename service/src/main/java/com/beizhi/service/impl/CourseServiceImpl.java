package com.beizhi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.dto.CourseDto;
import com.beizhi.common.result.Result;
import com.beizhi.common.result.ResultEnum;
import com.beizhi.dao.*;
import com.beizhi.entity.*;
import com.beizhi.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private StudentMapper studentMapper;
    // 课程老师关系表
    @Resource
    private ProfessorMapper professorMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentCourseGradeMapper studentCourseGradeMapper;
    @Override
    @Transactional
    public Result saveCourse(CourseDto courseDto, Integer userId) {
        // 保存课程
        Course course = new Course();
        course.setName(courseDto.getCourseName());
        course.setImage(courseDto.getCourseImage());
        course.setCredit(Integer.valueOf(courseDto.getCourseCredit()));
        courseMapper.insert(course);
        // 加入老师, 这个教师是否存在
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getUserid, userId);
        Teacher teacher =teacherMapper.selectOne(teacherLambdaQueryWrapper);
        if(Objects.isNull(teacher)){
            teacher = new Teacher();
            teacher.setUserid(userId);
            teacherMapper.insert(teacher);
        }


//        System.out.println(course.getCourseId());
//        先保存课程，在保存课程老师关系表

        // 绑定课程和老师的关系

        TeacherCourse professor = new TeacherCourse();
        BeanUtils.copyProperties(courseDto, professor);
        log.info(String.format("课程ID为【%s】", course.getId()));
        professor.setCourseId(course.getId());
        professor.setTeacherId(teacher.getId());
        professorMapper.insert(professor);
//        professor.set
        return Result.success("添加课程成功");
    }

    @Override
    public Result getCourseListByType(Integer type, Integer userId) {
        List<Integer> courseIdList = new ArrayList<>();
        switch (type){

            case 0:
//                我学的课
                List<StudentCourse> studentCourseGrades = studentCourseGradeMapper.selectCourseByUserId(userId);
                courseIdList =  studentCourseGrades.stream().map(StudentCourse::getCourseId).collect(Collectors.toList());
                break;
            case 1:
//                我教的课

                List<TeacherCourse> professorList = professorMapper.selectTeacherCourseByUserId(userId);
                courseIdList =  professorList.stream().map(TeacherCourse::getCourseId).collect(Collectors.toList());
                break;
            default:
        }

        List<Course> courseList = new ArrayList<>();
        for(Integer courseId : courseIdList){
            Course course = courseMapper.selectById(courseId);
            StringBuilder teacherStr = new StringBuilder();
            // 查询这个课的所有老师
            LambdaQueryWrapper<TeacherCourse> professorLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            professorLambdaQueryWrapper1.eq(TeacherCourse::getCourseId, courseId);
            List<TeacherCourse> professors = professorMapper.selectList(professorLambdaQueryWrapper1);
            List<Integer> teacherIdList = professors.stream().map(TeacherCourse::getTeacherId).collect(Collectors.toList());
            List<User> teacherList = new ArrayList<>();
            for(Integer teacherId : teacherIdList){
                User teacher = userMapper.selectById(teacherId);
                teacherList.add(teacher);
            }
            for(User teacher : teacherList){
                teacherStr.append(teacher.getName()).append("、");
            }
            course.setTeacherList(teacherStr.substring(0, teacherStr.length() - 1));
            courseList.add(course);
        }
        return Result.successData("获取成功", courseList);
    }

    @Override
    @Transactional
    public Result saveCourseStudent(StudentCourse courseGrade, Integer userId) {
//        判断课程存不存在
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseLambdaQueryWrapper.eq(Course::getId, courseGrade.getCourseId());
        Course course = courseMapper.selectOne(courseLambdaQueryWrapper);
        if(Objects.isNull(course)){
            throw new BusinessException(BaseErrorEnum.COURSE_NOT_EXIST);
        }

        // 查询用户的学号
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getUserid, userId);
        Student student = studentMapper.selectOne(studentLambdaQueryWrapper);
        if(Objects.isNull(student)){
            student = new Student();
            student.setUserid(userId);
            studentMapper.insert(student);
        }
//        判断学生是否已经在课程中
        LambdaQueryWrapper<StudentCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentCourse::getCourseId, courseGrade.getCourseId());
        queryWrapper.eq(StudentCourse::getStudentId, student.getId());
        List<StudentCourse> studentCourseGradeList = studentCourseGradeMapper.selectList(queryWrapper);
        if(!studentCourseGradeList.isEmpty()){
            throw new BusinessException(BaseErrorEnum.STUDENT_EXIST);
        }
//        先插入学生表
        courseGrade.setStudentId(student.getId());

//        插入
        studentCourseGradeMapper.insert(courseGrade);
        return Result.response(ResultEnum.JOIN_COURSE_SUCCESS);
    }
}
