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
    <title>教师管理</title>
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
    <script src="${contextPath}/assets/js/teacher/teacher.js"></script>
    <script src="${contextPath}/assets/js/valid/valid.js"></script>
    <!-- 当前页js -->
    <script>
        $(document).ready(function () {
            teacherManager.initTeacher('teacher')
            teacherManager.createList();
            teacherManager.initSubject();
            $("#teacherManager").addClass('active');
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
				</span> 教师管理
        </h3>
        <!--检索面板-->
        <div class="panel panel-info">
            <div class="bootstrap-admin-panel-content">
                <!-- /.row -->
                <div class="row">
                    <!-- /.col-lg-6 -->
                    <div class="col-lg-3">
                        <div class="input-group">
                            <span class="input-group-addon"> 教师 </span>
                            <input id="teacher" autocomplete="off" data-provide="typeahead" type="text"
                                   class="form-control" placeholder=""/>

                        </div>
                        <!-- /input-group -->
                    </div>

                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Left Align"
                                    onclick="teacherManager.createList()">
									<span class="glyphicon glyphicon-search" aria-hidden="true">
									</span> 检索
                            </button>
                        </div>
                    </div>
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Right Align"
                                    onclick="teacherManager.showCreateView()">
									<span class="glyphicon glyphicon-plus" aria-hidden="true">
									</span> 新建
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--检索面板结束-->
        <!--table面板-->
        <div class="panel panel-info">
            <div class="panel-heading">学生列表</div>
            <div class="bootstrap-admin-panel-content">

                <table class="table table-bordered" id="page_table"
                       table_url="${contextPath}/teacher/getPageList">
                    <thead>
                    <tr>
                        <th id='order' style="text-align:center;">序号</th>
                        <th id='name' style="text-align:center;">教师</th>
                        <th id='subjectName' style="text-align:center;">学科</th>
                        <th id='telephone' style="text-align:center;" data-options="teacherManager.transNull">联系方式</th>
                        <th id="teacher-operation" style="text-align:center;">操作</th>
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
                    <h4 class="modal-title" id="myModalLabel">教师设置</h4>
                </div>

                <div class="panel-info">
                    <div class="bootstrap-admin-panel-content">
                        <div class="row">
                            <!-- /.col-lg-6 -->
                            <div class="col-lg-5">
                                <div class="input-group">
                                    <span class="input-group-addon"> 姓名 </span>
                                    <input type="text" class="form-control" id="nameNew"
                                           aria-label="..." required message="教师姓名">
                                </div>
                                <!-- /input-group -->
                            </div>
                            <div class="col-lg-5">
                                <div class="input-group">
                                    <span class="input-group-addon"> 学科 </span>
                                    <select class="form-control" id="subject">

                                    </select>
                                </div>
                                <!-- /input-group -->
                            </div>
                        </div>

                        <div class="clearfix" style="margin-bottom: 10px;"></div>

                        <div class="clearfix" style="margin-bottom: 10px;"></div>

                        <div class="row">
                            <div class="col-lg-5">
                                <div class="input-group">
                                    <span class="input-group-addon"> 联系方式 </span>
                                    <input type="text"
                                           class="form-control"
                                           id="phoneNew"
                                           aria-label="...">
                                </div>
                            </div>
                        </div>
                        <div class="clearfix" style="margin-bottom: 10px;"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="teacherManager.create(0)" id="createButton">
                        保存并关闭
                    </button>
                    <button type="button" class="btn btn-info" onclick="teacherManager.create(1)" id="continueButton">
                        保存并继续
                    </button>
                    <button type="button" class="btn btn-primary" onclick="teacherManager.update()"
                            id="updateButton">
                        保存并关闭
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消
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