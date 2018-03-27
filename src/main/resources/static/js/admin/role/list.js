$(document).ready(function () {
    //初始化表格,动态从服务器加载数据
    $("#roleList").bootstrapTable({
        method: "GET",
        url: "/role/list",
        striped: true,
        pagination: true,
        pageSize: 10,
        pageNumber: 1,
        pageList: [5, 10, 15, 20, 25],
        showColumns:true,
        clickToSelect:true,
        toolbar:'#exampleToolbar',
        sidePagination: "server",
        singleSelect:true,
        queryParamsType: "undefined",
        responseHandler: function(res) {
            return {
                "rows": res.records,
                "total": res.total
            };
        },
        //数据列
        columns: [
            {field: "checkbox",checkbox:true},
            {title: "ID",field: "id"},
            {title: "角色名称",field: "name"},
            {title: "所属部门",field: "deptName"},
            {title: "备注",field: "remarks"}
        ]
    });
});

//新增
function addRole(){
    layer.open({
        type: 2,
        title: '角色新增',
        shade: 0.5,
        area: ['68%', '50%'],
        content: '/role/add',
        end: function(){
            $('#roleList').bootstrapTable("refresh");
        }
    });
}
//编辑
function editRole(){
    var selectNodes = $("#roleList").bootstrapTable('getSelections');
    if(selectNodes.length == 1){
        layer.open({
            type: 2,
            title: '修改用户信息',
            shade: 0.5,
            area: ['68%', '50%'],
            content: '/role/edit?id='+selectNodes[0].id,
            end: function(){
                $('#roleList').bootstrapTable("refresh");
            }
        });
    }else{
        layer.tips('请选择一条记录！', "#editButton", {
            tips: [1, '#3595CC'],
            time: 3000
        });
    }
}
//删除
function delRole(){
    var selectNodes = $("#roleList").bootstrapTable('getSelections');
    if(selectNodes.length == 1){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '/role/delRole?id='+selectNodes[0].id,
                success: function(ret){
                    if(ret.success){
                        layer.msg("删除成功！", {time: 1500},null);
                        $('#roleList').bootstrapTable("refresh");
                    }else{
                        layer.msg("删除失败："+ret.message, {time: 1500,icon:2});
                    }
                }
            });
        });
    }else{
        layer.tips('请选择一条记录！', "#delButton", {
            tips: [1, '#3595CC'],
            time: 3000
        });
    }
}
//权限
function permRole(){
    var selectNodes = $("#roleList").bootstrapTable('getSelections');
    if(selectNodes.length == 1){
        layer.open({
            type: 2,
            title: '权限配置',
            shade: 0.5,
            area: ['30%', '73%'],
            content: '/role/permissionsTreeInit?roleId='+selectNodes[0].id,
            end: function(){
                $('#roleList').bootstrapTable("refresh");
            }
        });
    }else{
        layer.tips('请选择一条记录！', "#permButton", {
            tips: [1, '#3595CC'],
            time: 3000
        });
    }
}
//搜索
function searchRole() {
    var param = {
        url: "/role/list?name="+$("#searchName").val()
    };
    $('#roleList').bootstrapTable('refresh',param);
}
