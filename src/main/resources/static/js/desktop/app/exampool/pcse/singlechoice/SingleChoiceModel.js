/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/20.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.define('pcseSingleChoice.SingleChoiceModel', {
    extend: 'Ext.data.Model',
    fields: ['id',
        'title',
        'answerA',
        'answerB',
        'answerC',
        'answerD',
        'type',
        'isImage',
        'imageUrl',
        'meno',
        'updateTime',
        'answerRight']
});