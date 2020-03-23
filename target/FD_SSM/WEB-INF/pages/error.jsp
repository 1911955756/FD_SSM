<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/16
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<center>
    <h3>操作异常统一跳转页面</h3>
    ${errorMsg}
    <a href="http://localhost:8090/FD_SSM_war/">重新登录</a>
</center>
</body>
</html>
