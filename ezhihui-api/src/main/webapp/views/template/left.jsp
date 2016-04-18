<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<body>
<!--左侧导航栏-->
<div class="col-md-2">
    <div class="list-group">
        <a href="${contextPath}/course/courseView" class="list-group-item" id="courseManager">课程管理</a>
        <a href="${contextPath}/student/view" class="list-group-item" id="studentManager">学生管理</a>
        <a href="${contextPath}/teacher/view" class="list-group-item" id="teacherManager">教师管理</a>
        <a href="${contextPath}/cost/view" class="list-group-item" id="costManager">财务管理</a>
    </div>
</div>
</body>
</html>