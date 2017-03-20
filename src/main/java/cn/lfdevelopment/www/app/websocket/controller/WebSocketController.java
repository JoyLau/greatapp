/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.websocket.controller;

import cn.lfdevelopment.www.app.websocket.model.Message;
import cn.lfdevelopment.www.app.websocket.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by LiuFa on 2017/1/16.
 * cn.lfdevelopment.www.app.websocket.controller
 * DevelopmentApp
 */
@Controller
public class WebSocketController {
    @RequestMapping("/ws")
    public String websocket(){
        return "dev/websocket/websocket";
    }

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public Response say(Message message) throws Exception {
        return new Response("Welcome, " + message.getName() + "!");
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;//1

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        if (principal.getName().equals("wyf")) {//3
            messagingTemplate.convertAndSendToUser("wisely",
                    "/queue/notifications", principal.getName() + "-send:"
                            + msg);
        } else {
            messagingTemplate.convertAndSendToUser("wyf",
                    "/queue/notifications", principal.getName() + "-send:"
                            + msg);
        }
    }
}
