/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/9.
 */
Ext.define('examPoolChoice.ChoiceStore', {
    extend : 'Ext.data.Store',
    storeId : 'ChoiceStore',
    model: 'examPoolChoice.ChoiceModel',
    autoLoad: false,
    pageSize: 10, //设置每页显示的数据数量
    proxy: {
        type: 'ajax',
        url: basePath + '/static/js/app/common/main/data/data.json',
        reader: {
            type: 'json',
            root: 'choice', //指定从json的哪个属性获取数据，如果不指定，则从json的跟属性获取
            totalProperty: 'total' //总数据数量
        }
    }
});
