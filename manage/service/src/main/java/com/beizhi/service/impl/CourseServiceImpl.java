package com.beizhi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.Constants;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.dto.CourseDto;
import com.beizhi.common.dto.GradeDto;
import com.beizhi.common.dto.WxIndexDto;
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
import java.math.BigDecimal;
import java.util.*;
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
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private StudentProcessMapper studentProcessMapper;
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

    @Override
    public Result selectPage(Integer pageNum, Integer pageSize, String name) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        if(!name.isEmpty()){
            queryWrapper.like(Course::getName, name);
        }
        IPage<Course> iPage = new Page<>(pageNum, pageSize);

        return Result.successData(courseMapper.selectPage(iPage, queryWrapper));
    }

    /**
     * 查询学生课程成绩
     * @param courseId
     * @return
     */
    @Override
    public Result getGrade(Integer courseId) {
        List<GradeDto> gradeDtoList = studentCourseGradeMapper.selectCourseGrade(courseId);
        return Result.successData(gradeDtoList);
    }

    /**
     * 计算一个课程的成绩
     * @param courseId
     * @return
     */
    @Override
    public Result calculate(Integer courseId) {
        // 获取所有属于这个课程的章节列表
        List<Integer> chapterIdList = courseChapters(courseId);
        // 获取这个课程的所有学生的用户ID
        List<Integer> studentIdList = courseStudents(courseId);
        // 计算单个学生的这个课程的成绩，然后写入
        for(Integer studentId : studentIdList){
            BigDecimal total = new BigDecimal(0);
            for(Integer chapterId : chapterIdList){
                LambdaQueryWrapper<StudentProcess> processLambdaQueryWrapper = new LambdaQueryWrapper<>();
                processLambdaQueryWrapper.eq(StudentProcess::getStudentId, studentId);
                processLambdaQueryWrapper.eq(StudentProcess::getChapterId, chapterId);
                StudentProcess studentProcess = studentProcessMapper.selectOne(processLambdaQueryWrapper);
                if(Objects.nonNull(studentProcess)){
                    BigDecimal chapterGrade = studentProcess.getVideoProcess().add(studentProcess.getExamineGrade());
                    total = total.add(chapterGrade);
                }
            }
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setCourseId(courseId);
            studentCourse.setStudentId(studentId);
            // 每个章节有视频进度和测验分数，满分均为100
            studentCourse.setGrade(total.divide(new BigDecimal(chapterIdList.size() * 200)).multiply(new BigDecimal(100)));
            // 判断成绩是否存在，存在则更新，不存在则添加
            LambdaQueryWrapper<StudentCourse> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
            StudentCourse course = studentCourseGradeMapper.selectOne(courseLambdaQueryWrapper);
            if(Objects.isNull(course)){
                studentCourseGradeMapper.insert(studentCourse);
            }else{
                studentCourseGradeMapper.update(studentCourse, courseLambdaQueryWrapper);
            }
        }

        return Result.success(Constants.OPERATION_SUCCESS);
    }

    /**
     * 获取一个课程的单个学生的学习进度
     * @param courseId 课程ID
     * @param studentId 学生ID
     * @return
     */
    @Override
    public Result studentCourseProcess(Integer courseId, Integer studentId) {
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, courseId);
        List<Chapter> chapterList = chapterMapper.selectList(chapterLambdaQueryWrapper);
        List<Map<String, String>>  process = new ArrayList<>();
        for(Chapter chapter : chapterList){
            LambdaQueryWrapper<StudentProcess> studentProcessLambdaQueryWrapper = new LambdaQueryWrapper<>();
            studentProcessLambdaQueryWrapper.eq(StudentProcess::getStudentId, studentId);
            studentProcessLambdaQueryWrapper.eq(StudentProcess::getChapterId, chapter.getId());
            StudentProcess studentProcess = studentProcessMapper.selectOne(studentProcessLambdaQueryWrapper);
            Map<String, String> map = getStringStringMap(chapter, studentProcess);
            process.add(map);
        }
//        System.out.println(process);
        return Result.successData(process);
    }

    private static Map<String, String> getStringStringMap(Chapter chapter, StudentProcess studentProcess) {
        Map<String, String> map = new HashMap<>();
        if(Objects.isNull(studentProcess)){
            map.put("videoProcess", "0");
            map.put("examineGrade", "0");
            map.put("chapterName", chapter.getName());
        }else{
            map.put("videoProcess", studentProcess.getVideoProcess().toString());
            map.put("examineGrade", studentProcess.getExamineGrade().toString());
            map.put("chapterName", chapter.getName());
        }
        return map;
    }

    @Override
    public Result wxIndexData(Integer userid) {
//        先查询用户所属专业
        User user = userMapper.selectById(userid);
        if(Objects.isNull(user.getMajorId())){
            // TODO 返回所有课程

        }

        WxIndexDto wxIndexDto = new WxIndexDto();

        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseLambdaQueryWrapper.eq(Course::getMajorId, user.getMajorId());
        wxIndexDto.setMyCourse(courseMapper.selectList(courseLambdaQueryWrapper));

        LambdaQueryWrapper<Course> unCourseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        unCourseLambdaQueryWrapper.ne(Course::getMajorId, user.getMajorId());
        wxIndexDto.setOtherCourse(courseMapper.selectList(unCourseLambdaQueryWrapper));

        return Result.successData(wxIndexDto);
    }

    @Override
    public Result getUserCourseGrade(Integer courseId, Integer userid) {
//       先获取用户学生ID
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getUserid, userid);
        Student student = studentMapper.selectOne(studentLambdaQueryWrapper);

//        直接查询课程信息
        LambdaQueryWrapper<StudentCourse> studentCourseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentCourseLambdaQueryWrapper.eq(StudentCourse::getCourseId, courseId);
        studentCourseLambdaQueryWrapper.eq(StudentCourse::getStudentId, student.getId());
        StudentCourse studentCourse = studentCourseGradeMapper.selectOne(studentCourseLambdaQueryWrapper);
        return Result.successData(studentCourse.getGrade());
    }

    @Override
    public Result getGradeByMajorId(Integer majorId) {
        List<GradeDto> gradeDtoList = studentCourseGradeMapper.selectMajorGrade(majorId);
        return Result.successData(gradeDtoList);
    }

    /**
     * 通过课程ID获取一个课程下的所有章节ID数组
     * @param courseId
     * @return
     */
    private List<Integer> courseChapters(Integer courseId){
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, courseId);
        List<Chapter> chapterList = chapterMapper.selectList(chapterLambdaQueryWrapper);
        return chapterList.stream().map(Chapter::getId).collect(Collectors.toList());
    }

    /**
     * 通过课程ID获取这个课程下的所有学生ID数组
     * @param courseId
     * @return
     */
    private List<Integer> courseStudents(Integer courseId){
        LambdaQueryWrapper<StudentCourse> studentCourseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentCourseLambdaQueryWrapper.eq(StudentCourse::getCourseId, courseId);
        List<StudentCourse> studentCourses = studentCourseGradeMapper.selectList(studentCourseLambdaQueryWrapper);
        return studentCourses.stream().map(StudentCourse::getStudentId).collect(Collectors.toList());
    }
}
