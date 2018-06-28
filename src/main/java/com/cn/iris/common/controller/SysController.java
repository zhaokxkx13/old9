package com.cn.iris.common.controller;

import com.cn.iris.admin.entity.Menu;
import com.cn.iris.admin.entity.User;
import com.cn.iris.admin.service.IMenuService;
import com.cn.iris.admin.service.IUserService;
import com.cn.iris.common.core.shiro.ShiroUtil;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String hello() {
        return "/login";
    }

    @GetMapping("/userlogin")
    public String index(HttpServletRequest request, String userAcc, String userPsw) {
        String codeMsg = (String) request.getAttribute("shiroLoginFailure");
        if ("code.error".equals(codeMsg)) {
            //model.addAttribute("message", "验证码错误");
            return "/welcome";
        }
        Subject subject = ShiroUtil.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userAcc.trim(),userPsw);
        subject.login(token);

        User user = userServiceImpl.findByAcc(userAcc);
        if(user.getUserPsw().equals(userPsw)){
            logger.info("用户登陆："+user.getNickName());
            return "redirect:/index";
        }
        return "login";
    }

    //跳转至登陆页
    @GetMapping("/logOut")
    public String logOut() {
        ShiroUtil.getSubject().logout();
        return "/login";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        User user = ShiroUtil.getUser();
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
        model.addAttribute("user", user);
        return "index";
    }

    //首页
    @RequestMapping(value = "/first",method = RequestMethod.GET)
    public String first(HttpServletRequest request, Model model) {
        return "first";
    }

}
