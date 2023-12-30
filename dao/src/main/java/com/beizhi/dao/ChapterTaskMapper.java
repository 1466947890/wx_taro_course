package com.beizhi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beizhi.entity.ChapterTask;
import com.beizhi.entity.Examine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChapterTaskMapper extends BaseMapper<ChapterTask> {

    @Select("select c.flag  from c_video c where c.chapter_id = #{chapterId}")
    String selectChapterVideoFlag(Integer chapterId);

    @Select("select e.title, e.e_options, e.`type` from c_examine e where e.chapter_id = #{chapteId}")
    List<Examine> selectChapterExamineList(Integer chapterId);
}
