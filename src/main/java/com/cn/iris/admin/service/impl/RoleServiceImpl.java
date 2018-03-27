package com.cn.iris.admin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.iris.admin.entity.Role;
import com.cn.iris.admin.mapper.RoleMapper;
import com.cn.iris.admin.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: IrisNew
 * Description:角色-服务实现类
 * Date: 2017/12/14 13:30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page<Role> selectRolePage(Page<Role> page, String name) {
        page.setRecords(roleMapper.selectRoleList(page,name));
        return page;
    }
}
