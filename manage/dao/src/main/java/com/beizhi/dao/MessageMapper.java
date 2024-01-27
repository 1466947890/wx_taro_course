package com.beizhi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beizhi.common.dto.message.MessageDto;
import com.beizhi.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    @Select("select m.id, u.name, m.value as message, m.m_time as time, m.pid from c_message m join c_user u ON m.userid = u.id where m.chapter_id = #{chapterId}")
    List<MessageDto> selectMessageByChapterId(Integer chapterId);
}
