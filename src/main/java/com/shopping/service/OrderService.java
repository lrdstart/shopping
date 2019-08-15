package com.shopping.service;

import com.shopping.model.Orders;
import com.shopping.model.OrdersDetails;

import java.util.List;

/**
 * @author LRD
 * @create 2019-05-26 20:20
 */
public interface OrderService {

    /**
     * 创建订单.
     */
    Orders create(Orders order);

    /**
     * 查询单个订单.
     * @param orderId
     */
    Orders findOne(String orderId);

    /**
     * 查询订单列表.
     */
    List<Orders> findAll(Integer page, Integer limit);

    /**
     * 根据订单id查询订单详情列表.
     * @param orderId
     */
    List<OrdersDetails> findOrderDetailsByOrderId(String orderId);

    /**
     * 取消订单.
     */
    int cancel(Orders order);

    /**
     * 发货
     */
    int send(Orders order, String trackingNumber);

    /**
     * 完结
     */
    int finish(Orders order);



}
