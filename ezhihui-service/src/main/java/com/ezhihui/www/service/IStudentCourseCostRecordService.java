package com.ezhihui.www.service;

import com.ezhihui.www.domain.StudentCourseCostRecord;
import com.ezhihui.www.response.PageListResponse;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface IStudentCourseCostRecordService {
    PageListResponse<StudentCourseCostRecord> getCostDetailList(StudentCourseCostRecord record);
}
