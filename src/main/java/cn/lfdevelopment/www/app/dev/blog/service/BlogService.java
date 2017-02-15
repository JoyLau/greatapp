/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.dev.blog.service;

import cn.lfdevelopment.www.app.dev.blog.mapper.BlogMapper;
import cn.lfdevelopment.www.app.sys.pojo.JsonTree;
import cn.lfdevelopment.www.app.sys.mapper.SysRightMapper;
import cn.lfdevelopment.www.app.sys.pojo.SysRight;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuFa on 2017/2/15.
 * cn.lfdevelopment.www.app.dev.blog.service
 * DevelopmentApp
 */
@Service
@Transactional
public class BlogService {
    private final SysRightMapper sysRightMapper;

    private final BlogMapper blogMapper;

    @Autowired
    public BlogService(BlogMapper blogMapper, SysRightMapper sysRightMapper) {
        this.blogMapper = blogMapper;
        this.sysRightMapper = sysRightMapper;
    }
    public String getMenuTree(){
        Example example = new Example(SysRight.class);
        example.createCriteria().andCondition("type = 1 and state = 1");
        List<SysRight> list = sysRightMapper.selectByExample(example);

        List<JsonTree> jsonTrees = JsonTree.sysRightToJsonTree(list);

        List<JsonTree> trees = new ArrayList<>();
        for (JsonTree jsonTree : jsonTrees) {
            if(jsonTree.isLeaf())
                continue;
            for (JsonTree jsonTree2 : jsonTrees) {
                if (jsonTree.getId()==jsonTree2.getParentid()) {
                    jsonTree.getChildren().add(jsonTree2);
                }
            }
            trees.add(jsonTree);
        }
        return JSON.toJSONString(trees);
    }
}
