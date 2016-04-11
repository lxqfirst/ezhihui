package com.ezhihui.www.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherGradeCost {
    private Integer teacherId;

    private Integer gradeId;

    private Double cost;
}