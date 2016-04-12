package com.ezhihui.www.dao;

import com.ezhihui.www.domain.Teacher;

import java.util.List;

public interface TeacherDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> getByCondi(Teacher teacher);
}