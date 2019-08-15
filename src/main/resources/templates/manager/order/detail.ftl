<html>
<#include "../common/header.ftl">
<head>
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script>
        //发货时判断是否输入了运单号
        function sendOrder() {
            if ($("#number").val().length != 0) {
                location.href = "/manager/sendOrder?orderId=${order.orderId}&trackingNumber=" + $("#number").val();
            } else {
                alert("请输入运单号");
            }
        }


    </script>
</head>
<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-5 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                            <th>运单号</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.orderAmount?string("currency")}</td>
                            <#if order.getOrderStatusEnum().message()=="进行中" && order.getPayStatusEnum().message()=="支付成功">
                                <td><input type="text" name="number" id="number"/></td>
                            <#elseif order.getOrderStatusEnum().message()=="已取消" && order.getPayStatusEnum().message()=="未支付">
                                <td></td>
                            <#elseif order.getOrderStatusEnum().message()=="进行中" && order.getPayStatusEnum().message()=="等待支付">
                                <td></td>
                            <#elseif order.getOrderStatusEnum().message()=="已发货" && order.getPayStatusEnum().message()=="支付成功">
                                <td>${order.trackingNumber}</td>
                            </#if>
                            <#if order.getOrderStatusEnum().message()=="已完结" && order.getPayStatusEnum().message()=="支付成功">
                                <td>${order.trackingNumber}</td>
                            </#if>
                            <#if order.getOrderStatusEnum().message()=="已收货" && order.getPayStatusEnum().message()=="支付成功">
                                <td>${order.trackingNumber}</td>
                            </#if>

                        </tr>
                        </tbody>
                    </table>
                </div>

                <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list details as list>
                            <tr>
                            <td>${list.itemId?c}</td>
                            <td>${list.itemName}</td>
                            <td>${list.itemPrice?string("currency")}</td>
                            <td>${list.itemCount}</td>
                            <td>${(list.itemPrice * list.itemCount)?string("currency")}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--操作-->
                <div class="col-md-12 column">
                    <#if order.getOrderStatusEnum().message()=="进行中" && order.getPayStatusEnum().message()=="支付成功">
                        <a href="javascript:sendOrder();" type="button" class="btn btn-default btn-primary">发&nbsp;&nbsp;货</a>
                    </#if>
                    <#if order.getOrderStatusEnum().message()=="已收货" && order.getPayStatusEnum().message()=="支付成功">
                        <a href="javascript:finishOrder();" type="button" class="btn btn-default btn-primary">完结订单</a>
                    </#if>
                </div>


            </div>
        </div>
    </div>
</div>
<script>
    function finishOrder() {
        if(confirm("是否完结此订单?")){
            location.href = "/manager/finishOrder?orderId=${order.orderId}";
        }

    }
</script>
</body>
</html>