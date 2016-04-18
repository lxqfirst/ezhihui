package com.ezhihui.www.service;

import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.domain.TeacherGradeCost;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;

import java.util.List;

/**
 * Created by lxq on 16/4/17.
 */
public interface ITeacherGradeCostService {

    /**
     * 计算教师薪水
     *
     * @param startTimeStr
     * @param endTimeStr
     * @param teacherId
     * @return
     */
    PageListResponse<Teacher> calcTeacherSalary(String startTimeStr, String endTimeStr, Integer teacherId);

    BaseResponse<TeacherGradeCost> getByCondi(TeacherGradeCost teacherGradeCost);
}
