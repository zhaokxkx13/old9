package com.cn.iris.common.core.shiro;

import com.cn.iris.admin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Author: IrisNew
 * Description:shiro工具类
 * Date: 2018/6/11 14:48
 */
public class ShiroUtil {

    /**
     * 获取当前 Subject
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取登陆用户
     */
    public static User getUser(){
        return (User)getSubject().getPrincipals().getPrimaryPrincipal();
    }

    /**
     * 验证用户是否属于某角色
     */
    public boolean hasRole(String roleName) {
        return getSubject() != null && roleName != null && roleName.length() > 0 && getSubject().hasRole(roleName);
    }

    /**
     * 验证用户是否拥有指定权限
     */
    public boolean hasPermission(String permission) {
        Subject subject = getSubject();
        return getSubject() != null && permission != null
                && permission.length() > 0
                && getSubject().isPermitted(permission);
    }

    public static Session getSession(){
        return getSubject().getSession();
    }
}
