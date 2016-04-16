package com.ezhihui.www.dao;

import com.ezhihui.www.domain.Grade;

import java.util.List;

public interface GradeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);

    List<Grade> getAll();
}