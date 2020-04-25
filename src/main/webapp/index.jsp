<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/16
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <title>饭店后台管理系统</title>
    <link rel="icon" href="images/timg.jpg" type="image/x-icon">
    <link rel="stylesheet" href="css/login.css" type="text/css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <%--    哀悼模式--%>
<%--    <style>

        html{

            filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);

            filter: grayscale(100%);

            -webkit-filter: grayscale(100%);

            -moz-filter: grayscale(100%);

            -o-filter: grayscale(100%);

            -webkit-filter: grayscale(1);

        }

    </style>--%>
</head>
<body style="text-align: center;" id="background">
    <div id="login">
        <img class="login-logo" src="images/timg.jpg">
        <div id="form">
        <fieldset>
            <legend>饭店后台登陆界面</legend>
        <form name="form1" action="user/login" method="post" id="form1">
            <input type="text" name="phone" id="phone" class="login-input" placeholder="请输入账号"/><br/>
            <input type="password" name="password" id="password" class="login-input" placeholder="请输入密码"/><br/>
            <button id="btn1" class="login-button" type="button">登录</button><br/>
        </form>
            <a href="#" class="btn-link">忘记密码？</a>
        </fieldset>
        </div>
    </div>
</body>
</html>
