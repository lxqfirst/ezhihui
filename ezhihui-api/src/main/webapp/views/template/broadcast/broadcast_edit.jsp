<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/tag/tag-lib.tag" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <link rel="shortcut icon" type="image/ico"
          href="/assets/images/favicon.ico">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>直播间信息</title>
    <!-- Bootstrap -->
    <link href="${contextPath}/assets/css/bootstrap.min.css"
          rel="stylesheet">
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

    <!-- 时间控件 -->
    <script src="${contextPath}/assets/js/moment.js"></script>
    <script src="${contextPath}/assets/js/daterangepicker.js"></script>

    <!-- 序列化 -->
    <script src="${contextPath}/assets/js/serializeJson.js"></script>

    <!-- 校验js -->
    <script src="${contextPath}/assets/js/valid/valid.js"></script>

    <script src="${contextPath}/assets/js/broadcast/broadcast.js"></script>

    <!--上传文件js-->
    <script src="${contextPath}/assets/js/ajaxfileupload.js"></script>

    <script src="${contextPath}/assets/js/datepicker/WdatePicker.js"></script>

    <script>
        $(document).ready(function () {
            var broadcastId = "${broadcastId}";
            $('#broadcastId').val(broadcastId);
            BroadcastManager.initBroadcast4Edit(broadcastId);
            $("#broadcastManager").addClass('active');
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
				</span> 直播间信息
        </h3>
        <!--商家基本信息面板-->
        <div class="panel panel-info" id="base_info_div">
            <div class="panel-heading">直播信息</div>
            <div class="bootstrap-admin-panel-content">
                <div class="row">
                    <!-- col-md-4 -->
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon"> 直播时间</span>
                            <input type="text" id="time"
                                   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                   class="Wdate form-control"
                                   style="height:auto" required message="直播时间"/>
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>

                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <!-- /.col-md-4 -->
                <div class="row">
                    <!-- col-md-4 -->
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon"> 直播主题 </span> <input
                                type="text" class="form-control" id="theme"
                                name="theme" aria-label="..." required
                                placeholder="" message="直播主题">
                        </div>
                        <!-- /input-group -->
                    </div>
                    <!-- /.col-md-4 -->
                </div>
                <div class="clearfix" style="margin-bottom: 10px;"></div>

                <div class="row">
                    <div class="col-md-1">
                        <div class="input-group">
                            <span class="input-group-addon">直播图片</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <img src="/assets/images/broadcast.png" id="broadcastImg"/>
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>

                <div class="clearfix" style="margin-bottom: 10px;"></div>

                <div class="row" id="broadcast_image_div">
                    <div class="col-md-1">
                        <div class="input-group">
                        </div>
                    </div>
                    <div class="col-lg-1">
                        <div class="input-group">
                            <button type="button" class="btn btn-info"
                                    onclick="BroadcastManager.updateImageBroadcast()">上传
                            </button>
                        </div>
                    </div>
                    <div class="col-lg-1">
                        <div class="input-group">
                            <input id="broadcastImage" name="broadcastImage" type="file" class="btn btn-info">
                        </div>
                    </div>

                </div>

                <div class="clearfix" style="margin-bottom: 10px;"></div>

                <div class="row">
                    <div class="col-md-1">
                        <div class="input-group">
                            <span class="input-group-addon">直播描述</span>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="input-group">
                            <textarea style="width: 415px; height: 68px"
                                      placeholder="" required message="直播描述" id="description"></textarea>
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>
                <div class="clearfix" style="margin-bottom: 10px;"></div>
            </div>
        </div>
        <!--配置视频流面板-->
        <div class="panel panel-info">
            <div class="panel-heading">配置视频流地址</div>
            <div class="bootstrap-admin-panel-content">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="input-group">
                            <span class="input-group-addon"> 直播视频流地址</span> <input
                                type="text" class="form-control" id="video_address" name="video_address"
                                aria-label="..." required message="直播视频流地址">
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>
                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <div class="row">
                    <!-- /.col-lg-4 -->
                    <div class="col-lg-8">
                        <div class="input-group">
                            <span class="input-group-addon">直播回放地址</span> <input
                                type="text" class="form-control" id="playback_address"
                                name="playback_address" aria-label="...">
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>
                <div class="clearfix" style="margin-bottom: 10px;"></div>
            </div>
        </div>

        <div class="panel panel-info">
            <div class="panel-heading">添加直播角色</div>
            <div class="bootstrap-admin-panel-content" id="guest-panel-id">
                <div class="row">
                    <div class="col-md-2">
                        <div class="input-group">
                            <span class="input-group-addon">嘉宾信息</span>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="input-group">
                            <span class="input-group-addon">头像</span>
                        </div>

                    </div>
                    <div class="col-md-1">
                        <div class="input-group">
                            <img src="/assets/images/head.png" id="img0" height="44px" width="44px"/>
                        </div>
                        <!-- /input-group -->
                    </div>
                    <div class="col-md-1">
                        <div class="input-group">
                            <button type="button" class="btn btn-info"
                                    onclick="BroadcastManager.updateHeaderImage(0)"/>
                            上传
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="input-group">
                            <input id="header0" name="header0" type="file" class="btn btn-info">
                        </div>
                    </div>
                </div>

                <div class="clearfix" style="margin-bottom: 10px;"></div>

                <div class="row">
                    <div class="col-md-2">
                        <div class="input-group">
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="input-group">
                            <span class="input-group-addon">头衔</span>
                        </div>

                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" class="form-control" id="title0" name="title0"
                                   aria-label="..." required message="嘉宾头衔">
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>

                <div class="clearfix" style="margin-bottom: 10px;"></div>

                <div class="row">
                    <div class="col-md-2">
                        <div class="input-group">
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="input-group">
                            <span class="input-group-addon">姓名</span>
                        </div>

                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" class="form-control" id="name0" name="name0"
                                   aria-label="..." required message="嘉宾姓名">
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>

                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <div id='guest-info'>
                    <div class="row">
                        <div class="col-md-2">
                            <div class="input-group">
                                <span class="input-group-addon">主持人信息</span>
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="input-group">
                                <span class="input-group-addon">头像</span>
                            </div>

                        </div>
                        <div class="col-md-1">
                            <div class="input-group">
                                <img src="/assets/images/head.png" id="img1" height="44px" width="44px"/>
                            </div>
                            <!-- /input-group -->
                        </div>
                        <div class="col-md-1">
                            <div class="input-group">
                                <button type="button" class="btn btn-info"
                                        onclick="BroadcastManager.updateHeaderImage(1)"/>
                                上传
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="input-group">
                                <input id="header1" name="header1" type="file" class="btn btn-info">
                            </div>
                        </div>
                    </div>

                    <div class="clearfix" style="margin-bottom: 10px;"></div>

                    <div class="row">
                        <div class="col-md-2">
                            <div class="input-group">
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="input-group">
                                <span class="input-group-addon">头衔</span>
                            </div>

                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <input type="text" class="form-control" id="title1" name="title1"
                                       aria-label="..." required message="主持人头衔">
                            </div>
                            <!-- /input-group -->
                        </div>
                    </div>

                    <div class="clearfix" style="margin-bottom: 10px;"></div>

                    <div class="row">
                        <div class="col-md-2">
                            <div class="input-group">
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="input-group">
                                <span class="input-group-addon">昵称</span>
                            </div>

                        </div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <input type="text" class="form-control" id="name1" name="name1"
                                       aria-label="..." required message="主持人昵称">
                            </div>
                            <!-- /input-group -->
                        </div>
                    </div>
                </div>
                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <div class="row">

                    <div class="col-md-2">
                        <div class="input-group">
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="input-group">
                            <button type="button" class="btn btn-info"
                                    onclick="BroadcastManager.createNewPresenter()" id="addGuestButton"/>
                            新增主持人
                        </div>
                    </div>
                </div>

                <div class="clearfix" style="margin-bottom: 10px;"></div>
            </div>
        </div>
        <!--收款账户信息面板结束-->

        <!--起始在线人数面板-->
        <div class="panel panel-info">
            <div class="panel-heading">起始在线人数</div>
            <div class="bootstrap-admin-panel-content">
                <div class="row">
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"> 基数(人)</span> <input type="text"
                                                                                 class="form-control" id="baseNum"
                                                                                 name="baseNum"
                                                                                 aria-label="..." required
                                                                                 message="人数基数">
                        </div>
                        <!-- /input-group -->
                    </div>
                </div>
                <!-- /.row 结束-->
                <div class="clearfix" style="margin-bottom: 10px;"></div>
                <!-- 清除浮动增加面板间间隔 -->
            </div>
            <!--其它信息面板结束-->

            <div class="row">
                <div class="col-md-5"></div>
                <div class="col-md-1">
                    <button id="send" type="button" class="btn btn-info"
                            onclick="BroadcastManager.updateBroadcast4Edit()">提交
                    </button>
                </div>
                <div class="col-md-1">
                    <button type="button" class="btn btn-info"
                            onclick="window.location.reload()">重置
                    </button>
                </div>
                <div class="col-md-5"></div>
            </div>
            <!-- row end -->


        </div>
        <!--右侧内容面板结束-->
        <input id="broadcastId" hidden="true">
        <!-- 弹出框 -->
        <div class="container"></div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content"></div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal -->
            <!-- 弹出框结束 -->
        </div>
    </div>
</body>
</html>