/**
 * Created by lxq on 16/4/18.
 */
/**
 * 缓存教师信息
 */
var teacherList = [];
var teacherMap = {};

var gradeMap = {};

var currentTeacherId = '';

var costManager = {
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
        if ($('#start_time_input').val() == '') {
            alert('请输入开始时间');
            return;
        }

        if ($('#end_time_input').val() == '') {
            alert('请输入结束时间');
            return;
        }

        var ss = {
            "pageSize": 20,
            "teacherId": costManager.getTeacherId('teacher'),
            "startTimeStr": $('#start_time_input').val(),
            "endTimeStr": $('#end_time_input').val(),
        };
        comJs.setParams(ss);
        comJs.createTable(1);
    },

    getTeacherId: function (id) {
        return teacherMap[$('#' + id).val()] === undefined ? "" : teacherMap[$('#' + id).val()].id;
    },


    transNull: function (param) {
        if (param === "" || param === "undefined" || param == "0" || param == "null") {
            return "&nbsp;";
        }

        if (param == "1") {
            return "课时费未设置";
        }
        return param;
    },

    showEditView: function ($a) {
        currentTeacherId = $a.parent().parent()[0].id;
        var result = comJs.getSync("/cost/getList?teacherId=" + currentTeacherId);
        $.each(result, function (n, value) {
            costManager.modelAdd(value);
        });
        var grades = comJs.getSync("/grade/getGradeNameByTeacherId?teacherId=" + currentTeacherId);
        var gradeText = '';
        $.each(grades, function (n, value) {
            gradeText += "," + value;
        });
        $('#grades').html(gradeText.substring(1));
        $('#myModalLabel').html('财务设置·' + $a.parent().parent().children()[1].innerHTML);
        $('#myModal').modal('show');
    },

    modelAdd: function (value) {
        var subjectId = 'subject' + $('.subject').length;
        var content = '<div class="row modelRow">';
        content += '<div class="col-lg-4">' +
            '<div class="input-group">' +
            '<span class="input-group-addon"> 年级 </span> <select class="form-control subject" id="' + subjectId + '" ></select>' +
            '</div></div>';

        var costId = 'cost' + $('.subject').length;
        content += '<div class="col-lg-4">' +
            '<div class="input-group">' +
            '<span class="input-group-addon"> 课时费/时 </span>' +
            '<input type="text" class="form-control" id="' + costId + '"' +
            'aria-label="..." required message="课时费">' +
            '</div>' +
            '</div>';
        content += '<div class="col-lg-1">' +
            '<div>' +
            '<button type="button" class="btn btn-info" aria-label="Left Align" onclick="costManager.deleteModelCost($(this))">' +
            '<span class="glyphicon" aria-hidden="true"></span> 删除' +
            '</button>' +
            '</div>' +
            '</div>';
        content += '</div><div class="clearfix modelClearFix" style="margin-bottom: 10px;"></div>';
        $('#model_panel').append(content);
        costManager.createGradeInfo(subjectId);

        if (value != null) {
            $('#' + costId).val(value.cost);
            $('#' + subjectId + ' option[value=' + value.gradeId + ']').prop('selected', 'selected');
        }
    },

    create: function () {
        if (!validJs.triggerValid()) {
            return;
        }

        var gradeIds = '';
        var costs = '';
        $.each($('.subject'), function (n, value) {
            gradeIds += $(value).val() + ",";
            costs += $(value).parent().parent().next().find('input').val() + ",";
        });
        var url = "/cost/update?teacherId=" + currentTeacherId + "&gradeIds=" + gradeIds + "&costs=" + costs;
        comJs.post(url, null, "更新成功", false);
        $('#myModal').modal('hide');
        costManager.clearModel();
        costManager.createList();
    },

    clearModel: function () {
        $('.modelRow').remove();
        $('.modelClearFix').remove()
    },

    hideModel: function () {
        costManager.clearModel();
        $('#myModal').modal('hide');
    },

    createGradeInfo: function (id) {
        var option = "";

        $.each(gradeMap, function (n, value) {
            option += '<option value="' + value + '">' + n + '</option>'
        });
        $('#' + id).append(option);
    },

    initGrade: function () {
        var result = comJs.getSync("/grade/getList");
        $.each(result, function (n, value) {
            gradeMap[value.gradeName] = value.id;
        });
    },

    deleteModelCost: function ($a) {
        $a.parent().parent().parent().next().remove();
        $a.parent().parent().parent().remove();
    },

    download: function () {
        if ($('#start_time_input').val() == '') {
            alert('请输入开始时间');
            return;
        }

        if ($('#end_time_input').val() == '') {
            alert('请输入结束时间');
            return;
        }

        var ss = {
            "pageSize": 20,
            "teacherId": costManager.getTeacherId('teacher'),
            "startTimeStr": $('#start_time_input').val(),
            "endTimeStr": $('#end_time_input').val(),
        };


        window.location.href = "/cost/exportCost?startTimeStr=" + ss.startTimeStr
            + "&endTimeStr=" + ss.endTimeStr + "&teacherId=" + ss.teacherId;
    }
}