package com.beizhi.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamineDto {
    private String title;
    private List<String> options;
    private Integer type;
    private String value;
}
