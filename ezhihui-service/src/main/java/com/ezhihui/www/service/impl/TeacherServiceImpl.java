package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.TeacherDAO;
import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.request.TeacherPageRequest;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageList;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.ITeacherService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxq on 16/4/12.
 */
@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public BaseResponse<Integer> create(Teacher teacher) {
        int rows = this.teacherDAO.insertSelective(teacher);
        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Integer> update(Teacher teacher) {
        int rows = this.teacherDAO.updateByPrimaryKeySelective(teacher);
        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Integer> deleteById(Integer id) {
        int rows = this.teacherDAO.deleteByPrimaryKey(id);
        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Teacher> getById(Integer id) {
        Teacher teacher = this.teacherDAO.selectByPrimaryKey(id);
        return new BaseResponse<>(teacher);
    }

    @Override
    public BaseResponse<List<Teacher>> getList(Teacher teacher) {
        List<Teacher> result = this.teacherDAO.getByCondi(teacher);

        return new BaseResponse<>(result);
    }

    @Override
    public PageListResponse<Teacher> getPageList(TeacherPageRequest request) {
        PageHelper.startPage(request.getPageIndex(), request.getPageSize());

        PageListResponse<Teacher> result = new PageListResponse<>();
        PageList<Teacher> pageList = new PageList<>();
        Teacher teacher = new Teacher();
        teacher.setId(request.getId());
        Page<Teacher> list = (Page<Teacher>) this.teacherDAO.getByCondi(teacher);

        if (list == null || list.size() == 0) {
            result.setData(null);
            return result;
        }
        pageList.setList(list);
        pageList.setPageIndex(request.getPageIndex());
        pageList.setPageSize(request.getPageSize());
        pageList.setTotal(list.getTotal());

        result.setData(pageList);
        return result;
    }
}
