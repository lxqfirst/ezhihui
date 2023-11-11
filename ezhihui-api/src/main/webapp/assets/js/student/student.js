/**
 * Created by lxq on 16/4/15.
 */

/**
 * 缓存学生信息
 */
var studentList = [];
var studentMap = {};

/**
 * 年级Map
 *
 * @type {{}}
 */
var gradeMap = {};

var currentStudentId;

var studentManager = {
    initStudent: function (id) {
        var url = "/student/getList";
        $.ajax({
            url: url,
            contentType: 'application/json',
            type: 'get',
            success: function (data) {
                if (data.code == 0) {
                    $.each(data.data, function (n, value) {
                        studentList.push(value.name);
                        studentMap[value.name] = value;
                    });
                    comJs.autoComplete(id, studentList);
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    },

    initStudentWithoutHttp: function (id) {
        comJs.autoComplete(id, studentList);
    },
    createList: function () {
        var ss = {
            "pageSize": 20,
            "id": studentManager.getStudentId('student'),
        };
        comJs.setParams(ss);
        comJs.createTable(1);
    },
    getStudentId: function (id) {
        return studentMap[$('#' + id).val()] === undefined ? "" : studentMap[$('#' + id).val()].id;
    },

    transNull: function (param) {
        if (param === "" || param === "undefined") {
            return "&nbsp;";
        }

        return param;
    },

    showEditView: function ($param) {
        studentManager.clearModel();
        currentStudentId = $param.parent().parent()[0].id;

        $('#nameNew').val($param.parent().parent().children()[1].innerHTML);
        if ($param.parent().parent().children()[4].innerHTML != "&nbsp;")
            $('#schoolNew').val($param.parent().parent().children()[4].innerHTML);
        if ($param.parent().parent().children()[5].innerHTML != "&nbsp;")
            $('#phoneNew').val($param.parent().parent().children()[5].innerHTML);
        if ($param.parent().parent().children()[6].innerHTML != "&nbsp;")
            $('#parentPhoneNew').val($param.parent().parent().children()[6].innerHTML);
        if ($param.parent().parent().children()[7].innerHTML != "&nbsp;")
            $('#fundNew').val($param.parent().parent().children()[7].innerHTML);
        var text = $param.parent().parent().children()[3].innerHTML;
        if (text != "&nbsp;")
            $('#classNameNew').val(text);
        var gradeName = $param.parent().parent().children()[2].innerHTML;
        $('option[value=' + gradeMap[gradeName] + ']').prop('selected', 'selected');

        $('#updateButton').show();
        $('#createButton').hide();
        $('#continueButton').hide();
        $('#myModal').modal('show');
    },

    deleteStudent: function ($param) {
        var confirm_msg = "删除后将无法恢复！";
        if (!window.confirm(confirm_msg)) {
            return false;
        }
        var id = $param.parent().parent()[0].id;

        comJs.post("/student/deleteById?id=" + parseInt(id), null, "删除成功", false);
        studentManager.createList();
    },

    showCreateView: function ($a) {
        studentManager.clearModel();
        $('#updateButton').hide();
        $('#createButton').show();
        $('#continueButton').show();
        $('#myModal').modal('show');
    },

    initGrade: function () {
        var result = comJs.getSync("/grade/getList");
        var option = "";
        $.each(result, function (n, value) {
            gradeMap[value.gradeName] = value.id;
            option += '<option value="' + value.id + '">' + value.gradeName + '</optionvalue>'
        });

        $('#grade').append(option);
    },

    create: function (flag) {
        if (!validJs.triggerValid()) {
            return;
        }

        var param = {
            "name": $('#nameNew').val(),
            "className": $('#classNameNew').val(),
            "school": $('#schoolNew').val(),
            "telephone": $('#phoneNew').val(),
            "fund": $('#fundNew').val(),
            "telephoneParent": $('#parentPhoneNew').val(),
            "grade": parseInt(document.getElementById('grade')[document.getElementById('grade').selectedIndex].value)
        };

        comJs.post("/student/create", param, "新加学生成功", false);
        studentManager.clearModel();
        studentManager.createList();
        if (flag == 0) {
            $('#myModal').modal('hide');
        }
    },
    clearModel: function () {
        $('#nameNew').val('');
        $('#schoolNew').val('');
        $('#phoneNew').val('');
        $('#parentPhoneNew').val('');
        $('#classNameNew').val('');
        $('#fundNew').val('');
        $('option:first').prop('selected', 'selected');
    },
    update: function () {
        if (!validJs.triggerValid()) {
            return;
        }

        var param = {
            "id": currentStudentId,
            "name": $('#nameNew').val(),
            "className": $('#classNameNew').val(),
            "school": $('#schoolNew').val(),
            "telephone": $('#phoneNew').val(),
            "telephoneParent": $('#parentPhoneNew').val(),
            "fund": $('#fundNew').val(),
            "grade": parseInt(document.getElementById('grade')[document.getElementById('grade').selectedIndex].value)
        };

        comJs.post("/student/update", param, "更新学生成功", false);
        studentManager.clearModel();
        studentManager.createList();
        $('#myModal').modal('hide');
    },

    showFeeEditView: function ($param) {
        studentManager.clearModel4FeeEdit();
        currentStudentId = $param.parent().parent()[0].id;

        result = comJs.getSync("/student/getFeeConfigList?studentId=" + currentStudentId);
        $.each(result, function (n, value) {
            studentManager.modelAdd(value);
        });

        $('#nameNew4Fee').val($param.parent().parent().children()[1].innerHTML);
        $('#myModal4FeeEdit').modal('show');
    },

    update4FeeEdit: function () {
        list = $('.modelRow');
        feeResult = new Array();
        for (i = 0; i < list.length; i++) {
            teacherName = list[i].children[0].children[0].children[1].value;
            cost = list[i].children[1].children[0].children[1].value;

            ss = {
                teacherName: teacherName,
                cost: cost,
                teacherId: teacherMap[teacherName].id,
                studentId: currentStudentId,
                subjectId: teacherMap[teacherName].subjectId
            };

            feeResult.push(ss)
        }

        comJs.post('/student/saveBatchFeeConfig?feeConfigInfo=' + encodeURI(JSON.stringify(feeResult)), null, "保存成功", false);
        studentManager.clearModel4FeeEdit();
        $('#myModal4FeeEdit').modal('hide');
    },

    clearModel4FeeEdit: function () {
        $('.modelRow').remove();
        $('.modelClearFixFee').remove();
    },

    deleteModel: function ($a) {
        $a.parent().parent().parent().next().remove();
        $a.parent().parent().parent().remove();
    },

    modelAddItem: function () {
        var timeStamp = new Date().getTime();
        var content = '<div class="row modelRow">';
        content += '<div class="col-lg-4">' +
            '<div class="input-group">' +
            '<span class="input-group-addon"> 教师 </span>' +
            '        <input id="teacher' + timeStamp + '" autoComplete="off" data-provide="typeahead" type="text"' +
            '               class="form-control" required placeholder=""/>' +
            '</div>' +
            '</div>';
        teacherManager.initTeacher('teacher' + timeStamp);

        // content += '<div class="col-lg-3">' +
        //     '<div class="input-group">' +
        //     '<span class="input-group-addon"> 学科 </span>' +
        //     '<input type="text" class="form-control" id="' + value.subjectId + '"' +
        //     'aria-label="..." required message="课时费" disabled value="' + value.subjectName + '">' +
        //     '</div>' +
        //     '</div>';

        content += '<div class="col-lg-5">' +
            '<div class="input-group">' +
            '<span class="input-group-addon"> 课时费/小时(元) </span>' +
            '<input type="text" class="form-control"' +
            'aria-label="..." required message="课时费">' +
            '</div>' +
            '</div>';

        content += '<div class="col-lg-1">' +
            '<div>' +
            '<button type="button" class="btn btn-info" aria-label="Left Align" onclick="studentManager.deleteModel($(this))">' +
            '<span class="glyphicon" aria-hidden="true"></span> 删除' +
            '</button>' +
            '</div>' +
            '</div>';
        content += '</div><div class="clearfix modelClearFixFee" style="margin-bottom: 10px;"></div>';
        $('#model_panel').append(content);
    },

    modelAdd: function (value) {
        var content = '<div class="row modelRow">';
        content += '<div class="col-lg-4">' +
            '<div class="input-group">' +
            '<span class="input-group-addon"> 教师 </span>' +
            '<input id="teacher' + value.teacherId + '" autoComplete="off" data-provide="typeahead" type="text"' +
            'class="form-control" required placeholder="" value="' + value.teacherName + '"/>' +
            '</div>' +
            '</div>';

        teacherManager.initTeacher('teacher' + value.teacherId);


        // content += '<div class="col-lg-3">' +
        //     '<div class="input-group">' +
        //     '<span class="input-group-addon"> 学科 </span>' +
        //     '<input type="text" class="form-control" id="' + value.subjectId + '"' +
        //     'aria-label="..." required message="课时费" disabled value="' + value.subjectName + '">' +
        //     '</div>' +
        //     '</div>';

        content += '<div class="col-lg-5">' +
            '<div class="input-group">' +
            '<span class="input-group-addon"> 课时费/小时(元) </span>' +
            '<input type="text" class="form-control" id=cost"' + value.cost + '"' +
            'aria-label="..." required message="课时费" value="' + value.cost + '">' +
            '</div>' +
            '</div>';

        content += '<div class="col-lg-1">' +
            '<div>' +
            '<button type="button" class="btn btn-info" aria-label="Left Align" onclick="studentManager.deleteModel($(this))">' +
            '<span class="glyphicon" aria-hidden="true"></span> 删除' +
            '</button>' +
            '</div>' +
            '</div>';
        content += '</div><div class="clearfix modelClearFixFee" style="margin-bottom: 10px;"></div>';
        $('#model_panel').append(content);
    },

    costDetail: function ($a) {
        window.open("/student/costDetailView");
    },

    setLastMonth: function () {
        var startDate = new Date();
        startDate.setDate(1);
        startDate.setMonth(startDate.getMonth() - 1);
        $('#start_time_input').val(startDate.Format("yyyy-MM-dd") + " 08:00:00");

        var endDate = new Date();
        endDate.setDate(1);
        endDate.setDate(endDate.getDate() - 1);
        $('#end_time_input').val(endDate.Format("yyyy-MM-dd") + " 23:00:00");
        course.createList();
    },

    createCostDetailList: function () {
        var type = $("#query-status").attr("menu-value");
        if (type === undefined || type == -1) {
            type = "";
        }
        var ss = {
            "pageSize": 10,
            "startTimeStr": $('#start_time_input').val(),
            "endTimeStr": $('#end_time_input').val(),
            "studentId": studentManager.getStudentId('student'),
            "teacherId": teacherManager.getTeacherId('teacher'),
            "type": type,
            "courseType": $('#query-type').attr("menu-value")
        };
        comJs.setParams(ss);
        comJs.createTable(1);
    },
    transCourseType: function (courseType) {
        if (courseType == 1) {
            return "文化课"
        }

        if (courseType == 2) {
            return "美术课"
        }
    },

    transType: function (type) {
        if (type == 1) {
            return "已扣款"
        }

        if (type == 2) {
            return "签到取消后退款"
        }

        if (type == 3) {
            return "删除课程后退款"
        }

        if (type == 4) {
            return "未计费"
        }
    },

}
