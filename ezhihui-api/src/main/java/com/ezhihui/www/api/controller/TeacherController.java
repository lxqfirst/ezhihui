package com.ezhihui.www.api.controller;

import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;
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
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> create(Teacher teacher) {
        return this.teacherService.create(teacher);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> update(Teacher teacher) {
        return this.teacherService.update(teacher);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<Teacher> getById(Integer id) {
        return this.teacherService.getById(id);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> deleteById(Integer id) {
        return this.teacherService.deleteById(id);
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<Teacher>> getList(@ModelAttribute Teacher teacher) {
        return this.teacherService.getList(teacher);
    }

    @RequestMapping(value = "/getPageList", method = RequestMethod.GET)
    @ResponseBody
    public PageListResponse<Teacher> getPageList(@ModelAttribute Teacher teacher) {
        return this.teacherService.getPageList(teacher);
    }
}
