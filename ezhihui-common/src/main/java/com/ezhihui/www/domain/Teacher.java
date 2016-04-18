package com.ezhihui.www.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Teacher {
    private Integer id;

    private String name;

    private String telephone;

    private String desc;

    private Integer subjectId;

    private String subjectName;

    private Double salary;

    private Integer comment = 0;
}