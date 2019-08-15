package com.shopping.enums;

public enum OrderStatusEnum implements CodeEnum {
    FINISHED(1, "已完结"),
    CANCEL(0, "已取消"),
    DOING(2, "进行中"),
    SEND(3,"已发货"),
    TAKEOVER(4,"已收货");



    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
