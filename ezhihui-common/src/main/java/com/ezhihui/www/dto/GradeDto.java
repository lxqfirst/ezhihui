package com.ezhihui.www.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lxq on 2017/9/14.
 */
@Getter
@Setter
@AllArgsConstructor
public class GradeDto {
    private Integer teacherId;

    private String startTime;

    private String endTime;
}
