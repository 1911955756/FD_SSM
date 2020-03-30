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
    <title>odview</title>
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
        .outer-container {
            position: relative;
            overflow: hidden;
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
        .bottom{
            border-bottom: 2px solid #850000;
        }
    </style>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body class="inner-container">
<div class="container-fluid ">
    <div class="row">
        <div class="col-sm-2 col-md-2">
            <div class="bottom">后厨：</div>
            <img src="../images/egg.jpg" class="img-responsive">
        </div>
        <div class="table-responsive col-sm-10 col-md-9 col-md-offset-1 outer-container">
            <div class="bottom">正在烹饪......</div>
            <iframe marginWidth=0 marginHeight=0 src="../orderdetail/findAll?status=烹饪中"  scrolling="auto" frameBorder=0 width="100%" height="50%"></iframe>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-2 col-md-2">
            <div class="bottom">后厨与服务员：</div>
            <img src="../images/waiter.png" class="img-responsive">
        </div>
        <div class="table-responsive col-sm-10 col-md-9 col-md-offset-1 outer-container">
            <div class="bottom">尽快处理:</div>
            <iframe marginWidth=0 marginHeight=0 src="../orderdetail/findAll?status=${status}"  scrolling="auto" frameBorder=0 width="100%" height="50%"></iframe>
        </div>
    </div>
</div>
</body>
</html>