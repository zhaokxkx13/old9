var selectedNode;
var insertOrUpdate; //新增，更新
$(document).ready(function () {
    //初始化菜单树
    initMenuTree();
    initParentMenuTree();

});

function initMenuTree() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/menu/list",
        success: function(ret){
            if(ret.success){
                var alternateData = ret.data;
                //树
                $('#menuTree').treeview({
                    expandIcon: "fa fa-plus-square",
                    collapseIcon: "fa fa-minus-square",
                    nodeIcon: "fa fa-bookmark",
                    color: "white",			        //字体颜色
                    backColor: "#1c2b36",	        //背景颜色
                    onhoverColor: "#696969",        //鼠标放上时颜色
                    showBorder: false,
                    showTags: true,
                    highlightSelected: true,
                    selectedColor: "black",         //选中时字体颜色
                    selectedBackColor: "#5CACEE",   //选中时背景颜色
                    data: alternateData,
                    onNodeSelected:function(event, data) {
                        selectedNode = data;
                        showMenuInfo(data,false);
                        $('#parentMenuTree').hide();
                    },
                    onNodeUnselected:function(event, data) {
                        selectedNode = {};
                        showMenuInfo(data,false);
                        $('#parentMenuTree').hide();
                    }
                });
            }else{
                layer.msg(ret.message, {time: 3000,icon:2},function () {});
            }
        }
    });
}

/**
 * 展示信息
 * @param menu 菜单
 * @param showOrHide 显示或隐藏 提交按钮 true-显示，false-隐藏
 */
function showMenuInfo(menu,showOrHide){
    $('#id').val(menu.id);
    $('#code').val(menu.code);
    $('#name').val(menu.name);
    $('#icon').val(menu.icon);
    $('#url').val(menu.url);
    $('#isMenu').val(menu.isMenu);
    $('#status').val(menu.status);
    $('#pId').val(menu.pId);
    $('#others').val(menu.others);
    var pName = $('#menuTree').treeview(true).getParent(menu);
    if(pName){
        $('#pName').val(pName.name);
    }else if(insertOrUpdate != "新增" ){
        $('#pName').val("根节点");
    }else{
        $('#pName').val("");
    }

    if(showOrHide){
        //$("#id").removeAttr("disabled");
        $('#code').removeAttr("disabled");
        $('#name').removeAttr("disabled");
        $('#icon').removeAttr("disabled");
        $('#url').removeAttr("disabled");
        $('#isMenu').removeAttr("disabled");
        $('#status').removeAttr("disabled");
        if(pName || insertOrUpdate){
            $('#pName').removeAttr("disabled");
        }else{
            $('#pName').attr("disabled","disabled");
        }
        $('#others').removeAttr("disabled");
        $('#menuSubmit').show();
    }else{
        $("#id").attr("disabled","disabled");
        $('#code').attr("disabled","disabled");
        $('#name').attr("disabled","disabled");
        $('#icon').attr("disabled","disabled");
        $('#url').attr("disabled","disabled");
        $('#isMenu').attr("disabled","disabled");
        $('#status').attr("disabled","disabled");
        $('#pName').attr("disabled","disabled");
        $('#others').attr("disabled","disabled");
        $('#menuSubmit').hide();
    }
}
//编辑
function editMenu(){
    if(selectedNode && selectedNode.id){
        insertOrUpdate = "更新";
        $("#saveButton").html('\<i class="fa fa-check-circle"\>\<\/i\> 保存');
        showMenuInfo(selectedNode,true);
    }else{
        showTips("#editButton");
    }
}
//新增
function addMenu(){
    var menu = {};
    insertOrUpdate = "新增";
    $("#saveButton").html('\<i class="fa fa-plus-circle"\>\<\/i\> 新增');
    showMenuInfo(menu,true);
}
//删除
function delMenu(){
    if(selectedNode && selectedNode.id){
        layer.confirm('确定删除本菜单及下属所有菜单按钮吗?', {icon: 3, title:'提示'}, function(){
            $.ajax({
                type: "DELETE",
                url: "/menu/delMenu?menuId=" + selectedNode.id,
                success: function(ret){
                    if(ret.success){
                        layer.msg("删除成功！", {time: 1500},function(){
                            initMenuTree();
                            initParentMenuTree();
                            var menu = {};
                            showMenuInfo(menu,false);
                        });
                    }else{
                        layer.msg("删除失败："+ret.message, {time: 1500,icon:2});
                    }
                }
            });
        });
    }else{
        showTips("#delButton");
    }
}

function showTips(divId) {
    layer.tips('请选择一条记录！', divId, {
        tips: [1, '#3595CC'],
        time: 3000
    });
}

function initParentMenuTree() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/menu/list",
        success: function(ret){
            if(ret.success){
                var alternateData = ret.data;
                //树
                $('#parentMenuTree').treeview({
                    expandIcon: "fa fa-plus-square",
                    collapseIcon: "fa fa-minus-square",
                    nodeIcon: "fa fa-bookmark",
                    color: "white",			//字体颜色
                    backColor: "#1c2b36",		//背景颜色
                    onhoverColor: "#696969",//鼠标放上时颜色
                    showBorder: false,
                    showTags: true,
                    highlightSelected: true,
                    selectedColor: "black",//选中时字体颜色
                    selectedBackColor: "#5CACEE",//选中时背景颜色
                    data: alternateData,
                    onNodeSelected:function(event, data) {
                        $('#pId').val(data.id);
                        $('#pName').val(data.name);
                        $('#parentMenuTree').hide();
                    },
                    onNodeUnselected:function(event, data) {
                        $('#pId').val(data.id);
                        $('#pName').val(data.name);
                        $('#parentMenuTree').hide();
                    }
                });
            }else{
                layer.msg(ret.message, {time: 3000,icon:2},function () {});
            }
        }
    });
}

function hideParentMenuTree() {
    var parentTree = $('#parentMenuTree').css('display');
    if(parentTree == 'none'){
        $('#parentMenuTree').show();
    }else{
        $('#parentMenuTree').hide();
    }
}

function saveMenu() {
    var menu = {
        id          :$("#id").val(),
        code        :$("#code").val(),
        name        :$("#name").val(),
        icon        :$("#icon").val(),
        url         :$("#url").val(),
        isMenu      :$("#isMenu").val(),
        status      :$("#status").val(),
        pId         :$("#pId").val(),
        others      :$("#others").val()
    };
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/menu/saveMenu",
        //data: $("#menuForm").serialize(),
        data:menu,
        success: function(ret){
            if(ret.success){
                layer.msg(insertOrUpdate+" 菜单/按钮 成功！", {time: 1500,icon:1},function(){
                    initMenuTree();
                    initParentMenuTree();
                    showMenuInfo(ret.data,false);
                });
            }else{
                var message = '';
                if(ret.message){
                    message = ret.message;
                }else{
                    message = insertOrUpdate+" 菜单/按钮 失败！"
                }
                layer.msg(message, {time: 1500,icon:2});
            }
        },
        error : function () {
            layer.msg("系统请求出错!", {time: 1500,icon:2});
        }
    });
}