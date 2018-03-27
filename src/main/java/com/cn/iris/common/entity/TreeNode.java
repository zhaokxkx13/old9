package com.cn.iris.common.entity;

import com.cn.iris.admin.entity.Menu;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: IrisNew
 * Description:Bootstrap Tree 节点生成json
 * Date: 2018/1/15 11:18
 */
public class TreeNode {

    private Long id;
    private String text;
    private List<String> tags;
    private List<TreeNode> nodes;

    public TreeNode() {
    }

    public TreeNode(Menu menu) {
        this.id = menu.getId();
        this.text = menu.getName();
        this.tags.add(String.valueOf(menu.getLevels()));
    }

    /*private SetNodesWithListMenus(List<Menu> listMenus){
        this.nodes = new ArrayList<>();
        for(Menu menu : listMenus){
            if (menu.getpCode().equals(this.getCode()))
                listChildren.add(menu);
        }
    }*/

//==============================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }
}
