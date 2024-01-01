
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Nidaye">
    <title>Chifan · Register</title>

    <!--<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/sign-in/">-->

    <!-- Bootstrap core CSS -->
<#--    <link href="/sell/bootstrap/bootstrap.min.css" rel="stylesheet">-->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="/sell/jquery/jquery-slim.min.js?v=2"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="/sell/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<#--action="Register"-->
<form class="form-signin" id="RegisterForm" >
    <img class="mb-4" src="/sell/images/favicon.ico" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>
    <div id="tipAlert" class="alert d-none" role="alert" >...</div>
    <label for="inputEmail" class="sr-only">UserName</label>
    <input type="text" id="inputEmail" name="userName" class="form-control" placeholder="UserName" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
    <label for="inputPasswordAg" class="sr-only">Password</label>
    <input type="password" id="inputPasswordAg" name="userPwdAg" class="form-control" placeholder="input password again" required>
    <div style="display: flex;">
        <div style="width: 50%; height: auto">
            <input type="text" id="inputVerifyCode" name="userVerifyCode" class="form-control" placeholder="verify code" required >
        </div>
        <div style="width: 50%; height: auto">
            <img id="imgVerifyCode" src="/sell/verify_code" alt="" />
        </div>

    </div>
    <div class="checkbox mb-3">
        <label>
            <input id="license" type="checkbox" value="remember-me"> Agree to relevant agreements
        </label>
    </div>
    <button id="btnSubmit"  class="btn btn-lg btn-primary btn-block" >Sign up</button>
    <div class="mt-3 text-muted"><span id="version" class="text-muted"></span> &copy; 2017-2019</div>

</form>

<!-- Modal -->
<div id="exampleModalCenter" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">
                    提示
                </h3>
            </div>
            <div class="modal-body">
                <p>
                    注册成功!
                </p>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                <a href="/sell/login" type="button" class="btn btn-primary">去登录</a>
            </div>
        </div>
    </div>
</div>

<#--<script src="main.js?v=11"></script>-->
<script>
    let basepath = "/";
    function reloadVerifyCode(){
        $("#imgVerifyCode").attr("src", "/sell/verify_code?ts=" + new Date().getTime());
    }
    $("#imgVerifyCode").click(function () {
        reloadVerifyCode();
    });
    function showTips(bShow, css, text){
        if(bShow)
        {
            $("#tipAlert").removeClass("d-none");
            $("#tipAlert").hide();
            $("#tipAlert").addClass(css);
            $("#tipAlert").text(text);
            $("#tipAlert").fadeIn(200);
        }else{
            $("#tipAlert").text("");
            $("#tipAlert").fadeOut(200);
            $("#tipAlert").removeClass();
            $("#tipAlert").addClass("alert")
        }
    }
    $("#btnSubmit").click( function (){
        var username = $.trim($("#inputEmail").val());
        var regex = /^.{6,10}$/;
        if (!regex.test(username)) {
            showTips(true, "alert-danger", "用户名请输入正确格式(6-10位)");
            return false;
        } else {
            showTips(false);
            console.log("输入的用户名：" + username);
        }
        var password = $.trim($("#inputPassword").val());

        if (!regex.test(password)) {
            showTips(true, "alert-danger", "密码请输入正确格式（6-10位）");
            return false;
        } else {
            showTips(false);
        }
        var passwordAg = $.trim($("#inputPasswordAg").val());
        if (password === passwordAg) {
            showTips(false);
        } else {
            showTips(true, "alert-danger", "两次输入的密码不一致");
            return false;
        }
        var isChecked = $("#license").is(':checked');
        if(!isChecked){
            showTips(true, "alert-danger", "请同意许可协议才能进行下一步");
            return false;
        }
        $btnReg = $(this);

        $btnReg.text("正在处理...");
        $btnReg.attr("disabled", "disabled");

        //发送ajax请求
        $.ajax({
            url: "/sell/registe",
            type: "post",
            dataType: "json",
            data: $("#RegisterForm").serialize(),
            success: function (data) {
                //结果处理,根据服务器返回code判断服务器处理状态
                //服务器要求返回JSON格式:
                //{"code":"0","msg":"处理消息"}
                console.info("服务器响应:", data);
                if (data.code == "0") {
                    //显示注册成功对话框
                    $("#exampleModalCenter").modal({});
                    $("#exampleModalCenter").modal("show");

                } else {
                    //服务器校验异常,提示错误信息
                    showTips(true, "alert-danger", data.msg);
                    reloadVerifyCode();
                }
                $btnReg.text("注    册");
                $btnReg.removeAttr("disabled");
            }
        });
        return false;
    });
    // $("#RegisterForm").attr("action", basepath + "Register");
    // $.get(basepath + "GetVersionInfo",
    //     function(e) {
    //         $('#version').html(e.Data);
    //     });

</script>
</body>
</html>