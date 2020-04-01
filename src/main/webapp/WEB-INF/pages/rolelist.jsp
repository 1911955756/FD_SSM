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
    <title>rolelist</title>
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
        .margintop{
            margin-top: 50px;
        }
        .bottom{
            border-bottom: 2px solid #850000;
        }
    </style>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#addrolebtn").click(function () {
                var addrole=$("#addrole").val();
                if(addrole==""){
                    alert("名称不能为空");
                    $("#addrole").focus();
                    return false;
                }
                else{
                    $.ajax({
                        // 编写json格式，设置属性和值
                        url:"../role/findRolename",
                        data:{
                            name:$("#addrole").val()
                        },
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            // data服务器端响应的json的数据，进行解析
                            if(result.length==0)
                            {$("#addroleform").submit();return true;}
                            else
                            {alert("已存在该角色");return false;}
                        }
                    });
                }
            });
            $("#check").click(function () {
                $("#check").attr("href","../role/findAll?rname="+$("#addrole").val()+"&&rstatus="+$("#rolestatus").val()+"")
            });
            $("#front").click(function () {
                $("#front").attr("href","../role/findAll?num=${rolelist.pageNum-1}&&rname="+$("#addrole").val()+"&&rstatus="+$("#rolestatus").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../role/findAll?num=1&&rname="+$("#addrole").val()+"&&rstatus="+$("#rolestatus").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../role/findAll?num=${rolelist.pages}&&rname="+$("#addrole").val()+"&&rstatus="+$("#rolestatus").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../role/findAll?num=${rolelist.pageNum+1}&&rname="+$("#addrole").val()+"&&rstatus="+$("#rolestatus").val()+"")
            });
        });
        function gonum(num) {
            $(".aurlcenter").attr("href","../role/findAll?num="+num+"&&rname="+$("#addrole").val()+"&&rstatus="+$("#rolestatus").val()+"")
        };
        function checkrole(roleid) {
            var flag=false;
            $.ajax({
                url:'../userrole/findroleid',
                datatype : "json",
                type : "post",
                data:{
                    roleid:roleid
                },
                async:false,
                success:function (res) {
                    console.log(res)
                    if(res==true){
                        alert("删除失败，类型已绑定");
                        flag=false;
                    }else{
                        alert("删除成功!");
                        flag=true;
                    }
                }
                ,
            })
            return flag;
        }
    </script>
</head>
<body class="inner-container">
<div class="container-fluid margintop">
    <div class="row">
<div class="table-responsive col-sm-12 col-md-7">
    <div class="bottom">已有角色：</div>
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td>名称</td>
            <td>状态</td>
            <td>创建时间</td>
            <td>更新时间</td>
            <td>操作</td>
        </tr>
        <tr>
            <form id="addroleform" method="post" action="../role/saverole">
            <td><input id="addrole" name="name" placeholder="请输入名称" type="text" class="form-control" value="${rstatus}"/></td>
                <td><input id="rolestatus" name="status" placeholder="输入状态查询" type="text" class="form-control" value="${rstatus}"/></td>
                <td ></td>
                <td ></td>
                <td><a id="check" href="" class="btn btn-success">查询</a>
                <input id="addrolebtn" type="button" value="添加" class="btn btn-primary"/></td>
            </form>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rolelist.list}" var="role">
            <tr>
                <td title="${role.rolename}">${role.rolename}</td>
                <td title="${role.status}">${role.status}</td>
                <td title="${role.createtime}">${role.createtime}</td>
                <td title="${role.updatetime}">${role.updatetime}</td>
                <td>
                    <c:choose>
                        <c:when test="${role.status=='注销'}">
                    <a href="../role/updaterolestatus?roleid=${role.roleid}&&status=使用&&num=${rolelist.pageNum}" class="btn btn-success">使用</a><br/>
                        </c:when>
                        <c:otherwise>
                    <a href="../role/updaterolestatus?roleid=${role.roleid}&&status=注销&&num=${rolelist.pageNum}" class="btn btn-warning">注销</a><br/>
                        </c:otherwise>
                    </c:choose>
                    <a href="../role/deleterole?roleid=${role.roleid}" class="btn btn-danger" onclick='return checkrole("${role.roleid}")' >删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    第${rolelist.pageNum}页，共${rolelist.pages}页
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${rolelist.isFirstPage}">
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
            <c:forEach items="${rolelist.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==rolelist.pageNum}">
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
                <c:when test="${rolelist.isLastPage}">
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
        <div class="table-responsive col-sm-12 col-md-5  outer-container">
            <div class="bottom">已关联用户:</div>
            <iframe marginWidth=0 marginHeight=0 src="../role/findPageUR"  scrolling="auto" frameBorder=0 width="100%" height="80%"></iframe>
        </div>
</div>
</div>
</body>
</html>
