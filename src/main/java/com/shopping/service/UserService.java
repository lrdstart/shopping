package com.shopping.service;

import com.shopping.model.Orders;
import com.shopping.model.Users;

public interface UserService {

    Users findOne(String uname, String password);

    Users findByName(String uname);

    int registerUser(String uname, String password, String email);

    /**
     * 查詢单个订单.
     */
    Orders findOrderOne(String openid, String orderId);

    /**
     * 取消订单.
     */
    Orders cancelOrder(String openid, String orderId);

    boolean active(String username, String password);

    void getBackPassword(String username, String email);

    int changePassword(String username, String password, String newPassword);
}
