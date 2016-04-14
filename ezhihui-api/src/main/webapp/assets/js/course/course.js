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

    initStudentWithoutHttp : function(id){
        course.autoComplete(id, studentList);
    },

    initTeacherWithoutHttp :function(id){
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
        }

        comJs.post("/course/create", param, "课程新建成功", false);
        course.createList();
        $('#courseTimeNew').val('');
        $('#timeNew').val('');
        $('#studentNew').val('');
        $('#teacherNew').val('');
        $('#classroomNew').val('');
        if (flag == 0) {
            $('#myModal').modal('hide');
        }
    },

    updateCourse: function () {

    },

    transStatus: function (status) {
        if (status == 0) {
            return "<img src='/assets/images/fail.png' style='width:16px' title='已见到'/>";
        } else {
            return "<img src='/assets/images/ok.png' style='width:16px' title='已见到'/>";
        }

    },

    transClassroom: function (classroom) {
        if (classroom === "") {
            return "-"
        }

        return classroom;
    }
}