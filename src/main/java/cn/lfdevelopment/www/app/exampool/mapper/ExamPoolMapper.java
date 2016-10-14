/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.exampool.mapper;

import cn.lfdevelopment.www.app.exampool.pojo.CivilServantChoice;
import cn.lfdevelopment.www.sys.base.BaseMapper;

import java.util.List;

/**
 * Created by LiuFa on 2016/10/8.
 * cn.lfdevelopment.www.app.exampool.mapper
 * DevelopmentApp
 */
public interface ExamPoolMapper extends BaseMapper<CivilServantChoice> {
    List<CivilServantChoice> getQueryData();
}
