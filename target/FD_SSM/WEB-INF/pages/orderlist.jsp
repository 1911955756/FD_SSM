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
    <title>orderlist</title>
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
             orderid=$("#inputorderid").val(),
            tableid=$("#inputtableid").val(),
            status=$("#inputstatus").val();
            $.ajax({
                url : "../order/findAll2",
                datatype : "json",
                type : "post",
                data:{
                    num:$(".active").val(),
                    now:change,
                    orderid:orderid,
                    tableid:tableid,
                    status:status
                },
                success : function(data) {
                    console.log(data.list.length)
                    //清空表格数据
                    $("tbody").html("");
                    //动态刷新表格
                    for (var i = 0; i < data.list.length; i++) {
                        $("tbody").append( "<tr>"
                            +"<td title='"+data.list[i].orderid+"'>"+data.list[i].orderid+"</td>"
                            +"<td title='"+data.list[i].cs_id+"'>"+data.list[i].cs_id+"</td>"
                            +"<td title='"+ data.list[i].tableid +"'>"+ data.list[i].tableid +"</td>"
                            +"<td title='"+ data.list[i].status +"'>"+ data.list[i].status +"</td>"
                            +"<td title='"+ data.list[i].createtime +"'>"+ data.list[i].createtime +"</td>"
                            +"<td title='"+ data.list[i].updatetime +"'>"+ data.list[i].updatetime +"</td>"
                            +"<td title='"+ data.list[i].total +"'>"+ data.list[i].total +"</td>"
                            +"</tr>");
                    }
                    $(".pagination").html("");
                    if(data.isFirstPage){
                        var first="<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>" +
                            "<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">首</span></a></li>";
                    }
                    else if(!data.isFirstPage){
                        var first="<li><a href=\"../order/findAll?num="+(data.pageNum-1)+"&&now="+change+"&&orderid="+orderid+"&&tableid="+tableid+"&&status="+status+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&laquo;</span></a></li>" +
                            "<li><a href=\"../order/findAll?num=1&&now="+change+"&&orderid="+orderid+"&&tableid="+tableid+"&&status="+status+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">首</span></a></li>";
                    }
                    var center="";
                    for(var i=0;i<data.navigatepageNums.length;i++){
                        if(data.navigatepageNums[i]==data.pageNum){
                            var now="<li class=\"active\" value="+data.navigatepageNums[i]+">\n" +
                                "                        <span>"+data.navigatepageNums[i]+"</span>\n" +
                                "                    </li>";
                        }else if(data.navigatepageNums[i]!=data.pageNum){
                            var now="<li><a href=\"../order/findAll?num="+data.navigatepageNums[i]+"&&now="+change+"&&orderid="+orderid+"&&tableid="+tableid+"&&status="+status+"\">"+data.navigatepageNums[i]+"</a></li>";
                        }
                        center=center+now;
                    }
                    if(data.isLastPage){
                        var last="<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">尾</span></a></li>" +
                            "<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
                    }
                    else if(!data.isLastPage){
                        var last="<li><a href=\"../order/findAll?num="+(data.pages)+"&&now="+change+"&&orderid="+orderid+"&&tableid="+tableid+"&&status="+status+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">尾</span></a></li>" +
                            "<li><a href=\"../order/findAll?num="+(data.pageNum+1)+"&&now="+change+"&&orderid="+orderid+"&&tableid="+tableid+"&&status="+status+"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&raquo;</span></a></li>";
                    }
                    var all=first+center+last;
                    $(".pagination").append(all);
                }
            });
        },1000);
    </script>
</head>
<body class="inner-container">
<div class="container-fluid">
    <div class="row">
<div class="table-responsive col-xs-12">
    <div style="display: none"><input id="change" value="${now}"></div>
    <button class="btn btn-primary" onclick="$('#change').val('no')">显示全部</button>
    <button class="btn btn-success" onclick="$('#change').val('yes')">显示今天</button>
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td>订单id</td>
            <td>顾客id</td>
            <td>餐桌号</td>
            <td>状态</td>
            <td>创建时间</td>
            <td>更新时间</td>
            <td>应付</td>
        </tr>
        <tr>
            <td><input class="form-control" placeholder="输入订单号查询"  id="inputorderid"></td>
            <td></td>
            <td><input class="form-control" placeholder="输入餐桌号查询"  id="inputtableid"></td>
            <td><input class="form-control" placeholder="输入状态查询"  id="inputstatus"></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderlist.list}" var="order">
            <tr>
                <td title="${order.orderid}">${order.orderid}</td>
                <td title="${order.cs_id}">${order.cs_id}</td>
                <td title="${order.tableid}">${order.tableid}</td>
                <td title="${order.status}">${order.status}</td>
                <td title="${order.createtime}">${order.createtime}</td>
                <td title="${order.updatetime}">${order.updatetime}</td>
                <td title="${order.total}">${order.total}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${orderlist.isFirstPage}">
                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">首</span></a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="../order/findAll?num=${orderlist.pageNum-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li>
                        <a href="../order/findAll?num=1" aria-label="Previous">
                            <span aria-hidden="true">首</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${orderlist.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==orderlist.pageNum}">
                        <li class="active" value="${num}">
                            <span>${num}</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="../order/findAll?num=${num}">${num}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${orderlist.isLastPage}">
                    <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">尾</span></a></li>
                    <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="../order/findAll?num=${orderlist.pages}" aria-label="Next">
                            <span aria-hidden="true">尾</span>
                        </a>
                    </li>
                    <li>
                        <a href="../order/findAll?num=${orderlist.pageNum+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>
</div>
</div>
</body>
</html>
