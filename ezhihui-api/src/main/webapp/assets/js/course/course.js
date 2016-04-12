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
                    course.autoComplete("teacher", teacherList);
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    },

    autoComplete: function (inputButtonId, array) {
        $("#" + inputButtonId).typeahead({
            source: array,
            items: 8,//最多显示个数
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
    }
}