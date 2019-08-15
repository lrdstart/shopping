<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="order by dede58.com"/>
    <title>找回密码</title>
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

        $(function () {
            //当某一个组件失去焦点，调用对应的校验方法
            $("#text-username").blur(checkUsername);
            $("#text-email").blur(checkEmail);
            $("#registerForm").submit(function () {

                if (checkUsername() && checkEmail()) {
                    $.ajax({
                        type: "post",
                        url: "/user/getBackPassword",
                        async: false,
                        data: JSON.stringify({
                            "email": $("#text-email").val(),
                            "username": $("#text-username").val()
                        }),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function (data) {
                            $("#result").html("");
                            $("#result").html(data.msg.toString());
                        }

                    });

                }
                return false;
            });
        });

    </script>
</head>
<body>
<form method="post" action="/user/registeruser" id="registerForm">
    <div class="regist">
        <div class="regist_center">
            <div class="regist_top">
                <div class="left fl">找回密码</div>
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
                <div class="username">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:&nbsp;&nbsp;
                    <input class="shurukuang" type="email" name="email" id="text-email"
                           placeholder="请填写你的邮箱地址"/><span id="msg-email"></span></div>
                <span id="result" style="font-size: large;color: red"></span>
            </div>
            <div class="regist_submit">
                <input class="submit" type="submit" name="submit" value="找回密码">
            </div>

        </div>
    </div>
</form>
</body>
</html>