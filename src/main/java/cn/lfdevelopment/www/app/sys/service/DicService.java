/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.sys.service;

import cn.lfdevelopment.www.app.sys.mapper.DicMapper;
import cn.lfdevelopment.www.app.sys.pojo.SysDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiuFa on 2016/9/14.
 * cn.lfdevelopment.www.app.sys.service
 * DevelopmentApp
 */
@Service
public class DicService {

    private final DicMapper dicMapper;

    @Autowired
    public DicService(DicMapper dicMapper) {
        this.dicMapper = dicMapper;
    }

    public List<SysDic> getdicList(){
        return dicMapper.selectAll();
    }
}
