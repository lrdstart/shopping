<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品首页</title>
    <!-- Bootstrap core CSS -->
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="/static/css/taobao.css" rel="stylesheet"/>
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="/static/bootstrap/js/bootstrap.js"></script>
    <script src="/static/js/taobao.js"></script>
    <script>
        $(function () {
            $("#typeCode").val("${item.categoryId}");
        });

        function resetInfo() {
            $("#typeCode").val("${item.categoryId}");
        }

    </script>

</head>

<body>

<!-- 横幅导航条开始 -->
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="">商品修改</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/manager/itemsList">返回</a></li>
            </ul>
        </div>

    </div>
</nav>
<!-- 横幅导航条结束 -->

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">
        <fieldset style="width:700px;">
            <legend>修改商品</legend>
            <span style="color: red">
                <#if msg??>
                    ${msg}
                </#if>
            </span>
            <form name="itemform" class="form-horizontal" method="post"
                  action="/manager/updateItems" enctype="multipart/form-data">
                <!-- 隐藏表单用来传输要修改的物品编号 -->
                <input type="hidden" name="itemId" value="${item.itemId}"/>
                <!-- 保留商品当前的图片地址：如果不修改图片依然会使用这个图片地址 -->
                <input type="hidden" name="itemImgUri" value="${item.itemImgUri}"/>


                <div class="form-group">
                    <label class="col-sm-3 control-label">商品类型：</label>
                    <div class="col-sm-3">
                        <select value class="form-control" name="typeCode" id="typeCode">
                            <#list categoriesList as list>
                                <option value="${list.categoryId}">${list.categoryName}</option>
                            </#list>
                        </select>
                    </div>
                    <label class="col-sm-3 control-label">商品名称：</label>
                    <div class="col-sm-3">
                        <input type=text value="${item.itemName}" class="form-control" name="itemName" size="50">
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-3 control-label">商品价格：</label>
                    <div class="col-sm-3">
                        <input type=text class="form-control" value="${item.itemPrice}" name="itemPrice" size="50">
                    </div>
                    <label class="col-sm-3 control-label">商品库存：</label>
                    <div class="col-sm-3">
                        <input type=number class="form-control" value="${item.itemAmount}" name="itemAmount" size="50">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">商品版本：</label>
                    <div class="col-sm-3">
                        <input type=text class="form-control" value="${item.itemEdition}" name="itemEdition"
                               id="putawayDate" size="50">
                    </div>
                    <label class="col-sm-3 control-label">商品封面：</label>
                    <div class="col-sm-3">
                        <input type="file" name="image" class="form-control"  size="40" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg"  style="opacity:1" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">商品描述：</label>
                    <div class="col-sm-6">
                        <textarea rows="5" cols="60" class="form-control"
                                  name="itemDescription">${item.itemDescription}</textarea>
                    </div>
                </div>
                <table align="center">
                    <tr>
                        <td><input type="submit" class="btn btn-success btn-sm" value="提交"/></td>
                        <td>&nbsp;&nbsp;<input type="reset" onclick="resetInfo()"
                                               class="btn btn-danger btn-sm" data-dismiss="modal" value="重置"/></td>
                    </tr>
                </table>
            </form>
        </fieldset>
    </div>
</div>
<hr>

</div>
</body>
</html>