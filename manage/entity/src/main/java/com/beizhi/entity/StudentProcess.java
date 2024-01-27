package com.beizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 14669
 * @date 2023/12/13 19:21
 * @describe
 */
@Data
@TableName("c_student_process")
public class StudentProcess {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private BigDecimal videoProcess;
    private BigDecimal examineGrade;
    private Integer studentId;
    private Integer chapterId;
}
