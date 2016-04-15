package com.ezhihui.www.service;

import com.ezhihui.www.domain.Student;
import com.ezhihui.www.request.StudentPageRequest;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;

import java.util.List;

/**
 * Created by lxq on 16/4/13.
 */
public interface IStudentService {
    BaseResponse<Integer> create(Student student);

    BaseResponse<Integer> update(Student student);

    BaseResponse<Integer> deleteById(Integer id);

    BaseResponse<Student> getById(Integer id);

    BaseResponse<List<Student>> getList(Student student);

    PageListResponse<Student> getPageList(StudentPageRequest request);
}
