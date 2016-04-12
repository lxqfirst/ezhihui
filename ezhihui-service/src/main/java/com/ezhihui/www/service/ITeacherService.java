package com.ezhihui.www.service;

import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;

import java.util.List;

/**
 * Created by lxq on 16/4/12.
 */
public interface ITeacherService {
    BaseResponse<Integer> create(Teacher teacher);

    BaseResponse<Integer> update(Teacher teacher);

    BaseResponse<Integer> deleteById(Integer id);

    BaseResponse<Teacher> getById(Integer id);

    BaseResponse<List<Teacher>> getList(Teacher teacher);

    PageListResponse<Teacher> getPageList(Teacher teacher);
}
