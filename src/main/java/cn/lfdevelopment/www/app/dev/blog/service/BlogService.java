/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.dev.blog.service;

import cn.lfdevelopment.www.app.dev.blog.mapper.BlogMapper;
import cn.lfdevelopment.www.app.dev.blog.pojo.JsonTree;
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

        List<JsonTree> jsonTrees = SysRightToJsonTree(list);

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
        System.out.println(JSON.toJSONString(trees));
        return JSON.toJSONString(trees);
    }


    public List<JsonTree> SysRightToJsonTree(List<SysRight> list){
        List<JsonTree> jsonTrees = new ArrayList<>();
        for (SysRight sysRight : list) {
            JsonTree jsonTree = new JsonTree();
            jsonTree.setId(sysRight.getId());
            jsonTree.setParentid(sysRight.getParentid());
            jsonTree.setText(sysRight.getName());
            jsonTree.setMid(sysRight.getLeaf() == 0 ? 1 : 2);
            jsonTree.setLeaf(sysRight.getLeaf() != 0);
            jsonTree.setExpanded(true);
            jsonTree.setUrl(sysRight.getUrl());
            jsonTrees.add(jsonTree);
        }
        return jsonTrees;
    }
}
