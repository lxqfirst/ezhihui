<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>

<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#"
               style="padding:10px 15px; 10px;background: url(${pageContext.request.contextPath}/assets/images/logo.png) no-repeat;width:200px;height:60px;">
            </a> <a class="navbar-brand" style="padding:15px 15px;" href="${contextPath}/broadcast/view">
            101网校课程管理平台</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">

            <!--右边导航栏-->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"> hello,<span id="username"></span> </a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown" role="button" aria-haspopup="true"
                                        aria-expanded="false"> 设置 <span class="caret"> </span>
                </a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/logout"> 注销 </a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
</body>
<script type="text/javascript">
    //$(document).ready(function() {
    //
    //	$.ajax({
    //		url : "/user/getName",
    //		type : "GET",
    //		dataType : "json",
    //		async : false,
    //		contentType : "application/json",
    //		success : function(data) {
    //			if (data.code == 0) {
    //				$("#username").html(data.data);
    //			} else {
    //				alert(data.message);
    //			}
    //		},
    //		error : function() {
    //			alert("异常！");
    //		}
    //	});
    //});
</script>
</html>