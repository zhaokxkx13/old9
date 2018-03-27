package com.cn.iris.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cn.iris.admin.entity.Role2menu;

import java.util.List;

/**
 * Author: IrisNew
 * Description:权限服务类
 * Date: 2018/3/27 13:35
 */
public interface IRole2menuService extends IService<Role2menu>{

    List<Long> getMenuIdsByRoleId(Long roleId);

    void deleteAllMenuByRoleId(Long roleId);
}
