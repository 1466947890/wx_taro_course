package com.beizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.result.Result;
import com.beizhi.dao.NotesMapper;
import com.beizhi.entity.Notes;
import com.beizhi.service.NotesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 14669
 * @date 2023/12/15 15:26
 * @describe
 */
@Service
public class NotesServiceImpl extends ServiceImpl<NotesMapper, Notes> implements NotesService {
    @Resource
    private NotesMapper notesMapper;
    @Override
    public Result saveNotes(Notes notes) {
        LambdaQueryWrapper<Notes> notesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        notesLambdaQueryWrapper.eq(Notes::getUserid, notes.getUserid());
        notesLambdaQueryWrapper.eq(Notes::getChapterId, notes.getChapterId());
        Notes notesData = notesMapper.selectOne(notesLambdaQueryWrapper);
        if(Objects.isNull(notesData)){
            notesMapper.insert(notes);
        }else{
            notesMapper.update(notes, notesLambdaQueryWrapper);
        }
        return Result.success("保存笔记成功");
    }

    @Override
    public Result getNotes(Integer chapterId, Integer userId) {
        LambdaQueryWrapper<Notes> notesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        notesLambdaQueryWrapper.eq(Notes::getUserid, userId);
        notesLambdaQueryWrapper.eq(Notes::getChapterId, chapterId);
        Notes notesData = notesMapper.selectOne(notesLambdaQueryWrapper);
        if(Objects.isNull(notesData)){
            notesData = new Notes();
            return Result.successData("获取成功", notesData);
        }
        return Result.successData(notesData);
    }
}
