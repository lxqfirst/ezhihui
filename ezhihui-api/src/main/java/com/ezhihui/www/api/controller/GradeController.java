package com.ezhihui.www.api.controller;

import com.ezhihui.www.domain.App;
import com.ezhihui.www.domain.Grade;
import com.ezhihui.www.dto.GradeDto;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.IAppService;
import com.ezhihui.www.service.IGradeService;
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
@RequestMapping("/grade")
public class GradeController extends BaseController {
    @Autowired
    private IGradeService gradeService;

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<Grade>> getById() {
        return this.gradeService.getAll();
    }

    @RequestMapping(value = "/getGradeNameByTeacherId", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<String>> getGradeNameByTeacherId(@RequestParam("teacherId") Integer teacherId,
                                                              @RequestParam("startTime") String startTime,
                                                              @RequestParam("endTime") String endTime) {
        GradeDto dto = new GradeDto(teacherId, startTime, endTime);
        return this.gradeService.getGradeNameByTeacherId(dto);
    }
}
