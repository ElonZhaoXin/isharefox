layui.define(['layer', 'form', 'table'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,table = layui.table;
    exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致

    //引用jquery
    var $ = layui.$;

    //加载商户列表
    loadTable();

    //监听行工具事件
    table.on('tool(items)', function(obj){
        var data = obj.data;
        switch(obj.event){
            case 'showCode':
                showCode(data.linkUrl)
                break;
            case 'edit':
                break;
            case 'delete':
                deleteItem(data.id);
                break;
        }
    });

    //展示二维码连接
    function showCode(url){
        window.open(url,'_blank');
    }

    //删除商品
    function deleteItem(id){
        layer.confirm('真的删除行么', function(index){
            layer.close(index);
            var itemDeleteDto = {
                "id" : id
            }
            $.ajax({
                url:"/user/item/delete",
                type: "Post",
                dataType: "json",
                contentType:'application/json',
                data: JSON.stringify(itemDeleteDto),
                success:function(data){
                    layer.msg(data.message);
                },
                error:function(data){
                    layer.msg('删除失败:' + data.message);
                }
            });
            loadTable();
        });
    }

    //初始化商品列表
    function loadTable() {
        table.render({
            elem: '#items'
            ,url: '/user/item/list' //数据接口
            ,method: 'post'
            ,height: 'full-390'
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'id', title: '编号', fixed: 'left', width:80}
                ,{field: 'description', title: '商品描述', width:180}
                ,{field: 'url', title: '资源链接', width:180}
                ,{field: 'amount', title: '金额', width:80, sort: true}
                ,{field: 'zipPwd', title: '密码', width:80, sort: true}
                ,{field: 'linkUrl', title: '收款链接', width: 300}
                ,{field: 'status', title: '状态', width:80, sort: true}
                ,{field: 'createTime', title: '创建时间', width: 177, sort: true}
                ,{field: 'updateTime', title: '更新时间', width: 177, sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#rowBar'}
            ]]
            , request: {
                pageName: 'current', //页码的参数名称，默认：page
                limitName: 'size' //每页数据量的参数名，默认：limit
            }
            , parseData: function(res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.records //解析数据列表
                }
            }
            , response: {
                statusCode: "SUCCESS" //规定成功的状态码，默认：0
            }
        });
    }
});