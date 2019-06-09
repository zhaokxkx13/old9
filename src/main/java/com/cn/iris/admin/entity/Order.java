package com.cn.iris.admin.entity;

import lombok.Data;

@Data
public class Order {
    Long id;
    Long OrderId;
    Long orderTime;
    Long userId;
    Long customerId;
    Double price;
}
