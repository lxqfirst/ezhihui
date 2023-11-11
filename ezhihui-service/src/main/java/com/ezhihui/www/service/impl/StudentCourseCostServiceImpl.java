package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.StudentCourseCostConfigMapper;
import com.ezhihui.www.dao.SubjectDAO;
import com.ezhihui.www.dao.TeacherDAO;
import com.ezhihui.www.domain.StudentCourseCostConfig;
import com.ezhihui.www.domain.Subject;
import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.IStudentCourseCostService;
import com.ezhihui.www.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("studentCourseCostService")
public class StudentCourseCostServiceImpl implements IStudentCourseCostService {

    @Autowired
    private StudentCourseCostConfigMapper studentCourseCostConfigMapper;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    public BaseResponse<List<StudentCourseCostConfig>> getByStudentId(Integer studentId) {
        List<StudentCourseCostConfig> configList = this.studentCourseCostConfigMapper.getByStudentIdList(studentId);

        for (StudentCourseCostConfig config : configList) {
            Teacher teacher = this.teacherDAO.selectByPrimaryKey(config.getTeacherId());
            Subject subject = subjectDAO.selectByPrimaryKey(config.getSubjectId());
            config.setTeacherName(teacher.getName());
            config.setSubjectName(subject.getSubjectName());
        }

        return new BaseResponse<>(configList);
    }

    @Override
    public BaseResponse<Integer> insertOrUpdateList(List<StudentCourseCostConfig> configList) {
        if (CollectionUtils.isEmpty(configList)) {
            return new BaseResponse<>(0);
        }

        Integer studentId = configList.get(0).getStudentId();
        this.studentCourseCostConfigMapper.deleteByStudentId(studentId);
        for (StudentCourseCostConfig config : configList) {
            config.setSubjectName("");
            config.setOperator("");
            config.setUpdateTime(new Date());
            config.setCreateTime(new Date());
            this.studentCourseCostConfigMapper.insertSelective(config);
        }
        return new BaseResponse<>(0);
    }
}
