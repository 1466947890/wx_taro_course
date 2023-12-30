package com.beizhi.common.dto.chapterTask;

import com.beizhi.common.dto.ExamineDto;
import com.beizhi.entity.Details;
import com.beizhi.entity.Examine;
import com.beizhi.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class ChapterTaskDto {
    private VideoInfo videoInfo;
    private List<Teacher> teacherList;
    private List<ExamineDto> examineList;
    private List<Details> detailsList;
}


