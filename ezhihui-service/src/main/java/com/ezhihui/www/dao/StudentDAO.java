package com.ezhihui.www.dao;

import com.ezhihui.www.domain.Student;

import java.util.List;

public interface StudentDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> getByCondi(Student student);
}