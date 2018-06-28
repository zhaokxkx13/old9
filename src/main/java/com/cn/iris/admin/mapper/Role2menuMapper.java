package com.cn.iris.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cn.iris.admin.entity.Role2menu;

import java.util.List;

/**
 * Author: IrisNew
 * Description:权限mapper
 * Date: 2018/3/27 11:22
 */
public interface Role2menuMapper extends BaseMapper<Role2menu> {

    /**
     * 根据单个角色查询其权限ID
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 查询多个角色的所有菜单ID
     */
    List<Long> getMenuIdsByRoleIds(List<String> roleIds);

    /**
     * 查询多个角色的所有权限编码
     */
    List<String> getMenuCodesByRoleIds(List<String> roleIds);

    void deleteAllMenuByRoleId(Long roleId);

}