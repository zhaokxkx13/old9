package com.cn.iris.admin.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cn.iris.admin.entity.User;

import java.util.List;

/**
 * Author: IrisNew
 * Description:用户Mapper
 * Date: 2017/12/13 12:22
 */
public interface UserMapper extends BaseMapper<User> {

    User findByAcc(String user_acc);

    List<User> selectUserList(Page page, String userAcc);

    void updateUserById(User user);

}