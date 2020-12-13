layui.define(['layer', 'form', 'flow','element'], function (exports) {
    var layer = layui.layer,
        form = layui.form,
        flow = layui.flow,
        element = layui.element;
    //当你执行这样一个方法时，即对页面中的全部带有lay-src的img元素开启了懒加载（当然你也可以指定相关img）
    flow.lazyimg();

    exports('login', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致

    var $ = layui.$;
    form.verify({
        checkPwd: function (value, item) {
            var password = $("input[name='pwd']").val();
            if (/^[\S]{6,9}$/.test(value) === false) {
                return '登录密码不可少于6位！';
            }
        }
    });

    //监听提交
    form.on('submit(submit)', function (data) {
        var index = layer.load();
        $.ajax({
            url: "/login",
            type: "Post",
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify(data.field),
            success: function (data) {
                layer.close(index);
                if (data.success) {
                    layer.msg(data.message, {
                        icon: 6,
                        time: 1000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        window.location.href = '/user/index';
                    });
                } else {
                    layer.msg(data.message, {
                        icon: 5,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        window.location.href = '/login.html';
                    });
                }
            },
            error: function (data) {
                layer.close(index);
                layer.msg('登录异常:' + data.message);
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //获取验证码
    kaptcha();
    //点击刷新验证码
    $("#kaptcha")[0].addEventListener("click", function (ev) {
        kaptcha();
    });
    function kaptcha() {
        $.ajax({
            url: "/kaptcha",
            type: "Post",
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                if (data.success) {
                    var url = data.urlBase64;
                    $("#kaptcha").attr("src", url);
                }
            },
            error: function (data) {
                layer.msg('获取验证码失败:' + data.message);
            }
        });
    }
});