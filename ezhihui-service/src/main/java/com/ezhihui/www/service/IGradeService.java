package com.ezhihui.www.service;

import com.ezhihui.www.domain.Grade;
import com.ezhihui.www.response.BaseResponse;

import java.util.List;

/**
 * Created by lxq on 16/4/15.
 */
public interface IGradeService {
    BaseResponse<List<Grade>> getAll();

    BaseResponse<List<String>> getGradeNameByTeacherId(Integer teacherId);

    BaseResponse<List<Integer>> getGradeIdsByTeacherId(Integer teacherId );
}
