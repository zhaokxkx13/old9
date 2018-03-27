package com.cn.iris.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cn.iris.admin.entity.Dept;

import java.util.List;

/**
 * Author: IrisNew
 * Description:部门mapper
 * Date: 2018/1/17 10:46
 */
public interface DeptMapper extends BaseMapper<Dept> {

    List<Dept> selectDeptListForPage(Page page);

    boolean deleteAllChildren(Long pId);
}