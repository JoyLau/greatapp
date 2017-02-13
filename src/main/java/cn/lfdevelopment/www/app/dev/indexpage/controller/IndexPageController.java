/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.dev.indexpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by LiuFa on 2017/2/9.
 * cn.lfdevelopment.www.app.dev.indexpage.controller
 * DevelopmentApp
 */
@Controller
public class IndexPageController {

    @RequestMapping(value = {"/","/index","/index"})
    public String indexPage(){
        return "dev/indexpage/index";
    }

    @RequestMapping("/index/listMoreProject")
    @ResponseBody
    public String listMoreProject(){
        return "";
    }
}
