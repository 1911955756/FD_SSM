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
    <title>userlist</title>
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
            $("#tmbtn").click(function () {
                $.ajax({
                    url : "../userrole/saveUserRole",
                    datatype : "json",
                    data:{
                        roleid:$("#roleid").val(),
                        userid:$("#userid").val()
                    },
                    type : "post",
                    success : function (data) {
                        if(data==false){
                            alert("授权成功");
                            return ture;
                        }else{
                            alert("已存在该授权");
                            return false;
                        }
                    }
                })
            });
            $("#front").click(function () {
                $("#front").attr("href","../user/findAll?num=${userlist.pageNum-1}&&uname="+$("#uname").val()+"&&ujob="+$("#ujob").val()+"&&uphone="+$("#uphone").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../user/findAll?num=1&&uname="+$("#uname").val()+"&&ujob="+$("#ujob").val()+"&&uphone="+$("#uphone").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../user/findAll?num=${userlist.pages}&&uname="+$("#uname").val()+"&&ujob="+$("#ujob").val()+"&&uphone="+$("#uphone").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../user/findAll?num=${userlist.pageNum+1}&&uname="+$("#uname").val()+"&&ujob="+$("#ujob").val()+"&&uphone="+$("#uphone").val()+"")
            });
        });
        function gonum(num) {
            $(".aurlcenter").attr("href","../user/findAll?num="+num+"&&uname="+$("#uname").val()+"&&ujob="+$("#ujob").val()+"&&uphone="+$("#uphone").val()+"")
        }
    </script>
</head>
<body class="inner-container">
<div class="table-responsive">
    <table class="table-responsive">
        <tr>
            <td><a href="../role/findAll">角色管理</a></td>
            <td>角色授权：</td>
            <td><select name="roleid" id="roleid" class="form-control maxwidth">
                <c:forEach items="${rolelist}" var="role">
                    <option value=${role.roleid}>${role.rolename}</option>
                </c:forEach>
            </select></td>
            <td>+</td>
            <td><select name="userid" id="userid" class="form-control maxwidth">
                <c:forEach items="${userlist.list}" var="user">
                    <option value=${user.userid}>${user.username}</option>
                </c:forEach>
            </select></td>
            <td><button id="tmbtn" type="button" class="btn btn-success">授权</button></td>
        </tr>
    </table>
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td>用户名</td>
            <td>手机号</td>
            <td>密码</td>
            <td>创建时间</td>
            <td>更新时间</td>
            <td>职位</td>
            <td>操作</td>
        </tr>
        <tr>
            <form action="../user/findAll">
            <td><input id="uname" name="uname" value="${uname}" placeholder="输入名字查询" class="form-control"></td>
            <td><input id="uphone" name="uphone" value="${uphone}" placeholder="输入号码查询" class="form-control"></td>
            <td></td>
            <td></td>
            <td></td>
            <td><input id="ujob" name="ujob" value="${ujob}" placeholder="输入职务查询" class="form-control"></td>
            <td><button class="btn btn-success" type="submit">搜索</button></td>
            </form>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userlist.list}" var="user">
            <tr>
                <td title="${user.username}">${user.username}</td>
                <td title="${user.phone}">${user.phone}</td>
                <td title="${user.password}">${user.password}</td>
                <td title="${user.createtime}">${user.createtime}</td>
                <td title="${user.updatetime}">${user.updatetime}</td>
                <td title="${user.job}">${user.job}</td>
                <td>
                    <a href="../user/toupdateUser?userid=1" class="btn btn-primary">新增</a><br/>
                    <a href="../user/toupdateUser?userid=${user.userid}" class="btn btn-warning">修改</a><br/>
                    <a href="../user/deleteUser?userid=${user.userid}"class="btn btn-danger">删除</a><br/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    第${userlist.pageNum}页，共${userlist.pages}页
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${userlist.isFirstPage}">
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
            <c:forEach items="${userlist.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==userlist.pageNum}">
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
                <c:when test="${userlist.isLastPage}">
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
