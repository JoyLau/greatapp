/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
* Created by LiuFa on 2016/9/19.
* ${PACKAGE_NAME}
* DevelopmentApp
*/
/**
 * @liufa
 * 全局的异步ajax处理
 * 处理异步清求时session失效
 */
/*
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
        var sessionStatus=XMLHttpRequest.getResponseHeader("sessionStatus");
        if(sessionStatus=="timeout"){
            //if the session is invalid,and forward to under URI
            window.location = XMLHttpRequest.getResponseHeader("URI");
        }
    }
});*/
synchronize = function(url) {
    function createXhrObject() {
        var http;
        var activeX = ['MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];
        try {
            http = new XMLHttpRequest();
        } catch (e) {
            for (var i = 0; i < activeX.length; ++i) {
                try {
                    http = new ActiveXObject(activeX[i]);
                    break;
                } catch (e) {}
            }
        } finally {
            return http;
        }
    }
    var conn = createXhrObject();
    conn.open("GET", url, false);
    conn.send(null);
    if (conn.responseText != '') {
        return Ext.decode(conn.responseText);
    } else {
        return null;
    }
};