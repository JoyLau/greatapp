/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/20.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.define('pcseSingleChoice.SingleChoiceController',{
    extend: 'Ext.app.Controller',
    stores: ['pcseSingleChoice.SingleChoiceStore'],
    models: ['pcseSingleChoice.SingleChoiceModel'],
    views : ['pcseSingleChoice.SingleChoiceForm','pcseSingleChoice.SingleChoiceDetail','pcseSingleChoice.SingleChoiceGrid'],
    init: function () {
    }
});