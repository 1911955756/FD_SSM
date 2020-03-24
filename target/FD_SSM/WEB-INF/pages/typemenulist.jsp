<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/12
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>typemenu</title>
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
        }
    </style>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#front").click(function () {
                $("#front").attr("href","../type/findPageTM?num=${tmlist.pageNum-1}&&tyname="+$("#tyname").val()+"&&mname="+$("#mname").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../type/findPageTM?num=1&&tyname="+$("#tyname").val()+"&&mname="+$("#mname").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../type/findPageTM?num=${tmlist.pages}&&tyname="+$("#tyname").val()+"&&mname="+$("#mname").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../type/findPageTM?num=${tmlist.pageNum+1}&&tyname="+$("#tyname").val()+"&&mname="+$("#mname").val()+"")
            });
        })
        function gonum(num) {
            $(".aurlcenter").attr("href","../type/findPageTM?num="+num+"&&tyname="+$("#tyname").val()+"&&mname="+$("#mname").val()+"")
        }
    </script>
</head>
<body class="inner-container">
<div class="table-responsive col-sm-12">
<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <td>类名</td>
        <td>菜名</td>
        <td>操作</td>
    </tr>
    <tr>
        <form action="../type/findPageTM">
        <td><input  class="form-control" placeholder="输入类名查询" name="tyname" id="tyname" value="${tyname}"></td>
        <td><input  class="form-control" placeholder="输入菜名查询" name="mname" id="mname" value="${mname}"></td>
        <td><button type="submit" class="btn btn-success" id="pagebtn">查询</button></td>
        </form>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tmlist.list}" var="typemenu">
        <c:forEach items="${typemenu.menus}" var="menu">
            <tr>
                <td title="${typemenu.name}">${typemenu.name}</td>
                <td title="${menu.menuname}">${menu.menuname}</td>
                <td><a href="../typemenu/deletetypemenu?typeid=${typemenu.typeid}&&menuid=${menu.menuid}" class="btn btn-danger">解绑</a></td>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>
<%--    分页条--%>
<nav aria-label="Page navigation" class="right">
    <ul class="pagination">
        <c:choose>
            <c:when test="${tmlist.isFirstPage}">
                <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">首</span></a></li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="" aria-label="Previous" id="front">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li>
                    <a href="" aria-label="Previous" id="first">
                        <span aria-hidden="true">首</span>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:forEach items="${tmlist.navigatepageNums}" var="num">
            <c:choose>
                <c:when test="${num==tmlist.pageNum}">
                    <li class="active">
                        <span>${num}<span class="sr-only">(current)</span></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="" class="aurlcenter" onclick='gonum("${num}")'>${num}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${tmlist.isLastPage}">
                <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">尾</span></a></li>
                <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="" aria-label="Next" id="last">
                        <span aria-hidden="true">尾</span>
                    </a>
                </li>
                <li>
                    <a href="" aria-label="Next" id="next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>
</div>
</body>
</html>
