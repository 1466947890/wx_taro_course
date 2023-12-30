package com.beizhi.common.dto.topic;


import lombok.Data;

import java.util.List;

@Data
public class TopicDto {
    private Integer chapterId;
    private String title;
    private List<TopicListDto> topic;
}
