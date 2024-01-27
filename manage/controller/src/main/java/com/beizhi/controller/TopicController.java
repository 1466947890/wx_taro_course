package com.beizhi.controller;

import com.beizhi.common.dto.topic.TopicDto;
import com.beizhi.common.result.Result;
import com.beizhi.service.TopicService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Resource
    private TopicService topicService;
    /*
        上传题目
        删除题库和所属的题目
        更新题目
        查询题库
     */

    /**
     * 上传题目
     * @param topicDto
     * @return
     */
    @PostMapping
    public Result uploadTopic(@RequestBody TopicDto topicDto){

        return topicService.saveTopic(topicDto);
    }
}
