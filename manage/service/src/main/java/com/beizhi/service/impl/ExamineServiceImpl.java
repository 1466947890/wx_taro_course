package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.result.Result;
import com.beizhi.dao.ExamineMapper;
import com.beizhi.entity.Examine;
import com.beizhi.service.ExamineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author 14669
 * @date 2023/12/13 17:43
 * @describe
 */
@Service
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine> implements ExamineService {
    @Resource
    private ExamineMapper examineMapper;
    @Override
    public Result gradeByChapterId(List<Examine> examineList, Integer chapterId) {
        // 获取当前章节测验数量
        LambdaQueryWrapper<Examine> examineLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examineLambdaQueryWrapper.eq(Examine::getChapterId, chapterId);
        // 正确答案
        List<Examine> examines = examineMapper.selectList(examineLambdaQueryWrapper);
        // 记录答案正确的个数
        float correct = 0;
        for(Examine examine : examineList){
            for(Examine examineTrue : examines){
                if(examine.getTitle().equals(examineTrue.getTitle())){
                    if(examine.getAnswer().equals(examineTrue.getAnswer())){
                        correct++;
                    }
                }
            }
        }
        BigDecimal grade = BigDecimal.valueOf(correct / examines.size());
        // 最终分数
        grade = grade.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);



        return Result.successData("提交成功", grade);
    }
}
