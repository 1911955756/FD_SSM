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
    <title>picturelist</title>
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
            margin-right: 50px;
            margin-top: -30px;
        }
        .margintop{
            margin-top: 50px;
        }
    </style>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#addpicbtn").click(function () {
                var addpicurl=$("#addpicurl").val();
                if(addpicurl==""){
                    alert("路径不能为空");
                    $("#addpicurl").focus();
                    return false;
                }
                else{
                    $("#formpicurl").submit();
                    return ture;
                }
            })
        });
    </script>
</head>
<body class="inner-container">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 picture margintop">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <c:forEach items="${picturelist.list}" var="list" varStatus="status">
                        <c:choose>
                            <c:when test="${status.index==0}">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            </c:when>
                            <c:otherwise>
                                <li data-target="#carousel-example-generic" data-slide-to="${status.index}"></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <c:forEach items="${picturelist.list}" var="list" varStatus="status">
                        <c:choose>
                            <c:when test="${status.index==0}">
                    <div class="item active">
                        <img src="${list.url}" alt="...">
                    </div>
                            </c:when>
                            <c:otherwise>
                    <div class="item">
                        <img src="${list.url}" alt="..." class="img-responsive center-block">
                    </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="col-md-9 table-responsive margintop">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td >图片id</td>
            <td >图片路径</td>
            <td >状态</td>
            <td >创建时间</td>
            <td >更新时间</td>
            <td >修改</td>
        </tr>
        <tr>
            <td></td>
            <form action="../picture/savePicture" id="formpicurl" method="post">
                <td>
                    <input type="text" name="url" id="addpicurl" placeholder="请输入图片路径" class="form-control"/>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                     <button id="addpicbtn" type="button"  class="btn btn-primary">添加</button>
                </td>
            </form>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${picturelist.list}" var="picture">
            <tr >
                <td title="${picture.pictureid}">${picture.pictureid}</td>
                <td title="${picture.url}"><img src=${picture.url} width:=50px; height=50px; /></td>
                <td title="${picture.status}">${picture.status}</td>
                <td title="${picture.createtime}">${picture.createtime}</td>
                <td title="${picture.updatetime}">${picture.updatetime}</td>
                <td>
                    <c:choose>
                    <c:when test="${picture.status=='下架'}">
                        <a href="../picture/updatepicturestatus?pictureid=${picture.pictureid}&&status=使用&&num=${picturelist.pageNum}" class="btn btn-success">使用</a>
                    </c:when>
                    <c:otherwise>
                        <a href="../picture/updatepicturestatus?pictureid=${picture.pictureid}&&status=下架&&num=${picturelist.pageNum}" class="btn btn-warning">下架</a>
                    </c:otherwise>
                </c:choose>
                    <a href="../picture/deletePicture?pictureid=${picture.pictureid}&&num=${picturelist.pageNum}"class="btn btn-danger">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${picturelist.isFirstPage}">
                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="../picture/findAll?num=${picturelist.pageNum-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${picturelist.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==picturelist.pageNum}">
                        <li class="active">
                            <span>${num}<span class="sr-only">(current)</span></span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="../picture/findAll?num=${num}">${num}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${picturelist.isLastPage}">
                    <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="../picture/findAll?num=${picturelist.pageNum+1}" aria-label="Next">
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
