package com.cn.iris.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cn.iris.admin.entity.User;

/**
 * Author: IrisNew
 * Description:用户服务类
 * Date: 2017/12/14 13:24
 */
public interface IUserService extends IService<User>{

    User findByAcc(String user_acc);

    Page<User> selectUserPage(Page<User> page, String userAcc);

    void updateUserById(User user);
}
