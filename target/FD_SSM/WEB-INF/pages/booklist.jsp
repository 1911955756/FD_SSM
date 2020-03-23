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
    <title>booklist</title>
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
        setInterval(function (){
            var change=$("#change").val(),
                bookid=$("#inputbookid").val(),
                plannum=$("#inputplannum").val();
            $.ajax({
                url : "../book/findAll2",
                datatype : "json",
                type : "post",
                data:{
                    num:$(".active").val(),
                    now:change,
                    bookid:bookid,
                    plannum:plannum
                },
                success : function(data) {
                    console.log(data.list.length)
                    //清空表格数据
                    $("tbody").html("");
                    //动态刷新表格
                    for (var i = 0; i < data.list.length; i++) {
                        $("tbody").append( "<tr>"
                            +"<td title='"+data.list[i].bookid+"'>"+data.list[i].bookid+"</td>"
                            +"<td title='"+data.list[i].cs_id+"'>"+data.list[i].cs_id+"</td>"
                            +"<td title='"+data.list[i].plan_num+"'>"+ data.list[i].plan_num +"</td>"
                            +"<td title='"+data.list[i].status+"'>"+ data.list[i].status +"</td>"
                            +"<td title='"+data.list[i].createtime+"'>"+ data.list[i].createtime +"</td>"
                            +"<td title='"+data.list[i].updatetime+"'>"+ data.list[i].updatetime +"</td>"
                            +"<td title='"+data.list[i].wait_num+"'>"+ data.list[i].wait_num +"</td>"
                            +"</tr>");
                    }
                    // 动态加载分页栏
                    $(".pagination").html("");
                    if(data.isFirstPage){
                        var first="<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
                    }
                    else if(!data.isFirstPage){
                        var first="                <li>\n" +
                            "                    <a href=\"../book/findAll?num="+(data.pageNum+1)+"&&now="+change+"&&bookid="+bookid+"&&plannum="+plannum+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&laquo;</span>\n" +
                            "                    </a>\n" +
                            "                </li>";
                    }
                    var center="";
                    for(var i=0;i<data.navigatepageNums.length;i++){
                        if(data.navigatepageNums[i]==data.pageNum){
                            var now="<li class=\"active\" value="+data.navigatepageNums[i]+">\n" +
                                "                        <span>"+data.navigatepageNums[i]+"</span>\n" +
                                "                    </li>";
                        }else if(data.navigatepageNums[i]!=data.pageNum){
                            var now="<li><a href=\"../book/findAll?num="+data.navigatepageNums[i]+"&&now="+change+"&&bookid="+bookid+"&&plannum="+plannum+"\">"+data.navigatepageNums[i]+"</a></li>";
                        }
                        center=center+now;
                    }
                    if(data.isLastPage){
                        var last="<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
                    }
                    else if(!data.isLastPage){
                        var last="                <li>\n" +
                            "                    <a href=\"../book/findAll?num="+(data.pageNum-1)+"&&now="+change+"&&bookid="+bookid+"&&plannum="+plannum+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&raquo;</span>\n" +
                            "                    </a>\n" +
                            "                </li>";
                    }
                    var all=first+center+last;
                    $(".pagination").append(all);
                }
            });
        },1000);
    </script>
</head>
<body class="inner-container">
<div class="table-responsive">
    <div style="display: none"><input id="change" value="${now}" ></div>
    <button class="btn btn-primary" onclick="$('#change').val('no')">显示全部</button>
    <button class="btn btn-success" onclick="$('#change').val('yes')">显示今天</button>
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td >预约id</td>
            <td>顾客id</td>
            <td>就餐人数</td>
            <td>状态</td>
            <td>创建时间</td>
            <td>更新时间</td>
            <td>等待人数</td>
        </tr>
        <tr>
            <td ><input class="form-control" placeholder="输入预约号查询"  id="inputbookid"></td>
            <td></td>
            <td><input class="form-control" placeholder="输入就餐人数查询"  id="inputplannum"></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${booklist.list}" var="book">
            <tr>
                <td title="${book.bookid}">${book.bookid}</td>
                <td title="${book.cs_id}">${book.cs_id}</td>
                <td title="${book.plan_num}">${book.plan_num}</td>
                <td  title="${book.status}">${book.status}</td>
                <td title="${book.createtime}">${book.createtime}</td>
                <td title="${book.updatetime}">${book.updatetime}</td>
                <td title="${book.wait_num}">${book.wait_num}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${booklist.isFirstPage}">
                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="../book/findAll?num=${booklist.pageNum-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${booklist.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==booklist.pageNum}">
                        <li class="active" value="${num}">
                            <span>${num}</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="../book/findAll?num=${num}">${num}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${booklist.isLastPage}">
                    <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="../book/findAll?num=${booklist.pageNum+1}" aria-label="Next">
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
