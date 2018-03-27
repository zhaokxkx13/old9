package com.cn.iris.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.iris.admin.entity.Menu;
import com.cn.iris.admin.entity.User;
import com.cn.iris.admin.service.IMenuService;
import com.cn.iris.common.entity.TreeNode;
import com.cn.iris.common.util.AjaxRetBean;
import com.cn.iris.common.util.ResWriteUtil;
import com.cn.iris.common.util.TreeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * Author: IrisNew
 * Description:菜单Controller
 * Date: 2018/1/15 10:27
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuServiceImpl;

    @GetMapping("/index")
    public String menuIndex() {
        return "/admin/menu/list";
    }

    @GetMapping("/listByRole")
    @ResponseBody
    public void getMenuListByUser(HttpServletRequest request, HttpServletResponse response) {
        AjaxRetBean<String> returnBean =  new AjaxRetBean<> ();
        User user = (User) request.getSession().getAttribute("iris_user");
        List<Menu> menuList = menuServiceImpl.getMenuListByRoleId(user.getRoleIds());
        List<Menu> menusTreeList= TreeParser.getTreeList(0L,menuList);
        String result = JSON.toJSONString(menusTreeList);
        returnBean.setData(result);
        returnBean.setSuccess(true);
        returnBean.setMessage("获取菜单树成功！");
        logger.info("根据角色获取菜单树："+returnBean);
        ResWriteUtil.writeObject(response,returnBean);
    }

    @GetMapping("/list")
    @ResponseBody
    public void getAllMenu( HttpServletResponse response) {
        AjaxRetBean<String> returnBean =  new AjaxRetBean<> ();
        try {
            List<Menu> menuList = menuServiceImpl.selectList(null);
            List<Menu> menusTreeList= TreeParser.getTreeList(0L,menuList);
            String result = JSON.toJSONString(menusTreeList);
            returnBean.setData(result);
            returnBean.setSuccess(true);
            returnBean.setMessage("获取菜单树成功！");
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

    @PostMapping("/saveMenu")
    @ResponseBody
    public void saveDept(Menu menu, HttpServletResponse response){
        AjaxRetBean<Menu> returnBean =  new AjaxRetBean<> ();
        try {
            Menu parentMenu = menuServiceImpl.selectById(menu.getpId());
            menu.setpIds(parentMenu.getpIds()+",["+parentMenu.getId()+"]");
            menu.setLevels(parentMenu.getLevels()+1);
            boolean sucFlag;
            if(null != menu.getId()){//更新
                sucFlag = menuServiceImpl.updateAllColumnById(menu);
            }else{//新增
                sucFlag = menuServiceImpl.insert(menu);
            }
            returnBean.setSuccess(sucFlag);
            returnBean.setData(menu);
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

    @DeleteMapping("/delMenu")
    @ResponseBody
    public void deleteDept(@RequestParam Long menuId, HttpServletResponse response){
        AjaxRetBean<Menu> returnBean =  new AjaxRetBean<> ();
        try {
            Menu menu = menuServiceImpl.selectById(menuId);
            if(menu != null && menu.getId() != null){
                menuServiceImpl.deleteById(menu.getId());
                menuServiceImpl.deleteAllChildren(menu.getId());
                returnBean.setSuccess(true);
            }else{
                returnBean.setSuccess(false);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

}
