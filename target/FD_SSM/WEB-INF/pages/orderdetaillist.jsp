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
    <title>orderdetaillist</title>
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
            //alert("响应了");
            $.ajax({
                url : "../orderdetail/findAll2",
                datatype : "json",
                type : "post",
                data:{
                    num:$(".active").val(),
                    status:$("#inputstatus").val()
                },
                success : function(data) {
                    //清空表格数据
                    $("tbody").html("");
                    //动态刷新表格
                    var html="";
                    for (var i = 0; i < data.list.length; i++) {
                        var content="<tr>"
                            +"<td title='"+data.list[i].odid+"'>"+data.list[i].odid+"</td>"
                            +"<td title='"+data.list[i].orderid+"'>"+data.list[i].orderid+"</td>"
                            +"<td title='"+data.list[i].menu.menuname+"'>"+data.list[i].menu.menuname+"</td>"
                            +"<td title='"+ data.list[i].menu_num +"'>"+ data.list[i].menu_num +"</td>"
                            +"<td title='"+ data.list[i].order1.tableid +"'>"+ data.list[i].order1.tableid +"</td>"
                            +"<td title='"+ data.list[i].status +"'>"+ data.list[i].status +"</td>"
                            +"<td title='"+ data.list[i].createtime +"'>"+ data.list[i].createtime +"</td>"
                            +"<td title='"+ data.list[i].updatetime +"'>"+ data.list[i].updatetime +"</td>";
                        if (data.list[i].status=='待烹饪'){
                            var a="<td><a href='../orderdetail/cook?odid="+data.list[i].odid+"'class=\"btn btn-primary\">烹饪</a></td></tr>"}
                        else if (data.list[i].status=='烹饪中'){
                            var a="<td><a href='../orderdetail/finishcook?odid="+data.list[i].odid+"' class=\"btn btn-success\">完成</a></td></tr>"}
                        else if (data.list[i].status=='待上菜'){
                            var a="<td><a href='../orderdetail/finish?odid="+data.list[i].odid+"' class=\"btn btn-warning\">已上菜</a></td></tr>"};
                        html=content+a;
                        $("tbody").append(html);
                    }
                    $(".pagination").html("");
                    if(data.isFirstPage){
                        var first="<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
                    }
                    else if(!data.isFirstPage){
                        var first="                <li>\n" +
                            "                    <a href=\"../orderdetail/findAll?num="+(data.pageNum+1)+"&&status="+ data.list[0].status +"\" aria-label=\"Previous\">\n" +
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
                            var now="<li><a href=\"../orderdetail/findAll?num="+data.navigatepageNums[i]+"&&status="+ data.list[0].status +"\">"+data.navigatepageNums[i]+"</a></li>";
                        }
                        center=center+now;
                    }
                    if(data.isLastPage){
                        var last="<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
                    }
                    else if(!data.isLastPage){
                        var last="                <li>\n" +
                            "                    <a href=\"../orderdetail/findAll?num="+(data.pageNum-1)+"&&status="+ data.list[0].status +"\" aria-label=\"Previous\">\n" +
                            "                        <span aria-hidden=\"true\">&raquo;</span>\n" +
                            "                    </a>\n" +
                            "                </li>";
                    }
                    var all=first+center+last;
                    $(".pagination").append(all);
                }
            });
        },3000);
    </script>
</head>
<body class="inner-container">
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <div style="display: none"><input id="inputstatus" value="${orderdetailstatus}"></div>
        <thead>
        <tr>
            <td>上菜单id</td>
            <td >订单id</td>
            <td>菜名</td>
            <td>数量</td>
            <td>桌号</td>
            <td>状态</td>
            <td>创建时间</td>
            <td>更新时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderdetaillist.list}" var="orderdetail">
            <tr>
                <td title="${orderdetail.odid}">${orderdetail.odid}</td>
                <td title="${orderdetail.orderid}">${orderdetail.orderid}</td>
                <td title="${orderdetail.menu.menuname}">${orderdetail.menu.menuname}</td>
                <td title="${orderdetail.menu_num}">${orderdetail.menu_num}</td>
                <td title="${orderdetail.order1.tableid}">${orderdetail.order1.tableid}</td>
                <td title="${orderdetail.status}">${orderdetail.status}</td>
                <td title="${orderdetail.createtime}">${orderdetail.createtime}</td>
                <td title="${orderdetail.updatetime}">${orderdetail.updatetime}</td>
                <td>
                    <c:choose>
                        <c:when test="${orderdetail.status=='待烹饪'}">
                        <a href='../orderdetail/cook?odid=${orderdetail.odid}' class="btn btn-primary">烹饪</a><br/>
                        </c:when>
                        <c:when test="${orderdetail.status=='烹饪中'}">
                            <a href='../orderdetail/finishcook?odid=${orderdetail.odid}' class="btn btn-success">完成</a><br/>
                        </c:when>
                        <c:when test="${orderdetail.status=='待上菜'}">
                        <a href='../orderdetail/finish?odid=${orderdetail.odid}' class="btn btn-warning">已上菜</a>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%--    分页条--%>
<nav aria-label="Page navigation" class="right">
    <ul class="pagination">
        <c:choose>
            <c:when test="${orderdetaillist.isFirstPage}">
                <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="../orderdetail/findAll?num=${orderdetaillist.pageNum-1}&&status=${orderdetaillist.list.get(0).status}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:forEach items="${orderdetaillist.navigatepageNums}" var="num">
            <c:choose>
                <c:when test="${num==orderdetaillist.pageNum}">
                    <li class="active" value="${num}">
                        <span>${num}</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="../orderdetail/findAll?num=${num}&&status=${orderdetaillist.list.get(0).status}">${num}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${orderdetaillist.isLastPage}">
                <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="../orderdetail/findAll?num=${orderdetaillist.pageNum+1}&&status=${orderdetaillist.list.get(0).status}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>
</body>
</html>
