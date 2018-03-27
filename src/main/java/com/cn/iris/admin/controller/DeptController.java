package com.cn.iris.admin.controller;

import com.alibaba.fastjson.JSON;
import com.cn.iris.admin.entity.Dept;
import com.cn.iris.admin.service.IDeptService;
import com.cn.iris.common.util.AjaxRetBean;
import com.cn.iris.common.util.ResWriteUtil;
import com.cn.iris.common.util.TreeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Author: IrisNew
 * Description:部门-Controller
 * Date: 2018/1/17 11:16
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    private Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private IDeptService deptServiceImpl;

    @GetMapping("/index")
    public String deptIndex() {
        return "/admin/dept/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public void getDeptList(HttpServletResponse response) {
        AjaxRetBean<String> returnBean =  new AjaxRetBean<> ();
        List<Dept> deptList = deptServiceImpl.getDeptList();
        List<Dept> menusTreeList= TreeParser.getTreeList(0L,deptList);
        String result = JSON.toJSONString(menusTreeList);
        //System.out.println(result);
        returnBean.setData(result);
        returnBean.setSuccess(true);
        returnBean.setMessage("获取部门树成功！");
        ResWriteUtil.writeObject(response,returnBean);
    }

    @PostMapping("/saveDept")
    @ResponseBody
    public void saveDept(Dept dept, HttpServletResponse response){
        AjaxRetBean<Dept> returnBean =  new AjaxRetBean<> ();
        try {
            Dept parentDept = deptServiceImpl.selectById(dept.getpId());
            dept.setpIds(parentDept.getpIds()+",["+parentDept.getId()+"]");
            boolean sucFlag;
            if(null != dept.getId()){//更新
                sucFlag = deptServiceImpl.updateAllColumnById(dept);
            }else{//新增
                sucFlag = deptServiceImpl.insert(dept);
            }
            returnBean.setSuccess(sucFlag);
            returnBean.setData(dept);
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }

    @DeleteMapping("/delDept")
    @ResponseBody
    public void deleteDept(@RequestParam Long deptId, HttpServletResponse response){
        AjaxRetBean<Dept> returnBean =  new AjaxRetBean<> ();
        try {
            Dept dept = deptServiceImpl.selectById(deptId);
            if(dept != null && dept.getId() != null){
                deptServiceImpl.deleteById(dept.getId());
                deptServiceImpl.deleteAllChildren(dept.getId());
                returnBean.setSuccess(true);
            }else{
                returnBean.setSuccess(false);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }
}
