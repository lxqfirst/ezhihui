/**
 * Created by lxq on 16/4/15.
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
 * 时间常量
 * @type {string[]}
 */
var timeList = ['08:00', '10:00', '13:00', '15:00', '17:00', '18:00', '19:00', '20:00'];

/**
 * 日期常量
 * @type {Array}
 */
var dayHeadList = [];

var batchManager = {
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
    initTeacher: function () {
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
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    },

    initTableHead: function () {
        var rows = 0;
        $.each($('#tr_head').children(), function (n, value) {
            if (n != 0) {
                var today = new Date();
                today.setDate(today.getDate() + rows - 1);
                var week = '';

                switch (today.getDay()) {
                    case 0:
                        week = '星期日';
                        break;
                    case 1:
                        week = '星期一';
                        break;
                    case 2:
                        week = '星期二';
                        break;
                    case 3:
                        week = '星期三';
                        break;
                    case 4:
                        week = '星期四';
                        break;
                    case 5:
                        week = '星期五';
                        break;
                    case 6:
                        week = '星期六';
                        break;
                    default:
                        alert("系统错误");
                        return;
                }
                dayHeadList.push(today.Format("yyyy-MM-dd"));
                $(value).text(today.Format("yyyy-MM-dd") + " " + week);
            }
            rows++;
        });
    },

    createTable: function () {
        var trs = '';
        var id = 1;
        $.each(timeList, function (n, value) {
            var tr = '<tr><td style="font-weight: bold">' + value + '</td>';
            for (var i = 0; i < 7; i++) {
                var td_id = "teacher" + n + "_" + i;
                var td = '<td><input id="' + td_id + '" autocomplete="off" data-provide="typeahead" type="text" class="form-control student_auto" placeholder=""/></td>';
                tr += td
            }

            tr += '</tr>';
            trs += tr;
        });

        $("#com_tbody").html(trs);
        batchManager.fillTeacherAutoData();
    },

    fillTeacherAutoData: function () {
        $.each($('.student_auto'), function (n, value) {
            comJs.autoComplete(value.id, teacherList);
        });
    }
}