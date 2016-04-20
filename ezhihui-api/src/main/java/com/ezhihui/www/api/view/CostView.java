package com.ezhihui.www.api.view;

import com.ezhihui.www.domain.Course;
import com.ezhihui.www.domain.Teacher;
import com.ezhihui.www.enums.CommonCode;
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
public class CostView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook hssfWorkbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        HSSFSheet excelSheet = hssfWorkbook.createSheet("sheet1");
        setExcelHeader(excelSheet);

        List<Teacher> teacherList = (List<Teacher>) map.get("teacherList");
        setExcelRows(excelSheet, teacherList);
    }

    /**
     * 设置头标题
     *
     * @param excelSheet
     */
    public void setExcelHeader(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("教师");
        excelHeader.createCell(1).setCellValue("薪水");
        excelHeader.createCell(2).setCellValue("备注");
    }

    public void setExcelRows(HSSFSheet excelSheet, List<Teacher> teacherList) {
        int record = 1;
        for (Teacher t : teacherList) {
            HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(t.getName());
            if (t.getSalary() != null)
                excelRow.createCell(1).setCellValue(t.getSalary());
            if (t.getComment() == CommonCode.SALAY_PARAM_ERROR.code) {
                excelRow.createCell(2).setCellValue("课时费未设置");
            }
        }
    }
}
