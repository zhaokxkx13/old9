var nodeCheckedSilent = false;
var nodeUncheckedSilent = false;
$(document).ready(function () {
    //初始化菜单树
    initPermissionsTree();
});

function initPermissionsTree() {
    var roleId = $('#roleId').val();
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/role/getPermissionsTree?roleId="+roleId,
        success: function(ret){
            if(ret.success){
                var alternateData = ret.data;
                //树
                $('#permissionsTree').treeview({
                    expandIcon: "fa fa-plus-square",
                    collapseIcon: "fa fa-minus-square",
                    nodeIcon: "fa fa-bookmark",
                    color: "white",			        //字体颜色
                    backColor: "#1c2b36",	        //背景颜色
                    onhoverColor: "#1c2b36",        //鼠标放上时颜色
                    showBorder: false,
                    showCheckbox: true,
                    showTags: true,
                    highlightSelected: true,
                    selectedColor: "white",         //选中时字体颜色
                    selectedBackColor: "#1c2b36",   //选中时背景颜色
                    data: alternateData,
                    onNodeChecked:nodeChecked ,
                    onNodeUnchecked:nodeUnchecked
                });
            }else{
                layer.msg(ret.message, {time: 3000,icon:2},function () {});
            }
        }
    });
}

function nodeChecked (event, node){
    if(nodeCheckedSilent){
        return;
    }
    nodeCheckedSilent = true;
    checkAllParent(node);
    checkAllSon(node);
    nodeCheckedSilent = false;
}

function nodeUnchecked  (event, node){
    if(nodeUncheckedSilent)
        return;
    nodeUncheckedSilent = true;
    uncheckAllParent(node);
    uncheckAllSon(node);
    nodeUncheckedSilent = false;
}

//选中全部父节点
function checkAllParent(node){
    $('#permissionsTree').treeview('checkNode',node.nodeId,{silent:true});
    var parentNode = $('#permissionsTree').treeview('getParent',node.nodeId);
    if(!("nodeId" in parentNode)){
        return;
    }else{
        checkAllParent(parentNode);
    }
}
//取消全部父节点
function uncheckAllParent(node){
    $('#permissionsTree').treeview('uncheckNode',node.nodeId,{silent:true});
    var siblings = $('#permissionsTree').treeview('getSiblings', node.nodeId);
    var parentNode = $('#permissionsTree').treeview('getParent',node.nodeId);
    if(!("nodeId" in parentNode)) {
        return;
    }
    var isAllUnchecked = true;  //是否全部没选中
    for(var i in siblings){
        if(siblings[i].state.checked){
            isAllUnchecked=false;
            break;
        }
    }
    if(isAllUnchecked){
        uncheckAllParent(parentNode);
    }

}

//级联选中所有子节点
function checkAllSon(node){
    $('#permissionsTree').treeview('checkNode',node.nodeId,{silent:true});
    if(node.nodes!=null&&node.nodes.length>0){
        for(var i in node.nodes){
            checkAllSon(node.nodes[i]);
        }
    }
}
//级联取消所有子节点
function uncheckAllSon(node){
    $('#permissionsTree').treeview('uncheckNode',node.nodeId,{silent:true});
    if(node.nodes!=null&&node.nodes.length>0){
        for(var i in node.nodes){
            uncheckAllSon(node.nodes[i]);
        }
    }
}


//=============================================================
//保存权限配置
function savePermissions() {
    var checkedNodes = $('#permissionsTree').treeview('getChecked');
    var nodeIds = "";
    for(var i=0; i<checkedNodes.length; i++){
        nodeIds = nodeIds +","+ checkedNodes[i].id;
    }
    var menuIds = nodeIds.substring(1);
    var roleId = $('#roleId').val();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/role/savePermissions?roleId="+roleId+"&menuIds="+menuIds,
        success: function(ret){
            if(ret.success){
                layer.msg("角色权限配置成功！", {time: 1500,icon:1},function(){
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                });
            }else{
                layer.msg("角色权限配置失败："+ret.message, {time: 1500,icon:2},null);
            }
        }
    });
}

//取消
function cancel() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}