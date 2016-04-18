package com.ezhihui.www.api.controller;

import com.ezhihui.www.domain.App;
import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.ITeacherGradeCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lxq on 16/1/22.
 */
@Controller
@RequestMapping("/cost")
public class TeacherGradeCostController extends BaseController {
    @Autowired
    private ITeacherGradeCostService teacherGradeCostService;

    @RequestMapping(value = "/getTeacherSalaryList", method = RequestMethod.GET)
    @ResponseBody
    public PageListResponse<Teacher> getById(@RequestParam("startTimeStr") String startTimeStr, @RequestParam("endTimeStr") String endTimeStr, @RequestParam(value = "teacherId", required = false) Integer teacherId) {
        return this.teacherGradeCostService.calcTeacherSalary(startTimeStr, endTimeStr, teacherId);
    }

    /**
     * view
     *
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view() {
        return "/template/cost/cost_query";
    }
}
