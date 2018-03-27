$(document).ready(function () {
    //初始化部门树
    initDeptTree();

    $("#roleForm").validate({
        rules: {
            name: {required: true, minlength:2},
            deptName: {required: true, minlength:1}
        },
        submitHandler:function(){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/role/saveRole",
                data: $("#roleForm").serialize(),
                success: function(ret){
                    if(ret.success){
                        layer.msg("修改角色成功！", {time: 1500,icon:1},function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index);
                        });
                    }else{
                        layer.msg(ret.message, {time: 1500,icon:2},null);
                    }
                }
            });
        }
    });
});

//保存
function saveRole() {
    $("#roleForm").submit();
}
//取消
function cancel() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}

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
                        $('#deptId').val(data.id);
                        $('#deptName').val(data.name);
                        $('#deptTree').hide();
                    },
                    onNodeUnselected:function(event, data) {
                        $('#deptId').val(data.id);
                        $('#deptName').val(data.name);
                        $('#deptTree').hide();
                    }
                });
            }else{
                layer.msg(ret.message, {time: 3000,icon:2},function () {});
            }
        }
    });
}

function hideDeptTree() {
    var tree = $('#deptTree').css('display');
    if(tree == 'none'){
        $('#deptTree').show();
    }else{
        $('#deptTree').hide();
    }
}