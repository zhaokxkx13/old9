var selectedNode;
var insertOrUpdate; //新增，更新
$(document).ready(function () {
    //初始化部门树
    initDeptTree();
    initParentDeptTree();

});

function initDeptTree() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/dept/list",
        success: function(ret){
            if(ret.success){
                var alternateData = ret.data;
                //树
                $('#deptTree').treeview({
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
                        selectedNode = data;
                        showDeptInfo(data,false);
                        $('#parentDeptTree').hide();
                    },
                    onNodeUnselected:function(event, data) {
                        selectedNode = {};
                        showDeptInfo(data,false);
                        $('#parentDeptTree').hide();
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
 * @param dept 部门
 * @param showOrHide 显示或隐藏 提交按钮 true-显示，false-隐藏
 */
function showDeptInfo(dept,showOrHide){
    $('#id').val(dept.id);
    $('#name').val(dept.name);
    $('#fullName').val(dept.fullName);
    $('#sort').val(dept.sort);
    $('#pId').val(dept.pId);
    $('#tips').val(dept.tips);
    var pName = $('#deptTree').treeview(true).getParent(dept);
    if(pName){
        $('#pName').val(pName.name);
    }else if(insertOrUpdate != "新增" ){
        $('#pName').val("根节点");
    }else{
        $('#pName').val("");
    }

    if(showOrHide){
        //$("#id").removeAttr("disabled");
        $('#name').removeAttr("disabled");
        $('#fullName').removeAttr("disabled");
        $('#sort').removeAttr("disabled");
        if(pName || insertOrUpdate){
            $('#pName').removeAttr("disabled");
        }else{
            $('#pName').attr("disabled","disabled");
        }
        $('#tips').removeAttr("disabled");
        $('#deptSubmit').show();
    }else{
        $("#id").attr("disabled","disabled");
        $('#name').attr("disabled","disabled");
        $('#fullName').attr("disabled","disabled");
        $('#sort').attr("disabled","disabled");
        $('#pName').attr("disabled","disabled");
        $('#tips').attr("disabled","disabled");
        $('#deptSubmit').hide();
    }
}
//编辑
function editDept(){
    if(selectedNode && selectedNode.id){
        insertOrUpdate = "更新";
        $("#saveButton").html('\<i class="fa fa-check-circle"\>\<\/i\> 保存');
        showDeptInfo(selectedNode,true);
    }else{
        showTips("#editButton");
    }
}
//新增
function addDept(){
    var dept = {};
    insertOrUpdate = "新增";
    $("#saveButton").html('\<i class="fa fa-plus-circle"\>\<\/i\> 新增');
    showDeptInfo(dept,true);
}
//删除
function delDept(){
    if(selectedNode && selectedNode.id){
        layer.confirm('确定删除本机构及下属所有机构吗?', {icon: 3, title:'提示'}, function(){
            $.ajax({
                type: "DELETE",
                url: "/dept/delDept?deptId=" + selectedNode.id,
                success: function(ret){
                    if(ret.success){
                        layer.msg("删除成功！", {time: 1500},function(){
                            initDeptTree();
                            initParentDeptTree();
                            var dept = {};
                            showDeptInfo(dept,false);
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

function initParentDeptTree() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/dept/list",
        success: function(ret){
            if(ret.success){
                var alternateData = ret.data;
                //树
                $('#parentDeptTree').treeview({
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
                        $('#parentDeptTree').hide();
                    },
                    onNodeUnselected:function(event, data) {
                        $('#pId').val(data.id);
                        $('#pName').val(data.name);
                        $('#parentDeptTree').hide();
                    }
                });
            }else{
                layer.msg(ret.message, {time: 3000,icon:2},function () {});
            }
        }
    });
}

function hideParentDeptTree() {
    var parentTree = $('#parentDeptTree').css('display');
    if(parentTree == 'none'){
        $('#parentDeptTree').show();
    }else{
        $('#parentDeptTree').hide();
    }
}

function saveUser() {
    var dept = {
        id          :$("#id").val(),
        name        :$("#name").val(),
        fullName    :$("#fullName").val(),
        sort        :$("#sort").val(),
        pId         :$("#pId").val(),
        tips        :$("#tips").val()
    };
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/dept/saveDept",
        //data: $("#deptForm").serialize(),
        data:dept,
        success: function(ret){
            if(ret.success){
                layer.msg(insertOrUpdate+"部门成功！", {time: 1500,icon:1},function(){
                    initDeptTree();
                    initParentDeptTree();
                    showDeptInfo(ret.data,false);
                });
            }else{
                var message = '';
                if(ret.message){
                    message = ret.message;
                }else{
                    message = insertOrUpdate+"部门失败！"
                }
                layer.msg(message, {time: 1500,icon:2});
            }
        }
    });
}