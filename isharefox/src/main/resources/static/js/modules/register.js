layui.define(['layer', 'form'], function(exports){
    var layer = layui.layer
        ,form = layui.form;
    layer.msg('register load [layer、form]');
    exports('register', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致

    var $ = layui.$;
    form.verify({
        checkPassword:function(value, item){
                var password = $("input[name='password']").val();
                if(/^[\S]{6,9}$/.test(value) === false){
                    return '登录密码不可少于6位！';
                }
            }
        ,confirmPassword:function(value, item){
            var password = $("input[name='password']").val();
            if(password !== value) {
                return '两次密码输入不一致,请重新输入！';
            }
        }
    });


    form.on('submit(submit)', function(data){
        // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        // console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        // console.log(data.field.email) //当前容器的全部表单字段，名值对形式：{name: value}
        // console.log(data.field.password) //当前容器的全部表单字段，名值对形式：{name: value}
        // console.log(JSON.stringify(data.field)) //当前容器的全部表单字段，名值对形式：{name: value}

        $.ajax({
            url:"/register",
            type: "Post",
            dataType: "json",
            contentType:'application/json',
            data: JSON.stringify(data.field),
            success:function(data){
                if (data.success) {
                    layer.msg(data.message);
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