package com.cn.iris.common.entity;

/**
 * Author: IrisNew
 * Description:bootstrap-treeview 节点属性类
 * Date: 2018/3/27 10:39
 */
public class State {

    private boolean checked = false;    //是否处于checked状态
    private boolean disabled = false;   //是否处于disabled状态
    private boolean expanded = false;   //是否处于展开状态
    private boolean selected = false;   //是否可以被选择

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
