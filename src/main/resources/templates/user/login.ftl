<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="order by dede58.com"/>
    <title>会员登录</title>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css">
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script>
        $(function () {
            $("#login_submit").submit(function () {
                $.ajax({
                    type: "post",
                    url: "/user/loginUser",
                    async: false,
                    data: JSON.stringify({
                        "password": $("#text-password").val(),
                        "username": $("#text-username").val(),
                        "checkCode": $("#text-checkCode").val()
                    }),
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    success: function (data) {
                        $("#result").html("");
                        if (data.msg == 1) {
                            location.href = "/manager/orderList";
                        } else if (data.msg == 2) {
                            location.href = "/";
                            //location.href = "http://localhost/project10/index.jsp";
                        } else {
                            // alert(data.msg.toString());
                            $("#result").html(data.msg.toString());
                        }
                    }

                });
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
<!-- login -->
<div class="top center">
    <div class="logo center">
        <a href="/" target="_blank"><img src="/static/image/mistore_logo.png" alt=""></a>
    </div>
</div>
<form method="post" action="/user/loginUser" class="form center" id="login_submit">
    <div class="login">
        <div class="login_center">
            <div class="login_top">
                <div class="left fl">会员登录</div>
                <div class="right fr">您还不是我们的会员？<a href="/user/register" target="_self">立即注册</a></div>
                <div class="clear"></div>
                <div class="xian center"></div>
            </div>
            <div class="login_main center">

                <div class="username">用户名:&nbsp;<input class="shurukuang" type="text" name="username"
                                                       id="text-username"
                                                       placeholder="请输入你的用户名"/></div>
                <div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;<input class="shurukuang" type="password"
                                                                              id="text-password"
                                                                              name="password" placeholder="请输入你的密码"/>
                </div>
                <div class="username">
                    <div class="left fl">验证码:&nbsp;<input class="yanzhengma" type="text" name="checkCode"
                                                          id="text-checkCode"
                                                          placeholder="请输入验证码"/></div>
                    <div class="right fl"><img id="imgVerify" src="/user/getVerify"
                                               height="36" width="100%" onclick="getVerify(this)">
                    </div>
                    <div class="clear">
                    </div>
                </div>
                <span id="result" style="font-size: large;color: red"></span>
            </div>
            <div class="login_submit">
                <input class="submit" type="submit" name="submit" value="立即登录"><a href="/user/forget_password"
                                                                                  target="_self" style="color: #28a4c9">忘记密码?</a>
            </div>


        </div>
    </div>
</form>
<footer>
    <div class="copyright">小米公司版权所有-京ICP备10046444-<img src="/static/image/ghs.png" alt="">京公网安备11010802020134号-京ICP证110507号
    </div>

</footer>
</body>
</html>