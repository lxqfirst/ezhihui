package com.ezhihui.www.service;

import com.ezhihui.www.domain.BatchStudent;
import com.ezhihui.www.domain.Course;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lxq on 16/4/11.
 */
public interface ICourseService {
    BaseResponse<Integer> create(Course course);

    BaseResponse<Integer> update(Course course);

    BaseResponse<Integer> deleteById(Integer id);

    BaseResponse<Integer> copy(List<Integer> ids, String dateStr);

    BaseResponse<List<Course>> getByCondi(Course course);

    BaseResponse<Course> getById(Integer id);

    BaseResponse<Boolean> saveBatchCourse(BatchStudent batchStudent);

    PageListResponse<Course> getList(Course course);
}
