layui.use(['element','jquery','table','form','laydate'], function(){
    var $ = layui.jquery
        ,table =layui.table
        ,form =layui.form
        ,laydate =layui.laydate
        ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

    laydate.render({
        //指定元素
        elem: '#dateTime',
        type: 'datetime'
    });
    table.render({
        method:'post'
        ,elem: '#demo'
        ,height: 620
        ,limit:20
        ,limits:[10,20,50,100]
        ,url: '/crm/customer/interviewlist' //数据接口
        ,page: true //开启分页
        ,response: {
            statusName: 'status'
            ,statusCode: 0
            ,countName: 'total'
            ,dataName: 'data'
        },cols:  [[ //标题栏
            {type:"numbers", title: '序号', minWidth: 80}
            ,{field: 'calledNum', title: '号码', minWidth: 120}
            ,{field: 'customerName', title: '用户名', minWidth: 120}
            ,{field: 'label', title: '标签', minWidth:80,  templet: function(d){
                var labelname;
                switch (d.label) {
                    case 0:labelname='A+'
                        break;
                    case 1:labelname='A'
                        break;
                    case 2:labelname='B+'
                        break;
                    case 3:labelname='B'
                        break;
                    case 4:labelname='C'
                        break;
                    case 5:labelname='F'
                        break;
                    case 6:labelname='T'
                        break;
                    case 7:labelname='S'
                        break;
                }
                return labelname;
            }}
            ,{field: 'type', title: '类型', minWidth: 80,templet: function(d){
                    var typename;
                    switch (d.label) {
                        case 0:typename='手机'
                            break;
                        case 1:typename='固话'
                            break;
                    }
                    return typename;
                }}
            ,{field: 'result', title: '访问内容', minWidth: 200}
            ,{field: 'viewTime', title: '访问时间', minWidth: 80, templet: function(d){
                    return f_datetime(d.viewTime)}}
        ]]
    });
    // 监听提交
  form.on('submit(demo1)', function() {
        var url = "/crm/customer/save";
        $.ajax({
            type : "POST",
            url : url,
            dataType: "json",
            data:$('form').serialize(),
            success : function(res) {
                console.info(res)
                if(res.code==1){
                    layer.closeAll();
                    table.reload();
                }else if(res.code==0){
                    layer.msg(res.msg);
                }
            }
        });
        layer.closeAll();
    });
    function reloadPage() {
        table.reload('tableId', {
            where : {
                // "queryConditions.channel_name" : $("#channel_name").val(),
            },
            page : {
                curr : 1
                // 重新从第 1 页开始
            }
        });
    }

});
function openwinclick() {
    openWindow('添加回访记录');
}
function openWindow(title) {
    var laydate = layui.laydate
        ,form = layui.form
        ,$ = layui.jquery;
    console.log("title: " + title);
    // 多窗口模式，层叠置顶
    layer.open({
        skin : 'cs-class',
        title : title,
        type : 1,
        shade : 0,
        area : [ '528px', '350px' ],
        content : $("#windiv"),
        btn : [ '确认', '取消' ],
        btnAlign : 'c',
        cancel : function() {
            layer.closeAll();
        },
        yes : function(index, layero) {
            $('#demo1').click();
        },
        btn2 : function(index, layero) {
        },
        end : function() {
        }
    });

}