layui.define(['layer', 'form', 'table'], function(exports){
    var layer = layui.layer
        ,form = layui.form
        ,table = layui.table;

    exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
    var $ = layui.$;

    table.render({
        elem: '#items'
        ,url: '/user/item/list' //数据接口
        ,method: 'post'
        ,height: 500
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'detail', title: '商品描述', width:180, sort: true, fixed: 'left'}
            ,{field: 'amount', title: '金额', width:80, sort: true}
            ,{field: 'status', title: '状态', width:80, sort: true}
            ,{field: 'createTime', title: '创建时间', width: 177, sort: true}
            ,{field: 'updateTime', title: '更新时间', width: 177, sort: true}
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
});