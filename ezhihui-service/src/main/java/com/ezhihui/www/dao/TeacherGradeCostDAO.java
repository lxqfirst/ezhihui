package com.ezhihui.www.dao;

import com.ezhihui.www.domain.TeacherGradeCost;

import java.util.List;

public interface TeacherGradeCostDAO {
    int insert(TeacherGradeCost record);

    int insertSelective(TeacherGradeCost record);

    List<TeacherGradeCost> getByCondi(TeacherGradeCost teacherGradeCost);

    int deleteByTeacherId(Integer teacherId);
}