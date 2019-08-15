<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品首页</title>
    <!-- Bootstrap core CSS -->
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="/static/css/taobao.css" rel="stylesheet"/>

</head>

<body>

<!-- 横幅导航条开始 -->
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="">商品详情查看</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:history.go(-1)">返回</a></li>
            </ul>
        </div>

    </div>
</nav>
<!-- 横幅导航条结束 -->

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <!-- 内容主体开始 -->
        <div class="col-xs-12 col-sm-12">
            <div class="col-xs-12 col-sm-6">
                <img alt="商品的图片"  width="280" height="280" src="${items.itemImgUri}">
            </div>
            <div class="col-xs-12 col-sm-6">
                <p>商品id：${items.itemId?c}</p>
                <p>商品名称：${items.itemName}</p>
                <p><span>商品版本：${items.itemEdition}</span></p>
                <p><span style="color: red">折扣价：${items.itemPrice?string("currency")}</span></p>
                <p>
                    库存量：<span id="storage">${items.itemAmount}</span>
                </p>
                <p>
                    商品类别：
                    ${cateName}
                </p>

            </div>
            <div class="col-xs-12">
                <fieldset>
                    <legend>商品详情</legend>
                    ${items.itemDescription}
                </fieldset>
            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
    </footer>
</div>
<script src="/static/js/jquery-3.3.1.js"></script>
<script src="/static/bootstrap/js/bootstrap.js"></script>
</body>
</html>