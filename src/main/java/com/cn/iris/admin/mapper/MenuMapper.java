package com.cn.iris.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.iris.admin.entity.Menu;

import java.util.List;

/**
 * Author: IrisNew
 * Description:菜单Mapper
 * Date: 2017/12/13 12:22
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectMenuList(Page page);

    List<Menu> getMenuListByRoleId(List<String> roleIds);

    boolean deleteAllChildren(Long pId);
}