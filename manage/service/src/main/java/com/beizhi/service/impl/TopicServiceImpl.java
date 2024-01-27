package com.beizhi.service.impl;

import com.beizhi.common.dto.topic.Option;
import com.beizhi.common.dto.topic.TopicDto;
import com.beizhi.common.dto.topic.TopicListDto;
import com.beizhi.common.result.Result;
import com.beizhi.dao.BankMapper;
import com.beizhi.dao.ExamineMapper;
import com.beizhi.entity.Examine;
import com.beizhi.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    private BankMapper bankMapper;

    @Resource
    private ExamineMapper examineMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveTopic(TopicDto topicDto) {

        Examine examine = new Examine();
        for(TopicListDto topic : topicDto.getTopic()){
            examine.setTitle(topic.getTopicTitle());
//            examine.setBankId(bank.getBankId());
            examine.setChapterId(topicDto.getChapterId());
            examine.setType(topic.getType());
            examine.setAnswer(topic.getCorrect());
//            解析选项 存入 选项1#选项2形式
//            System.out.println();
            examine.setEOptions(parseOption(topic.getOption()));
            examineMapper.insert(examine);
        }

        return Result.successData("添加题库成功");
    }

    private String parseOption(List<Option> options) {
        StringBuilder optionStr = new StringBuilder();
        for(Option option : options){
            optionStr.append(option.getValue()).append("#");
        }
        return optionStr.substring(0, optionStr.length() - 1);
    }
}
