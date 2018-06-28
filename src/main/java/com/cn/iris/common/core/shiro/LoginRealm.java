package com.cn.iris.common.core.shiro;

import com.cn.iris.admin.entity.User;
import com.cn.iris.admin.service.IRole2menuService;
import com.cn.iris.admin.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: IrisNew
 * Description: shiro权限登录器
 * Date: 2018/6/8 16:14
 */
@Service
public class LoginRealm extends AuthorizingRealm {

  @Autowired
  private IUserService userServiceImpl;
  @Autowired
  private IRole2menuService role2menuService;

  // 认证.登录 提供账户信息返回认证信息
  /**
   * 当调用如下代码时会进入这个方法（一般是登录页面）
   * Subject currentUser = SecurityUtils.getSubject();
   * currentUser.login(token);
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; // 获取用户输入的token

    //输入的用户名
    String userAcc = token.getUsername();
    User user = userServiceImpl.findByAcc(userAcc);

    if (user == null) {
      throw new UnknownAccountException("账号或者密码错误！");
    }
    return new SimpleAuthenticationInfo(user, user.getUserPsw(), getName());
  }

  // 授权
  // 提供用户信息返回权限信息
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

    // 获取session中的用户

    User user = (User) principalCollection.getPrimaryPrincipal();
    List<String> roIdsList = user.getRoleIds();
    List<String> permissionsList = role2menuService.getMenuCodesByRoleIds(roIdsList);

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addRoles(roIdsList);
    if(permissionsList != null && permissionsList.size()>0){
      info.addStringPermissions(permissionsList); // 将权限放入shiro中.
    }
    return info;
  }

}
