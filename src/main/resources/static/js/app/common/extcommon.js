/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/


Ext.Ajax.on('requestexception',fn);
function fn(conn, response, options) {
    var state = response.status;
    if(state == 401){
        Ext.MessageBox.show({
            title : '操作提示',
            msg : '您没有正常登录，或者登录超时，请重新登录',
            icon: Ext.Msg.WARNING,
            buttons : Ext.MessageBox.OK,
            fn : function() {
                window.top.location = basePath + '/login';
            }
        });
    }
}