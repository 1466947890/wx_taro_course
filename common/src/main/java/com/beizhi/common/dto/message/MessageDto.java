package com.beizhi.common.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class MessageDto {
    private Integer id;
    private String name;
    private String message;
    private String time;
    @JsonIgnore
    private Integer pid;
    private List<MessageDto> children;
}
