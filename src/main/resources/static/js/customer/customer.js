layui.use([ 'table', 'form', 'laydate', 'jquery' ], function() {
    var layer = layui.layer;
    var form = layui.form, table = layui.table;
    var $ = layui.jquery;
    laydate = layui.laydate;

/*

    $('.demoTable .search').on('click', function() {
        reloadPage();
    });
    $('.demoTable .add').on('click', function() {
        openWindow("新增通道");
    });

    // 监听工具条
    table.on('tool(listTable)', function(obj) {
        var data = obj.data;
        console.log(data);
        if (obj.event === 'edit') {
            editOne(data.cid);// 加载远程数据
            openWindow("编辑通道");
        } else if (obj.event === 'changeCards') {
            // editOne(data.cid);// 加载远程数据
            changeCards(data.cid);
        }
    });
    form.on('switch(status)', function(obj) {
        var b = obj.elem.checked ? 1 : 0;
        var url = "/website/callChannel/onoff?cid=" + this.value
            + "&onOff=" + b;
        console.info(this.value + ' ：' + b);
        $.ajax({
            type : "POST",
            url : url,
            success : function(res) {
                var data = JSON.parse(res);
                if (data.code == "-1") {
                    layer.msg(data.msg, {
                        icon : 2
                    });
                } else {
                    reloadPage();
                    layer.msg('操作成功');
                }
            }
        });
    });
    // 监听提交
    form.on('submit(demo1)', function(data) {
        var url = "/website/callChannel/save";
        if ($("#cid").val() != "") {
            url = "/website/callChannel/update";
        }
        console.log("url: " + url);
        $.ajax({
            type : "POST",
            url : url,
            contentType : "application/json",
            data : JSON.stringify({
                "cid" : $("#cid").val(),
                "channelName" : $("#channelName").val(),
                "channelName" : $("#channelName").val(),
                "quantity" : $("#quantity").val(),
                "isvalid" : $("#isvalid").val(),
                "remark" : $("#remark").val()
            }),
            success : function(res) {
                var data = JSON.parse(res);
                if (data.code == "0") {
                    layer.msg('操作成功');
                    reloadPage();
                } else {
                    layer.msg(data.msg, {
                        icon : 2
                    });
                }
            }
        });
        layer.closeAll();
        return false;
    });


    function reloadPage() {
        table.reload('tableId', {
            where : {
                "queryConditions.channel_name" : $("#channel_name").val(),
            },
            page : {
                curr : 1
                // 重新从第 1 页开始
            }
        });
    }

    // 远程加载数据
    function editOne(cid) {
        $.get("/website/callChannel/getChannelInfoOne/" + cid, function(res) {
            if (res.code == "0") {
                console.log(res.obj);
                $("#cid").val(res.obj.cid);
                $("#channelName").val(res.obj.channelName);
                $("#quantity").val(res.obj.quantity);
                $("#isvalid").val(res.obj.isvalid);
                $("#remark").val(res.obj.remark);
                form.render('select');
            } else {
                layer.alert(res.msg, {
                    icon : 2
                });
            }
        }, 'json');

    }

    function openWindow(title) {
        var laydate = layui.laydate, form = layui.form;
        console.log("title: " + title);
        // 多窗口模式，层叠置顶
        layer.open({
            skin : 'cs-class',
            title : title,
            type : 1,
            shade : 0,
            area : [ '528px', '300px' ],
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
                // location.reload();
            }
        });
//		})
    }
    function changeCards(cid) {
        $.get("/website/callChannel/queryStatus/?cid=" + cid, function(res) {
            if (res.code == "0") {
                $("#ccid").val(cid);
                var p = res.obj.position;
                var obj = eval( res.obj );
                var num = 8;
                console.info(p+" = "+JSON.stringify(obj));
                if (res.obj.empty0 !=null && res.obj.empty0!='undefind') {
                    if (res.obj.empty0 > num) {
                        var input = $("#cc0");
                        input.attr("disabled","disabled");
                    }
                }
                if (res.obj.empty1 !=null && res.obj.empty1!='undefind') {
                    if (res.obj.empty1 > num) {
                        var input = $("#cc1");
                        input.attr("disabled","disabled");
                    }
                }
                if (obj.empty2 !=null && obj.empty2!='undefind') {
                    if (res.obj.empty2 > num) {
                        var input = $("#cc2");
                        input.attr("disabled","disabled");
                    }
                }
                if (obj.empty3 !=null && obj.empty3!='undefind') {
                    if (res.obj.empty3 > num) {
                        var input = $("#cc3");
                        input.attr("disabled","disabled");
                    }
                }
                if (p !=null && p !='undefind') {
                    $(("#cc"+p)).prop("checked",true);
                }

                form.render();
            } else {
                layer.alert(res.msg, {
                    icon : 2
                });
            }
        }, 'json');

        // 多窗口模式，层叠置顶
        layer.open({
            skin : 'cs-class',
            title : '换卡操作',
            type : 1,
            shade : 0,
            area : [ '520px', '180px' ],
            content : $("#changeCards"),
            btn : [ '确认', '取消' ],
            btnAlign : 'c',
            cancel : function() {
                // 右上角的关闭
                layer.closeAll();
            },
            yes : function(index, layero) {
                $('#changeCards').click(subChange());
            },
            btn2 : function(index, layero) {
            },
            end : function() {
                location.reload();
            }
        });
    }
    // 监听换卡
    function subChange() {
        var aa = $("input[name='cc']:checked").val();
        var ccid =$("#ccid").val();
        var url = "/website/callChannel/changeCards?cid="+ccid+"&port="+aa;
        console.log("url: " + url + "  val:" + aa + "  ccid:" + ccid);
        $.ajax({
            type : "POST",
            url : url,
            success : function(res) {
//				var data = JSON.parse(res);
                if (data.code == "0") {
                    layer.msg('操作成功');
                    reloadPage();
                } else {
                    layer.msg(data.msg, {
                        icon : 2
                    });
                }
            }
        });
        layer.closeAll();
        return false;
    }
*/


});
