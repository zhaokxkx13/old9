package com.cn.iris.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.cn.iris.common.entity.TreeEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: IrisNew
 * Description:部门
 * Date: 2018/1/16 15:27
 */
@TableName("sys_dept")
public class Dept extends Model<Dept> implements TreeEntity<Dept> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private int sort;
    private Long pId;
    private String pIds;
    private String name;
    private String fullName;
    private String tips;
    private String temp;

    //=======菜单树用===========
    @TableField(exist=false)
    private List<Dept> nodes;
    @TableField(exist=false)
    private String text;
    @TableField(exist=false)
    private List<String> tags;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Long getParentId() {
        return this.pId;
    }

    @Override
    public void setChildList(List<Dept> childList) {
        this.nodes = childList;
    }

    //=========各get&set方法

    public void setId(Long id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
        this.tags = new ArrayList<>();
        tags.add(String.valueOf(sort));
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getpIds() {
        return pIds;
    }

    public void setpIds(String pIds) {
        this.pIds = pIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.text = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public List<Dept> getNodes() {
        return nodes;
    }

    public void setNodes(List<Dept> nodes) {
        this.nodes = nodes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
