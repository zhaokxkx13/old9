package com.cn.iris.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.iris.admin.entity.Dept;
import com.cn.iris.admin.entity.Menu;
import com.cn.iris.admin.entity.Role;
import com.cn.iris.admin.entity.Role2menu;
import com.cn.iris.admin.service.IDeptService;
import com.cn.iris.admin.service.IMenuService;
import com.cn.iris.admin.service.IRole2menuService;
import com.cn.iris.admin.service.IRoleService;
import com.cn.iris.common.entity.State;
import com.cn.iris.common.util.AjaxRetBean;
import com.cn.iris.common.util.ResWriteUtil;
import com.cn.iris.common.util.TreeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


/**
 * @Author: IrisNew
 * @Description: 角色Controller
 * @Date: 2018/03/15 17:03
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleServiceImpl;
    @Autowired
    private IDeptService deptServiceImpl;
    @Autowired
    private IMenuService menuServiceImpl;
    @Autowired
    private IRole2menuService role2menuService;

    @GetMapping("/index")
    public String index() {
        return "/admin/role/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public Page<Role> listUsers(Role role) {
        Page<Role> page = roleServiceImpl.selectRolePage(new Page<>(0, 12),role.getName());
        logger.info("角色列表信息:"+page);
        return page;
    }

    @GetMapping("/add")
    public String addUser(){
        return "admin/role/addForm";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam Long id,Model model){
        if(id != null){
            Role role = roleServiceImpl.selectById(id);
            if(role != null && role.getId() != null){
                Dept roleDept = deptServiceImpl.selectById(role.getDeptId());
                role.setDeptName(roleDept.getName());
                model.addAttribute("role",role);
                return "admin/role/editForm";
            }
        }
        return "error/404";
    }

    @PostMapping("/saveRole")
    public void saveAdd(Role role,HttpServletResponse response){
        AjaxRetBean<JSONObject> returnBean =  new AjaxRetBean<> ();
        try {
            boolean suc;
            if (role.getId() != null){
                suc = roleServiceImpl.updateById(role);
            }else{
                suc = roleServiceImpl.insert(role);
            }
            if(suc){
                logger.info("角色保存成功："+role.toString());
                returnBean.setSuccess(true);
                returnBean.setMessage("角色保存成功！");
            }else{
                returnBean.setSuccess(false);
                returnBean.setMessage("角色保存失败！");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

    @PostMapping("/delRole")
    public void delete(@RequestParam Long id,HttpServletResponse response){
        AjaxRetBean<JSONObject> returnBean =  new AjaxRetBean<> ();
        try {
            Role role = roleServiceImpl.selectById(id);
            if(role != null && role.getId() != null){
                boolean flag = roleServiceImpl.deleteById(id);
                if(flag){
                    returnBean.setSuccess(true);
                }else{
                    returnBean.setSuccess(false);
                    returnBean.setMessage("删除失败！");
                }
            }else {
                returnBean.setSuccess(false);
                returnBean.setMessage("角色不存在！");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage("删除失败："+e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

    @GetMapping("/permissionsTreeInit")
    public String permissionsTree(@RequestParam Long roleId,Model model){
        if(roleId != null){
            Role role = roleServiceImpl.selectById(roleId);
            if(role != null && role.getId() != null){
                model.addAttribute("role",role);
                return "admin/role/permissionsTree";
            }
        }
        return "error/404";
    }

    @GetMapping("/getPermissionsTree")
    public void getAllMenu(@RequestParam Long roleId, HttpServletResponse response) {
        AjaxRetBean<String> returnBean =  new AjaxRetBean<> ();
        try {
            List<Menu> menuList = menuServiceImpl.selectList(null);
            List<Long> menuIds = role2menuService.getMenuIdsByRoleId(roleId);
            for (int i=0; i<menuList.size(); i++) {
                if (menuIds.contains(menuList.get(i).getId())) {
                    State state = new State();
                    state.setChecked(true);
                    if (menuList.get(i).getLevels()==1) state.setExpanded(true);
                    menuList.get(i).setState(state);
                }
            }
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

    @PostMapping("/savePermissions")
    public void savePermissions(@RequestParam Long roleId,@RequestParam String menuIds, HttpServletResponse response){
        AjaxRetBean<String> returnBean =  new AjaxRetBean<> ();
        try {
            role2menuService.deleteAllMenuByRoleId(roleId);
            String[] tempTds = menuIds.split(",");
            for (String tempTd : tempTds) {
                Role2menu role2menu = new Role2menu();
                role2menu.setRoleId(roleId);
                role2menu.setMenuId(Long.parseLong(tempTd));
                role2menuService.insert(role2menu);
            }
            returnBean.setSuccess(true);
            returnBean.setMessage("角色权限配置成功！");
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

}
