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
        <form action="../user/saveUser" class="form-inline" method="post">
            <tr><td>用户名：</td><td><input type="text" name="username" class="form-control"/></td></tr>
            <tr><td>联系方式:</td><td><input type="text"  name="phone" class="form-control"/></td></tr>
            <tr><td>密码:</td><td><input type="text"  name="password" class="form-control"/></td></tr>
            <tr><td>职位:</td><td><input type="text"  name="job" class="form-control"/></td></tr>
            <tr><td></td><td><input type="submit"value="添加" class="btn btn-primary"/>
        </form>
        <a href="../user/findAll" class="btn btn-warning">取消</a></td></tr>
        </c:if>
        <c:if test="${!result}">
            <form action="../user/updateUser" class="form-inline" method="post">
                <tr><td>用户id：</td><td><input type="text" name="userid" class="form-control" value="${byuserid.userid}" /></td></tr>
                <tr><td>用户名：</td><td><input type="text" name="username" class="form-control" value="${byuserid.username}"/></td></tr>
                <tr><td>联系方式:</td><td><input type="text"  name="phone" class="form-control" value="${byuserid.phone}"/></td></tr>
                <tr><td>密码:</td><td><input type="text"  name="password" class="form-control" value="${byuserid.password}"/></td></tr>
                <tr><td>职位:</td><td><input type="text"  name="job" class="form-control" value="${byuserid.job}"/></td></tr>
                <tr><td></td><td><input type="submit"value="更新" class="btn btn-success"/>
            </form>
            <a href="../user/findAll" class="btn btn-warning">取消</a></td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
