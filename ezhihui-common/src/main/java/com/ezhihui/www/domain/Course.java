package com.ezhihui.www.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Course {
    private Integer id;

    private Integer studentId;

    private Integer teacherId;

    private Integer subjectId;

    private Double courseTime;

    private Date time;

    private String classroom;

    private Date addTime;

    private Integer status;

    private Date endTime;

    private String startTimeStr;

    private String endTimeStr;

    private String studentName;

    private String teacherName;

    private String subjectName;

    private String signString;

    private Long gradeId;
}