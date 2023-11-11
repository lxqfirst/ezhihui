/**
 * Created by lxq on 16/4/16.
 */

/**
 * 缓存教师信息
 */
var teacherMap = {};

var subjectMap = {};

var currentTeacherId;

var teacherManager = {
    initTeacher: function (id) {
        teacherList = [];
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
            subjectMap[value.subjectName] = value.id;
            option += '<option value="' + value.id + '">' + value.subjectName + '</optionvalue>'
        });

        $('#subject').append(option);
    },

    create: function (flag) {
        if (!validJs.triggerValid()) {
            return;
        }

        var param = {
            "name": $('#nameNew').val(),
            "telephone": $('#phoneNew').val(),
            "subjectId": parseInt(document.getElementById('subject')[document.getElementById('subject').selectedIndex].value)
        };

        comJs.post("/teacher/create", param, "新加教师成功", false);
        teacherManager.clearModel();
        teacherManager.createList();
        if (flag == 0) {
            $('#myModal').modal('hide');
        }
    },


    showCreateView: function ($a) {
        teacherManager.clearModel();
        $('#updateButton').hide();
        $('#createButton').show();
        $('#continueButton').show();
        $('#myModal').modal('show');
    },

    clearModel: function () {
        $('#nameNew').val('');
        $('#phoneNew').val('');
        $('option:first').prop('selected', 'selected');
    },

    showEditView: function ($param) {
        teacherManager.clearModel();
        currentTeacherId = $param.parent().parent()[0].id;

        $('#nameNew').val($param.parent().parent().children()[1].innerHTML);
        if ($param.parent().parent().children()[3].innerHTML != "&nbsp;")
            $('#phoneNew').val($param.parent().parent().children()[3].innerHTML);

        var subject = $param.parent().parent().children()[2].innerHTML;

        $('option[value=' + subjectMap[subject] + ']').prop('selected', 'selected');

        $('#updateButton').show();
        $('#createButton').hide();
        $('#continueButton').hide();
        $('#myModal').modal('show');
    },

    update: function () {
        if (!validJs.triggerValid()) {
            return;
        }

        var param = {
            "id": currentTeacherId,
            "name": $('#nameNew').val(),
            "telephone": $('#phoneNew').val(),
            "subjectId": parseInt(document.getElementById('subject')[document.getElementById('subject').selectedIndex].value)
        };

        comJs.post("/teacher/update", param, "更新教师成功", false);
        teacherManager.clearModel();
        teacherManager.createList();
        $('#myModal').modal('hide');
    }
}
