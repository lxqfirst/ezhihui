package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.CourseDAO;
import com.ezhihui.www.dao.GradeDAO;
import com.ezhihui.www.dao.StudentCourseCostConfigMapper;
import com.ezhihui.www.dao.StudentCourseCostRecordMapper;
import com.ezhihui.www.domain.*;
import com.ezhihui.www.enums.CourseStatusEnum;
import com.ezhihui.www.enums.FeeEnum;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.response.PageList;
import com.ezhihui.www.response.PageListResponse;
import com.ezhihui.www.service.ICourseService;
import com.ezhihui.www.service.IStudentService;
import com.ezhihui.www.service.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lxq on 16/4/11.
 */
@Slf4j
@Service("courseService")
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private GradeDAO gradeDAO;

    @Autowired
    private StudentCourseCostConfigMapper studentCourseCostConfigMapper;

    @Autowired
    private StudentCourseCostRecordMapper studentCourseCostRecordMapper;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Override
    public BaseResponse<Integer> create(Course course) {
        course.setEndTime(DateUtils.addMinutes(course.getTime(), (int) (course.getCourseTime() * 60)));
        int rows = this.courseDAO.insertSelective(course);
        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Integer> update(Course course) {
        if (course.getTimeStr() != null && course.getCourseTime() != null) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                course.setTime(sf.parse(course.getTimeStr()));
            } catch (ParseException e) {
                log.info("param error ," + course.getTimeStr());
            }
            course.setEndTime(DateUtils.addMinutes(course.getTime(), (int) (course.getCourseTime() * 60)));
        }

        Course oldCourse = this.courseDAO.selectByPrimaryKey(course.getId());
        StudentCourseCostConfig config = this.studentCourseCostConfigMapper.getByParam(oldCourse.getStudentId(), oldCourse.getTeacherId());
        StudentCourseCostRecord record = this.studentCourseCostRecordMapper.getByCourseId(course.getId());
        Student student = studentService.getById(oldCourse.getStudentId()).getData();
        Teacher teacherCondition = new Teacher();
        teacherCondition.setId(oldCourse.getTeacherId());
        Teacher teacher = teacherService.getList(teacherCondition).getData().get(0);

        if (oldCourse.getStatus().equals(CourseStatusEnum.NOT_SIGNED.code) && course.getStatus().equals(CourseStatusEnum.SIGNED.code)) {
            //扣款，签到
            if (record == null) {
                record = this.buildNewRecord(oldCourse, config, student, teacher);
                studentCourseCostRecordMapper.insertSelective(record);

                //扣费
                student.setFund(student.getFund() - record.getCost());
                this.studentService.update(student);
            } else {
                if (record.getType() == FeeEnum.DEDUCTION.value || record.getType() == FeeEnum.DELETE_REFUND.value) {
                    //这里有异常
                }

                if (record.getType() == FeeEnum.NO_SIGNED_REFUND.value || record.getType() == FeeEnum.NO_CHARGE.value) {
                    if (config != null) {
                        int fee = (int) Math.round(config.getCost() * oldCourse.getCourseTime());
                        record.setCost(fee);
                        record.setType(FeeEnum.DEDUCTION.value);
                        studentCourseCostRecordMapper.updateByPrimaryKeySelective(record);

                        student.setFund(student.getFund() - fee);
                        this.studentService.update(student);
                    }
                }
            }
        }

        if (oldCourse.getStatus().equals(CourseStatusEnum.SIGNED.code) && course.getStatus().equals(CourseStatusEnum.NOT_SIGNED.code)) {
            //退款，取消签到
            if (record != null && record.getType() == FeeEnum.DEDUCTION.value) {
                student.setFund(student.getFund() + record.getCost());
                this.studentService.update(student);

                record.setType(FeeEnum.NO_SIGNED_REFUND.value);
                this.studentCourseCostRecordMapper.updateByPrimaryKeySelective(record);
            }

            if (record != null && (record.getType() == FeeEnum.DELETE_REFUND.value || record.getType() == FeeEnum.NO_SIGNED_REFUND.value)) {
                //这里有异常
            }
        }
        int rows = this.courseDAO.updateByPrimaryKeySelective(course);
        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Integer> deleteById(Integer id) {
        Course course = this.courseDAO.selectByPrimaryKey(id);

        StudentCourseCostRecord record = studentCourseCostRecordMapper.getByCourseId(id);
        if (course.getStatus() == CourseStatusEnum.SIGNED.code) {
            if (record != null && record.getType().equals(FeeEnum.DEDUCTION.value)) {
                Student student = studentService.getById(course.getStudentId()).getData();
                student.setFund(student.getFund() + record.getCost());
                this.studentService.update(student);

                record.setType(FeeEnum.DELETE_REFUND.value);
                this.studentCourseCostRecordMapper.updateByPrimaryKeySelective(record);
            }
        }
        int rows = this.courseDAO.deleteByPrimaryKey(id);
        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<Integer> copy(List<Integer> ids, String dateStr) {
        int rows = 0;
        for (Integer id : ids) {
            Course course = this.courseDAO.selectByPrimaryKey(id);
            if (course != null) {
                Date date = course.getTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Integer.parseInt(dateStr.split("-")[0]), Integer.parseInt(dateStr.split("-")[1]) - 1, Integer.parseInt(dateStr.split("-")[2]));

                course.setTime(calendar.getTime());
                course.setId(null);
                course.setStatus(CourseStatusEnum.NOT_SIGNED.code);
                course.setAddTime(new Date());
                this.create(course);
                rows++;
            }
        }
        return new BaseResponse<>(rows);
    }

    @Override
    public BaseResponse<List<Course>> getByCondi(Course course) {
        List<Course> list = this.courseDAO.getCourseByCondi(course);
        return new BaseResponse<>(list);
    }

    @Override
    public BaseResponse<Course> getById(Integer id) {
        Course course = this.courseDAO.selectByPrimaryKey(id);
        return new BaseResponse<>(course);
    }

    @Override
    @Transactional
    public BaseResponse<Boolean> saveBatchCourse(BatchStudent batchStudent) {
        List<BatchStudent.BatchCourse> list = batchStudent.getNewCourseList();
        Integer studentId = Integer.valueOf(batchStudent.getStudentId());
        for (BatchStudent.BatchCourse batchCourse : list) {
            Course course = new Course();
            course.setStudentId(studentId);

            Integer teacherId = Integer.valueOf(batchCourse.getTeacherId());
            course.setTeacherId(teacherId);
            course.setTime(com.ezhihui.www.utils.DateUtils.StringToDate(batchCourse.getTime(), "yyyy-MM-dd HH:mm:ss"));
            course.setCourseTime(2.0);
            course.setClassroom("");
            course.setAddTime(new Date());
            course.setStatus(CourseStatusEnum.NOT_SIGNED.code);
            this.create(course);
        }

        for (BatchStudent.BatchCourse batchCourse : batchStudent.getDeletedCourseList()) {
            this.deleteById(batchCourse.getCourseId());
        }

        for (BatchStudent.BatchCourse batchCourse : batchStudent.getUpdatedCourseList()) {
            Course course = new Course();
            course.setId(batchCourse.getCourseId());
            course.setTeacherId(Integer.valueOf(batchCourse.getTeacherId()));
            this.update(course);
        }

        return new BaseResponse<>(Boolean.TRUE);
    }

    @Override
    public PageListResponse<Course> getList(Course course) {
        PageListResponse<Course> result = new PageListResponse<>();
        PageList<Course> pageList = new PageList<>();

        List<Course> list = this.getByCondi(course).getData();
        if (list == null || list.size() == 0) {
            result.setData(null);
            return result;
        }

        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Grade grade = gradeDAO.selectByPrimaryKey(list.get(i).getGradeId().intValue());
            list.get(i).setGradeName(grade.getGradeName());
            sum += list.get(i).getCourseTime();
        }

        pageList.setList(list);
        pageList.setPageIndex(1);
        pageList.setPageSize(list.size());
        pageList.setTotal(list.size());

        result.setData(pageList);
        result.setRequestId(sum + "课时");
        return result;
    }

    private StudentCourseCostRecord buildNewRecord(Course oldCourse, StudentCourseCostConfig config, Student student, Teacher teacher) {
        StudentCourseCostRecord record = new StudentCourseCostRecord();
        BeanUtils.copyProperties(oldCourse, record);

        record.setId(null);
        record.setCourseId(oldCourse.getId());
        record.setStudentName(student.getName());
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        record.setTeacherName(teacher.getName());
        record.setSubjectId(teacher.getSubjectId());
        record.setSubjectName(teacher.getSubjectName());

        if (config == null) {
            record.setCost(0);
            record.setType(FeeEnum.NO_CHARGE.value);
        } else {
            int fee = (int) Math.round(config.getCost() * oldCourse.getCourseTime());
            record.setCost(fee);
            record.setType(FeeEnum.DEDUCTION.value);
        }

        return record;
    }
}