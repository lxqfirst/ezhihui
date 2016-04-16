package com.ezhihui.www.api.controller;

import com.ezhihui.www.domain.Grade;
import com.ezhihui.www.domain.Subject;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.IGradeService;
import com.ezhihui.www.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lxq on 16/1/22.
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {
    @Autowired
    private ISubjectService subjectService;

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<Subject>> getById() {
        return this.subjectService.getAll();
    }
}
