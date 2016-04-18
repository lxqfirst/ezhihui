/**
 * Created by lxq on 16/4/18.
 */
/**
 * 缓存教师信息
 */
var teacherList = [];
var teacherMap = {};

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
        if($('#start_time_input').val() == ''){
            alert('请输入开始时间');
            return;
        }

        if($('#end_time_input').val() == ''){
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
}