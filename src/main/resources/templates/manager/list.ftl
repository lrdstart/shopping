<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品首页</title>
    <!-- Bootstrap core CSS -->
    <link href="${request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="${request.contextPath}/css/taobao.css" rel="stylesheet"/>
</head>

<body>

<!-- 横幅导航条开始 -->
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand " style="color: red" href="#">电商系统,卖家系统-商品管理</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- 横幅导航条结束 -->

<!--  横幅下方的主体开始 -->
<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <!-- 侧边导航开始
           一级类型的
        -->
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <div class="list-group">
                <a href="#" class="list-group-item" active>123</a>
                <a href="#" class="list-group-item" active>123</a>
                <a href="#" class="list-group-item" active>123</a>
            </div>
        </div>
        <!--  侧边导航结束 -->
        <!-- 内容主体开始 -->
        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">显示导航条</button>
            </p>


            <div class="alert alert-info" role="alert">
                <div>
                    <form action="#" method="post">
                        <table class="table-condensed">
                            <tbody>
                            <tr>
                                <td>
                                    <select class="btn btn-default" placeholder="类型" id="secondType"
                                            name="secondType">
                                        <option value="">==请选择类型==</option>
                                        <option value="1111"></option>
                                        <option value="1111"></option>
                                        <option value="1111"></option>
                                        <option value="1111"></option>
                                    </select>
                                </td>
                                <td>
                                    <!-- 如果当前选择了商品的类型，仅在该类型下面进行搜索 -->
                                    <input type="hidden" name="typeCode" value=""/>
                                    <div class="input-group">
                                        <input type="text" name="title" value=""
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
                                    <span style="color: red;">456</span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>

            <!-- 展示商品数据 -->
            <div class="row">
                <div class="col-xs-6 col-lg-4">
            	<span class="thumbnail">
                    <a class="label label-danger" href="">删除</a>
                    <a class="label label-success" href="product/update.ftl">修改</a>
                    <a href=""/>
				      <img src="" alt="..."/>
		              <p style="height: 20px; overflow: hidden;">商品类型</p>
                    </a>
                    <p>
		              	<span class="price">价格</span>
                            <span class="discountPrice">惊爆价:</span>
		              </p>
              </span>
                </div>

            </div>
            <div class="row">
                <!-- 分页标签 -->
                <nav>
                    <ul class="pagination">
                        <li>
                            <a href="">首页</a>
                        </li>
                        <li>
                            <a href="">上一页</a>
                        </li>
                        <li>
                            <a href="">下一页</a>
                        </li>
                        <li>
                            <a href="">尾页</a>
                        </li>
                        <li>
                            <a>总数据量100，当前<span style="color: red;"></span></a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

    </div>

    <hr>

    <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
    </footer>
</div>

<!-- 弹出框 -->
<div class="modal fade" id="exampleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">添加物品</h4>
            </div>
            <div class="modal-body ">
                <div align="center">
                    <span style="color:red;"></span>
                    <form name="articleform" class="form-horizontal" action=""
                          method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">类型编号：</label>
                            <div class="col-sm-4">
                                <select class="form-control" name="code" id="addTypeCode">
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标题：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="titleStr" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">供应商：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="supplier" size="50">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">地区：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="locality" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">价格：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="price" size="50">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">库存数量：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="storage" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">上架日期：</label>
                            <div class="col-sm-4">
                                <input class="form-control" name="putawayDate" id="putawayDate"
                                       style="width: 180px;" type="text" class="Wdate"
                                       onclick=""
                                       size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">物品封面：</label>
                            <div class="col-sm-4">
                                <input type="file" class="form-control" name="image" size="40"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">书面描述：</label>
                            <div class="col-sm-6">
                                <textarea rows="5" cols="60" class="form-control" name="description"></textarea>
                            </div>
                        </div>
                        <table>
                            <tr>
                                <td><input type="submit" class="btn btn-success btn-sm" value="提交"/></td>
                                <td>&nbsp;&nbsp;<input type="button" class="btn btn-danger btn-sm" data-dismiss="modal"
                                                       value="取消"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<!--  横幅下方的主体结束 -->
<!-- 一般来讲，css必须在head里面引入，因为页面加载完成，就需要显示正确的样式 -->
<!-- js一般在页面最后面加载，等页面布局都完成以后，再来处理js文件！ -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${request.contextPath}/js/jquery-3.3.1.js"></script>
<script src="${request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    // 等整个加载完成以后为二级类型绑定切换事件
    $(function () {
    });
</script>
</body>
</html>