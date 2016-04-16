package com.ezhihui.www.service;

import com.ezhihui.www.domain.Subject;
import com.ezhihui.www.response.BaseResponse;

import java.util.List;

/**
 * Created by lxq on 16/4/16.
 */
public interface ISubjectService {
    BaseResponse<List<Subject>> getAll();
}
