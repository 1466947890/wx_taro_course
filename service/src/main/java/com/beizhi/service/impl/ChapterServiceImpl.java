package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.result.Result;
import com.beizhi.common.result.ResultEnum;
import com.beizhi.dao.ChapterMapper;
import com.beizhi.entity.Chapter;
import com.beizhi.service.ChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;
    @Override
    public Result saveChapterByCourseId(Chapter chapter) {
//        从当前课程中查找相同名字的章节
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, chapter.getCourseId());
        chapterLambdaQueryWrapper.eq(Chapter::getName, chapter.getName());
        List<Chapter> chapterList = chapterMapper.selectList(chapterLambdaQueryWrapper);
        if(!chapterList.isEmpty()){
            throw new BusinessException(BaseErrorEnum.CHAPTER_EXIST);
        }
        chapterMapper.insert(chapter);
        return Result.response(ResultEnum.SAVE_SUCCESS);
    }

    @Override
    public Result getChapterListByCourseId(Integer courseId) {
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, courseId);
        chapterLambdaQueryWrapper.orderByAsc(Chapter::getId);
        return Result.successData(chapterMapper.selectList(chapterLambdaQueryWrapper));
    }

    @Override
    public Result deleteChapterByCourseId(Integer courseId) {
        chapterMapper.deleteById(courseId);
        return Result.response(ResultEnum.DELETE_SUCCESS);
    }
}
