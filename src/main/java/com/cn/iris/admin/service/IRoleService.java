package com.cn.iris.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cn.iris.admin.entity.Role;

/**
 * Author: IrisNew
 * Description:角色服务类
 * Date: 2017/12/14 13:24
 */
public interface IRoleService extends IService<Role>{

    Page<Role> selectRolePage(Page<Role> page, String name);
}
