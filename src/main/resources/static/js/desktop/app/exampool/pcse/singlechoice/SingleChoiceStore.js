/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/20.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.define('pcseSingleChoice.SingleChoiceStore', {
    extend : 'Ext.data.Store',
    model: 'pcseSingleChoice.SingleChoiceModel',
    autoLoad: false,
    pageSize: 10, //设置每页显示的数据数量
    proxy: {
        type: 'ajax',
        // getMethod: function(){ return 'POST'; },
        actionMethods: {
            read: 'POST'
        },
        url: basePath + '/exampool/pcse/singleChoice/getStore',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'total'
        }
    },
    listeners : {
        beforeload : function(s, o) {
            var data = Ext.getCmp('exampool-choice-form').form.getValues();
            s.getProxy().extraParams = {data : Ext.encode(data)};
        }
    }
});