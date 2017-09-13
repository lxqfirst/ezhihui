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
    <title>财务管理</title>
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
    <script src="${contextPath}/assets/js/cost/cost.js"></script>
    <script src="${contextPath}/assets/js/course/course.js"></script>
    <script src="${contextPath}/assets/js/valid/valid.js"></script>
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
            var day = new Date();
            day.setDate(day.getDate() - 7);
            $('#start_time_input').val(day.Format("yyyy-MM-dd") + " 08:00:00");
            $('#end_time_input').val(new Date().Format("yyyy-MM-dd") + " 23:00:00");
            costManager.initTeacher('teacher')
            costManager.createList();
            costManager.initGrade();
            $("#costManager").addClass('active');
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
				</span> 财务管理
        </h3>
        <!--检索面板-->
        <div class="panel panel-info">
            <div class="bootstrap-admin-panel-content">
                <!-- /.row -->
                <div class="row">
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
                    <!-- /.col-lg-6 -->
                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon"> 教师 </span>
                            <input id="teacher" autocomplete="off" data-provide="typeahead" type="text"
                                   class="form-control" placeholder=""/>

                        </div>
                        <!-- /input-group -->
                    </div>
                </div>

                <div class="clearfix" style="margin-bottom: 10px;"></div>

                <div class="row">
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Left Align"
                                    onclick="costManager.createList()">
									<span class="glyphicon glyphicon-search" aria-hidden="true">
									</span> 检索
                            </button>
                        </div>
                    </div>
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Right Align"
                                    onclick="costManager.download()">
									<span class="glyphicon glyphicon-download" aria-hidden="true">
									</span> 下载
                            </button>
                        </div>
                    </div>
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Right Align"
                                    onclick="course.setLastMonth()">
									<span class="glyphicon glyphicon-calendar" aria-hidden="true">
									</span> 上月
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--检索面板结束-->
        <!--table面板-->
        <div class="panel panel-info">
            <div class="panel-heading">财务列表</div>
            <div class="bootstrap-admin-panel-content">

                <table class="table table-bordered" id="page_table"
                       table_url="${contextPath}/cost/getTeacherSalaryList">
                    <thead>
                    <tr>
                        <th id='order' style="text-align:center;">序号</th>
                        <th id='name' style="text-align:center;">教师</th>
                        <th id='salary' style="text-align:center;" data-options="costManager.transNull">薪水</th>
                        <th id='comment' style="text-align:center; width: 150px" data-options="costManager.transNull">
                            备注
                        </th>
                        <th id="cost-operation" style="text-align:center;">操作</th>
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

    <!-- 禁用弹出框 -->
    <div class="container"></div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">财务设置</h4>
                </div>
                <div class="row">
                    <div class="col-lg-5">
                        <div class="input-group">
                            涉及年级:<label id="grades"> </label>
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>
                <div class="clearfix modelClearFix" style="margin-bottom: 10px;"></div>
                <div class="panel-info">
                    <div class="bootstrap-admin-panel-content" id="model_panel">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="costManager.modelAdd(null)">
                        新增
                    </button>
                    <button type="button" class="btn btn-info" onclick="costManager.create()" id="continueButton">
                        保存
                    </button>
                    <button type="button" class="btn btn-default" onclick="costManager.hideModel()">取消
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
        <!-- 禁用弹出框结束 -->
    </div>
</body>

</html>