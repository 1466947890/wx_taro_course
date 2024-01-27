package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chapter_student_video")
public class ChapterStudentVideo {
    private Integer chapterId;
    private Integer studentId;
    private Integer process;
}
