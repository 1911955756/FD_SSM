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
    <link rel="stylesheet" href="css/login.css" type="text/css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <link rel="icon" href="images/timg.jpg" type="image/x-icon">
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
    <script >
        //页面加载，绑定单击事件
        $(function () {
            var flag=false;
            $(document).keyup(function(event){
                if(event.keyCode ==13){
                    $("#btn1").trigger("click");
                }
            });
            $("#btn1").click(function () {
                if($("#phone").val()==""){
                    alert("账号不能为空");
                    $("#phone").focus();
                    flag=false;
                    return flag;
                }
                else if ($("#password").val() == "") {
                    alert("密码不能为空");
                    $("#password").focus();
                    flag=false;
                    return flag;
                }
                //发送ajax请求
                $.ajax({
                    // 编写json格式，设置属性和值
                    url:"user/findByPhone",
                    contentType:"application/json;charset=UTF-8",
                    data:"{\"phone\":\""+$("#phone").val()+"\",\"password\":\""+$("#password").val()+"\"}",
                    dataType:"json",
                    type:"post",
                    success:function(result){
                        // data服务器端响应的json的数据，进行解析
                        if(result==true)
                        {$.ajax({
                            url:'user/checkapplication',
                            datatype : "json",
                            type : "post",
                            data:{
                                phone:$("#phone").val()
                            },
                            success:function(res){
                                if(res==false){
                                    $("#form1").submit();return true;
                                }else{
                                    alert("该账号已登录");return false;
                                }
                            }
                        })
                        } else if(result==flag)
                        {alert("账号或密码不正确");return false;}
                    }
                });
            });
        });

    </script>

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
        </fieldset>
    </div>
</div>
</div>
</body>
</html>
