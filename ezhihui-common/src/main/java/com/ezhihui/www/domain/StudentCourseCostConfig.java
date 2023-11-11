package com.ezhihui.www.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
public class StudentCourseCostConfig {
    private Integer id;

    private Integer studentId;

    private Integer teacherId;

    private String teacherName;

    private Integer subjectId;

    private String subjectName;

    private Integer cost;

    private String operator;

    private Date createTime;

    private Date updateTime;
}