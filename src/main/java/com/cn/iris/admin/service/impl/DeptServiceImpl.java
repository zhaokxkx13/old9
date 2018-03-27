package com.cn.iris.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.iris.admin.entity.Dept;
import com.cn.iris.admin.mapper.DeptMapper;
import com.cn.iris.admin.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: IrisNew
 * Description:部门-服务实现类
 * Date: 2018/1/17 10:59
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper,Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Page<Dept> selectDeptListForPage(Page<Dept> page) {
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper.groupBy("id");
        deptMapper.selectList(wrapper);
        page.setRecords(deptMapper.selectDeptListForPage(page));
        return page;
    }

    @Override
    public List<Dept> getDeptList() {
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort");
        return deptMapper.selectList(wrapper);
    }

    @Override
    public boolean deleteAllChildren(Long pId) {
        return deptMapper.deleteAllChildren(pId);
    }

}
