package com.cn.iris.admin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.iris.admin.entity.Menu;
import com.cn.iris.admin.mapper.MenuMapper;
import com.cn.iris.admin.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: IrisNew
 * Description:菜单-服务实现类
 * Date: 2017/12/14 13:30
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Page<Menu> selectMenuPage(Page<Menu> page) {
        page.setRecords(menuMapper.selectMenuList(page));
        return page;
    }

    @Override
    public List<Menu> getMenuListByRoleId(List<String> roleIds) {
        List<Menu> list = menuMapper.getMenuListByRoleId(roleIds);
        return list;
    }

    @Override
    public boolean deleteAllChildren(Long pId) {
        return menuMapper.deleteAllChildren(pId);
    }
}
