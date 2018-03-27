package com.cn.iris.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.iris.admin.entity.Role2menu;
import com.cn.iris.admin.mapper.Role2menuMapper;
import com.cn.iris.admin.service.IRole2menuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: IrisNew
 * Description:角色-服务实现类
 * Date: 2017/12/14 13:30
 */
@Service
public class Role2menuServiceImpl extends ServiceImpl<Role2menuMapper,Role2menu> implements IRole2menuService {

    @Autowired
    private Role2menuMapper role2menuMapper;

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return role2menuMapper.getMenuIdsByRoleId(roleId);
    }

    @Override
    public void deleteAllMenuByRoleId(Long roleId) {
        role2menuMapper.deleteAllMenuByRoleId(roleId);
    }
}
