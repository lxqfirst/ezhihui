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
    <script src="${contextPath}/assets/js/jquery.cookie.min.js"></script>
    <!-- 当前页js -->
    <script>
        $(document).ready(function () {
            var ss = {
                "startTimeStr": '${startTimeStr}',
                "endTimeStr": '${endTimeStr}',
                "teacherId": ${teacherId},
                "status": 1
            };
            comJs.setParams(ss);
            comJs.createTable(1);
            $("#costManager").addClass('active');
            $('#username').html($.cookie('userName'));
        });

        function download() {
            window.location.href = "/course/exportCourse?startTimeStr=" + '${startTimeStr}'
                    + "&endTimeStr=" + '${endTimeStr}' + "&teacherId=" + ${teacherId}
                    + "&status=" + 1;
        }
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
				</span> 课程管理
        </h3>
        <!--检索面板-->
        <div class="panel panel-info">
            <div class="bootstrap-admin-panel-content">
                <div class="row">
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Right Align"
                                    onclick="download()">
									<span class="glyphicon glyphicon-download" aria-hidden="true">
									</span> 下载
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--检索面板结束-->
        <!--table面板-->
        <div class="panel panel-info">
            <div class="panel-heading">课程列表</div>
            <div class="bootstrap-admin-panel-content">

                <table class="table table-bordered" id="page_table"
                       table_url="${contextPath}/course/getList">
                    <thead>
                    <tr>
                        <th id='studentName' style="text-align:center;">学生</th>
                        <th id='teacherName' sty
                            le="text-align:center;">教师
                        </th>
                        <th id='subjectName' style="text-align:center;">科目</th>
                        <th id='courseTime' style="text-align:center;">时长</th>
                        <th id='time' style="text-align:center;" data-options="comJs.formmatDate">上课时间</th>
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
</body>

</html>