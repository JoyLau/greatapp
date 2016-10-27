/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.pcse.mapper;

import cn.lfdevelopment.www.app.pcse.pojo.PcseSingleChoice;
import cn.lfdevelopment.www.sys.base.BaseMapper;

import java.util.List;
import java.util.Map;

public interface PcseSingleChoiceMapper extends BaseMapper<PcseSingleChoice> {
    List<PcseSingleChoice> getStoreData(Map map);

    List<PcseSingleChoice> removeRepeatChoice();
}