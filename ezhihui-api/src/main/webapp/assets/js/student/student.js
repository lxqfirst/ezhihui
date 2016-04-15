/**
 * Created by lxq on 16/4/15.
 */

/**
 * 缓存学生信息
 */
var studentList = [];
var studentMap = {};

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

    },

    deleteStudent: function ($param) {
        var confirm_msg = "删除后将无法恢复！";
        if (!window.confirm(confirm_msg)) {
            return false;
        }
        var id = $param.parent().parent()[0].id;

        comJs.post("/student/deleteById?id=" + parseInt(id), null, "删除成功", false);
        studentManager.createList();
    }
}
