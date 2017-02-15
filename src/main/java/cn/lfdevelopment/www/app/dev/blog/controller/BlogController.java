/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.dev.blog.controller;

import cn.lfdevelopment.www.app.dev.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by LiuFa on 2017/2/13.
 * cn.lfdevelopment.www.app.dev.blog.controller
 * DevelopmentApp
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = {"","/","/index"})
    public String index(){
        return "dev/blog/index";
    }

    @RequestMapping("/ie")
    public String ie(){
        return "dev/blog/ie";
    }

    @RequestMapping("/getMenuTree")
    @ResponseBody
    public String getMenuTree(){
        return blogService.getMenuTree();
    }
}
