package com.ezhihui.www.dao;

import com.ezhihui.www.domain.Course;

import java.util.List;

public interface CourseDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> getCourseByCondi(Course course);
}