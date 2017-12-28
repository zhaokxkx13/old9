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
        showToggle:true,
        //点击行时，自动选择checkbox
        clickToSelect:true,
        //工具条
        toolbar:'#exampleToolbar',
        //是否启用详细信息视图
        //detailView:true,
        //detailFormatter:detailFormatter,
        //表示服务端请求
        sidePagination: "server",
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
        columns: [{
            title: "ID",
            field: "id",
            checkbox:true
        },{
            title: "账号",
            field: "userAcc"
        },{
            title: "昵称",
            field: "nickName"
        },{
            title: "盐资源",
            field: "salt"
        },{
            title: "头像",
            field: "avatar"
        },{
            title: "创建日期",
            field: "createTime",
            formatter:function (value, row, index) {
                return changeDateFormat(value)
            }
        },{
            title: "状态",
            sortable: true,
            field: "status",
            formatter: function (value, row, index) {
                if (value == '-1')
                    return '<span class="label label-danger">锁定</span>';
                else if (value == '0')
                    return '<span class="label label-info">未激活</span>';
                else
                    return '<span class="label label-primary">已激活</span>';
            }
        },/*{
            title: "操作",
            field: "empty",
            formatter: function (value, row, index) {
                return template("operateHtml",{"row":row});
            }
        }*/]
    });
});

function edit(id){
    layer.open({
        type: 2,
        title: '用户修改',
        shadeClose: true,
        shade: false,
        area: ['893px', '600px'],
        content: 'xxxx/user/form?id=' + id,
        end: function(index){
            $('#table_list').bootstrapTable("refresh");
        }
    });
}
function add(){
    layer.open({
        type: 2,
        title: '用户新增',
        shade: 0.5,
        area: ['85%', '80%'],
        content: '/user/form',
        end: function(index){
            $('#table_list').bootstrapTable("refresh");
        }
    });
}
function del(id){
    layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "xxxxx/user/" + id + "/del",
            success: function(msg){
                layer.msg(msg.msg||"删除成功", {time: 2000},function(){
                    $('#table_list').bootstrapTable("refresh");
                    layer.close(index);
                });
            }
        });
    });
}

function detailFormatter(index, row) {
    var html = [];
    html.push('<p><b>描述:</b> ' + row.description + '</p>');
    return html.join('');
}

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

function addSysUser() {
    alert(1);
}