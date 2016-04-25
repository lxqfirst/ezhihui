<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>101网校课程管理平台</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/login.css">

    <link rel="shortcut icon" type="image/ico" href="/assets/images/favicon.ico">
    <script type="text/javascript" src="assets/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
</head>

<body class="login">

<nav class="navbar navbar-edaijia login">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
            </a>
        </div>
    </div>
</nav>

<div class="container login">
    <div class="login-body">
        <div class="content">
            <p class="title">101网校课程管理平台</p>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon icon-person"></span>
                    <input name="userName" type="text" class="form-control user-name" placeholder="请输入账号" value=""/>
                </div>
                <p><span class="icon"></span><span class="msg">请输入账号</span></p>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon icon-pwd"></span>
                    <input name="password" type="password" class="form-control user-pwd" placeholder="请输入密码" value=""/>
                </div>
                <p><span class="icon"></span><span class="msg">请输入密码</span></p>
            </div>
            <button type="text" class="btn btn-blue btn-submit" id="btnLogin">登 录</button>
        </div>
    </div>
</div>

<div class="l_foot">
    Copyright @ 2016 ezhihui101.com All Right Reserved.
</div>

<script type="text/javascript">
    if ($.cookie('token') && $.cookie('userName')) {
        location.href = 'course/courseView';
    }


    $('#btnLogin').on('click', function () {
        // 用户名
        var $userName = $(".user-name");
        var userName = $.trim($userName.val());
        if (!userName) {
            $userName.closest(".form-group").addClass("input-error");
            return false;
        }
        // 密码
        var $password = $(".user-pwd");
        var password = $.trim($password.val());
        if (!password) {
            $password.closest(".form-group").addClass("input-error");
            return false;
        }

        $.ajax({
            url: "login?name=" + userName + "&password=" + password,
            type: 'post',
            contentType: "application/json",
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    $.cookie('userName', userName);
                    location.href = 'course/courseView';
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
    })
</script>
</body>
</html>
