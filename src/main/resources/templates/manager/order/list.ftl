<html>
<#include "../common/header.ftl">
<head>
    <script src="/static/bootstrap/js/bootstrap.js"></script>
    <script src="/static/bootstrap/css/bootstrap.css"></script>
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
                                <th>订单编号</th>
                                <th>用户名</th>
                                <th>金额</th>
                                <th>地址</th>
                                <th>手机号</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list ordersList as order>
                                <tr>
                                <td>${order.orderId}</td>
                                <td>${order.userName}</td>
                                <td>${order.orderAmount}元</td>
                                <td>${order.address}</td>
                                <td>${order.phone}</td>
                                <td>${order.getOrderStatusEnum().message()}</td>
                                <td>${order.getPayStatusEnum().message()}</td>


                                <td>${order.deliverTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td><a href="/manager/orderDetails?orderId=${order.orderId}">详情</a></td>
                                <td>
                                <#if order.getOrderStatusEnum().message()=="进行中" && order.getPayStatusEnum().message()=="等待支付">

                                    <a href="/manager/cancelOrder?orderId=${order.orderId}">取消</a>
                                </#if>
                                </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>

                    <#--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                            <li><a href="/manager/orderList?page=1">首页</a>
                            </li>
                            <#if currentPage lte 1>
                                <li class="disabled"><a href="#"><span aria-hidden="true">&laquo;</span></a></li>
                            <#else>
                                <li><a href="/manager/orderList?page=${currentPage - 1}">
                                <span
                                        aria-hidden="true">&laquo;</span></a></li>
                            </#if>

                            <#list 1..pageInfo.navigateLastPage as index>
                                <#if currentPage == index>
                                    <li class="active"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/manager/orderList?page=${index}">${index}</a></li>
                                </#if>
                            </#list>

                            <#if currentPage gte pageInfo.navigateLastPage>
                                <li class="disabled"><a href="#"><span aria-hidden="true">&raquo;</span></a></li>
                            <#else>
                                <li><a href="/manager/orderList?page=${currentPage - 1}">
                                <span
                                        aria-hidden="true">&raquo;</span></a></li>
                            </#if>
                            <li>
                                <a href="/manager/orderList?page=${pageInfo.navigateLastPage}">尾页</a>
                            </li>
                            <li>
                                <a>总数据量${pageInfo.getTotal()}，当前<span
                                            style="color: red;">第${pageInfo.getPageNum()}页</span></a>
                            </li>
                        </ul>
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