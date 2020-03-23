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
        $(function () {
            var flag=false;
            $("#addbtn").click(function () {
                if($("#mname").val()==""){
                    alert("菜名不能为空");
                    $("#mname").focus();
                    flag=false;
                    return flag;
                }else if($("#mimage").val()==""){
                    alert("图片路径不能为空");
                    $("#mimage").focus();
                    flag=false;
                    return flag;
                }
                else if($("#mprice").val()==""){
                    alert("价格不能为空");
                    $("#mprice").focus();
                    flag=false;
                    return flag;
                }
                else if($("#mkucun").val()==""){
                    alert("库存不能为空");
                    $("#mkucun").focus();
                    flag=false;
                    return flag;
                }
                else{
                    $.ajax({
                        // 编写json格式，设置属性和值
                        url:"../menu/findMenu",
                        data:{
                            menuname:$("#mname").val()
                        },
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            // data服务器端响应的json的数据，进行解析
                            if(result.length==0)
                            {$("#addform").submit();return true;}
                            else
                            {alert("已存在该菜单");return false;}
                        }
                    });
                }
            })
        })
    </script>

</head>
<body class="inner-container">
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <td >标题</td>
            <td>内容</td>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty bymenuid.menuname}" var="result">
            <form action="../menu/saveMenu" class="form-inline" id="addform">
                <tr><td>菜名：</td><td><input type="text" name="menuname" class="form-control" id="mname"/></td></tr>
                <tr><td>图片路径:</td><td><input type="text"  name="image" class="form-control" id="mimage"/></td></tr>
                <tr><td>单价:</td><td><input type="text"  name="price" class="form-control" id="mprice"/></td></tr>
                <tr><td>描述:</td><td><input type="text"  name="description" class="form-control"/></td></tr>
                <tr><td>库存:</td><td><input type="text" name="kucun" class="form-control" id="mkucun"/></td></tr>
                <tr><td></td><td><input type="button"value="添加" class="btn btn-primary" id="addbtn"/>
            </form>
            <a href="../menu/findAll" class="btn btn-warning">取消</a></td></tr>
        </c:if>
        <c:if test="${!result}">
            <form action="../menu/updateMenu" class="form-inline">
                <tr><td>菜单id:</td><td><input type="text" name="menuid" value="${bymenuid.menuid}"class="form-control" /></td></tr>
                <tr><td>菜名:</td><td><input type="text" name="menuname" value="${bymenuid.menuname}"class="form-control"/></td></tr>
                <tr><td>图片路径:</td><td><input type="text"name="image" value="${bymenuid.image}"class="form-control"/></td></tr>
                <tr><td>单价:</td><td><input type="text"  name="price" value="${bymenuid.price}"class="form-control"/></td></tr>
                <tr><td>描述:</td><td><input type="text"  name="description" value="${bymenuid.description}"class="form-control"/></td></tr>
                <tr><td>状态:</td><td><input type="text" name="status" value="${bymenuid.status}"class="form-control"/></td></tr>
                <tr><td>库存:</td><td><input type="text"name="kucun" value="${bymenuid.kucun}"class="form-control"/></td></tr>
                <tr><td></td><td><input type="submit"value="更新" class="btn btn-success"/>
            </form>
            <a href="../menu/findAll" class="btn btn-warning">取消</a></td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>

