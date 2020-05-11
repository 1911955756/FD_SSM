<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/14
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>找回密码</title>
    <link rel="icon" href="images/timg.jpg" type="image/x-icon">
    <link rel="stylesheet" href="css/modify.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/modify.js"></script>
</head>
<body id="background">
<div class="container">
    <div class="row ">
        <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 div-background">
            <form action="user/updatepassword" title="找回密码" class="form-group" method="post">
                <p>
                    <label for="account">登录账号：</label>
                    <input class="form-control" id="account" placeholder="请输入账号" name="account" oninput="checkinput('account')">
                    <div id="account-tips"></div><div style="display: none"><input value="false" id="account-check"></div>
                </p>
                <p>
                    <label for="email">邮箱：</label>
                    <input class="form-control" id="email" placeholder="请输入邮箱" name="email" oninput="checkinput('email')">
                    <div id="email-tips"></div><div style="display: none"><input value="false" id="email-check"></div>
                </p>
                <p>
                    <label for="captcha">验证码：</label>
                    <input class="form-control" id="captcha" placeholder="请输入验证码" name="captcha" oninput="checkinput('captcha')">
                    <div id="captcha-tips"></div><div style="display: none"><input value="false" id="captcha-check"></div>
                    <input class="btn btn-info" type="button" value="获取验证码" id="get-captcha">
                </p>
                <p>
                    <label for="pwd">输入密码：</label>
                    <input class="form-control" id="pwd" placeholder="请输入密码" name="pwd" type="password" oninput="checkinput('pwd')" onfocus="$('#confirm-pwd').val('')">
                    <div id="pwd-tips"></div><div style="display: none"><input value="false" id="pwd-check"></div>
                </p>
                <p>
                    <label for="confirm-pwd">确认密码：</label>
                    <input class="form-control" id="confirm-pwd" placeholder="再次确认密码" name="confirm-pwd" type="password" oninput="checkinput('confirm-pwd')">
                    <div id="confirm-pwd-tips"></div><div style="display: none"><input value="false" id="confirm-pwd-check"></div>
                </p>
                <p>
                    <input class="btn btn-success" id="submit-btn" type="button" value="确认提交" disabled>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
