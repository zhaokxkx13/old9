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

    List<Long> getMenuIdsByRoleId(Long roleId);

    void deleteAllMenuByRoleId(Long roleId);

}