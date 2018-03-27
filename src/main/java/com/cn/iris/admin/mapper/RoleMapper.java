package com.cn.iris.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.iris.admin.entity.Role;

import java.util.List;

/**
 * Author: IrisNew
 * Description:角色Mapper
 * Date: 2017/12/13 12:22
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRoleList(Page page, String name);
}