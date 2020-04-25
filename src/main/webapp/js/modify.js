$(function () {
    $("#get-captcha").click(function () {
        if($("#email").val()==""){
            alert("邮箱不能为空");
            $("#email").focus();
            return false;
        } else if($("#email").val().indexOf('@')=='-1'||$("#email").val().indexOf('.com')=='-1'){
            alert("请检查邮箱格式是否正确")
        } else{
            $.ajax({
                url:'user/checkemail',
                datatype : "json",
                type : "post",
                data:{
                    email:$("#email").val()
                },
                success:function (res) {
                    if(res==true){
                        $("#get-captcha").attr("disabled", true);
                        $("#get-captcha").val("发送中...");
                        $.ajax({
                            url:'email/send',
                            datatype : "json",
                            type : "post",
                            data:{
                                email:$("#email").val()
                            },
                            success:function (res) {
                                if(res){
                                    alert("验证码发送成功")
                                    var count = 60;
                                    var countdown = setInterval(CountDown, 1000);
                                    function CountDown() {
                                        $("#get-captcha").attr("disabled", true);
                                        $("#get-captcha").val(count + "s");
                                        if (count == 0) {
                                            $("#get-captcha").val("重新获取验证码").removeAttr("disabled");
                                            clearInterval(countdown);
                                            $.ajax({
                                                url : "user/deletecaptcha",
                                                datatype : "json",
                                                type : "post",
                                                success:function () {
                                                    alert("获取验证码失效，请重新获取")
                                                }
                                            })
                                        }
                                        count--;
                                    }
                                }
                            },error:function () {
                                alert("获取验证码失败")
                                $("#get-captcha").val("重新获取验证码").removeAttr("disabled");
                            }
                        })
                    }else{
                        alert("该邮箱不能获取验证码")
                    }

                }})
        }
    });
    $("#submit-btn").click(function () {
        $.ajax({
            url:"user/findPhone",
            data:{
                phone:$("#account").val()
            },
            dataType:"json",
            type:"post",
            success:function(result){
                if(result==true){
                    alert("没有该账号");
                    return false;
                }else{
                    $.ajax({
                        url:"user/checkcaptcha",
                        data:{
                            captcha:$("#captcha").val()
                        },
                        dataType:"json",
                        type:"post",
                        success:function (res) {
                            if(res==false){
                                alert("验证码不正确");
                                return false;
                            }else{
                                alert("修改成功")
                                $("form").submit();
                            }
                        }
                    })
                }
            }
        })
    });
    window.onbeforeunload= function (e) {
        var flag=true;
        while(flag==true){
            clean();
            flag=false;
            pushHistory();
            return flag;
        }
    };
});
function clean() {
    $.ajax({
        url:'user/deletecaptcha',
        datatype : "json",
        type : "post",
        success:function (res) {}
    })
};
function pushHistory() {
    var state = {
        title: "title",
        url: "/FD_SSM_war/"
    };
    window.history.pushState(state, "title", "/FD_SSM_war/");
}
function checkinput(id) {
    if($("#"+id+"").val()==""){
        $("#"+id+"-tips").html("");
        $("#"+id+"-tips").append("<h5 style='color:red'>不能为空！</h5>");
        $("#"+id+"-check").val("false");
    }else{
        if(id=='confirm-pwd'){
            if($("#"+id+"").val()!=$("#pwd").val()){
                $("#"+id+"-tips").html("");
                $("#"+id+"-tips").append("<h5 style='color:red'>两次密码不正确</h5>");
                $("#"+id+"-check").val("false");
            }else{
                $("#"+id+"-tips").html("");
                $("#"+id+"-check").val("true");}
        }else{
            $("#"+id+"-tips").html("");
            $("#"+id+"-check").val("true");
        }
    }
    if($("#account-check").val()=="true"&&$("#pwd-check").val()=="true"&&$("#email-check").val()=="true"&&$("#confirm-pwd-check").val()=="true"&&$("#captcha-check").val()=="true"){
        $("#submit-btn").removeAttr("disabled");
    }else{
        $("#submit-btn").attr("disabled", true);
    }
}