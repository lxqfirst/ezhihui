package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.StudentCourseCostRecordMapper;
import com.ezhihui.www.domain.Course;
import com.ezhihui.www.domain.Grade;
import com.ezhihui.www.domain.StudentCourseCostRecord;
import com.ezhihui.www.response.PageList;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.IStudentCourseCostRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service("studentCourseCostRecordService")
public class StudentCourseCostRecordServiceImpl implements IStudentCourseCostRecordService {

    @Autowired
    private StudentCourseCostRecordMapper studentCourseCostRecordMapper;

    @Override
    public PageListResponse<StudentCourseCostRecord> getCostDetailList(StudentCourseCostRecord record) {
        PageListResponse<StudentCourseCostRecord> result = new PageListResponse<>();
        PageList<StudentCourseCostRecord> pageList = new PageList<>();

        List<StudentCourseCostRecord> list = studentCourseCostRecordMapper.getList(record);
        if (CollectionUtils.isEmpty(list)) {
            result.setData(null);
            return result;
        }

        pageList.setList(list);
        pageList.setPageIndex(1);
        pageList.setPageSize(list.size());
        pageList.setTotal(list.size());

        result.setData(pageList);
        return result;
    }
}
