package com.ezhihui.www.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentCourseCostRecord {
    private Integer id;

    private Integer courseId;

    private Integer studentId;

    private String studentName;

    private Integer teacherId;

    private String teacherName;

    private Double courseTime;

    private Integer courseType;

    private Date time;

    private Integer subjectId;

    private String subjectName;

    private Integer cost;

    private Integer type;

    private String startTimeStr;

    private String endTimeStr;

    private Date createTime;

    private Date updateTime;
}