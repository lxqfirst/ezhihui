package com.ezhihui.www.dao;

import com.ezhihui.www.domain.Subject;

public interface SubjectDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
}