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

        a{
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
        //删除单个类目
        function deleteItemCategory(categoryId) {
            //用户安全提示
            if (confirm("您确定要删除吗?"))
                $.post("/manager/deleteItemCategory", {categoryId: categoryId}, function (data) {
                    alert(data.msg);
                    window.location.reload();
                });
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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>类目id</th>
                            <th>名字</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list categoriesList as category >
                            <tr>
                            <td>${category.categoryId?c}</td>
                            <td>${category.categoryName}</td>
                            <td>
                        <a href="/manager/categoryUpdate?categoryId=${category.categoryId?c}">修改</a>
                        &nbsp; &nbsp;
                        <a href="javascript:deleteItemCategory(${category.categoryId?c});">删除</a>
                            </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
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