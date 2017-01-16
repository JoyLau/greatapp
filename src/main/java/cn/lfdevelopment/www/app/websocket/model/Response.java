/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.websocket.model;

/**
 * Created by LiuFa on 2017/1/16.
 * cn.lfdevelopment.www.app.websocket.model
 * DevelopmentApp
 */
public class Response {
    private String responseMessage;
    public Response(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}
