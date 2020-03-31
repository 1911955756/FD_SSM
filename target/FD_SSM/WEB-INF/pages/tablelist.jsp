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
    <script>
        setInterval(function (){
            var tableid=$("#addtaid").val(),
                peoplenum=$("#addtanum").val(),
                status=$("#addtastatus").val();
            $.ajax({
                url : "../table/findAll2",
                datatype : "json",
                type : "post",
                data:{
                    num:$(".active").val(),
                    taid:tableid,
                    penum:peoplenum,
                    tasta:status
                },
                success : function(data) {
                    var pagenum=$(".active").val()
                    //清空表格数据
                    $("tbody").html("");
                    //动态刷新表格
                    for (var i = 0; i < data.list.length; i++) {
                        var table="<tr>"
                            +"<td title='"+data.list[i].tableid+"'>"+data.list[i].tableid+"</td>"
                            +"<td title='"+data.list[i].people_num+"'>"+data.list[i].people_num+"</td>"
                            +"<td title='"+ data.list[i].status +"' id='status'>"+ data.list[i].status +"</td>";
                        if(data.list[i].status=='用餐中'){
                            var btn="<td><a href=\"../table/updatetablestatus?tableid="+data.list[i].tableid+"&&status=空闲&&num="+pagenum+"\"><button class='btn btn-success'>空闲</button></a></td>"
                            +"</tr>";}
                        else if(data.list[i].status=='空闲'){
                            var btn="<td><a href=\"../table/updatetablestatus?tableid="+data.list[i].tableid+"&&status=用餐中&&num="+pagenum+"\"><button class='btn btn-primary'>就餐</button></a></td>"
                                +"</tr>";}
                        var all=table+btn;
                        $("tbody").append(all);
                    }
                    $(".pagination").html("");
                    if(data.isFirstPage){
                        var first="<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>" +
                            "<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">首</span></a></li>";
                    }
                    else if(!data.isFirstPage){
                        var first="<li><a href=\"../table/findAll?num="+(data.pageNum-1)+"&&tableid="+tableid+"&&peoplenum="+peoplenum+"&&status="+status+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&laquo;</span></a></li>" +
                            "<li><a href=\"../table/findAll?num=1&&tableid="+tableid+"&&peoplenum="+peoplenum+"&&status="+status+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">首</span></a></li>";
                    }
                    var center="";
                    for(var i=0;i<data.navigatepageNums.length;i++){
                        if(data.navigatepageNums[i]==data.pageNum){
                            var now="<li class=\"active\" value="+data.navigatepageNums[i]+">\n" +
                                "                        <span>"+data.navigatepageNums[i]+"</span>\n" +
                                "                    </li>";
                        }else if(data.navigatepageNums[i]!=data.pageNum){
                            var now="<li><a href=\"../table/findAll?num="+data.navigatepageNums[i]+"&&tableid="+tableid+"&&peoplenum="+peoplenum+"&&status="+status+"\">"+data.navigatepageNums[i]+"</a></li>";
                        }
                        center=center+now;
                    }
                    if(data.isLastPage){
                        var last="<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">尾</span></a></li>" +
                            "<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
                    }
                    else if(!data.isLastPage){
                        var last="<li><a href=\"../table/findAll?num="+(data.pages)+"&&tableid="+tableid+"&&peoplenum="+peoplenum+"&&status="+status+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">尾</span></a></li>" +
                            "<li><a href=\"../table/findAll?num="+(data.pageNum+1)+"&&tableid="+tableid+"&&peoplenum="+peoplenum+"&&status="+status+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&raquo;</span></a></li>";
                    }
                    var all=first+center+last;
                    $(".pagination").append(all);

                }
            });
        },2000);
        $(function () {
            $("#addtablebtn").click(function () {
                if($("#addtaid").val()==""){
                    alert("餐桌号不能为空");
                    $("#addtaid").focus();
                    return false;
                }
                else if($("#addtanum").val()==""){
                    alert("可坐人数不能为空");
                    $("#addtanum").focus();
                    return false;
                }
                else{
                    $.ajax({
                        url : "../table/savetable",
                        datatype : "json",
                        data:{'tableid':$("#addtaid").val(),'people_num':$("#addtanum").val()},
                        type : "post",
                        success : function(data) {
                            if(data){
                                alert("添加成功");
                            return ture;}
                        }
                    });
                }
            });
            $("#front").click(function () {
                $("#front").attr("href","../table/findAll?num=${tablelist.pageNum-1}&&tasta="+$("#addtastatus").val()+"&&taid="+$("#addtaid").val()+"&&penum="+$("#addtanum").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../table/findAll?num=1&&tasta="+$("#addtastatus").val()+"&&taid="+$("#addtaid").val()+"&&penum="+$("#addtanum").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../table/findAll?num=${orderlist.pages}&&tasta="+$("#addtastatus").val()+"&&taid="+$("#addtaid").val()+"&&penum="+$("#addtanum").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../table/findAll?num=${orderlist.pageNum+1}&&tasta="+$("#addtastatus").val()+"&&taid="+$("#addtaid").val()+"&&penum="+$("#addtanum").val()+"")
            });
        })
        function gonum(num) {
            $(".aurlcenter").attr("href","../table/findAll?num="+num+"&&tasta="+$("#addtastatus").val()+"&&taid="+$("#addtaid").val()+"&&penum="+$("#addtanum").val()+"")
        }
    </script>
</head>
<body class="inner-container">
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td >餐桌号</td>
            <td>可坐人数</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        <tr>
            <td>
                <input type="text" name="tableid" id="addtaid" placeholder="请输入餐桌号" value="${taid}" class="form-control"/>
            </td>
            <td>
                <input type="text" name="people_num" id="addtanum" placeholder="请输入人数" value="${penum}" class="form-control"/>
            </td>
            <td><input type="text" name="people_num" id="addtastatus" placeholder="请输入状态"  value="${tasta}"class="form-control"/></td>
            <td>
                <button id="addtablebtn" type="button" class="btn btn-warning">添加</button>
            </td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tablelist.list}" var="table">
            <tr>
                <td title="${table.tableid}">${table.tableid}</td>
                <td title="${table.tableid}">${table.people_num}</td>
                <td title="${table.status}" id="status">${table.status}</td>
                    <c:choose>
                    <c:when test="${table.status=='用餐中'}">
                        <td><a href="../table/updatetablestatus?tableid=${table.tableid}&&status=空闲&&num=${tablelist.pageNum}"><button class='btn btn-success'>空闲</button></a></td>
                    </c:when>
                    <c:otherwise >
                        <td><a href="../table/updatetablestatus?tableid=${table.tableid}&&status=用餐中&&num=${tablelist.pageNum}"><button class='btn btn-primary'>就餐</button></a></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%--    分页条--%>
<nav aria-label="Page navigation" class="right">
    <ul class="pagination">
        <c:choose>
            <c:when test="${tablelist.isFirstPage}">
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
        <c:forEach items="${tablelist.navigatepageNums}" var="num">
            <c:choose>
                <c:when test="${num==tablelist.pageNum}">
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
            <c:when test="${tablelist.isLastPage}">
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
</body>
</html>
