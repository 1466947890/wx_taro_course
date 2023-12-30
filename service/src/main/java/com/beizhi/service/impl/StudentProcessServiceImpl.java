package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.result.Result;
import com.beizhi.dao.StudentProcessMapper;
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
    @Override
    public Result recordVideoProcess(Integer chapterId, Integer duration, Integer current, Integer userid) {

        BigDecimal process = BigDecimal.valueOf(current / duration).setScale(0, RoundingMode.HALF_UP);
        if(process.compareTo(new BigDecimal(1)) > 0) {
            return Result.success("视频任务已完成");
        }

        LambdaQueryWrapper<StudentProcess> studentProcessLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentProcessLambdaQueryWrapper.eq(StudentProcess::getUserid, userid);
        studentProcessLambdaQueryWrapper.eq(StudentProcess::getChapterId, chapterId);

        // 先判断数据是否存在，不存在则创建
        StudentProcess studentProcess = studentProcessMapper.selectOne(studentProcessLambdaQueryWrapper);
        if(Objects.isNull(studentProcess)){
            studentProcess = new StudentProcess();
            studentProcess.setUserid(userid);
            studentProcess.setChapterId(chapterId);
            studentProcessMapper.insert(studentProcess);
        }else{
            if(studentProcess.getVideoProcess() >= 1){
                return Result.success("视频任务已完成");
            }
            studentProcess.setVideoProcess(process.intValue());
            if(studentProcessMapper.update(studentProcess, studentProcessLambdaQueryWrapper)> 0){
                return Result.success("记录进度成功");
            }else{
                throw new BusinessException(BaseErrorEnum.OPERATION_SQL_ERROR);
            }
        }
        return Result.success("记录进度成功");

    }
}
