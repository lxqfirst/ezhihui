package com.ezhihui.www.api.controller;

import com.ezhihui.www.api.annotations.Login;
import com.ezhihui.www.api.view.CostView;
import com.ezhihui.www.api.view.CourseView;
import com.ezhihui.www.domain.App;
import com.ezhihui.www.domain.Course;
import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.domain.TeacherGradeCost;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.IGradeService;
import com.ezhihui.www.service.ITeacherGradeCostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lxq on 16/1/22.
 */
@Controller
@RequestMapping("/cost")
public class TeacherGradeCostController extends BaseController {
    @Autowired
    private ITeacherGradeCostService teacherGradeCostService;

    @Autowired
    private IGradeService gradeService;

    @RequestMapping(value = "/getTeacherSalaryList", method = RequestMethod.GET)
    @ResponseBody
    @Login
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
    @Login
    public BaseResponse<List<TeacherGradeCost>> getList(@RequestParam("teacherId") Integer teacherId) {
        TeacherGradeCost cost = new TeacherGradeCost();
        cost.setTeacherId(teacherId);
        return this.teacherGradeCostService.getByCondi(cost);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @Login
    public BaseResponse<Boolean> update(@RequestParam("teacherId") Integer teacherId,
                                        @RequestParam(value = "gradeIds", required = false) String gradeIds,
                                        @RequestParam(value = "costs", required = false) String costs) {

        if (StringUtils.isBlank(gradeIds) || StringUtils.isBlank(costs)) {
            return new BaseResponse<>(Boolean.TRUE);
        }

        teacherGradeCostService.deleteByTeacherId(teacherId);
        BaseResponse<List<Integer>> gradeIdsByTeacherId = this.gradeService.getGradeIdsByTeacherId(teacherId);

        String[] gradeIdsArray = gradeIds.split(",");
        String[] costsArray = costs.split(",");

        Set<String> set = new HashSet<>();
        for (int i = 0; i < gradeIdsArray.length; i++) {
            String key = teacherId + gradeIdsArray[i] + costsArray[i];
            if (set.contains(key) || !gradeIdsByTeacherId.getData().contains(Integer.valueOf(gradeIdsArray[i]))) {
                continue;
            } else {
                set.add(key);
            }

            TeacherGradeCost tmp = new TeacherGradeCost();
            tmp.setTeacherId(teacherId);
            tmp.setGradeId(Integer.valueOf(gradeIdsArray[i]));
            tmp.setCost(Double.valueOf(costsArray[i]));
            this.teacherGradeCostService.create(tmp);
        }
        return new BaseResponse<>(Boolean.TRUE);
    }

    @RequestMapping(value = "/exportCost", method = RequestMethod.GET)
    @Login
    public ModelAndView exportDrivers(@RequestParam("startTimeStr") String startTimeStr, @RequestParam("endTimeStr") String endTimeStr, @RequestParam(value = "teacherId", required = false) Integer teacherId) {
        PageListResponse<Teacher> result = this.teacherGradeCostService.calcTeacherSalary(startTimeStr, endTimeStr, teacherId);
        CostView view = new CostView();
        return new ModelAndView(view, "teacherList", result.getData().getItems());
    }


    @RequestMapping(value = "/courseView", method = RequestMethod.GET)
    @Login
    public ModelAndView courseView(@RequestParam("startTimeStr") String startTimeStr, @RequestParam("endTimeStr") String endTimeStr, @RequestParam(value = "teacherId") Integer teacherId) {
        ModelAndView modelAndView = new ModelAndView("/template/cost/cost_course_query");
        modelAndView.addObject("startTimeStr", startTimeStr);
        modelAndView.addObject("endTimeStr", endTimeStr);
        modelAndView.addObject("teacherId", teacherId);

        return modelAndView;
    }
}
