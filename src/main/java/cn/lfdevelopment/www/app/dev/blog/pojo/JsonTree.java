/*******************************************************************************
 * Copyright (c) 2017 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.dev.blog.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuFa on 2017/2/15.
 * cn.lfdevelopment.www.app.dev.blog.pojo
 * DevelopmentApp
 */
public class JsonTree {
    public int id;
    public int parentid;
    public String text;
    public boolean leaf;
    public boolean expanded;
    public int mid;
    public String url;
    public List<JsonTree> children = new ArrayList<>();

    public List<JsonTree> getChildren() {
        return children;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public void setChildren(List<JsonTree> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
