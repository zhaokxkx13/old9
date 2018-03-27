package com.cn.iris.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cn.iris.admin.entity.Dept;
import com.cn.iris.admin.entity.Menu;

import java.util.List;

/**
 * Author: IrisNew
 * Description:部门-服务类
 * Date: 2018/1/17 10:58
 */
public interface IDeptService extends IService<Dept>{

    Page<Dept> selectDeptListForPage(Page<Dept> page);

    List<Dept> getDeptList();

    boolean deleteAllChildren(Long pId);

}
