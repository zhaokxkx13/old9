package com.cn.iris.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.cn.iris.common.entity.State;
import com.cn.iris.common.entity.TreeEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: IrisNew
 * Description:菜单
 * Date: 2017/12/18 13:59
 */
@TableName("sys_menu")
public class Menu extends Model<Menu> implements TreeEntity<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String code;
    private Long pId;
    private String pIds;
    private String name;
    private String icon;
    private String url;
    private int levels;
    private int isMenu;
    private String others;
    private int status;
    private int isOpen;

    //=======首页菜单用=========
    @TableField(exist=false)
    private List<Menu> listChildren;    //菜单使用：1级菜单的所有子菜单
    //=======菜单树用===========
    @TableField(exist=false)
    private List<Menu> nodes;
    @TableField(exist=false)
    private String text;
    @TableField(exist=false)
    private List<String> tags;
    @TableField(exist=false)
    private State state;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return this.pId;
    }

    public void setChildList(List<Menu> childList) {
        this.nodes=childList;
    }


    //==============各get&set方法============

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
        this.tags = new ArrayList<>();
        this.tags.add(String.valueOf(levels));
    }

    public int getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(int isMenu) {
        this.isMenu = isMenu;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Menu> getListChildren() {
        return listChildren;
    }

    public void setListChildren(List<Menu> listAll) {
        this.listChildren = new ArrayList<>();
        for(Menu menu : listAll){
            if (menu.getpId().equals(this.getId()))
                listChildren.add(menu);
        }
    }

    public List<Menu> getNodes() {
        return nodes;
    }

    public void setNodes(List<Menu> childListTree) {
        this.nodes = childListTree;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
