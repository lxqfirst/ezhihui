<%--直播间--%>
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
    <!-- 时间控件 -->
    <script src="${contextPath}/assets/js/moment.js"></script>
    <script src="${contextPath}/assets/js/daterangepicker.js"></script>
    <script src="${contextPath}/assets/js/broadcast/broadcast.js"></script>
    <script src="${contextPath}/assets/js/broadcast/broadcast_room.js"></script>

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
				</span> 直播间
        </h3>
        <div class="panel panel-default" id="com_list_msg"></div>
        <!--table面板-->
        <div class="panel panel-info">
            <div class="bootstrap-admin-panel-content">

                <table class="table table-bordered" id="page_table"
                       table_url="${contextPath}/broadcast/getList">
                    <thead>
                    <tr>
                        <th id='order'>#</th>
                        <th id='timeStr'>直播时间</th>
                        <th id='theme'>直播主题</th>
                        <th id='description'>描述</th>
                        <th id='guestName'>嘉宾</th>
                        <th id='status' data-options="BroadcastManager.statusTrans">直播状态</th>
                        <th id='#1'>在线人数</th>
                        <th id='#2'>参与人数</th>
                        <th id='operation-broadcastQuery'>操作</th>
                    </tr>
                    </thead>
                    <tbody id='com_tbody'>
                    <tr>
                        <td id="broadcast_id">${broadcastId}</td>
                        <td>${broadcastTime}</td>
                        <td>${broadcast.theme}</td>
                        <td>${broadcast.description}</td>
                        <td>${broadcast.guestName}</td>
                        <td>${online}</td>
                        <td>${broadcast.totalAudienceNum}</td>
                        <td>${broadcast.totalAudienceNum}</td>
                        <td><a href="javascript:void(0)" id="edit_broadcase">编辑直播信息</a><br>
                            <c:choose>
                                <c:when test="${broadcast.status == 0}">
                                    <a href="javascript:void(0)" id="menu_start_broadcast" value="1">开始直播</a>
                                </c:when>
                                <c:when test="${broadcast.status == 1}">
                                    <a href="javascript:void(0)" id="menu_start_broadcast" value="2">结束直播</a>
                                </c:when>
                                <c:when test="${broadcast.status == 2}">
                                    <a href="javascript:void(0)" id="menu_start_broadcast" value="2">直播已结束</a>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <nav class="pull-right" id="com_list_nav"></nav>
            </div>
        </div>
        <!--table面板结束-->

        <div>
            <div class="col-lg-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center">图文直播</h3>
                    </div>
                    <div class="panel-body" id="imageTextPanel">
                        <div>
                            <div class="col-lg-2"><img src="#" alt=""></div>
                            <div class="col-lg-10">
                                <div>
                                    <span style="color: red" class="col-lg-6">主持人 小小</span>
                                    <span class="text-right col-lg-6">15:32</span>
                                </div>

                                <div style="word-wrap: break-word;">
                                    aaaaaaaaaasssssssssssssaaaaaaaaaaaaaaaaaadasdsdass
                                </div>
                                <div><a href="javascript:void(0)" class="delete_comment" value="1">删除</a>&nbsp;&nbsp;&nbsp;<a
                                        href="">编辑</a></div>
                                <br>
                            </div>
                        </div>
                        <div>
                            <div class="col-lg-2"><img src="#" alt=""></div>
                            <div class="col-lg-10">
                                <div>
                                    <span style="color: red" class="col-lg-6">主持人 小小</span>
                                    <span class="text-right col-lg-6">15:32</span>
                                </div>

                                <div style="word-wrap: break-word;">
                                    aaaaaaaaaasssssssssssssaaaaaaaaaaaaaaaaaadasdsdass
                                </div>
                                <div><a href="">删除</a>&nbsp;&nbsp;&nbsp;<a href="">编辑</a></div>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center">聊天室/用户提问</h3>
                    </div>
                    <div class="panel-body" id="chartPanel">
                        <div>
                            <div class="col-lg-2"><img src="#" alt=""></div>
                            <div class="col-lg-10">
                                <div style="color: red">主持人 小小</div>
                                <div style="word-wrap: break-word;">aaaaaaaaasssssssssssss</div>
                                <div><a href="javascript:void(0)" class="delete_comment" value="1">删除</a>&nbsp;&nbsp;&nbsp;<a
                                        href="javascript:void(0)" class="reply_user" messageId="123">回复</a></div>
                            </div>
                        </div>
                        <div>
                            <div class="col-lg-2"><img src="#" alt=""></div>
                            <div class="col-lg-10">
                                <div style="color: red">主持人 小小</div>
                                <div style="word-wrap: break-word;">
                                    aaaaaaaaaasssssssssssssaaaaaaaaaaaaaaaaaadasdsdass
                                </div>
                                <div><a href="javascript:void(0)" class="delete_comment" value="1">删除</a>&nbsp;&nbsp;&nbsp;<a
                                        href="">回复</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center">直播发言角色</h3>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <c:forEach items="${users}" var="u">
                                    <div class="checkbox">
                                        <label>
                                            <input type="radio" name="author" value="${u.id}" author="${u.name}" checked>
                                            <img src="${u.image}" alt="" width="20px" height="20px" id="reply_author_image">&nbsp;&nbsp;
                                            <c:choose>
                                                <c:when test="${u.type == 1}">嘉宾:</c:when>
                                                <c:otherwise>
                                                    主持人:
                                                </c:otherwise>
                                            </c:choose>
                                                ${u.name}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="text-muted" id="replyPanel">
                                    <div style="display: none" id="replaySrc">
                                        <div id="replaySrcAuthor">小王</div>
                                        <div style="word-wrap: break-word;" id="replaySrcContent">aaaaaaaaaaaaaaaaaaaaaa</div>
                                        <div class="text-muted">回复</div>
                                        <input type="hidden" id="replaySrcId" value="0">
                                    </div>
                                    <div>
                                        <textarea class="form-control" rows="3" id="replyComment"></textarea>
                                    </div>
                                    <br>
                                    <div><img src="#" alt="" id="replyImage"> <a href="" class="btn btn-default">+</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div><a class="btn btn-info" id="cancelReply">取消回复</a>&nbsp;&nbsp;&nbsp;<a
                                href="javascript:void(0)" class="btn btn-success" id="send_reply">发送</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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