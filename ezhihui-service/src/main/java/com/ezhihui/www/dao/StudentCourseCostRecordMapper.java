package com.ezhihui.www.dao;

import com.ezhihui.www.domain.StudentCourseCostRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseCostRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentCourseCostRecord record);

    int insertSelective(StudentCourseCostRecord record);

    StudentCourseCostRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentCourseCostRecord record);

    int updateByPrimaryKey(StudentCourseCostRecord record);

    StudentCourseCostRecord getByCourseId(@Param("courseId") Integer courseId);

    List<StudentCourseCostRecord> getList(StudentCourseCostRecord record);
}