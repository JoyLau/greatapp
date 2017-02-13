/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.dev.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LiuFa on 2017/2/13.
 * cn.lfdevelopment.www.app.dev.blog.controller
 * DevelopmentApp
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @RequestMapping(value = {"","/","/index"})
    public String index(){
        return "dev/blog/index";
    }

    @RequestMapping("/ie")
    public String ie(){
        return "dev/blog/ie";
    }
}
