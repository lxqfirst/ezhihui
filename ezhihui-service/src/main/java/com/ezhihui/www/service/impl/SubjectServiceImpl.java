package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.SubjectDAO;
import com.ezhihui.www.domain.Subject;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxq on 16/4/16.
 */
@Service("subjectService")
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    public BaseResponse<List<Subject>> getAll() {
        return new BaseResponse<>(subjectDAO.getAll());
    }
}
