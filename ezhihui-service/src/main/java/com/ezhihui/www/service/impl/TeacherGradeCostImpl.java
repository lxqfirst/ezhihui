package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.CourseDAO;
import com.ezhihui.www.dao.TeacherGradeCostDAO;
import com.ezhihui.www.domain.Course;
import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.domain.TeacherGradeCost;
import com.ezhihui.www.enums.CommonCode;
import com.ezhihui.www.enums.CourseStatusEnum;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageList;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.ITeacherGradeCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by lxq on 16/4/17.
 */
@Service("teacherGradeCost")
public class TeacherGradeCostImpl implements ITeacherGradeCostService {
    @Autowired
    private TeacherGradeCostDAO teacherGradeCostDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Override
    public PageListResponse<Teacher> calcTeacherSalary(String startTimeStr, String endTimeStr, Integer teacherId) {
        Map<String, Double> costMap = this.getCostMapByTeacherId(teacherId);

        Course course = new Course();
        course.setStartTimeStr(startTimeStr);
        course.setEndTimeStr(endTimeStr);
        course.setTeacherId(teacherId);
        course.setStatus(CourseStatusEnum.SIGNED.code);
        List<Course> courseList = courseDAO.getCourseByCondi(course);

        Map<String, Teacher> salaryMap = new HashMap<>();

        for (Course c : courseList) {
            String costMapKey = c.getTeacherId() + "-" + c.getGradeId();
            String teacherMapKey = String.valueOf(c.getTeacherId());

            if (salaryMap.containsKey(teacherMapKey)) {
                if (costMap.containsKey(costMapKey)
                        && !salaryMap.get(teacherMapKey).getComment().equals(CommonCode.SALAY_PARAM_ERROR.code)) {
                    salaryMap.get(teacherMapKey).setSalary(salaryMap.get(teacherMapKey).getSalary() + c.getCourseTime() * costMap.get(costMapKey));
                } else {
                    salaryMap.get(teacherMapKey).setComment(CommonCode.SALAY_PARAM_ERROR.code);// 1L代表有错误
                }
            } else {
                Teacher teacherTmp = new Teacher();
                teacherTmp.setId(c.getTeacherId());
                teacherTmp.setName(c.getTeacherName());

                if (costMap.containsKey(costMapKey)) {
                    teacherTmp.setSalary(c.getCourseTime() * costMap.get(costMapKey));
                } else {
                    teacherTmp.setComment(CommonCode.SALAY_PARAM_ERROR.code);
                }
                salaryMap.put(String.valueOf(c.getTeacherId()), teacherTmp);
            }
        }

        Iterator<Entry<String, Teacher>> iterator = salaryMap.entrySet().iterator();
        List<Teacher> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Entry<String, Teacher> entry = iterator.next();
            list.add(entry.getValue());
        }

        PageListResponse<Teacher> result = new PageListResponse<>();
        PageList<Teacher> pageList = new PageList<>();

        if (list == null || list.size() == 0) {
            result.setData(null);
            return result;
        }
        pageList.setList(list);
        pageList.setPageIndex(1);
        pageList.setPageSize(list.size());
        pageList.setTotal(list.size());

        result.setData(pageList);
        return result;

    }

    @Override
    public BaseResponse<List<TeacherGradeCost>> getByCondi(TeacherGradeCost teacherGradeCost) {
        List<TeacherGradeCost> teacherGradeCostList = teacherGradeCostDAO.getByCondi(teacherGradeCost);
        return new BaseResponse<>(teacherGradeCostList);
    }

    @Override
    public BaseResponse<Integer> create(TeacherGradeCost cost) {
        int rows = this.teacherGradeCostDAO.insertSelective(cost);
        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Integer> deleteByTeacherId(Integer teacherId) {
        int rows = this.teacherGradeCostDAO.deleteByTeacherId(teacherId);
        return new BaseResponse<>(rows);
    }

    private Map<String, Double> getCostMapByTeacherId(Integer teacherId) {
        TeacherGradeCost teacherGradeCost = new TeacherGradeCost();
        teacherGradeCost.setTeacherId(teacherId);
        List<TeacherGradeCost> teacherGradeCostList = teacherGradeCostDAO.getByCondi(teacherGradeCost);

        Map<String, Double> costMap = new HashMap<String, Double>();

        for (TeacherGradeCost t : teacherGradeCostList) {
            costMap.put(t.getTeacherId() + "-" + t.getGradeId(), t.getCost());
        }

        return costMap;
    }
}
