package com.cn.iris.common.entity;

import java.util.List;

/**
 * Author: IrisNew
 * Description:树形数据实体接口
 * Date: 2018/1/16 17:15
 */
public interface TreeEntity<E> {
    public Long getId();
    public Long getParentId();
    public void setChildList(List<E> childList);
}
