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
                    //清空表格数据
                    $("tbody").html("");
                    //动态刷新表格
                    for (var i = 0; i < data.list.length; i++) {
                        $("tbody").append( "<tr>"
                            +"<td title='"+data.list[i].bookid+"'>"+data.list[i].bookid+"</td>"
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
                        var first="<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>" +
                            "<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">首</span></a></li>";
                    }
                    else if(!data.isFirstPage){
                        var first="<li><a href=\"../book/findAll?num="+(data.prePage)+"&&now="+change+"&&bookid="+bookid+"&&plannum="+plannum+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&laquo;</span></a></li>" +
                            "<li><a href=\"../book/findAll?num=1&&now="+change+"&&bookid="+bookid+"&&plannum="+plannum+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">首</span></a></li>";
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
                        var last="<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">尾</span></a></li>" +
                            "<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
                    }
                    else if(!data.isLastPage){
                        var last="<li><a href=\"../book/findAll?num="+(data.pages)+"&&now="+change+"&&bookid="+bookid+"&&plannum="+plannum+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">尾</span></a></li>" +
                            "<li><a href=\"../book/findAll?num="+(data.nextPage)+"&&now="+change+"&&bookid="+bookid+"&&plannum="+plannum+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&raquo;</span></a></li>";
                    }
                    var all=first+center+last;
                    $(".pagination").append(all);
                    $(".hhh").html("");
                    var hhh="第"+data.pageNum+"页，共"+data.pages+"页/"+data.total+"条";
                    $(".hhh").append(hhh);
                }
            });
        },1000);
        $(function () {
            $("#front").click(function () {
                $("#front").attr("href","../book/findAll?num=${booklist.prePage}&&bookid="+$("#inputbookid").val()+"&&plannum="+$("#inputplannum").val()+"&&now="+$("#change").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../book/findAll?num=1&&bookid="+$("#inputbookid").val()+"&&plannum="+$("#inputplannum").val()+"&&now="+$("#change").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../book/findAll?num=${booklist.pages}&&bookid="+$("#inputbookid").val()+"&&plannum="+$("#inputplannum").val()+"&&now="+$("#change").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../book/findAll?num=${booklist.nextPage}&&bookid="+$("#inputbookid").val()+"&&plannum="+$("#inputplannum").val()+"&&now="+$("#change").val()+"")
            });
        })
        function gonum(num) {
            $(".aurlcenter").attr("href","../book/findAll?num="+num+"&&bookid="+$("#inputbookid").val()+"&&plannum="+$("#inputplannum").val()+"&&now="+$("#change").val()+"")
        }
    </script>
</head>
<body class="inner-container">
<table><thead><tr>
    <td><button class="btn btn-primary" onclick="$('#change').val('全部')">显示全部</button></td>
    <td><button class="btn btn-success" onclick="$('#change').val('今天未就餐')">显示今天</button></td>
    <td><input id="change" value="${now}" class="form-control" readonly style="max-width: 120px"></td></tr></thead>
</table>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td >预约id</td>
            <td>就餐人数</td>
            <td>状态</td>
            <td>创建时间</td>
            <td>更新时间</td>
            <td>等待人数</td>
        </tr>
        <tr>
            <td ><input class="form-control" placeholder="输入预约号查询"  id="inputbookid" value="${bookid}"></td>
            <td><input class="form-control" placeholder="输入就餐人数查询"  id="inputplannum" value="${plannum}"></td>
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
                <td title="${book.plan_num}">${book.plan_num}</td>
                <td  title="${book.status}">${book.status}</td>
                <td title="${book.createtime}">${book.createtime}</td>
                <td title="${book.updatetime}">${book.updatetime}</td>
                <td title="${book.wait_num}">${book.wait_num}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="hhh">第${booklist.pageNum}页，共${booklist.pages}页/${booklist.total}条</div>
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${booklist.isFirstPage}">
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
            <c:forEach items="${booklist.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==booklist.pageNum}">
                        <li class="active" value="${num}">
                            <span>${num}<span class="sr-only">(current)</span></span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="" class="aurlcenter" onclick='gonum("${num}")'>${num}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${booklist.isLastPage}">
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
