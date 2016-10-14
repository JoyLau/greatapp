/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/


Ext.Ajax.on('requestcomplete',main);
function main(conn, response, options) {
    var str = response.responseText;
    alert(str)
    if(str == '定义画布宽高和生成点的个数'){
        Ext.MessageBox.show({
            title : '<span style="color: red; ">操作提示</span>',
            msg : '您没有正常登录，或者登录超时，请重新登录',
            buttons : Ext.MessageBox.OK,
            fn : function() {
                window.top.location = basePath + '/login';
            }
        });
    }
}


ExtCommonRequest = function(cfg) {
    var mk =  new Ext.LoadMask(cfg.owner || Ext.getBody(), {
        msg : cfg.text || '正在执行操作...'
    });
    if (!cfg.hidmask) {
        mk.show();
    }
    Ext.Ajax.request({
        url : cfg.url,
        method : cfg.method || 'post',
        params : cfg.params || {},
        success : function(response) {
            if (!cfg.hidmask) {
                mk.hide();
            }
            if (!validJson(response.responseText)) {
                return false;
            }

            var json = Ext.util.JSON.decode(response.responseText);
            if (json.success) {
                if (cfg.success)
                    cfg.success(json.msg);
                else
                    Ext.Msg.alert('提示', json.msg);
            } else {
                if (cfg.failure)
                    cfg.failure(json.msg);
                else
                    Ext.Msg.alert('提示', json.msg);
            }

        },
        failure : function(response) {
            if (!cfg.hidmask) {
                mk.hide();
            }
            if(response.responseText){
                Ext.MessageBox.show({
                    title : '操作提示',
                    msg : '操作执行失败：' + response.responseText,
                    buttons : Ext.MessageBox.OK,
                    icon : Ext.MessageBox.ERROR
                });
            }else{
                Ext.MessageBox.show({
                    title : '操作提示',
                    msg : '操作执行失败：' + response.responseText+cfg.url?
                        cfg.url.substring(cfg.url.lastIndexOf('/')+1,cfg.url.indexOf('.')):cfg.url,
                    buttons : Ext.MessageBox.OK,
                    icon : Ext.MessageBox.ERROR
                });
            }
        }
    });
};


function validJson(json) {
    if (json == 'noright') {
        Ext.MessageBox.show({
            title : '操作提示',
            msg : '您没有权限执行该操作，请联系管理员获取权限',
            buttons : Ext.MessageBox.OK
        });
        return false;
    }

    if (json == 'login') {
        Ext.MessageBox.show({
            title : '操作提示',
            msg : '您没有正常登录，或者登录超时，请重新登录',
            buttons : Ext.MessageBox.OK,
            fn : function() {
                window.top.location = basePath;
            }
        });
    }

    return true;
}