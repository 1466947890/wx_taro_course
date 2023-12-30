package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 一个章节下的任务
 */
@Data
@TableName("c_chapter_task")
public class ChapterTask {
    private Integer chapterId;
    private Integer videoId;
    private Integer examineId;
}
