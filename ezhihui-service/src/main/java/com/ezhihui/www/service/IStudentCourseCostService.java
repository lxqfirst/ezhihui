package com.ezhihui.www.service;

import com.ezhihui.www.domain.StudentCourseCostConfig;
import com.ezhihui.www.response.BaseResponse;
import org.springframework.data.redis.connection.RedisServer;

import java.util.List;

public interface IStudentCourseCostService {
    BaseResponse<List<StudentCourseCostConfig>> getByStudentId(Integer studentId);

    BaseResponse<Integer> insertOrUpdateList(List<StudentCourseCostConfig> configList);
}
