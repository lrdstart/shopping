package com.shopping.service.impl;

import com.shopping.mapper.OrdersDetailsMapper;
import com.shopping.mapper.OrdersMapper;
import com.shopping.model.Orders;
import com.shopping.model.OrdersDetails;
import com.shopping.model.OrdersDetailsExample;
import com.shopping.model.OrdersExample;
import com.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LRD
 * @create 2019-05-27 16:10
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrdersDetailsMapper ordersDetailsMapper;

    @Override
    public Orders create(Orders order) {
        return null;
    }

    @Override
    public Orders findOne(String orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<Orders> findAll(Integer page, Integer limit) {
        OrdersExample example = new OrdersExample();
        example.setOrderByClause("deliver_time");
        return ordersMapper.selectByExample(example);
    }

    @Override
    public List<OrdersDetails> findOrderDetailsByOrderId(String orderId) {
        List<OrdersDetails> ordersDetails = null;
        try {
            ordersDetails = ordersDetailsMapper.selectByOrderId(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersDetails;
    }

    @Override
    public int cancel(Orders order) {
        if(order.getPayStatus()==2 && order.getOrderStatus()==0){
            return 0;
        }else {
            order.setPayStatus(2);
            order.setOrderStatus(0);
            return ordersMapper.updateByPrimaryKey(order);
        }
    }

    @Override
    public int send(Orders order, String trackingNumber) {
        int result = 0;
        try {
            order.setTrackingNumber(trackingNumber);
            //订单状态改成已发货
            order.setOrderStatus(3);
            result = ordersMapper.updateByPrimaryKey(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int finish(Orders order) {
        int result = 0;
        try {
            //订单状态改成已完结
            order.setOrderStatus(1);
            result = ordersMapper.updateByPrimaryKey(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
