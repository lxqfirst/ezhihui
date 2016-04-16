package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.GradeDAO;
import com.ezhihui.www.domain.Grade;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxq on 16/4/15.
 */
@Service("gradeService")
public class GradeServiceImpl implements IGradeService {
    @Autowired
    private GradeDAO gradeDAO;

    @Override
    public BaseResponse<List<Grade>> getAll() {
        List<Grade> list = gradeDAO.getAll();
        return new BaseResponse<>(list);
    }
}
