$(document).ready(function () {
    //初始化表格,动态从服务器加载数据
    $("#table_list").bootstrapTable({
        //使用get请求到服务器获取数据
        method: "GET",
        //必须设置，不然request.getParameter获取不到请求参数
        //contentType: "application/x-www-form-urlencoded",
        //获取数据的Servlet地址
        url: "/user/list",
        //表格显示条纹
        striped: true,
        //启动分页
        pagination: true,
        //每页显示的记录数
        pageSize: 10,
        //当前第几页
        pageNumber: 1,
        //记录数可选列表
        pageList: [5, 10, 15, 20, 25],
        //是否启用查询
        //search: true,
        //searchText:"输入姓名搜索",
        //是否显示 内容列下拉框
        showColumns:true,
        //是否显示 切换试图（table/card）按钮
        //showToggle:true,
        //点击行时，自动选择checkbox
        clickToSelect:true,
        //工具条
        toolbar:'#exampleToolbar',
        //是否启用详细信息视图
        //detailView:true,
        //detailFormatter:detailFormatter,
        //表示服务端请求
        sidePagination: "server",
        //单选，默认false
        singleSelect:true,
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        //json数据解析
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
            {title: "账号",field: "userAcc"},
            {title: "昵称",field: "nickName"},
            {title: "所属部门",field: "deptName"},
            {title: "盐资源",field: "salt"},
            {title: "头像",field: "avatar"},
            {title: "创建日期",field: "createTime",
                formatter:function (value, row, index) {
                    return changeDateFormat(value)
                }},
            {title: "状态",sortable: true,field: "status",
                formatter: function (value, row, index) {
                    if (value == '-1')
                        return '<span class="label label-danger">锁定</span>';
                    else if (value == '0')
                        return '<span class="label label-info">未激活</span>';
                    else
                        return '<span class="label label-primary">已激活</span>';
                }}/*{
            title: "操作",
            field: "empty",
            formatter: function (value, row, index) {
                return template("operateHtml",{"row":row});
            }
        }*/]
    });
});

//新增
function addUser(){
    layer.open({
        type: 2,
        title: '用户新增',
        shade: 0.5,
        area: ['85%', '80%'],
        content: '/user/add',
        end: function(){
            $('#table_list').bootstrapTable("refresh");
        }
    });
}
//查看
function showUser() {
    var selectNodes = $("#table_list").bootstrapTable('getSelections');
    if(selectNodes.length == 1){
        layer.open({
            type: 2,
            title: '查看用户信息',
            shade: 0.5,
            area: ['85%', '80%'],
            content: '/user/show?id='+selectNodes[0].id
        });
    }else{
        layer.tips('请选择一条记录！', "#showButton", {
            tips: [1, '#3595CC'],
            time: 3000
        });
    }
}
//编辑
function editUser(){
    var selectNodes = $("#table_list").bootstrapTable('getSelections');
    if(selectNodes.length == 1){
        layer.open({
            type: 2,
            title: '修改用户信息',
            shade: 0.5,
            area: ['85%', '80%'],
            content: '/user/edit?id='+selectNodes[0].id,
            end: function(){
                $('#table_list').bootstrapTable("refresh");
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
function delUser(){
    var selectNodes = $("#table_list").bootstrapTable('getSelections');
    if(selectNodes.length == 1){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '/user/delUser?id='+selectNodes[0].id,
                success: function(ret){
                    if(ret.success){
                        layer.msg("删除成功！", {time: 1500},null);
                        $('#table_list').bootstrapTable("refresh");
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

function searchUser() {
    var param = {
        url: "/user/list?userAcc="+$("#searchAcc").val()
    };
    $('#table_list').bootstrapTable('refresh',param);
}

/*function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>描述:</b> ' + row.description + '</p>');
    return html.join('');
}*/

//修改——转换日期格式(时间戳转换为datetime格式)1512716658000
function changeDateFormat(cellval) {
    if (cellval != null) {
        var date = new Date(parseInt(cellval, 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var time = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var min = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        return date.getFullYear() + "-" + month + "-" + currentDate + " "+time+":"+min+":"+second;
    }
}


