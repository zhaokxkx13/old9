var BASE_URL = 'js/plugins/webuploader';
//关闭
function cancel() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}