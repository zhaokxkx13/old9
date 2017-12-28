package com.cn.iris.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.iris.admin.entity.User;
import com.cn.iris.admin.service.IUserService;
import com.cn.iris.common.util.AjaxRetBean;
import com.cn.iris.common.util.ResWriteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        return "admin/user/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public Page<User> listUsers(HttpServletRequest request) {
        Page<User> page = userServiceImpl.selectUserPage(new Page<User>(0, 12));
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required=false) Long id,Model model){
        if(id != null){
            User user = userServiceImpl.selectById(id);
            model.addAttribute("user",user);
        }
        return "admin/user/form";
    }

    @PostMapping("/save")
    public void save(User user,HttpServletResponse response){
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

}
