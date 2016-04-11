package com.ezhihui.www.dao;

import com.ezhihui.www.domain.TeacherGradeCost;

public interface TeacherGradeCostDAO {
    int insert(TeacherGradeCost record);

    int insertSelective(TeacherGradeCost record);
}