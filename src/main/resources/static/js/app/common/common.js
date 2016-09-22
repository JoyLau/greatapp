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
