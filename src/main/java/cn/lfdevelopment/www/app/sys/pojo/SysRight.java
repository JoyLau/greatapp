/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.sys.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sys_right")
public class SysRight {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * URL地址
     */
    private String url;

    /**
     * 小图标，可以是图片路径，可用是css样式，也可以是glyph属性
     */
    private String icon;

    /**
     * 是否子节点 0.否1.是
     */
    private Integer leaf;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父节点id
     */
    private Integer parentid;

    /**
     * 权限可用状态0.不可用1.可用
     */
    private Integer state;

    /**
     * 权限备注
     */
    private String notes;

    /**
     * 权限菜单的分类（0：题库类，1：个人网站及博客类）
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Transient
    private List<SysRight> children = new ArrayList<>();
    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取权限名
     *
     * @return name - 权限名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限名
     *
     * @param name 权限名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取URL地址
     *
     * @return url - URL地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL地址
     *
     * @param url URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取小图标，可以是图片路径，可用是css样式，也可以是glyph属性
     *
     * @return icon - 小图标，可以是图片路径，可用是css样式，也可以是glyph属性
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置小图标，可以是图片路径，可用是css样式，也可以是glyph属性
     *
     * @param icon 小图标，可以是图片路径，可用是css样式，也可以是glyph属性
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取是否子节点 0.否1.是
     *
     * @return leaf - 是否子节点 0.否1.是
     */
    public Integer getLeaf() {
        return leaf;
    }

    /**
     * 设置是否子节点 0.否1.是
     *
     * @param leaf 是否子节点 0.否1.是
     */
    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取父节点id
     *
     * @return parentid - 父节点id
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 设置父节点id
     *
     * @param parentid 父节点id
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取权限可用状态0.不可用1.可用
     *
     * @return state - 权限可用状态0.不可用1.可用
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置权限可用状态0.不可用1.可用
     *
     * @param state 权限可用状态0.不可用1.可用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取权限备注
     *
     * @return notes - 权限备注
     */
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public List<SysRight> getChildren() {
        return children;
    }

    public void setChildren(List<SysRight> children) {
        this.children = children;
    }
}