package com.ezhihui.www.dao;


import com.ezhihui.www.domain.User;

import java.util.List;

public interface UserDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> getByCondi(User user);
}