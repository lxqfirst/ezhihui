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
var timeList = ['08:00', '10:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00'];

/**
 * 日期常量
 * @type {Array}
 */
var dayHeadList = [];

/**
 *记录原始课程
 *
 * @type {Array}
 */
var oldCourseMap = {};

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
    },

    initBatchCourse: function () {
        //清空数据
        $('.student_auto').val('').removeAttr('disabled');
        $('#courseComment').remove();

        var today = new Date();
        var todayStr = today.Format("yyyy-MM-dd") + " 00:00:00";

        var sevenDayLater = new Date();
        sevenDayLater.setDate(today.getDate() + 6);
        var sevenDayLaterStr = sevenDayLater.Format("yyyy-MM-dd") + " 23:59:59";

        if (studentMap[$('#student').val()] === undefined) {
            alert("请输入学生姓名");
            return;
        }
        var studentId = studentMap[$('#student').val()].id;

        var param = "studentId=" + studentId + "&startTimeStr=" + todayStr + "&endTimeStr=" + sevenDayLaterStr;
        var result = comJs.getSync("/course/getList?" + param);
        if (result == null)
            return;

        var courseList = result.items;
        var info = '<div id="courseComment"><label>其他时间课程:</label><br>';

        for (var i = 0; i < courseList.length; i++) {
            var row = batchManager.getRowNumByTime(courseList[i].time);
            var col = batchManager.getColNumByTime(courseList[i].time);

            if (row != -1 && col != -1) {
                var id = 'teacher' + row + '_' + col;
                $('#' + id).val(courseList[i].teacherName);
                if (courseList[i].status == 1) {
                    $('#teacher' + row + '_' + col).attr('disabled', true);
                }
                oldCourseMap[id] = courseList[i];
            } else {
                // 待显示为备注区域
                info += '<label>' + courseList[i].teacherName + " " + (new Date(courseList[i].time)).Format("yyyy-MM-dd hh:mm:ss") + '</label>'
                info += "<br>";
            }
        }
        info += '</div>';
        $('#comment').append(info);

    },

    saveBatchCourse: function () {
        if (studentMap[$('#student').val()] === undefined) {
            alert("请输入学生姓名");
            return;
        }
        var studentId = studentMap[$('#student').val()].id;

        var newCourseList = new Array();
        var deletedCourseList = new Array();
        var updatedCourseList = new Array();

        $.each($('.student_auto'), function (n, value) {
            if (oldCourseMap[value.id] != undefined && oldCourseMap[value.id].status == 1)
                return;
            var text = $(value).val();
            var course = {};
            if (text !== '') {
                var teacherId = teacherMap[text].id;
                if (oldCourseMap[value.id] !== undefined && text !== oldCourseMap[value.id].teacherName) {
                    course.courseId = oldCourseMap[value.id].id;
                    course.teacherId = teacherId;
                    updatedCourseList.push(course)
                } else if (oldCourseMap[value.id] !== undefined && text === oldCourseMap[value.id].teacherName) {
                    return;
                } else {
                    course.teacherId = teacherId;
                    var row = value.id.split("_")[0].substring(7);
                    var col = value.id.split("_")[1];
                    course.time = dayHeadList[col] + " " + timeList[row] + ":00";
                    newCourseList.push(course);
                }
            } else {
                if (oldCourseMap[value.id] !== undefined) {
                    course.courseId = oldCourseMap[value.id].id;
                    deletedCourseList.push(course);
                }
            }
        });

        if (newCourseList.length == 0 && deletedCourseList.length == 0 && updatedCourseList.length == 0) {
            alert('没有进行任何排课');
            return;
        }

        var param = {
            studentId: studentId,
            newCourseList: newCourseList,
            deletedCourseList: deletedCourseList,
            updatedCourseList: updatedCourseList
        };

        comJs.post('/course/saveBatchCourse?courseInfo=' + JSON.stringify(param), null, "保存成功", false);
        batchManager.initBatchCourse();
        return;
    },

    // 获取数据所在的行
    getRowNumByTime: function (time) {
        var date = new Date(time);
        var row = -1;
        if (date.getMinutes() == 0 && date.getSeconds() == 0) {
            switch (date.getHours()) {
                case 8:
                    row = 0;
                    break;
                case 10:
                    row = 1;
                    break;
                case 13:
                    row = 2;
                    break;
                case 14:
                    row = 3;
                    break;
                case 15:
                    row = 4;
                    break;
                case 16:
                    row = 5;
                    break;
                case 17:
                    row = 6;
                    break;
                case 18:
                    row = 7;
                    break;
                case 19:
                    row = 8;
                    break;
                case 20:
                    row = 9;
                    break;
                default:
                    row = -1;
            }
        }
        return row;
    },

    //获取数据所在的列
    getColNumByTime: function (time) {
        var date = new Date(time);
        for (var i = 0; i < 7; i++) {
            var today = new Date();
            today.setDate(today.getDate() + i);
            if (today.getDay() == date.getDay()) {
                return i;
            }
        }
        return -1;
    }
}
