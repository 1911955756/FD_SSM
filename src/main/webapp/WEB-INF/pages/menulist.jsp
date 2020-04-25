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
    <title>menu1list</title>
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
                    url : "../typemenu/savetypemenu",
                    datatype : "json",
                    data:{'typeid':$("#typeid").val(),'menuid':$("#menuid").val()},
                    type : "post",
                    success : function (data) {
                        if(data==false){
                            alert("关联成功");
                            return ture;
                        }else{
                            alert("已存在该关联");
                            return false;
                        }
                    }
                })

            });
            $("#front").click(function () {
                $("#front").attr("href","../menu/findAll?num=${listmenu.prePage}&&msta="+$("#msta").val()+"&&mname="+$("#mname").val()+"")
            });
            $("#first").click(function () {
                $("#first").attr("href","../menu/findAll?num=1&&msta="+$("#msta").val()+"&&mname="+$("#mname").val()+"")
            });
            $("#last").click(function () {
                $("#last").attr("href","../menu/findAll?num=${listmenu.pages}&&msta="+$("#msta").val()+"&&mname="+$("#mname").val()+"")
            });
            $("#next").click(function () {
                $("#next").attr("href","../menu/findAll?num=${listmenu.nextPage}&&msta="+$("#msta").val()+"&&mname="+$("#mname").val()+"")
            });
            $(".downmodel").click(function () {
                $(".downmodel").attr("href","../file/饭店菜谱模板.xlsx")
            })
        });
        function gonum(num) {
            $(".aurlcenter").attr("href","../menu/findAll?num="+num+"&&msta="+$("#msta").val()+"&&mname="+$("#mname").val()+"")
        }
    </script>

</head>
<body class="inner-container">
<div class="table-responsive">
    <table class="table-responsive">
        <tr>
            <td><a href="../type/findAll">类目管理</a></td>
            <td>类目菜单绑定：</td>
            <td><select name="typeid" id="typeid" class="form-control maxwidth">
                <c:forEach items="${listtype}" var="type">
                    <option value=${type.typeid}>${type.name}</option>
                </c:forEach>
            </select></td>
            <td>+</td>
            <td><select name="menuid" id="menuid" class="form-control maxwidth">
                <c:forEach items="${listmenu.list}" var="menu">
                    <option value=${menu.menuid}>${menu.menuname}</option>
                </c:forEach>
            </select></td>
            <td><button id="tmbtn" type="button" class="btn btn-warning">关联</button></td>
            <td><a class="btn btn-info downmodel" href="">下载模板</a></td>
            <td><input class="form-control" id="fliename" disabled/></td>
            <td><input class="btn btn-primary" type="button" onclick="$('#file_excel').click();" value="上传文件"/></td>
            <td><input class="btn btn-success" type="button" onclick="$('#btn_excel').click();" value="提交"/></td>
        </tr>
    </table>
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td>菜名</td>
            <td>单价</td>
            <td>图片路径</td>
            <td>状态</td>
            <td>创建时间</td>
            <td>更新时间</td>
            <td>描述</td>
            <td>月销</td>
            <td>库存</td>
            <td>操作</td>
        </tr>
        <tr>
            <form action="../menu/findAll">
            <td><input class="form-control" name="mname" placeholder="输入菜名搜索" value="${mname}" id="mname"></td>
            <td></td>
            <td></td>
            <td><input class="form-control" name="msta" placeholder="输入状态搜索" value="${msta}" id="msta"></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><button class="btn btn-success" type="submit">搜索</button></td>
            </form>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listmenu.list}" var="menu">
            <tr>
                <td title="${menu.menuname}">${menu.menuname}</td>
                <td title="${menu.price}">${menu.price}</td>
                <td title="${menu.image}"><img src=${menu.image} width:=50px; height=50px; /></td>
                <td title="${menu.status}">${menu.status}</td>
                <td title="${menu.createtime}">${menu.createtime}</td>
                <td title="${menu.updatetime}">${menu.updatetime}</td>
                <td title="${menu.description}">${menu.description}</td>
                <td title="${menu.sell_num}">${menu.sell_num}</td>
                <td title="${menu.kucun}">${menu.kucun}</td>
                <td><a href="../menu/toupdateMenu?menuid=1" class="btn btn-primary">添加</a><br/>
                    <a href="../menu/toupdateMenu?menuid=${menu.menuid}" class="btn btn-warning">修改</a><br/>
<%--                    <a href="../menu/deleteMenu?menuid=${menu.menuid}" class="btn btn-danger">删除</a><br/>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    第${listmenu.pageNum}页，共${listmenu.pages}页/${listmenu.total}条
    <%--    分页条--%>
    <nav aria-label="Page navigation" class="right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${listmenu.isFirstPage}">
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
            <c:forEach items="${listmenu.navigatepageNums}" var="num">
                <c:choose>
                    <c:when test="${num==listmenu.pageNum}">
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
                <c:when test="${listmenu.isLastPage}">
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
    <div id="hidden_file_div" style="display:none">
    <form id="form_excel" name="form_excel"  action="../menu/uploadMenu" method="post" enctype="multipart/form-data">
        <input type="file" id="file_excel" name="file_excel" onchange="$('#fliename').val($('#file_excel').val())" />
        <input type="submit" class="btn btn-success" id="btn_excel"/>
    </form>
    </div>
</body>
</html>
