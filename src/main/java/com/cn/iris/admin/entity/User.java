package com.cn.iris.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Author: IrisNew
 * Description:用户实体类
 * Date: 2017/12/8 17:46
 */
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userAcc;
    private String userPsw;
    private String nickName;
    private String avatar;
    private Date createTime;
    private String description;
    private String salt;
    private Integer status;
    private String roleId;
    private Long deptId;

    //=============================
    @TableField(exist=false)
    private List<String> roleIds;
    @TableField(exist=false)
    private String deptName;
    @TableField(exist=false)
    private String userPsw2;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userAcc='" + userAcc + '\'' +
                //", userPsw='" + userPsw + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
               // ", salt='" + salt + '\'' +
                ", status=" + status +
                //", roleId=" + roleId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
        if (roleId != null && roleId.contains(",")){
            this.roleIds = Arrays.asList(roleId.split(","));
        }else {
            this.roleIds = new ArrayList<>();
            this.roleIds.add(roleId);
        }
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserPsw2() {
        return userPsw2;
    }

    public void setUserPsw2(String userPsw2) {
        this.userPsw2 = userPsw2;
    }
}
