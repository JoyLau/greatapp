/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.weixin.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by LiuFa on 2017/2/16.
 * cn.lfdevelopment.www.app.weixin.controller
 * DevelopmentApp
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {

    private static final String token = "1001520@WSX3edc";


    @RequestMapping(value = {"","/","/index"})
    @ResponseBody
    public String index(HttpServletRequest request){
        // 接收微信服务器以Get请求发送的4个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (checkSignature(signature,timestamp,nonce)){
            System.out.println(true);
        }
        System.out.println("signature:"+signature);
        System.out.println("timestamp:"+timestamp);
        System.out.println("nonce:"+nonce);
        System.out.println("echostr:"+echostr);
        return echostr;
    }

    private static boolean checkSignature(String signature, String timestamp, String nonce){

        String[] arr = new String[] { token, timestamp, nonce };

        // 排序
        Arrays.sort(arr);
        // 生成字符串
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }

        // sha1加密
        String temp = getSHA1String(content.toString());

        return temp.equals(signature); // 与微信传递过来的签名进行比较
    }

    private static String getSHA1String(String data){
        return DigestUtils.sha1Hex(data);    // 使用commons codec生成sha1字符串
    }

}
