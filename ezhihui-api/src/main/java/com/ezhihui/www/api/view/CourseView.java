package com.ezhihui.www.api.view;

import com.ezhihui.www.domain.Course;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by lxq on 16/4/14.
 */
public class CourseView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook hssfWorkbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        HSSFSheet excelSheet = hssfWorkbook.createSheet("sheet1");
        setExcelHeader(excelSheet);

        List<Course> brandList = (List<Course>) map.get("courseList");
        setExcelRows(excelSheet, brandList);
    }

    /**
     * 设置头标题
     *
     * @param excelSheet
     */
    public void setExcelHeader(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("学生");
        excelHeader.createCell(1).setCellValue("教师");
        excelHeader.createCell(2).setCellValue("学科");
        excelHeader.createCell(3).setCellValue("课时长");
        excelHeader.createCell(4).setCellValue("上课时间");
        excelHeader.createCell(5).setCellValue("教室");
        excelHeader.createCell(6).setCellValue("签到状态");
    }

    public void setExcelRows(HSSFSheet excelSheet, List<Course> courseList) {
        int record = 1;
        for (Course course : courseList) {
            HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(course.getStudentName());
            excelRow.createCell(1).setCellValue(course.getTeacherName());
            excelRow.createCell(2).setCellValue(course.getSubjectName());
            excelRow.createCell(3).setCellValue(course.getCourseTime());
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            excelRow.createCell(4).setCellValue(sf.format(course.getTime()));
            excelRow.createCell(5).setCellValue(course.getClassroom());
            excelRow.createCell(6).setCellValue(course.getStatus() == 0 ? "未签到" : "已签到");
        }
    }
}
