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
    <title>typelist</title>
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
            $("#back").click(function () {
                window.history.back()
            })
            $("#addtypebtn").click(function () {
                var addtype=$("#addtype").val();
                if(addtype==""){
                    alert("名称不能为空");
                    $("#addtype").focus();
                    return false;
                }
                else{
                    $.ajax({
                        // 编写json格式，设置属性和值
                        url:"../type/findIdByname",
                        data:{
                            name:$("#addtype").val()
                        },
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            // data服务器端响应的json的数据，进行解析
                            if(result==true)
                            {$("#addtypeform").submit();return true;}
                            else
                            {alert("已存在该类型");return false;}
                        }
                    });
                }
            });
            $("#check").click(function () {
                $("#check").attr("href","../type/findAll?tyname="+$("#addtype").val()+"&&tystatus="+$("#typestatus").val()+"")
            });
            $("#front").click(function () {
                $("#front").attr("href","../type/findAll?num=${typelist.prePage}&&tyname="+$("#addtype").val()+"&&tystatus="+$("#typestatus").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../type/findAll?num=1&&tyname="+$("#addtype").val()+"&&tystatus="+$("#typestatus").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../type/findAll?num=${typelist.pages}&&tyname="+$("#addtype").val()+"&&tystatus="+$("#typestatus").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../type/findAll?num=${typelist.nextPage}&&tyname="+$("#addtype").val()+"&&tystatus="+$("#typestatus").val()+"")
            });
        });
        function gonum(num) {
            $(".aurlcenter").attr("href","../type/findAll?num="+num+"&&tyname="+$("#addtype").val()+"&&tystatus="+$("#typestatus").val()+"")
        };
        function checktype(typeid) {
            var flag=false;
            $.ajax({
                url:'../typemenu/findtypeid',
                datatype : "json",
                type : "post",
                data:{
                    typeid:typeid
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
<button class="btn btn-info" id="back">&laquo;返回</button>
<div class="container-fluid margintop">
    <div class="row">
<div class="table-responsive col-sm-12 col-md-7">
    <div class="bottom">已有类型：</div>
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
            <form id="addtypeform" method="post" action="../type/savetype">
            <td><input id="addtype" name="name" placeholder="请输入名称" type="text" class="form-control" value="${tyname}"/></td>
                <td ><input id="typestatus" name="status" placeholder="输入状态查询" type="text" class="form-control" value="${tystatus}"/></td>
                <td ></td>
                <td ></td>
            <td>
                <a id="check" href="" class="btn btn-success">查询</a>
                <input id="addtypebtn" type="button" value="添加" class="btn btn-primary"/>
            </td>
            </form>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${typelist.list}" var="type">
            <tr>
                <td title="${type.name}">${type.name}</td>
                <td title="${type.status}">${type.status}</td>
                <td title="${type.createtime}">${type.createtime}</td>
                <td title="${type.updatetime}">${type.updatetime}</td>
                <td>
                    <c:choose>
                        <c:when test="${type.status=='下架'}">
                    <a href="../type/updatetypestatus?typeid=${type.typeid}&&status=上架&&num=${typelist.pageNum}" class="btn btn-success">上架</a><br/>
                        </c:when>
                        <c:otherwise>
                    <a href="../type/updatetypestatus?typeid=${type.typeid}&&status=下架&&num=${typelist.pageNum}" class="btn btn-warning">下架</a><br/>
                        </c:otherwise>
                    </c:choose>
                    <a href="../type/deletetype?typeid=${type.typeid}" class="btn btn-danger" onclick='return checktype("${type.typeid}")' >删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    第${typelist.pageNum}页，共${typelist.pages}页/${typelist.total}条
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${typelist.isFirstPage}">
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
            <c:forEach items="${typelist.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==typelist.pageNum}">
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
                <c:when test="${typelist.isLastPage}">
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
            <div class="bottom">已关联类目:</div>
            <iframe marginWidth=0 marginHeight=0 src="../type/findPageTM"  scrolling="auto" frameBorder=0 width="100%" height="500px"></iframe>
        </div>
</div>
</div>
</body>
</html>
