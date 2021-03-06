<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/20
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>tablelist</title>
    <link rel="stylesheet" href="../css/bootstrap.css" type="text/css">
    <style>
        body{
            background-color: whitesmoke;
        }
        table{
            table-layout:fixed;
        }
        td{
            overflow:hidden;
            white-space:nowrap;
            text-overflow:ellipsis;
        }
        .inner-container {
            position: absolute; left: 0;
            overflow-x: hidden;
            overflow-y: scroll;
        }
        /* for Chrome 只针对谷歌浏览器*/
        .inner-container::-webkit-scrollbar {
            display: none;
        }
        .right{
            float: right;
            padding-right: 50px;
            margin-top: -30px;
        }
    </style>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script>
        $(function () {
            var flag=false;
            $("#addbtn").click(function () {
                if($("#username").val()==""){
                    alert("用户名不能为空");
                    $("#username").focus();
                    flag=false;
                    return flag;
                }else if($("#phone").val()==""){
                    alert("联系方式不能为空");
                    $("#phone").focus();
                    flag=false;
                    return flag;
                }
                else if($("#password").val()==""){
                    alert("密码不能为空");
                    $("#password").focus();
                    flag=false;
                    return flag;
                }
                else{
                    $.ajax({
                        // 编写json格式，设置属性和值
                        url:"../user/findPhone",
                        data:{
                            phone:$("#phone").val()
                        },
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            // data服务器端响应的json的数据，进行解析
                            if(result==true)
                            {$("#addform").submit();return true;}
                            else
                            {alert("已存在该联系方式");return false;}
                        }
                    });
                }
            })
        })
    </script>

</head>
<body class="inner-container">
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td >标题</td>
            <td>内容</td>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty byuserid.phone}" var="result">
        <form action="../user/saveUser" class="form-inline" method="post" id="addform">
            <tr><td>用户名：</td><td><input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名"/></td></tr>
            <tr><td>联系方式:</td><td><input type="text"  name="phone" class="form-control" id="phone" placeholder="联系方式用于登录系统"/></td></tr>
            <tr><td>邮箱:</td><td><input type="text"  name="email" class="form-control" id="email" placeholder="邮箱用于找回密码"/></td></tr>
            <tr><td>密码:</td><td><input type="text"  name="password" class="form-control" id="password" value="123456" placeholder="默认密码：123456"/></td></tr>
            <tr><td>职位:</td><td><input type="text"  name="job" class="form-control" placeholder="请输入当前职位"/></td></tr>
            <tr><td></td><td><input type="button"value="添加" class="btn btn-primary" id="addbtn" />
        </form>
        <a href="../user/findAll" class="btn btn-warning">取消</a></td></tr>
        </c:if>
        <c:if test="${!result}">
            <form action="../user/updateUser" class="form-inline" method="post">
                <tr><td>用户id：</td><td><input type="text" name="userid" class="form-control" value="${byuserid.userid}" readonly/></td></tr>
                <tr><td>用户名：</td><td><input type="text" name="username" class="form-control" value="${byuserid.username}" placeholder="请输入用户名"/></td></tr>
                <tr><td>联系方式:</td><td><input type="text"  name="phone" class="form-control" value="${byuserid.phone}" placeholder="联系方式用于登录系统"/></td></tr>
                <tr><td>邮箱:</td><td><input type="text"  name="email" class="form-control" value="${byuserid.email}" placeholder="邮箱用于找回密码"/></td></tr>
                <tr><td>密码:</td><td><input type="password"  name="password" class="form-control" value="${byuserid.password}" placeholder="默认密码：123456"/></td></tr>
                <tr><td>职位:</td><td><input type="text"  name="job" class="form-control" value="${byuserid.job}" placeholder="请输入当前职位"/></td></tr>
                <tr><td></td><td><input type="submit"value="更新" class="btn btn-success"/>
            </form>
            <a href="../user/findAll" class="btn btn-warning">取消</a></td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
