package com.beizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.dto.message.MessageDto;
import com.beizhi.common.result.Result;
import com.beizhi.dao.MessageMapper;
import com.beizhi.entity.Message;
import com.beizhi.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Resource
    private MessageMapper messageMapper;
    @Override
    public Result saveMessage(Message message, Integer userId) {
        message.setUserid(userId);
        messageMapper.insert(message);
        return Result.success("留言成功");
    }

    @Override
    public Result getMessage(Integer chapterId) {
        List<MessageDto> messageDtoList = messageMapper.selectMessageByChapterId(chapterId);
        List<MessageDto> parent = new ArrayList<>();
        for(MessageDto messageDto : messageDtoList){
            if(Objects.isNull(messageDto.getPid())){
                parent.add(messageDto);
            }
        }

//        for(MessageDto messageDto : messageDtoList){
//            if(messageDto.getPid() == )
//        }
        for(MessageDto p : parent){
            List<MessageDto> children = new ArrayList<>();
            for(MessageDto c : messageDtoList){
                if(Objects.equals(c.getPid(), p.getId())){
                    children.add(c);
                }
                p.setChildren((children));
            }
        }

//        Collections.copy(messageDtoList, messageDtoListCopy);
//        System.out.println(messageDtoListCopy);
//        for(MessageDto messageDto : messageDtoList){
//            if(messageDto.getPid() == me){
//
//            }
//        }
//        先获取父级评价
        return Result.successData(parent);
    }
}
