<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/16
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>饭店后台</title>
    <link rel="stylesheet" href="../css/bootstrap.css" type="text/css">
    <STYLE type="text/css">
        .outer-container {
            position: relative;
            overflow: hidden;
        }
        .margintop{
            margin-top:50px;
            background-color: #3c3c3c;
        }
        .name{
            height: 50px;
            color: whitesmoke;
            text-align: center;
            line-height: 50px;
        }
        .left{
            height: 85%;

        }
        .right{
            background-color: whitesmoke;
        }
    </STYLE>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script>
        $(function(){
            $(".sidebar li").on("click",function(){
                var address =$(this).attr("data-src");
                $("iframe").attr("src",address);
            });
        });
    </script>
</head>
<body>
<%--顶部导航栏--%>
<nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">饭店点菜系统管理后台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <c:forEach items="${userrolelist}" var="ur"><a class="name">您好：${ur.username}${ur.roles[0].rolename}</a></c:forEach>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="exit" >退出</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="暂未开通">
            </form>
        </div>
    </div>
        </nav>

<div class="container-fluid margintop" >
    <div class="row">
        <!-- 左侧导航 -->
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar left">
                <c:forEach items="${userrolelist}" var="ur">
                    <h5></h5><br/>
                    <c:choose>
                        <c:when test="${ur.roles[0].rolename=='管理员'}">
                            <li data-src="../order/findAll" ><a href="#">订单列表</a></li>
                            <li data-src="../book/findAll" ><a href="#">预约列表</a></li>
                            <li data-src="../odview/findAll?role=服务员"><a href="#">上菜列表</a></li>
                            <li data-src="../table/findAll"><a href="#">餐桌列表</a></li>
                            <li data-src="../menu/findAll"><a href="#">菜单列表</a></li>
                            <li data-src="../user/findAll"><a href="#">用户列表</a></li>
                            <li data-src="../picture/findAll"><a href="#">轮播图列表</a></li>
                            <li data-src="../chart/findAll"><a href="#">图表列表</a></li>
                        </c:when>
                        <c:when test="${ur.roles[0].rolename=='服务员'}">
                            <li data-src="../order/findAll" ><a href="#">订单列表</a></li>
                            <li data-src="../book/findAll" ><a href="#">预约列表</a></li>
                            <li data-src="../odview/findAll?role=服务员"><a href="#">上菜列表</a></li>
                            <li data-src="../table/findAll"><a href="#">餐桌列表</a></li>
                        </c:when>
                        <c:otherwise>
                            <li data-src="../odview/findAll?role=后厨"><a href="#">上菜列表</a></li>
                            <li data-src="../menu/findAll"><a href="#">菜单列表</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
<%--        右侧内容--%>
        <div class="col-sm-9 col-md-10 outer-container right"><iframe marginWidth=0 marginHeight=0 src=""  scrolling="auto" frameBorder=0 width="100%" height="91%"></iframe></div>
    </div>
</div>
</body>
</html>
