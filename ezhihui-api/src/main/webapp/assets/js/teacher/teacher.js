/**
 * Created by lxq on 16/4/16.
 */

/**
 * 缓存教师信息
 */
var teacherList = [];
var teacherMap = {};

var subjectMap = {};

var currentStudentId;

var teacherManager = {
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
                    comJs.autoComplete(id, teacherList);
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    },

    createList: function () {
        var ss = {
            "pageSize": 20,
            "id": teacherManager.getTeacherId('teacher')
        };
        comJs.setParams(ss);
        comJs.createTable(1);
    },

    getTeacherId: function (id) {
        return teacherMap[$('#' + id).val()] === undefined ? "" : teacherMap[$('#' + id).val()].id;
    },

    transNull: function (param) {
        if (param === "" || param === "undefined") {
            return "&nbsp;";
        }

        return param;
    },

    deleteStudent: function ($param) {
        var confirm_msg = "删除后将无法恢复！";
        if (!window.confirm(confirm_msg)) {
            return false;
        }
        var id = $param.parent().parent()[0].id;

        comJs.post("/teacher/deleteById?id=" + parseInt(id), null, "删除成功", false);
        teacherManager.createList();
    },

    initSubject: function () {
        var result = comJs.getSync("/subject/getList");
        var option = "";
        $.each(result, function (n, value) {
            gradeMap[value.gradeName] = value.id;
            option += '<option value="' + value.id + '">' + value.subjectName + '</optionvalue>'
        });

        $('#subject').append(option);
    },
}
