package com.cn.iris.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.iris.admin.entity.Dept;
import com.cn.iris.admin.entity.User;
import com.cn.iris.admin.service.IDeptService;
import com.cn.iris.admin.service.IUserService;
import com.cn.iris.common.util.AjaxRetBean;
import com.cn.iris.common.util.ResWriteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * @Author: IrisNew
 * @Description: 用户Controller
 * @Date: 2017/12/7 17:03
 */
@Controller
@RequestMapping("/user")
public class UserLoginController {

    private Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private IUserService userServiceImpl;
    @Autowired
    private IDeptService deptServiceImpl;

    @GetMapping("/index")
    public String index() {
        return "/admin/user/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public Page<User> listUsers(User user) {
        Page<User> page = userServiceImpl.selectUserPage(new Page<>(0, 12),user.getUserAcc());
        return page;
    }

    @GetMapping("/add")
    public String addUser(){
        return "admin/user/addForm";
    }

    @GetMapping("/show")
    public String showUser(@RequestParam Long id,Model model){
        if(id != null){
            User user = userServiceImpl.selectById(id);
            if(user != null && user.getId() != null){
                Dept userDept = deptServiceImpl.selectById(user.getDeptId());
                user.setDeptName(userDept.getName());
                model.addAttribute("user",user);
                return "admin/user/showForm";
            }
        }
        return "error/404";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam Long id,Model model){
        if(id != null){
            User user = userServiceImpl.selectById(id);
            if(user != null && user.getId() != null){
                Dept userDept = deptServiceImpl.selectById(user.getDeptId());
                user.setDeptName(userDept.getName());
                model.addAttribute("user",user);
                return "admin/user/editForm";
            }
        }
        return "error/404";
    }

    @PostMapping("/saveAdd")
    public void saveAdd(User user,HttpServletResponse response){
        AjaxRetBean<JSONObject> returnBean =  new AjaxRetBean<> ();
        user.setCreateTime(new Date());
        user.setStatus(1);
        user.setRoleId("1");
        try {
            User tempUser = userServiceImpl.findByAcc(user.getUserAcc());
            if(tempUser != null  && tempUser.getId()!=null){
                throw new Exception("用户名已存在！");
            }
            boolean suc = userServiceImpl.insert(user);
            if(suc){
                logger.info("新增用户成功："+user.toString());
                returnBean.setSuccess(true);
                returnBean.setMessage("新增用户成功！");
            }else{
                returnBean.setSuccess(false);
                returnBean.setMessage("新增用户失败！");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

    @PostMapping("/saveEdit")
    public void saveEdit(User user,HttpServletResponse response){
        AjaxRetBean<JSONObject> returnBean =  new AjaxRetBean<> ();
        user.setCreateTime(new Date());
        try {
            if(user.getUserPsw().equals(user.getUserPsw2())){
                userServiceImpl.updateUserById(user);
                returnBean.setSuccess(true);
            }else{
                returnBean.setSuccess(false);
                returnBean.setMessage("密码不一致！");
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage("修改失败："+e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

    @PostMapping("/delUser")
    public void delete(@RequestParam Long id,HttpServletResponse response){
        AjaxRetBean<JSONObject> returnBean =  new AjaxRetBean<> ();
        try {
            User user = userServiceImpl.selectById(id);
            if(user != null && user.getId() != null){
                boolean flag = userServiceImpl.deleteById(id);
                if(flag){
                    returnBean.setSuccess(true);
                }else{
                    returnBean.setSuccess(false);
                    returnBean.setMessage("删除失败！");
                }
            }else {
                returnBean.setSuccess(false);
                returnBean.setMessage("用户不存在！");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage("删除失败："+e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

}
