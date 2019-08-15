<html>
<#include "../common/header.ftl">
<head>
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
                        <form role="form" method="post" action="/manager/categorySave">
                            <div class="form-group">
                                <label>类目名称</label>
                                <input name="categoryName" type="text" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>类目id</label>
                                <input name="categoryId" type="number" class="form-control"/>
                            </div>
                            <button type="submit" class="btn btn-default">提交</button>
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