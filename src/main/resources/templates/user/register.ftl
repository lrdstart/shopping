<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="order by dede58.com"/>
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css">
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script>
        function checkUsername() {
            //获取用户名的值
            var username = $("#text-username").val();
            //定义正则
            var reg_username = /^([a-z]|[A-Z]|[0-9]){6,20}$/;
            //判断，给出提示信息
            var flag = reg_username.test(username);
            if (flag) {
                $("#text-username").css("border", "");
                $("#msg-username").html("")

            } else {
                $("#text-username").css("border", "1px solid red");
                $("#msg-username").html("请输入6-20位的非汉字字符");
            }

            return flag;
        }

        function checkEmail() {
            var email = $("#text-email").val();
            var reg_email = /^\w+@\w+\.\w+$/;
            var flag = reg_email.test(email);
            if (flag) {
                $("#text-email").css("border", "");
                $("#msg-email").html("")
            } else {
                $("#text-email").css("border", "1px solid red");
                $("#msg-email").html("请输入正确的邮箱")
            }
            return flag;
        }

        function checkPassword() {
            //获取用户名的值
            var password = $("#text-password").val();
            //定义正则
            var reg_password = /^\w{6,20}$/;
            //判断，给出提示信息
            var flag = reg_password.test(password);
            if (flag) {
                $("#text-password").css("border", "");
                $("#msg-password").html("");
            } else {
                $("#text-password").css("border", "1px solid red");
                $("#msg-password").html("请输入6-20位字符")
            }
            return flag;
        }

        function checkRePassword() {
            var password = $("#text-password").val();
            var repassword = $("#text-repassword").val();
            if (password !== repassword) {
                $("#msg-RePassword").html("两次密码要输入一致哦");
                $("#text-repassword").css("border", "1px solid red");
                return false;
            } else {
                $("#msg-RePassword").html("");
                $("#text-repassword").css("border", "");
                return true;
            }
        }

        $(function () {
            //当某一个组件失去焦点，调用对应的校验方法
            $("#text-username").blur(checkUsername);
            $("#text-password").blur(checkPassword);
            $("#text-repassword").blur(checkRePassword);
            $("#text-email").blur(checkEmail);

            $("#registerForm").submit(function () {

                if (checkUsername() && checkPassword() && checkRePassword() && checkEmail()) {
                    $.ajax({
                        type: "post",
                        url: "/user/registerUser",
                        async: false,
                        data: JSON.stringify({
                            "email": $("#text-email").val(),
                            "password": $("#text-password").val(),
                            "username": $("#text-username").val(),
                            "checkCode": $("#text-checkCode").val()
                        }),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function (data) {
                            $("#result").html("");
                            if (data.result == 1) {
                                alert("注册成功跳转");
                                location.href = "/user/registerok";
                            } else {
                                $("#result").html(data.result.toString());
                            }
                        }

                    });

                }
                return false;
            });
        });


        //获取验证码
        function getVerify(obj) {
            obj.src = "/user/getVerify?" + Math.random();
        }
    </script>
</head>
<body>
<form method="post" action="/user/registeruser" id="registerForm">
    <div class="regist">
        <div class="regist_center">
            <div class="regist_top">
                <div class="left fl">会员注册</div>
                <div class="right fr"><a href="/" target="_self">米柚商城</a></div>
                <div class="clear"></div>
                <div class="xian center"></div>
            </div>
            <div class="regist_main center">
                <div class="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;
                    <input class="shurukuang" type="text"
                           name="username" id="text-username"
                           placeholder="请输入你的用户名"/><span id="msg-username"></span>
                </div>
                <div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;
                    <input class="shurukuang" type="password" name="password" id="text-password"
                           placeholder="请输入你的密码"/><span id="msg-password"></span></div>
                <div class="username">确认密码:&nbsp;&nbsp;
                    <input class="shurukuang" type="password" name="repassword" id="text-repassword"
                           placeholder="请确认你的密码"/><span id="msg-RePassword"></span></div>
                <div class="username">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:&nbsp;&nbsp;
                    <input class="shurukuang" type="email" name="email" id="text-email"
                           placeholder="请填写你的邮箱地址"/><span id="msg-email"></span></div>
                <div class="username">
                    <div class="left fl">验&nbsp;&nbsp;证&nbsp;&nbsp;码:&nbsp;&nbsp;
                        <input class="yanzhengma" type="text"
                               name="checkCode" id="text-checkCode"
                               placeholder="请输入验证码"/></div>
                    <div class="right fl"><img id="imgVerify" src="/user/getVerify"
                                               height="36" width="100%" onclick="getVerify(this)"></div>
                    <div class="clear"></div>
                </div>
                <span id="result" style="font-size: large;color: red"></span>
            </div>
            <div class="regist_submit">
                <input class="submit" type="submit" name="submit" value="立即注册">
            </div>

        </div>
    </div>
</form>
</body>
</html>