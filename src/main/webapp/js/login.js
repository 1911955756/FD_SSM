//页面加载，绑定单击事件
$(function () {
    var flag=false;
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $("#btn1").trigger("click");
        }
    });
    $("#btn1").click(function () {
        if($("#phone").val()==""){
            alert("账号不能为空");
            $("#phone").focus();
            flag=false;
            return flag;
        }
        else if ($("#password").val() == "") {
            alert("密码不能为空");
            $("#password").focus();
            flag=false;
            return flag;
        }
        //发送ajax请求
        $.ajax({
            // 编写json格式，设置属性和值
            url:"user/findByPhone",
            contentType:"application/json;charset=UTF-8",
            data:"{\"phone\":\""+$("#phone").val()+"\",\"password\":\""+$("#password").val()+"\"}",
            dataType:"json",
            type:"post",
            success:function(result){
                // data服务器端响应的json的数据，进行解析
                if(result==true)
                {$.ajax({
                    url:'user/checkapplication',
                    datatype : "json",
                    type : "post",
                    data:{
                        phone:$("#phone").val()
                    },
                    success:function(res){
                        if(res==false){
                            $("#form1").submit();return true;
                        }else{
                            alert("该账号已登录");return false;
                        }
                    }
                })
                } else if(result==flag)
                {alert("账号或密码不正确");return false;}
            }
        });
    });
    $(".btn-link").click(function () {
        window.open("modify.jsp")
    })
});