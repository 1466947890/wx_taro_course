package com.beizhi.common.dto.topic;

import lombok.Data;

import java.util.List;

@Data
public class TopicListDto {
    private String topicTitle;
    private List<Option> option;
    private String correct;
    private Integer type;
}


