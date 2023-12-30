package com.beizhi.service;

import com.beizhi.common.dto.topic.TopicDto;
import com.beizhi.common.result.Result;

public interface TopicService {
    Result saveTopic(TopicDto topicDto);
}
