package com.ezhihui.www.service;

import com.ezhihui.www.domain.Course;
import com.ezhihui.www.enums.CourseStatusEnum;
import org.junit.Test;

import java.util.Date;

/**
 * Created by lxq on 16/4/11.
 */
public class CourseServiceTest extends BaseTest {
    protected ICourseService courseService;

    @Override
    public void init(){
        super.init();
        courseService = this.applicationContext.getBean(ICourseService.class);
    }

    @Test
    public void testCreateCourse(){
        Course course = new Course();
        course.setStudentId(295);
        course.setTeacherId(80);
        course.setCourseTime(2.0);
        course.setTime(new Date());
        course.setStatus(CourseStatusEnum.NOT_SIGNED.code);
        course.setClassroom("9");
        courseService.create(course);
    }
}
