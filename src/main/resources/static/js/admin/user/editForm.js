var BASE_URL = 'js/plugins/webuploader';

$(document).ready(function () {
    //初始化部门树
    initDeptTree();
    initRoleSelect();

    $("#userForm").validate({
        rules: {
            userAcc: {required: true, minlength:3},
            userPsw: {required: true, minlength:3},
            userPsw2: {required: true, minlength:3},
            nickName: {required: true, minlength:1, maxlength:10},
            description: {required: false, maxlength:40}
        },
        submitHandler:function(){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/user/saveEdit",
                data: $("#userForm").serialize(),
                success: function(ret){
                    if(ret.success){
                        layer.msg("修改用户成功！", {time: 1500,icon:1},function(){
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
function saveUser() {
    $("#userForm").submit();
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

function initRoleSelect() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/role/list",
        success: function(ret){
            if(ret.records){
                var records = ret.records;
                for(var i=0;i<records.length;i++){
                    $("#roleIdsTmp").append("<option value='"+records[i].id+"'>"+records[i].name+"</option>");
                }
            }else{
                layer.msg("角色信息获取失败！", {time: 3000,icon:2},function () {});
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

function selectRoles(value) {
    var selectedOption=value.options[value.selectedIndex];
    var tempName = selectedOption.text;
    var tempId = selectedOption.value;

    var reg = new RegExp(" ","g");//g,表示全部替换
    var roleIds =  $('#roleIds').val().replace("[","").replace("]","").replace(reg,"");
    var roleNames =  $('#roleNames').val().replace("[","").replace("]","").replace(reg,"");
    var roleIdsArr = roleIds.split(",");
    var roleNamesArr = roleNames.split(",");
    if(roleIds.length>0){
        var hadIndex = roleIdsArr.indexOf(tempId);
        if(hadIndex>=0){
            roleIdsArr.splice(hadIndex, 1);
            roleNamesArr.splice(hadIndex, 1);
            $('#roleIds').val(roleIdsArr.toString());
            $('#roleNames').val(roleNamesArr.toString());
        }else{
            $('#roleIds').val(roleIds+","+tempId);
            $('#roleNames').val(roleNames+","+tempName);
        }
    }else{
        $('#roleIds').val(tempId);
        $('#roleNames').val(tempName);
    }
    $('#roleIds').val("["+$('#roleIds').val()+"]");
    $('#roleNames').val("["+$('#roleNames').val()+"]");
}