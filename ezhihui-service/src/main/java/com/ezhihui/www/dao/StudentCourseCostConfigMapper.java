package com.ezhihui.www.dao;

import com.ezhihui.www.domain.StudentCourseCostConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseCostConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByStudentId(Integer studentId);

    int insert(StudentCourseCostConfig record);

    int insertSelective(StudentCourseCostConfig record);

    StudentCourseCostConfig selectByPrimaryKey(Integer id);

    List<StudentCourseCostConfig> getByStudentIdList(Integer studentId);

    int updateByPrimaryKeySelective(StudentCourseCostConfig record);

    int updateByPrimaryKey(StudentCourseCostConfig record);

    StudentCourseCostConfig getByParam(@Param("studentId") Integer studentId, @Param("teacherId") Integer teacherId);
}