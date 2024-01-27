package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 每个章节下学生的测验成绩
 */
@Data
@TableName("c_chapter_student_examine")
public class ChapterStudentExamine {
    private Integer chapterId;
    private Integer studentId;
//    分数
    private Integer grade;
}
