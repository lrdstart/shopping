<html>
<head>
    <title>商品展示页</title>
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="/static/css/taobao.css" rel="stylesheet"/>
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="/static/bootstrap/js/bootstrap.js"></script>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        // 等整个加载完成以后为类型绑定切换事件
        $(function () {
            $("#typeCode").change(function () {
                // 把类型选中
                window.location = "/manager/itemsList?categoryId=" + this.value;
            });

            if (${categoryId}=="0")
                {
                    $("#typeCode").val("0");
                }
            else
                {
                    $("#typeCode").val("${categoryId?c}");
                }

            /*$("#modal_msg").html();
            $("#myModal").modal('show');*/

        });


        //删除单个商品
        function deleteItem(itemId) {
            //用户安全提示
            if (confirm("您确定要删除吗?"))
                $.post("/manager/deleteItem", {itemId: itemId}, function (data) {
                    alert(data.msg);
                    window.location.reload();
                });
        }
    </script>
</head>
<body>
<div id="wrapper" class="toggled">
    <#include "../common/header.ftl">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">


    <!-- 内容主体开始 -->

    <div class="alert alert-info" role="alert">
        <div>
            <form>
                <table class="table-condensed" align="center">
                    <tbody>
                    <tr>
                        <td>
                            <select class="btn btn-default" placeholder="类型" id="typeCode"
                                    name="categoryId">
                                <option value="0">==请选择类型==</option>
                                <#list categoriesList as list>
                                    <option value="${list.categoryId?c}">${list.categoryName}</option>
                                </#list>
                            </select>
                        </td>
                        <td>
                            <!-- 如果当前选择了商品的类型，仅在该类型下面进行搜索 -->
                            <#--<input type="hidden" name="typeCode" value=""/>-->
                            <div class="input-group">
                                <input type="text" name="itemName" value=""
                                       class="form-control" placeholder="搜索商品的标题"/>
                                <div class="input-group-btn">
                                    <button class="btn" type="submit">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModal" data-whatever="添加商品">添加商品
                            </button>
                            <span style="color: red;"></span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

    <!-- 展示商品数据 -->
    <div class="row">
        <#list itemsList as list>
            <div class="col-xs-6 col-lg-4">
            <span class="thumbnail">
            <a class="label label-danger" href="javascript:deleteItem(${list.itemId?c});">删除</a>
        <a
        class="label label-success" href="/manager/update?itemId=${list.itemId?c}&page=${currentPage}&categoryId=${categoryId?c}&itemName=${itemName}">
                修改</a>
        <a href="/manager/itemDetails?itemId=${list.itemId}"/>
        <img src="${list.itemImgUri}" alt="..."/>
            <p style="height: 20px; overflow: hidden;">${list.itemName}</p>
        </a>
            <p>
            <span class="price">${list.itemEdition}</span>
            <span class="discountPrice">惊爆价:${list.itemPrice}元</span>
            </p>
            </span>
            </div>
        </#list>
    </div>
    <div class="row" align="center">
        <!-- 分页标签 -->
        <nav>
            <ul class="pagination">

                <li><a href="/manager/itemsList?page=1&categoryId=${categoryId?c}&itemName=${itemName}">首页</a></li>
                <#if currentPage lte 1>
                    <li class="disabled"><a href="#"><span aria-hidden="true">&laquo;</span></a></li>
                <#else>
                    <li><a href="/manager/itemsList?page=${currentPage - 1}&categoryId=${categoryId?c}&itemName=${itemName}">
                    <span
                            aria-hidden="true">&laquo;</span></a></li>
                </#if>

                <#list 1..pageInfo.navigateLastPage as index>
                    <#if currentPage == index>
                        <li class="active"><a href="#">${index}</a></li>
                    <#else>
                        <li><a href="/manager/itemsList?page=${index}&categoryId=${categoryId?c}&itemName=${itemName}">${index}</a></li>
                    </#if>
                </#list>

                <#if currentPage gte pageInfo.navigateLastPage>
                    <li class="disabled"><a href="#"><span aria-hidden="true">&raquo;</span></a></li>
                <#else>
                    <li><a href="/manager/itemsList?page=${currentPage + 1}&categoryId=${categoryId?c}&itemName=${itemName}">
                    <span
                            aria-hidden="true">&raquo;</span></a></li>
                </#if>
                <li>
                    <a href="/manager/itemsList?page=${pageInfo.navigateLastPage}&categoryId=${categoryId?c}&itemName=${itemName}">尾页</a>
                </li>
                <li>
                    <a>总数据量${pageInfo.getTotal()}，当前<span style="color: red;">第${pageInfo.getPageNum()}页</span></a>
                </li>
            </ul>
        </nav>
    </div>
    <hr>
    <footer style="text-align: center">
        <p>&copy; 版权所有，欢迎借鉴</p>
    </footer>
</div>

<!-- 弹出框 -->
<div class="modal fade" id="exampleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">添加商品</h4>
            </div>
            <div class="modal-body ">
                <div align="center">
                    <span style="color:red;"></span>
                    <form name="articleform" class="form-horizontal" id="addItem_submit" enctype="multipart/form-data"
                          action="/manager/addItems" method="post">
                        <input type="hidden" name="categoryId" value="${categoryId}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品类型：</label>
                            <div class="col-sm-4">
                                <select class="form-control" name="itemType" id="itemType">
                                    <#list categoriesList as list>
                                        <option value="${list.categoryId}">${list.categoryName}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品名称：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" id="itemName" name="itemName" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品版本：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="itemEdition" id="itemEdition" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品价格：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="itemPrice" id="itemPrice" size="50">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品库存：</label>
                            <div class="col-sm-4">
                                <input type=number class="form-control" name="itemAmount" id="itemAmount" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品封面：</label>
                            <div class="col-sm-4">
                                <input type="file" class="form-control" name="itemImgUri" id="itemImgUri" size="40"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">商品描述：</label>
                            <div class="col-sm-6">
                                <textarea rows="5" cols="60" class="form-control" id="itemDescription"
                                          name="itemDescription"></textarea>
                            </div>
                        </div>
                        <table>
                            <tr>
                                <td><input type="submit" class="btn btn-success btn-sm" value="提交"/></td>
                                <td>&nbsp;&nbsp;<input type="button" class="btn btn-danger btn-sm"
                                                       data-dismiss="modal"
                                                       value="取消"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>