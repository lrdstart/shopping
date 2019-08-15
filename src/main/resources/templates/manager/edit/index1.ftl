<html>
<#include "../common/header.ftl">
<head>
    <script src="/static/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/texiao/css/normalize.css"/><!--CSS RESET-->
    <link rel="stylesheet" type="text/css" href="/static/texiao/css/demo.css"><!--演示页面样式，使用时可以不引用-->
    <style>
        html, body {
            height: 100%;
            width: 100%;
            margin: 0;
            overflow: hidden;
        }

        a {
            color: red;
        }

        #site-landing {
            position: relative;
            height: 100%;
            width: 100%;
            background-image: linear-gradient(to top, #30cfd0 0%, #330867 100%);
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#newPassword").blur(checkPassword);
            $("#oldPassword").blur(checkOldPassword);
        });

        function checkPassword() {
            //获取密码的值
            var password = $("#newPassword").val();
            //定义正则
            var reg_password = /^\w{6,20}$/;
            //判断，给出提示信息
            var flag = reg_password.test(password);
            if (flag) {

            } else {
                alert("请输入6-20位字符");
            }
            return flag;
        }
        function checkOldPassword() {
            //获取密码的值
            var password = $("#oldPassword").val();
            //定义正则
            var reg_password = /^\w{6,20}$/;
            //判断，给出提示信息
            var flag = reg_password.test(password);
            if (flag) {

            } else {
                alert("请输入6-20位字符");
            }
            return flag;
        }

        function chnangePassword() {
            if ($("#oldPassword").val().length !== 0 && $("#newPassword").val().length !== 0 && $("#reNewPassword").val().length!==0) {

                if ($("#newPassword").val() === $("#reNewPassword").val()) {
                    window.location.href = "/manager/editPassword?username=${Session["manager"].userName}&password=" + $("#oldPassword").val()+"&newPassword="+$("#newPassword").val();
                } else {
                    alert("请保证两次密码输入一致");
                }
            }


        }
    </script>
</head>
<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <div id="site-landing">
        <#--主要内容content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" method="post">
                            <table class="table table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th><span class="title">账户名：</span></th>
                                    <th><label>原密码</label></th>
                                    <th><label>新密码</label></th>
                                    <th><label>确认密码</label></th>
                                    <th><label>操作</label></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>${Session["manager"].userName}</td>
                                    <td><input name="oldPassword" id="oldPassword" type="password"
                                               class="form-control"/></td>
                                    <td><input name="newPassword" id="newPassword" type="password"
                                               class="form-control"/></td>
                                    <td><input name="reNewPassword" id="reNewPassword" type="password"
                                               class="form-control"/></td>
                                    <td><a href="javascript:chnangePassword();"
                                           style="color: red">修改</a></td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/texiao/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/static/texiao/js/polygonizr.min.js"></script>
<script type="text/javascript">
    $('#site-landing').polygonizr();
</script>
</body>
</html>