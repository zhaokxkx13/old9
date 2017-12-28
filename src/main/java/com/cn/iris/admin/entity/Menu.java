package com.cn.iris.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: IrisNew
 * Description:菜单
 * Date: 2017/12/18 13:59
 */
@TableName("sys_menu")
public class Menu extends Model<Menu>{

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private String code;
    private String pCode;
    private String pCodes;
    private String name;
    private String icon;
    private String url;
    private int levels;
    private int isMenu;
    private String others;
    private int status;
    private int isOpen;

    //======================
    private List<String> pCodesList;
    private List<Menu> listChildren;    //菜单使用：1级菜单的所有子菜单

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", pCode='" + pCode + '\'' +
                ", pCodes='" + pCodes + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", levels=" + levels +
                ", isMenu=" + isMenu +
                ", others='" + others + '\'' +
                ", status=" + status +
                ", isOpen=" + isOpen +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpCodes() {
        return pCodes;
    }

    public void setpCodes(String pCodes) {
        this.pCodes = pCodes;
        if (pCodes != null && pCodes.contains(",")){
            this.pCodesList = Arrays.asList(pCodes.split(","));
        }else{
            this.pCodesList = new ArrayList<>();
            this.pCodesList.add(pCodes);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getpCodesList() {
        return pCodesList;
    }

    public List<Menu> getListChildren() {
        return listChildren;
    }

    public void setListChildren(List<Menu> listAll) {
        this.listChildren = new ArrayList<>();
        for(Menu menu : listAll){
            if (menu.getpCode().equals(this.getCode()))
                listChildren.add(menu);
        }
    }
}
