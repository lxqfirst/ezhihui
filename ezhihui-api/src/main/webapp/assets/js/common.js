/**
 * 公共函数库
 */
// var COM_URL_GETLIST;
document.write('<script src="/assets/js/jquery-confirm.min.js"></script>');// 引入弹出框js文件
document
    .write('<link href="/assets/css/jquery-confirm.min.css"rel="stylesheet">');// 引入弹出框css文件
/**
 * 重写alert方法
 */
function alert(msg, flag) {
    var methodName = "";
    if (flag == 1) {
        methodName = 'window.location.reload()';//刷新当前页面
    }
    if (flag == 2) {
        methodName = 'window.history.back(-1)';//回退到上一个页面
    }
    $.alert({
        icon: 'glyphicon glyphicon-hand-right',
        title: 'message',
        content: msg,
        theme: 'black',
        animation: 'RotateX',
        confirmButtonClass: 'btn-info',
        confirmButton: '确认',
        confirm: function () {
            eval(methodName);// 刷新当前页面
        }
    });
}

/**
 * 第三方接口使用
 */
var getClickPageThird = function (pageNo) {
    comJs.createTableThird(pageNo);

}

var getClickPage = function (pageNo) {
    comJs.createTable(pageNo);

}

var COM_PARAMS_GETLIST = new Object();
var comJs = {


    /**
     * 添加下拉操作按钮 for business_query
     *
     * @returns {String}
     */
    appendOpForBusiness: function () {
        var opTd = '<td> <div class="btn-group">';
        opTd += '<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">操作<span class="caret"></span></button>';
        opTd += '<ul class="dropdown-menu">'
        opTd += '<li><a href="#" onclick="businessQueryJs.getDetail($(this),'// 商家详情
        opTd += "'/business/updateView'";
        opTd += ')">详情</a></li>'// 商家详情end
        opTd += '<li><a onclick="businessQueryJs.getDetail($(this),'// 充值明细
        opTd += "'/trade/chargeDetailView'";
        opTd += ')">充值明细</a></li>'// 充值明细end
        opTd += '<li><a onclick="businessQueryJs.getDetail($(this),'// 消费明细
        opTd += "'/trade/consumerDetailView'";
        opTd += ')">消费明细</a></li>'// 消费明细end
        /*opTd += '<li><a onclick="businessQueryJs.getDetail($(this),'// 收款明细
         opTd += "'/trade/collectionDetailView'";
         opTd += ')">收款明细</a></li>'// 收款明细end*/
        opTd += '<li><a onclick="businessQueryJs.getConfirm($(this))">激活/禁用</a></li></ul>';
        opTd += '</div></td>';
        return opTd;
    },
    /**
     * 格式化时间
     */
    formmatDate: function (gmtTime) {
        var datetime = new Date();
        datetime.setTime(gmtTime);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0"
        + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
            : datetime.getDate();
        var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
            : datetime.getHours();
        var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
            : datetime.getMinutes();
        var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
            : datetime.getSeconds();
        return year + "-" + month + "-" + date + " " + hour + ":" + minute
            + ":" + second;
    },

    formmatNum: function (num) {
        return num.toString();
    },
    /**
     * 格式化状态
     *
     * @param status
     * @returns {String}
     */
    statusFormat: function (status) {
        if (status == 1)
            return "正常";
        return "禁用";
    },

    /**
     * 构造COM_URL_GETLIST参数，从json转成？field=value的形式
     *
     * @param paramMap
     */
    setParams: function (paramJson) {
        var params = '&';
        $.each(paramJson, function (name, value) {
            if (value != "" && value != null)
                params += name + "=" + value + "&";
        });
        if (params != '&') {
            var pos = params.lastIndexOf("&");
            params = params.substring(0, pos);
        } else {
            params = "";
        }
        COM_PARAMS_GETLIST = params;
    },
    /**
     * 创建下拉框
     */
    createDropDownMenu: function () {
        var menus = $(".myDrop");
        menus.each(function () {
            var $menu = $(this);
            var dropUrl = $menu.attr("drop-url");
            if (dropUrl == null) {
                return;
            }
            if (dropUrl != null) {
                var aj = $.ajax({
                    url: dropUrl,
                    contentType: 'application/json',
                    type: 'get',
                    async: false,
                    success: function (data) {
                        if (data.code == 0) {
                            comJs.dropMenuSuppot(data.data, $menu);
                        } else {
                            alert("create dropDownMenu error");
                        }
                    },
                    error: function () {
                        // view("异常！");
                        alert("异常！");
                    }
                });
            }
        });
    },
    /**
     * createDropDownMenu的工具方法
     */
    dropMenuSuppot: function (data, $menu) {
        var liTabs = '';
        $.each(data, function (name, value) {
            if (value["id"] == null && value["type"] != null) {
                value["id"] = value["type"];
            }
            if (value["code"] != null) {// 判断是否是city
                value["id"] = value["code"];
            }
            if (value["businessId"] == null && value["groupId"] != null) {// 判断是否是group，真是艰难
                value["id"] = value["groupId"];
            }
            if (value["name"] == null && value["groupName"] != null) {
                value["name"] = value["groupName"];
            }
            liTabs += '<li><a onclick="comJs.menuOnclick(this)" attrId='
                + value["id"] + '>' + value["name"] + '</a></li>';
        });
        $menu.append(liTabs);
    },
    /**
     * 将每列的id和name放到button的自定义属性menu-value和menu-text中
     *
     * @param $a
     */
    menuOnclick: function (hobj) {
        var $a = $(hobj);
        var menuName = $a.html();
        var menuValue = $a.attr("attrId");
        var menuText = menuName;
        if (menuValue == null) {
            menuText = null;
            menuValue = null;
        }
        var menu = $a.parent().parent().prev();
        $(menu).attr("menu-value", menuValue);
        $(menu).attr("menu-text", menuText);
        menuName += '<span class="caret"> </span>';
        $(menu).html(menuName);
    },

    /**
     * 获取页面数据 需要在table标签中加id="page_table" table_url="访问list的url,不带参数"
     *
     * @param pageIndex
     */
    createTable: function (pageIndex) {
        var getTimestamp = new Date().getTime();
        COM_PARAMS_GETLIST.pageIndex = pageIndex;
        COM_URL_GETLIST = $("#page_table").attr("table_url") + "?pageIndex="
            + pageIndex + COM_PARAMS_GETLIST + "&timeStamp=" + getTimestamp;
        var aj = $.ajax({
            url: COM_URL_GETLIST,
            contentType: 'application/json',
            type: 'get',
            success: function (data) {
                if (data.code == 0) {
                    comJs.getTable(data.data.items);
                    comJs.getPage(data.data);
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                // view("异常！");
                alert("异常！");
            }
        });
    },

    /**
     * 针对第三方提供接口的表格渲染.
     */
    createTableThird: function (pageIndex) {
        var getTimestamp = new Date().getTime();
        COM_PARAMS_GETLIST.pageIndex = pageIndex;
        COM_URL_GETLIST = $("#page_table").attr("table_url") + "?pageIndex="
            + pageIndex + COM_PARAMS_GETLIST + "&timeStamp=" + getTimestamp;
        var aj = $.ajax({
            url: COM_URL_GETLIST,
            contentType: 'application/json',
            type: 'get',
            success: function (data) {
                if (data.code == 0) {
                    comJs.getTable(data.data.items);
                    comJs.getPageThird(data.data);
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                // view("异常！");
                alert("异常！");
            }
        });
    },

    /**
     * 获取表格
     *
     * @param data
     */
    getTable: function (data) {
        var ths = $("#page_table th");
        //var tbody = "<tbody id='com_tbody'>";
        // ------------遍历数组 .each的使用-------------
        var trs = "";
        //tbody += trs;
        $
            .each(
                data,
                function (n, value) {
                    var tr = "<tr>";
                    ths
                        .each(function () {
                            var dataOptions = $(this).attr(
                                "data-options");
                            var fieldName = $(this).attr("id");
                            var fields = fieldName.split(".");
                            var fieldVal;

                            if (fields.length == 1) {
                                fieldVal = value[fieldName];
                            } else if (fields.length == 2) {
                                fieldVal = (value[fields[0]])
                                && (value[fields[0]][fields[1]]) ? value[fields[0]][fields[1]]
                                    : "";
                            }


                            if ($(this).attr("otherParms")) {
                                var parmaName = $(this).attr("otherParms");
                                var args = parmaName.split(",");

                                for (i = 0; i < args.length; i++) {
                                    var parmFields = args[i].split(".");
                                    var ParmFieldVal;

                                    if (parmFields.length == 1) {
                                        ParmFieldVal = value[args[i]];
                                    } else if (parmFields.length == 2) {
                                        ParmFieldVal = (value[parmFields[0]])
                                        && (value[parmFields[0]][parmFields[1]]) ? value[parmFields[0]][parmFields[1]]
                                            : "";
                                    }

                                    fieldVal += "," + ParmFieldVal;
                                }
                            }


                            if (dataOptions != null) {
                                if (typeof ("") === "string") {
                                    fieldVal = "\"" + fieldVal
                                        + "\"";
                                }
                                // 执行dataOptions中引用的方法
                                fieldVal = eval(dataOptions + "("
                                    + fieldVal + ")");
                            }
                            if (fieldVal == null || fieldVal == "") {
                                fieldVal = "无";
                            }
                            if (fieldName == 'order') {
                                tr += "<td>" + (n + 1) + "</td>";
                            } else if (fieldName == 'operation-businessQuery') {
                                tr += comJs.appendOpForBusiness();
                            } else {
                                tr += "<td>" + fieldVal + "</td>";
                            }
                        });
                    tr += "</tr>";
                    trs += tr;
                });
        $("#page_table #com_tbody").empty();
        $("#page_table #com_tbody").html(trs);
    },

    /**
     * 添加分页标签
     *
     * @param pageInfo
     */
    getPage: function (pageInfo) {
        var tb = $("#page_table");// 拿到表对象
        var total = pageInfo.total;// 总记录数
        var pageIndex = pageInfo.pageIndex;// 当前页码
        var pageSize = pageInfo.pageSize;// 每页显示数量
        var totalPageCount = pageInfo.totalPageCount;// 总页数
        var more = pageInfo.more;// 是否有下一页
        var first = pageInfo.first;// 未知
        var calTotalPageCount = pageInfo.calTotalPageCount;// 未知

        // 构建分页信息标签，当前n页，共m页，l条
        var pageTagTop = '';
        pageTagTop += '<div class="bootstrap-admin-panel-content">';
        pageTagTop += '<nav class="pull-right"><span>当前第' + pageIndex + '页，共'
            + totalPageCount + '页,共' + total + '条</span> </nav>';
        pageTagTop += '</div>';
        $("#com_list_msg").empty();
        $("#com_list_msg").html(pageTagTop);

        // 构建分页标签
        var pageTag = '';
        pageTag += '<ul class="pagination">';
        pageTag += '<li><a href="javascript:getClickPage(1)" aria-label="Previous"> <spanaria-hidden="true">&lt;&lt;</span></a></li>';
        pageTag += '<li><a href="javascript:getClickPage('
            + (pageIndex - 1)
            + ')" aria-label="Previous"> <spanaria-hidden="true">&lt;</span></a></li>';
        pageLi = '';

        // 以1-10页，11-20页的格式显示
        var page_base = Math.floor(pageIndex / 10);
        if (page_base == pageIndex / 10) {
            page_base -= 1;
        } else if (pageIndex == 0) {
            page_base = 0;
        }
        var page_start = page_base * 10 + 1;// 获取当前页面所在区间
        var page_end;
        if (totalPageCount > (page_start + 9)) {
            page_end = page_start + 9;
        } else {
            page_end = totalPageCount;
        }
        for (var i = page_start; i <= page_end; i++) {
            pageLi += '<li id="li' + i + '"><a href="javascript:getClickPage('
                + i + ')">' + i + '</a></li>';
        }
        pageTag += pageLi;
        if (pageIndex >= totalPageCount) {
            pageTag += '<li class="disabled"><span aria-hidden="true">&gt;</span></li>';
            pageTag += '<li class="disabled"><span aria-hidden="true">&gt;&gt;</span></li>';
            pageTag += '</ul></nav>';
        } else {
            pageTag += '<li><a href="javascript:getClickPage('
                + (pageIndex + 1)
                + ')" aria-label="Next"> <span aria-hidden="true">&gt;</span></a></li>';
            pageTag += '<li><a href="javascript:getClickPage('
                + totalPageCount
                + ')" aria-label="Next"> <span aria-hidden="true">&gt;&gt;</span></a></li>';
        }

        pageTag += '</ul>';
        $("#com_list_nav").empty();
        $("#com_list_nav").html(pageTag);
        $("#li" + pageIndex).attr("class", "active");// 当前页的标签显示为active
    },

    /**
     * 针对第三方接口的分页
     */
    getPageThird: function (pageInfo) {
        var tb = $("#page_table");// 拿到表对象

        var pageIndex = pageInfo.pageIndex;// 当前页码
        var pageSize = pageInfo.pageSize;// 每页显示数量

        var more = pageInfo.more;// 是否有下一页
        var first = pageInfo.first;// 未知
        var calTotalPageCount = pageInfo.calTotalPageCount;// 未知


        // 构建分页标签
        var pageTag = '';
        pageTag += '<ul class="pagination">';
        if (pageIndex - 1 > 0) {
            pageTag += '<li><a href="javascript:getClickPageThird('
                + (pageIndex - 1)
                + ')" aria-label="Previous"> <spanaria-hidden="true">上一页</span></a></li>';
        }
        if (more) {
            pageTag += '<li><a href="javascript:getClickPageThird('
                + (pageIndex + 1)
                + ')" aria-label="Next"> <span aria-hidden="true">下一页</span></a></li>';
        }

        pageTag += '</ul>';
        $("#com_list_nav").empty();
        $("#com_list_nav").html(pageTag);
        $("#li" + pageIndex).attr("class", "active");// 当前页的标签显示为active
    },

    /**
     * business上方的导航栏聚焦设置，初始化时调用此方法传入Num，Num为数字，代表 第几个导航窗格
     *
     * @param Num
     */
    businessNavFocus: function (Num) {
        $(".active.myBaTop").attr("class", null);// 取消原有的聚焦
        $ul = $(".baTop");
        lis = $ul.children();
        $(lis[Num - 1]).attr("class", "active myBaTop");
    },
    /**
     * get timePicker'time time.startDate time.endDate
     *
     * @returns {___anonymous10219_10225}
     */
    getTimeOfTimePicker: function () {
        var time = $('#reservation').val();
        var timeFmt = new Object();
        timeFmt.startDate = "";
        timeFmt.endDate = "";
        if (time != null && time != "") {
            timeFmt.startDate = time.split("-")[0].split("/")[2]
                    .substring(0, 4)
                + "-"
                + time.split("-")[0].split("/")[0]
                + "-"
                + time.split("-")[0].split("/")[1];
            timeFmt.endDate = time.split("-")[1].split("/")[2] + "-"
                + time.split("-")[1].split("/")[0].substring(1, 3) + "-"
                + time.split("-")[1].split("/")[1];
        }
        return timeFmt;
    },

}