package com.cn.iris.admin.entity;

import lombok.Data;

@Data
public class Item {
    Long id;
    Long itemId;
    String itemName;
    String itemPic;
    Double itemPrice;
}
