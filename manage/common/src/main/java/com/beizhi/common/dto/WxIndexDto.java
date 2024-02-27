package com.beizhi.common.dto;

import com.beizhi.entity.Course;
import lombok.Data;

import java.util.List;

/**
 * @author 14669
 * @date 2024/2/3 18:40
 * @describe
 */
@Data
public class WxIndexDto {
    private String indexImage;
    private List<Course> myCourse;
    private List<Course> otherCourse;
}
