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

    /**
     * 根据单个角色查询其权限菜单
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 查询多个角色的所有权限菜单ID
     */
    List<Long> getMenuIdsByRoleIds(List<String> roleIds);

    /**
     * 查询多个角色的所有权限编码
     */
    List<String> getMenuCodesByRoleIds(List<String> roleIds);

    void deleteAllMenuByRoleId(Long roleId);
}
