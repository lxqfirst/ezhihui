/**
 * Created by lxq on 16/4/11.
 */
/**
 * 缓存教师信息
 */
var teacherList = [];
var teacherMap = {};

/**
 * 缓存学生信息
 */
var studentList = [];
var studentMap = {};

/**
 * 当前更新的课程Id
 */
var currentCourseId;

var course = {
    initTeacher: function (id) {
        var url = "/teacher/getList";
        $.ajax({
            url: url,
            contentType: 'application/json',
            type: 'get',
            success: function (data) {
                if (data.code == 0) {
                    $.each(data.data, function (n, value) {
                        teacherList.push(value.name);
                        teacherMap[value.name] = value;
                    });
                    course.autoComplete(id, teacherList);
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    },

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
                    course.autoComplete(id, studentList);
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
        course.autoComplete(id, studentList);
    },

    initTeacherWithoutHttp: function (id) {
        course.autoComplete(id, teacherList);
    },

    autoComplete: function (inputButtonId, array) {
        $("#" + inputButtonId).typeahead({
            source: array,
            items: 15,//最多显示个数
            updater: function (item) {
                return item;//这里一定要return，否则选中不显示，外加调用display的时候null reference错误。
            },
            displayText: function (item) {
                return item;//返回字符串
            },
            afterSelect: function (item) {
                //选择项之后的事件 ，item是当前选中的。
            },
            delay: 100//延迟时间
        });
        //$("#look_up_array").click(function () {
        //    $("#local_data").typeahead("lookup", '');
        //});
        //$("#get_value_array").click(function () {
        //    var val = $("#local_data").typeahead("getActive");
        //    console.log(val);
        //});
    },

    createList: function () {
        var status = $("#query-status").attr("menu-value");
        if (status === undefined || status == -1) {
            status = "";
        }
        var ss = {
            "pageSize": 10,
            "startTimeStr": $('#start_time_input').val(),
            "endTimeStr": $('#end_time_input').val(),
            "studentId": course.getStudentId('student'),
            "teacherId": course.getTeacherId('teacher'),
            "status": status
        };
        comJs.setParams(ss);
        comJs.createTable(1);
    },

    setToday: function () {
        $('#start_time_input').val(new Date().Format("yyyy-MM-dd") + " 08:00:00");
        $('#end_time_input').val(new Date().Format("yyyy-MM-dd") + " 23:00:00");
        course.createList();
    },

    setTomorrow: function () {
        var startDay = new Date();
        startDay.setDate(startDay.getDate() + 1);
        $('#start_time_input').val(startDay.Format("yyyy-MM-dd") + " 08:00:00");

        var endDay = new Date();
        endDay.setDate(endDay.getDate() + 1);
        $('#end_time_input').val(endDay.Format("yyyy-MM-dd") + " 23:00:00");
        course.createList();
    },

    showCreateCourseView: function () {
        $('#updateButton').hide();
        $('#createButton').show();
        $('#continueButton').show();
        $('#myModal').modal('show');
    },

    getStudentId: function (id) {
        return studentMap[$('#' + id).val()] === undefined ? "" : studentMap[$('#' + id).val()].id;
    },

    getTeacherId: function (id) {
        return teacherMap[$('#' + id).val()] === undefined ? "" : teacherMap[$('#' + id).val()].id;
    },

    /**
     * flag = 0 隐藏弹出框, flag = 1 不隐藏弹出框
     * @param flag
     */
    createCourse: function (flag) {
        if (!validJs.triggerValid()) {
            return;
        }

        var param = {
            "studentId": course.getStudentId('studentNew'),
            "teacherId": course.getTeacherId('teacherNew'),
            "courseTime": $('#courseTimeNew').val(),
            "timeStr": $('#timeNew').val(),
            "classroom": $('#classroomNew').val()
        };

        comJs.post("/course/create", param, "课程新建成功", false);
        course.clearModel();
        course.createList();
        if (flag == 0) {
            $('#myModal').modal('hide');
        }
    },

    clearModel: function () {
        $('#courseTimeNew').val('2');
        $('#timeNew').val('');
        $('#studentNew').val('');
        $('#teacherNew').val('');
        $('#classroomNew').val('');
    },

    updateCourse: function () {
        if (!validJs.triggerValid()) {
            return;
        }

        var param = {
            "id": currentCourseId,
            "studentId": course.getStudentId('studentNew'),
            "teacherId": course.getTeacherId('teacherNew'),
            "courseTime": $('#courseTimeNew').val(),
            "timeStr": $('#timeNew').val(),
            "classroom": $('#classroomNew').val()
        };
        comJs.post("/course/update", param, "课程更新成功", false);
        course.clearModel();
        course.createList();
        $('#myModal').modal('hide');
    },

    transStatus: function (status) {
        if (status == 0) {
            return "<img src='/assets/images/fail.png' style='width:16px' title='未签到'/>";
        } else {
            return "<img src='/assets/images/ok.png' style='width:16px' title='已签到'/>";
        }

    },

    transClassroom: function (classroom) {
        if (classroom === "") {
            return "-"
        }

        return classroom;
    },

    checkAll: function () {
        //全部选择
        if ($('#checkAll').prop('checked')) {
            $.each($('.check'), function (n, value) {
                $(value).prop('checked', true);
            });
        } else {
            $.each($('.check'), function (n, value) {
                $(value).prop('checked', false);
            });
        }
    },

    deleteCourse: function ($a) {
        var confirm_msg = "删除后将无法恢复！";
        if (!window.confirm(confirm_msg)) {
            return false;
        }
        var id = $a.parent().parent()[0].id;

        comJs.post("/course/deleteById?id=" + parseInt(id), null, "删除成功", false);
        course.createList();
    },

    signInCourse: function ($a) {
        var id = $a.parent().parent()[0].id;
        var src = $a.parent().prev().children()[0].src;
        var param = {
            id: parseInt(id)
        };

        if (src.endsWith('fail.png')) {
            param.status = 1;
        } else {
            param.status = 0;
        }

        comJs.post("/course/update", param, "操作成功", false);
        if (param.status == 1) {
            $a.parent().prev().children()[0].src = '/assets/images/ok.png';
        } else {
            $a.parent().prev().children()[0].src = '/assets/images/fail.png';
        }
    },

    showEditView: function ($a) {
        $('#courseTimeNew').val($a.parent().parent().children()[4].innerHTML);
        $('#timeNew').val($a.parent().parent().children()[5].innerHTML);
        $('#studentNew').val($a.parent().parent().children()[1].innerHTML);
        $('#teacherNew').val($a.parent().parent().children()[2].innerHTML);

        if ($a.parent().parent().children()[6].innerHTML != "-")
            $('#classroomNew').val($a.parent().parent().children()[6].innerHTML);
        currentCourseId = $a.parent().parent()[0].id;

        $('#updateButton').show();
        $('#createButton').hide();
        $('#continueButton').hide();
        $('#myModal').modal('show');
    }
}