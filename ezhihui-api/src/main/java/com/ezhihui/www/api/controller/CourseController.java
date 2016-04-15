package com.ezhihui.www.api.controller;

import com.ezhihui.www.api.view.CourseView;
import com.ezhihui.www.domain.BatchStudent;
import com.ezhihui.www.domain.Course;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.ICourseService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lxq on 16/4/11.
 */
@Controller
@RequestMapping("/course")
@Slf4j
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
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = sf.parse(course.getTimeStr());
            course.setTime(date);
        } catch (ParseException e) {
            log.error("time error" + course.getTimeStr());
        }
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


    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Integer> deleteById(@RequestParam("id") Integer id) {
        return this.courseService.deleteById(id);
    }

    @RequestMapping(value = "/exportCourse", method = RequestMethod.GET)
    public ModelAndView exportDrivers(@ModelAttribute Course course) {
        List<Course> courseList = this.courseService.getList(course).getData().getItems();
        CourseView view = new CourseView();
        return new ModelAndView(view, "courseList", courseList);
    }

    /**
     * 批量添加课程View
     *
     * @return
     */
    @RequestMapping(value = "/batchView", method = RequestMethod.GET)
    public String batchView() {
        return "/template/course/batch_course";
    }

    /**
     * 批量添加课程View
     *
     * @return
     */
    @RequestMapping(value = "/courseView", method = RequestMethod.GET)
    public String courseiew() {
        return "/template/course/course_query";
    }
}
