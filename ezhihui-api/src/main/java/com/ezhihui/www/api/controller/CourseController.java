package com.ezhihui.www.api.controller;

import com.ezhihui.www.domain.BatchStudent;
import com.ezhihui.www.domain.Course;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.ICourseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxq on 16/4/11.
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {
    @Autowired
    private ICourseService courseService;

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public PageListResponse<Course> getList(@ModelAttribute Course course) {
        return this.courseService.getList(course);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> create(@RequestBody Course course) {
        return this.courseService.create(course);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> update(@RequestBody Course course) {
        return this.courseService.update(course);
    }

    @RequestMapping(value = "/saveBatchCourse", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> saveBatchCourse(@RequestParam("courseInfo") String courseInfo) {
        Gson gson = new Gson();
        BatchStudent batchStudent = gson.fromJson(courseInfo, BatchStudent.class);
        return this.courseService.saveBatchCourse(batchStudent);
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> copy(@RequestParam("ids") String ids, @RequestParam("dateStr") String dateStr) {
        String[] idsArray = ids.split("[|]");
        List<Integer> idsInt = new ArrayList<>();
        for (String idStr : idsArray) {
            idsInt.add(Integer.valueOf(idStr));
        }

        return this.courseService.copy(idsInt, dateStr);
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> download() {
        return new BaseResponse<>(Boolean.TRUE);
    }
}
