layui.define(['layer', 'form'], function(exports){
    var layer = layui.layer
        ,form = layui.form;
    exports('add', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致

    //引用jquery
    var $ = layui.$;
    form.on('submit(submit)', function(data){
        $.ajax({
            url:"/user/item/add",
            type: "Post",
            dataType: "json",
            contentType:'application/json',
            data: JSON.stringify(data.field),
            success:function(data){
                if (data.success) {
                    layer.msg(data.message);
                    window.location.href='/user/index';
                } else {
                    layer.msg('注册失败:' + data.message);
                }
            },
            error:function(data){
                layer.msg('注册失败:' + data.message);
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});