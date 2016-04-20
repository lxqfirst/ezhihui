package com.ezhihui.www.api.controller;

import com.ezhihui.www.api.view.CostView;
import com.ezhihui.www.api.view.CourseView;
import com.ezhihui.www.domain.App;
import com.ezhihui.www.domain.Course;
import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.domain.TeacherGradeCost;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.ITeacherGradeCostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<TeacherGradeCost>> getList(@RequestParam("teacherId") Integer teacherId) {
        TeacherGradeCost cost = new TeacherGradeCost();
        cost.setTeacherId(teacherId);
        return this.teacherGradeCostService.getByCondi(cost);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> update(@RequestParam("teacherId") Integer teacherId,
                                        @RequestParam(value = "gradeIds", required = false) String gradeIds,
                                        @RequestParam(value = "costs", required = false) String costs) {
        teacherGradeCostService.deleteByTeacherId(teacherId);

        if (StringUtils.isBlank(gradeIds) || StringUtils.isBlank(costs)) {
            return new BaseResponse<>(Boolean.TRUE);
        }
        String[] gradeIdsArray = gradeIds.split(",");
        String[] costsArray = costs.split(",");

        for (int i = 0; i < gradeIdsArray.length; i++) {
            TeacherGradeCost tmp = new TeacherGradeCost();
            tmp.setTeacherId(teacherId);
            tmp.setGradeId(Integer.valueOf(gradeIdsArray[i]));
            tmp.setCost(Double.valueOf(costsArray[i]));
            this.teacherGradeCostService.create(tmp);
        }
        return new BaseResponse<>(Boolean.TRUE);
    }

    @RequestMapping(value = "/exportCost", method = RequestMethod.GET)
    public ModelAndView exportDrivers(@RequestParam("startTimeStr") String startTimeStr, @RequestParam("endTimeStr") String endTimeStr, @RequestParam(value = "teacherId", required = false) Integer teacherId) {
        PageListResponse<Teacher> result = this.teacherGradeCostService.calcTeacherSalary(startTimeStr, endTimeStr, teacherId);
        CostView view = new CostView();
        return new ModelAndView(view, "teacherList", result.getData().getItems());
    }
}
