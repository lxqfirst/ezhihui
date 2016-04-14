package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.StudentDAO;
import com.ezhihui.www.domain.Student;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageList;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxq on 16/4/13.
 */
@Service("studentService")
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public BaseResponse<Integer> create(Student student) {
        int rows = this.studentDAO.insertSelective(student);

        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Integer> update(Student student) {
        int rows = this.studentDAO.updateByPrimaryKeySelective(student);

        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Integer> deleteById(Integer id) {
        int rows = this.studentDAO.deleteByPrimaryKey(id);

        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Student> getById(Integer id) {
        Student student = this.studentDAO.selectByPrimaryKey(id);

        return new BaseResponse<>(student);
    }

    @Override
    public BaseResponse<List<Student>> getList(Student student) {
        List<Student> result = this.studentDAO.getByCondi(student);
        return new BaseResponse<>(result);
    }

    @Override
    public PageListResponse<Student> getPageList(Student student) {
        PageListResponse<Student> result = new PageListResponse<>();
        PageList<Student> pageList = new PageList<>();

        List<Student> list = this.studentDAO.getByCondi(student);
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
}
