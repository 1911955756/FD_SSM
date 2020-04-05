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
    <title>couponlist</title>
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
            $("#front").click(function () {
                $("#front").attr("href","../coupon/findAll?num=${coulist.pageNum-1}&&couname="+$("#couname").val()+"&&coutype="+$("#coutype").val()+"&&coustatus="+$("#coustatus").val()+"&&couprice="+$("#couprice").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../coupon/findAll?num=1&&couname="+$("#couname").val()+"&&coutype="+$("#coutype").val()+"&&coustatus="+$("#coustatus").val()+"&&couprice="+$("#couprice").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../coupon/findAll?num=${coulist.pages}&&couname="+$("#couname").val()+"&&coutype="+$("#coutype").val()+"&&coustatus="+$("#coustatus").val()+"&&couprice="+$("#couprice").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../coupon/findAll?num=${coulist.pageNum+1}&&couname="+$("#couname").val()+"&&coutype="+$("#coutype").val()+"&&coustatus="+$("#coustatus").val()+"&&couprice="+$("#couprice").val()+"")
            });
        });
        function gonum(num) {
            $(".aurlcenter").attr("href","../coupon/findAll?num="+num+"&&couname="+$("#couname").val()+"&&coutype="+$("#coutype").val()+"")
        }
    </script>

</head>
<body class="inner-container">
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td>优惠券</td>
            <td>类型</td>
            <td>状态</td>
            <td>优惠价格</td>
            <td>使用条件</td>
            <td>创建时间</td>
            <td>结束时间</td>
            <td>更新时间</td>
            <td>操作</td>
        </tr>
        <tr>
            <form action="../coupon/findAll">
            <td><input class="form-control" name="couname" placeholder="输入优惠券名搜索" value="${couname}" id="couname"></td>
            <td><input class="form-control" name="coutype" placeholder="输入类型搜索" value="${coutype}" id="coutype"></td>
            <td><input class="form-control" name="coustatus" placeholder="输入状态搜索" value="${coustatus}" id="coustatus"></td>
            <td><input class="form-control" name="couprice" placeholder="输入优惠价格搜索" value="${couprice}" id="couprice"></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><button class="btn btn-success" type="submit">搜索</button></td>
            </form>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${coulist.list}" var="coupon">
            <tr>
                <td title="${coupon.couponname}">${coupon.couponname}</td>
                <td title="${coupon.type}">${coupon.type}</td>
                <td title="${coupon.status}">${coupon.status}</td>
                <td title="${coupon.price}">${coupon.price}</td>
                <td title="${coupon.condition}">${coupon.condition}</td>
                <td title="${coupon.createtime}">${coupon.createtime}</td>
                <td title="${coupon.endtime}">${coupon.endtime}</td>
                <td title="${coupon.updatetime}">${coupon.updatetime}</td>
                <td><a href="../coupon/toupdatecoupon?couponid=1" class="btn btn-primary">添加</a><br/>
                    <a href="../coupon/toupdatecoupon?couponid=${coupon.couponid}" class="btn btn-warning">修改</a><br/>
<%--                    <a href="../coupon/deleteCoupon?couponid=${coupon.couponid}&&status=已过期" class="btn btn-danger">删除</a><br/>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    第${coulist.pageNum}页，共${coulist.pages}页
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${coulist.isFirstPage}">
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
            <c:forEach items="${coulist.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==coulist.pageNum}">
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
                <c:when test="${coulist.isLastPage}">
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
