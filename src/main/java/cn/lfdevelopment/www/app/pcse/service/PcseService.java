/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.pcse.service;

import cn.lfdevelopment.www.app.pcse.mapper.PcseSingleChoiceMapper;
import cn.lfdevelopment.www.app.pcse.pojo.PcseSingleChoice;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiuFa on 2016/10/20.
 * cn.lfdevelopment.www.app.pcse.service
 * DevelopmentApp
 */
@Service
public class PcseService {

    @Autowired
    private PcseSingleChoiceMapper pcseSingleChoiceMapper;

    public List<PcseSingleChoice> getStoreData(int page, int limit) {
        PageHelper.startPage(page, limit);
        return pcseSingleChoiceMapper.getStoreData();
    }
}
