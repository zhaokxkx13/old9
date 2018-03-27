package com.cn.iris.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cn.iris.admin.entity.Menu;

import java.util.List;

/**
 * Author: IrisNew
 * Description:菜单服务类
 * Date: 2017/12/14 13:24
 */
public interface IMenuService extends IService<Menu>{

    Page<Menu> selectMenuPage(Page<Menu> page);

    List<Menu> getMenuListByRoleId(List<String> roleIds);

    boolean deleteAllChildren(Long pId);
}
