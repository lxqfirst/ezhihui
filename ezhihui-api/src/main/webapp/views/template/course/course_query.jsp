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
    <title>直播间管理</title>
    <!-- Bootstrap -->
    <link href="${contextPath}/assets/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="${contextPath}/assets/css/daterangepicker-bs3.css"
          rel="stylesheet" type="text/css" media="all"/>
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
    <script src="${contextPath}/assets/js/daterangepicker.js"></script>
    <script src="${contextPath}/assets/js/course/course.js"></script>
    <!-- 当前页js -->
    <script>
        $(document).ready(function () {
            $('#reservation').daterangepicker(null, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });

            var ss = {
                "pageSize": 10
            };
            comJs.setParams(ss);
            comJs.createTable(1);
            $("#broadcastManager").addClass('active');
            course.initTeacher();

            //function parseLocalArrayData() {

            //}

        });
    </script>
</head>

<body>
<c:import url="/views/template/head.jsp"></c:import>
<div class="row-fluid ">
    <!--左侧导航栏-->
    <c:import url="/views/template/broadcastleft.jsp"></c:import>
    <!--右侧内容面板-->
    <div class="col-md-10">
        <h3>
				<span class="glyphicon glyphicon-align-left" aria-hidden="true">
				</span> 直播间管理
        </h3>
        <!--检索面板-->
        <div class="panel panel-info">
            <div class="bootstrap-admin-panel-content">
                <div class="row">
                    <!-- /.col-lg-4 -->
                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon">直播日期</span>
                            <div class="control-group">
                                <div class="controls">
                                    <div class="input-prepend input-group">
											<span class="add-on input-group-addon"> <i
                                                    class="glyphicon glyphicon-calendar fa fa-calendar"></i>
											</span> <input type="text" name="reservation" id="reservation"
                                                           class="form-control" value=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.col-lg-6 -->
                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon"> 嘉宾名称 </span> <input
                                type="text" class="form-control" id="guestNameCondi"
                                aria-label="...">
                        </div>
                        <!-- /input-group -->
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
                    <!-- /.col-lg-6 -->
                </div>
                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <!-- 清除浮动 给row之间增加间隔-->
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon"> 直播状态 </span>
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button"
                                        id="query-status" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="true" menu-value menu-text>
                                    全部 <span class="caret"> </span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                    <li><a onclick="comJs.menuOnclick(this)">全部</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="1">正在直播</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="0">直播预告</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="2">直播结束</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- /input-group -->
                    </div>
                    <!-- /.col-lg-4 -->
                    <div class="col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon"> 上线状态 </span>
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button"
                                        id="query-online" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="true" menu-value menu-text>
                                    全部 <span class="caret"> </span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                    <li><a onclick="comJs.menuOnclick(this)">全部</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="0">上线</a></li>
                                    <li><a onclick="comJs.menuOnclick(this)" attrid="1">下线</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- /input-group -->
                    </div>

                    <!-- /.col-lg-6 -->
                </div>
                <!-- /.row -->


                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <!-- 清除浮动增加面板间间隔 -->
                <div class="row">
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Left Align"
                                    onclick="">
									<span class="glyphicon glyphicon-search" aria-hidden="true">
									</span> 检索
                            </button>
                        </div>
                    </div>
                    <div class="col-lg-1">
                        <div>
                            <button type="button" class="btn btn-info"
                                    aria-label="Right Align"
                                    onclick="BroadcastManager.createView()">
									<span class="glyphicon glyphicon-plus" aria-hidden="true">
									</span> 新建
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
            <div class="panel-heading">直播间列表</div>
            <div class="bootstrap-admin-panel-content">

                <table class="table table-bordered" id="page_table"
                       table_url="${contextPath}/course/getList">
                    <thead>
                    <tr>
                        <th id='order'>#</th>
                        <th id='studentName'>直播时间</th>
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
                    <h4 class="modal-title" id="myModalLabel">默认值</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">确认</button>
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