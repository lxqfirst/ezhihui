package com.ezhihui.www.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxq on 16/3/13.
 */
@Getter
@Setter
public class BatchStudent implements Serializable {
    String studentId;
    List<BatchCourse> newCourseList;
    List<BatchCourse> deletedCourseList;
    List<BatchCourse> updatedCourseList;

    @Getter
    @Setter
    public class BatchCourse {
        Integer courseId;
        String teacherId;
        String time;
    }
}
