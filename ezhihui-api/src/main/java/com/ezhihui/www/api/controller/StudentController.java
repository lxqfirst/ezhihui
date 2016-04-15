package com.ezhihui.www.api.controller;

import com.ezhihui.www.domain.Student;
import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.request.StudentPageRequest;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.IStudentService;
import com.ezhihui.www.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lxq on 16/4/12.
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> create(Student student) {
        return this.studentService.create(student);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> update(Student student) {
        return this.studentService.update(student);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<Student> getById(Integer id) {
        return this.studentService.getById(id);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> deleteById(Integer id) {
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

    /**
     * view
     *
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view() {
        return "/template/student/student_query";
    }
}
