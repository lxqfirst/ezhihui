package com.ezhihui.www.api.controller;

import com.ezhihui.www.api.annotations.Login;
import com.ezhihui.www.domain.*;
import com.ezhihui.www.request.StudentPageRequest;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.IStudentCourseCostRecordService;
import com.ezhihui.www.service.IStudentCourseCostService;
import com.ezhihui.www.service.IStudentService;
import com.ezhihui.www.service.ITeacherService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lxq on 16/4/12.
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStudentCourseCostService studentCourseCostService;

    @Autowired
    private IStudentCourseCostRecordService studentCourseCostRecordService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> create(@RequestBody Student student) {
        return this.studentService.create(student);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> update(@RequestBody Student student) {
        return this.studentService.update(student);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<Student> getById(@RequestParam("id") Integer id) {
        return this.studentService.getById(id);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> deleteById(@RequestParam("id") Integer id) {
        return this.studentService.deleteById(id);
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<Student>> getList(@ModelAttribute Student student) {
        return this.studentService.getList(student);
    }

    @RequestMapping(value = "/getPageList", method = RequestMethod.GET)
    @ResponseBody
    public PageListResponse<Student> getPageList(@ModelAttribute StudentPageRequest request) {
        return this.studentService.getPageList(request);
    }

    @RequestMapping(value = "/getFeeConfigList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<StudentCourseCostConfig>> getFeeList(@ModelAttribute @RequestParam("studentId") Integer studentId) {
        return this.studentCourseCostService.getByStudentId(studentId);
    }

    @RequestMapping(value = "/saveBatchFeeConfig", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> saveBatchFeeConfig(@RequestParam("feeConfigInfo") String feeConfigInfo) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<StudentCourseCostConfig>>() {
        }.getType();
        List<StudentCourseCostConfig> configList = gson.fromJson(feeConfigInfo, listType);

        return this.studentCourseCostService.insertOrUpdateList(configList);
    }

    @RequestMapping(value = "/getCostDetailList", method = RequestMethod.GET)
    @ResponseBody
    public PageListResponse<StudentCourseCostRecord> getCostDetailList(@ModelAttribute StudentCourseCostRecord record) {
        return this.studentCourseCostRecordService.getCostDetailList(record);
    }

    /**
     * view
     *
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view() {
        return "/template/student/student_query";
    }

    @RequestMapping(value = "/costDetailView", method = RequestMethod.GET)
    @Login
    public ModelAndView courseView() {
        return new ModelAndView("/template/student/student_cost_course_query");
    }
}
