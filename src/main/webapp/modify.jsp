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
    <link rel="stylesheet" href="css/modify.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <style>
        .div-background{
            margin-top: 100px;
        }
    </style>
    <script>
        $(function () {
            $("#get-captcha").click(function () {
                if($("#email").val()==""){
                    alert("邮箱不能为空");
                    $("#email").focus();
                    return false;
                } else if($("#email").val().indexOf('@')=='-1'||$("#email").val().indexOf('.com')=='-1'){
                    alert("请检查邮箱格式是否正确")
                } else{
                    $.ajax({
                        url:'user/checkemail',
                        datatype : "json",
                        type : "post",
                        data:{
                            email:$("#email").val()
                        },
                        success:function (res) {
                            if(res==true){
                                $("#get-captcha").attr("disabled", true);
                                $("#get-captcha").val("发送中");
                                $.ajax({
                                    url:'email/send',
                                    datatype : "json",
                                    type : "post",
                                    data:{
                                        email:$("#email").val()
                                    },
                                    success:function (res) {
                                        if(res){
                                            alert("验证码发送成功")
                                            var count = 60;
                                            var countdown = setInterval(CountDown, 1000);
                                            function CountDown() {
                                                $("#get-captcha").attr("disabled", true);
                                                $("#get-captcha").val(count + "s");
                                                if (count == 0) {
                                                    $("#get-captcha").val("重新获取验证码").removeAttr("disabled");
                                                    clearInterval(countdown);
                                                    $.ajax({
                                                        url : "user/deletecaptcha",
                                                        datatype : "json",
                                                        type : "post",
                                                        success:function () {
                                                            alert("获取验证码失效，请重新获取")
                                                        }
                                                    })
                                                }
                                                count--;
                                            }
                                        }
                                    },error:function () {
                                        alert("获取验证码失败")
                                        $("#get-captcha").val("重新获取验证码").removeAttr("disabled");
                                    }
                                })
                            }else{
                                alert("该邮箱不能获取验证码")
                            }

                        }})
                }
            });
            $("#submit-btn").click(function () {
                $.ajax({
                        url:"user/findPhone",
                        data:{
                            phone:$("#account").val()
                        },
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            if(result==true){
                                alert("没有该账号");
                                return false;
                            }else{
                                $.ajax({
                                    url:"user/checkcaptcha",
                                    data:{
                                        captcha:$("#captcha").val()
                                    },
                                    dataType:"json",
                                    type:"post",
                                    success:function (res) {
                                        if(res==false){
                                            alert("验证码不正确");
                                            return false;
                                        }else{
                                            alert("修改成功")
                                            $("form").submit();
                                        }
                                    }
                                })
                            }
                        }
                })
            })
        });
        function checkinput(id) {
            if($("#"+id+"").val()==""){
                $("#"+id+"-tips").html("");
                $("#"+id+"-tips").append("<h5 style='color:red'>不能为空！</h5>");
                $("#"+id+"-check").val("false");
            }else{
                if(id=='confirm-pwd'){
                    if($("#"+id+"").val()!=$("#pwd").val()){
                        $("#"+id+"-tips").html("");
                        $("#"+id+"-tips").append("<h5 style='color:red'>两次密码不正确</h5>");
                        $("#"+id+"-check").val("false");
                    }else{
                        $("#"+id+"-tips").html("");
                        $("#"+id+"-check").val("true");}
                }else{
                $("#"+id+"-tips").html("");
                    $("#"+id+"-check").val("true");
                }
            }
            if($("#account-check").val()=="true"&&$("#pwd-check").val()=="true"&&$("#email-check").val()=="true"&&$("#confirm-pwd-check").val()=="true"&&$("#captcha-check").val()=="true"){
                $("#submit-btn").removeAttr("disabled");
            }else{
                $("#submit-btn").attr("disabled", true);
            }
        }
    </script>
</head>
<body id="background">
<div class="container div-background">
    <div class="row ">
        <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
            <form action="user/updatepassword" title="找回密码" class="form-group">
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
