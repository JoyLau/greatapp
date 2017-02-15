package cn.lfdevelopment.www.app.dev.blog.mapper;

import cn.lfdevelopment.www.app.dev.blog.pojo.Blog;
import cn.lfdevelopment.www.app.sys.pojo.SysRight;
import cn.lfdevelopment.www.sys.base.BaseMapper;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {
    List<SysRight> getMenuTree();
}