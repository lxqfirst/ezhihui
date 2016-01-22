package com.ezhihui.www.dao;

import com.ezhihui.www.domain.App;

public interface AppDAO {
    int deleteByPrimaryKey(Long id);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}