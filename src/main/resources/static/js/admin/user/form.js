var BASE_URL = 'js/plugins/webuploader';

$(document).ready(function () {
    $("#userForm").validate({
        rules: {
            userAcc: {
                required: true,
                minlength: 3
            },
            userPsw: {
                required: true,
                minlength: 3
            },
            userPsw2: {
                required: true,
                minlength: 3
            },
            nickName: {
                required: true,
                minlength: 4,
                maxlength: 10
            },
            description: {
                required: false,
                maxlength: 40
            }
        },
        messages: {},
        submitHandler:function(form){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/user/save",
                data: $(form).serialize(),
                success: function(ret){
                    if(ret.success){
                     layer.msg("新增用户成功！", {time: 3000,icon:1},function(){
                         var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                         parent.layer.close(index);
                     });
                     }else{
                        layer.msg(ret.message, {time: 3000,icon:2},function () {
                            
                        });
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