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
    <title>userrple</title>
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
                $("#front").attr("href","../role/findPageUR?num=${urlist.prePage}&&rname="+$("#rname").val()+"&&uname="+$("#uname").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../role/findPageUR?num=1&&rname="+$("#rname").val()+"&&uname="+$("#uname").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../role/findPageUR?num=${urlist.pages}&&rname="+$("#rname").val()+"&&uname="+$("#uname").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../role/findPageUR?num=${urlist.nextPage}&&rname="+$("#rname").val()+"&&uname="+$("#uname").val()+"")
            });
        })
        function gonum(num) {
            $(".aurlcenter").attr("href","../role/findPageUR?num="+num+"&&rname="+$("#rname").val()+"&&uname="+$("#uname").val()+"")
        }
    </script>
</head>
<body class="inner-container">
<div class="table-responsive col-sm-12">
<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <td>角色</td>
        <td>姓名</td>
        <td>操作</td>
    </tr>
    <tr>
        <form action="../role/findPageUR">
        <td><input  class="form-control" placeholder="输入角色查询" name="rname" id="rname" value="${rname}"></td>
        <td><input  class="form-control" placeholder="输入姓名查询" name="uname" id="uname" value="${uname}"></td>
        <td><button type="submit" class="btn btn-success" id="pagebtn">查询</button></td>
        </form>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${urlist.list}" var="role">
        <c:forEach items="${role.user1s}" var="user">
            <tr>
                <td title="${role.rolename}">${role.rolename}</td>
                <td title="${user.username}">${user.username}</td>
                <td><a href="../userrole/deleteuserrole?roleid=${role.roleid}&&userid=${user.userid}" class="btn btn-danger">解绑</a></td>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>
第${urlist.pageNum}页，共${urlist.pages}页/${urlist.total}条
<%--    分页条--%>
<nav aria-label="Page navigation" class="right">
    <ul class="pagination">
        <c:choose>
            <c:when test="${urlist.isFirstPage}">
                <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo; </span></a></li>
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
        <c:forEach items="${urlist.navigatepageNums}" var="num">
            <c:choose>
                <c:when test="${num==urlist.pageNum}">
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
            <c:when test="${urlist.isLastPage}">
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
