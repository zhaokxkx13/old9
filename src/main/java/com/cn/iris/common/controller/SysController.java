package com.cn.iris.common.controller;

import com.cn.iris.admin.entity.Menu;
import com.cn.iris.admin.entity.User;
import com.cn.iris.admin.service.IMenuService;
import com.cn.iris.admin.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: IrisNew
 * Description:系统级controller
 * Date: 2017/12/19 14:42
 */
@Controller
public class SysController {

    private Logger logger = LoggerFactory.getLogger(SysController.class);

    @Autowired
    private IUserService userServiceImpl;
    @Autowired
    private IMenuService menuServiceImpl;

    //跳转至登陆页
    @GetMapping("/welcome")
    public String hello(HttpServletRequest request, Model model) {
        request.setAttribute("ctx", request.getContextPath());
        return "login";
    }

    @GetMapping("/userlogin")
    public String index(HttpServletRequest request, String userAcc, String userPsw) {
        User user = userServiceImpl.findByAcc(userAcc);
        request.setAttribute("ctx", request.getContextPath());
        if(user.getUserPsw().equals(userPsw)){
            logger.info("用户登陆："+user.getNickName());
            request.getSession().setAttribute("iris_user",user);
            return "redirect:/index";
        }
        return "login";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("iris_user");
        //通过用户角色获取菜单权限
        List<Menu> menuList = menuServiceImpl.getMenuListByRoleId(user.getRoleIds());
        List<Menu> resultList = new ArrayList<>();
        for(Menu menu : menuList){
            if(menu.getLevels() == 1){
                menu.setListChildren(menuList);
                resultList.add(menu);
            }
        }
        model.addAttribute("userMenus",resultList);
        //System.out.println(resultList.size());
        model.addAttribute("user", user);
        return "index";
    }

    //首页
    @RequestMapping(value = "/first",method = RequestMethod.GET)
    public String first(HttpServletRequest request, Model model) {
        return "first";
    }

}
