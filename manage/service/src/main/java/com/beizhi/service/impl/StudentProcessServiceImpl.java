package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.result.Result;
import com.beizhi.dao.StudentMapper;
import com.beizhi.dao.StudentProcessMapper;
import com.beizhi.entity.Student;
import com.beizhi.entity.StudentProcess;
import com.beizhi.service.StudentProcessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author 14669
 * @date 2023/12/13 19:27
 * @describe
 */
@Service
public class StudentProcessServiceImpl extends ServiceImpl<StudentProcessMapper, StudentProcess> implements StudentProcessService {
    @Resource
    private StudentProcessMapper studentProcessMapper;
    @Resource
    private StudentMapper studentMapper;


    @Override
    public Result recordVideoProcess(Integer chapterId, Integer duration, Integer current, Integer userid) {
        // 查询用户对应的学生ID
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getUserid, userid);
        Student student = studentMapper.selectOne(studentLambdaQueryWrapper);
        Integer studentId = student.getId();

        BigDecimal process = BigDecimal.valueOf(current / duration).setScale(0, RoundingMode.HALF_UP);
        LambdaQueryWrapper<StudentProcess> studentProcessLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentProcessLambdaQueryWrapper.eq(StudentProcess::getStudentId, studentId);
        studentProcessLambdaQueryWrapper.eq(StudentProcess::getChapterId, chapterId);
        StudentProcess studentProcess = studentProcessMapper.selectOne(studentProcessLambdaQueryWrapper);
        if(process.compareTo(new BigDecimal(1)) >= 0) {
            studentProcess = new StudentProcess();
            studentProcess.setStudentId(studentId);
            studentProcess.setChapterId(chapterId);
            studentProcess.setVideoProcess(new BigDecimal(100));
            if(Objects.isNull(studentProcess)){
                studentProcessMapper.insert(studentProcess);
            }else{
                studentProcessMapper.update(studentProcess, studentProcessLambdaQueryWrapper);
            }

            return Result.success("视频任务已完成");
        }

//        StudentProcess studentProcess = studentProcessMapper.selectOne(studentProcessLambdaQueryWrapper);
        if(Objects.isNull(studentProcess)){
            studentProcess = new StudentProcess();
            studentProcess.setStudentId(studentId);
            studentProcess.setChapterId(chapterId);
            studentProcessMapper.insert(studentProcess);
        }else{
            if( studentProcess.getVideoProcess().compareTo(new BigDecimal(100)) == 0 || studentProcess.getVideoProcess().compareTo(new BigDecimal(100)) > 0){
                return Result.success("视频任务已完成");
            }
            studentProcess.setVideoProcess(process.multiply(new BigDecimal(100)));
            if(studentProcessMapper.update(studentProcess, studentProcessLambdaQueryWrapper)> 0){
                return Result.success("记录进度成功");
            }else{
                throw new BusinessException(BaseErrorEnum.OPERATION_SQL_ERROR);
            }
        }
        return Result.success("记录进度成功");

    }
}
