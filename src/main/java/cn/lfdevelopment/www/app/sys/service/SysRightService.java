/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.sys.service;

import cn.lfdevelopment.www.app.sys.mapper.SysRightMapper;
import cn.lfdevelopment.www.app.sys.pojo.SysRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuFa on 2016/10/18.
 * cn.lfdevelopment.www.app.sys.service
 * DevelopmentApp
 */
@Service
public class SysRightService {
    @Autowired
    private SysRightMapper sysRightMapper;

    public List<SysRight>  getSysRight() {
        return sysRightMapper.selectAll();
    }

    public List<SysRight> getSysRightRoot() {
        Example example = new Example(SysRight.class);
        example.createCriteria().andCondition("parentid=0 and type = 0 and state = 1");
        return sysRightMapper.selectByExample(example);
    }

    public List<SysRight> getSysRightChildren(int rootRightId) {
        Example example = new Example(SysRight.class);
        example.createCriteria().andCondition("parentid=",rootRightId);
        return sysRightMapper.selectByExample(example);
    }
}
