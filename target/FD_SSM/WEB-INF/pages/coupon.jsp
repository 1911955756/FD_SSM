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
    <title>coupon</title>
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
                if($("#couname").val()==""){
                    alert("优惠券名不能为空");
                    $("#couname").focus();
                    flag=false;
                    return flag;
                }else if($("#coutype").val()==""){
                    alert("类型不能为空");
                    $("#coutype").focus();
                    flag=false;
                    return flag;
                }
                else if($("#couprice").val()==""){
                    alert("价格不能为空");
                    $("#couprice").focus();
                    flag=false;
                    return flag;
                }
                else if($("#couend").val()==""){
                    alert("结束时间不能为空");
                    $("#couend").focus();
                    flag=false;
                    return flag;
                }
                else{
                    $.ajax({
                        // 编写json格式，设置属性和值
                        url:"../coupon/findCoupon",
                        data:{
                            couponname:$("#couname").val()
                        },
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            // data服务器端响应的json的数据，进行解析
                            if(result.length==0)
                            {$("#addform").submit();return true;}
                            else
                            {alert("已存在该优惠券");return false;}
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
        <c:if test="${empty bycouponid.couponname}" var="result">
            <form action="../coupon/saveCoupon" class="form-inline" id="addform">
                <tr><td>优惠券：</td><td><input type="text" name="couponname" class="form-control" id="couname"/></td></tr>
                <tr><td>类型:</td><td><input type="text"  name="type" class="form-control" id="coutype"/></td></tr>
                <tr><td>优惠价格:</td><td><input type="text"  name="price" class="form-control" id="couprice"/></td></tr>
                <tr><td>结束时间:</td><td><input type="text"  name="endtime" placeholder="输入格式：年-月-日 时：分：秒" class="form-control" id="couend"/></td></tr>
                <tr><td></td><td><input type="button"value="添加" class="btn btn-primary" id="addbtn"/>
            </form>
            <a href="../coupon/findAll" class="btn btn-warning">取消</a></td></tr>
        </c:if>
        <c:if test="${!result}">
            <form action="../coupon/updateCoupon" class="form-inline">
                <tr><td>菜单id:</td><td><input type="text" name="couponid" value="${bycouponid.couponid}" class="form-control" /></td></tr>
                <tr><td>优惠券：</td><td><input type="text" name="couponname" class="form-control" value="${bycouponid.couponname}"/></td></tr>
                <tr><td>类型:</td><td><input type="text"  name="type" class="form-control" value="${bycouponid.type}"/></td></tr>
                <tr><td>状态:</td><td><input type="text"  name="status" class="form-control" value="${bycouponid.status}"/></td></tr>
                <tr><td>优惠价格:</td><td><input type="text"  name="price" class="form-control" value="${bycouponid.price}" /></td></tr>
                <tr><td>结束时间:</td><td><input type="text"  name="endtime"placeholder="输入格式：年-月-日 时：分：秒" class="form-control" value="${bycouponid.endtime}"/></td></tr>
                <tr><td></td><td><input type="submit"value="更新" class="btn btn-success"/>
            </form>
            <a href="../coupon/findAll" class="btn btn-warning">取消</a></td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>

