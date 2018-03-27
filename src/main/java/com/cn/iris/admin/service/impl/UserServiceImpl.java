package com.cn.iris.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.iris.admin.entity.User;
import com.cn.iris.admin.mapper.UserMapper;
import com.cn.iris.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: IrisNew
 * Description:用户-服务实现类
 * Date: 2017/12/14 13:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByAcc(String user_acc) {
        return userMapper.findByAcc(user_acc);
    }

    @Override
    public Page<User> selectUserPage(Page<User> page, String userAcc) {
        page.setRecords(userMapper.selectUserList(page,userAcc));
        return page;
    }

    @Override
    public void updateUserById(User user) {
        userMapper.updateUserById(user);
    }
}
