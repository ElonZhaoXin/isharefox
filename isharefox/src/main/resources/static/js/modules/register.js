layui.define(['layer', 'form', 'flow','element'], function (exports) {
    var layer = layui.layer,
        form = layui.form,
        flow = layui.flow,
        element = layui.element;
    exports('register', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致

    //当你执行这样一个方法时，即对页面中的全部带有lay-src的img元素开启了懒加载（当然你也可以指定相关img）
    flow.lazyimg();

    var $ = layui.$;
    form.verify({
        checkPwd: function (value, item) {
            var password = $("input[name='pwd']").val();
            if (/^[\S]{6,9}$/.test(value) === false) {
                return '登录密码不可少于6位！';
            }
        }
        , confirmPwd: function (value, item) {
            var password = $("input[name='pwd']").val();
            if (password !== value) {
                return '两次密码输入不一致,请重新输入！';
            }
        }
    });

    //监听提交
    form.on('submit(submit)', function (data) {
        var index = layer.load();
        $.ajax({
            url: "/register",
            type: "Post",
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.close(index);
                if (data.success) {
                    layer.msg(data.message, {
                        icon: 6,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        window.location.href = '/';
                    });
                } else {
                    layer.msg(data.message, {
                        icon: 5,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        window.location.href = '/register.html';
                    });
                }
            },
            error: function (data) {
                layer.close(index);
                layer.msg('注册失败:' + data.message);
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});