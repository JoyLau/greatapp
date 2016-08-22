/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * BaseMapper
 * @author liuzh
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
