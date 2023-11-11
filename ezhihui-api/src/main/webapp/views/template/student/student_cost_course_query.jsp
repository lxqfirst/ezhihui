<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/tag/tag-lib.tag" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <link rel="shortcut icon" type="image/ico" href="/assets/images/favicon.ico">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>课程管理</title>
    <!-- Bootstrap -->
    <link href="${contextPath}/assets/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="${contextPath}/assets/css/daterangepicker-bs3.css"
          rel="stylesheet" type="text/css" media="all"/>
    <link href="${contextPath}/assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="all">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media
            queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file://
            -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js">
    </script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js">
    </script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${contextPath}/assets/js/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files
            as needed -->
    <script src="${contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${contextPath}/assets/js/common.js"></script>
    <script src="${contextPath}/assets/js/bootstrap3-typeahead.js"></script>
    <!-- 时间控件 -->
    <script src="${contextPath}/assets/js/moment.js"></script>
    <script src="${contextPath}/assets/js/bootstrap-datetimepicker.js"></script>
    <script src="${contextPath}/assets/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${contextPath}/assets/js/valid/valid.js"></script>
    <script src="${contextPath}/assets/js/student/student.js"></script>
    <script src="${contextPath}/assets/js/teacher/teacher.js"></script>
    <script src="${contextPath}/assets/js/jquery.cookie.min.js"></script>
    <script src="${contextPath}/assets/js/WdatePicker/WdatePicker.js"></script>
    <!-- 当前页js -->
    <script>
        $(document).ready(function () {
            //初始化时间控件
            $('.date').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1
            });
            $('#start_time_input').val(new Date().Format("yyyy-MM-dd") + " 08:00:00");
            $('#end_time_input').val(new Date().Format("yyyy-MM-dd") + " 23:00:00");

            studentManager.createCostDetailList()
            teacherManager.initTeacher("teacher");
            studentManager.initStudent("student");

            $("#studentManager").addClass('active');
            $('#username').html($.cookie('userName'));

        });
    </script>
</head>

<body>
<c:import url="/views/template/head.jsp"></c:import>
<div class="row-fluid ">
    <!--左侧导航栏-->
    <c:import url="/views/template/left.jsp"></c:import>
    <!--右侧内容面板-->
    <div class="col-md-10">
        <h3>
				<span class="glyphicon glyphicon-align-left" aria-hidden="true">
				</span> 学生费用记录
        </h3>
        <!--检索面板-->
        <div class="panel panel-info">
            <div class="bootstrap-admin-panel-content">
                <div class="row">
                    <!-- /.col-lg-4 -->
                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon">开始时间</span>
                            <div id="start_time" class="input-group">
                                <input type="text" class="input Wdate form-control" style="height: 34px;"
                                       id="start_time_input"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="bdate"
                                       onClick="WdatePicker()">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon">结束时间</span>
                            <div id="end_time" class="input-group">
                                <input type="text" class="input Wdate form-control" style="height: 34px"
                                       id="end_time_input"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="bdate"
                                       onClick="WdatePicker()">
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon"> 状态 </span>
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button"
                                        id="query-status" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="true" menu-value menu-text>
                                    全部 <span class="caret"> </span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="-1">全部</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="1">已扣款</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="2">签到取消后退款</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="3">删除课程后退款</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="4">未计费</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>
                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <!-- 清除浮动 给row之间增加间隔-->
                <!-- /.row -->
                <div class="row">
                    <!-- /.col-lg-6 -->
                    <div class="col-lg-3">
                        <div class="input-group">
                            <span class="input-group-addon"> 学生 </span>
                            <input id="student" autocomplete="off" data-provide="typeahead" type="text"
                                   class="form-control" placeholder=""/>

                        </div>
                        <!-- /input-group -->
                    </div>

                    <!-- /.col-lg-6 -->
                    <div class="col-lg-3">
                        <div class="input-group">
                            <span class="input-group-addon"> 教师 </span>
                            <input id="teacher" autocomplete="off" data-provide="typeahead" type="text"
                                   class="form-control" placeholder=""/>

                        </div>
                        <!-- /input-group -->
                    </div>
                    <!-- /.col-lg-6 -->

                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon"> 类型 </span>
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button"
                                        id="query-type" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="true" menu-value="1" menu-text>
                                    文化课 <span class="caret"> </span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="1">文化课</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="2">美术课</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>
                <!-- /.row -->

                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <!-- 清除浮动增加面板间间隔 -->
                <div class="row">
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Left Align"
                                    onclick="studentManager.createCostDetailList()">
									<span class="glyphicon glyphicon-search" aria-hidden="true">
									</span> 检索
                            </button>
                        </div>
                    </div>
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Right Align"
                                    onclick="studentManager.setLastMonth()">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true">
									</span> 上月
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--检索面板结束-->

        <div class="panel panel-default" id="com_list_msg"></div>
        <!--table面板-->
        <div class="panel panel-info">
            <div class="panel-heading">课程列表</div>
            <div class="bootstrap-admin-panel-content">

                <table class="table table-bordered" id="page_table"
                       table_url="${contextPath}/student/getCostDetailList">
                    <thead>
                    <tr>
                        <th id='studentName' style="text-align:center;">学生</th>
                        <th id='teacherName' style="text-align:center;">教师</th>
                        <th id='subjectName' style="text-align:center;">科目</th>
                        <th id='courseTime' style="text-align:center;">时长</th>
                        <th id='time' style="text-align:center;" data-options="comJs.formmatDate">上课时间</th>
                        <th id='cost' style="text-align:center;">费用</th>
                        <th id='type' style="text-align:center;" data-options="studentManager.transType">状态</th>
                        <th id='courseType' style="text-align:center;" data-options="studentManager.transCourseType">
                            类型
                        </th>
                    </tr>
                    </thead>
                    <tbody id='com_tbody'>
                    </tbody>
                </table>
                <nav class="pull-right" id="com_list_nav"></nav>
            </div>
        </div>
        <!--table面板结束-->


    </div>
    <!--右侧内容面板结束-->

</body>

</html>