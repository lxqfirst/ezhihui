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
            "grade": parseInt(document.getElementById('grade')[document.getElementById('grade').selectedIndex].value)
        };

        comJs.post("/student/update", param, "更新学生成功", false);
        studentManager.clearModel();
        studentManager.createList();
        $('#myModal').modal('hide');
    }
}
