/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/20.
 * static.js.desktop.app.exampool.pcse.singlechoice
 * DevelopmentApp
 */
Ext.define('pcseSingleChoice.SingleChoiceDetail',{
    extend : 'Ext.panel.Panel',
    alias : 'widget.PESCSingleChoiceDetail',
    title: '题目详细信息',
    glyph: 0xf05a,
    id: 'pcse-singleChoice-detail',
    frame: true,
    region: 'east',
    collapsible: true,
    split: true,
    autoScroll : true,
    minWidth: 150,
    width : 250,
    maxWidth: 400,
    bodyStyle: {
        background: '#ffc',
        padding: '10px'
    },
    html : '<p style="text-align: center;"><span style="color: rgb(75, 172, 198);">鼠标移至题目上查看详情</span></p>'
});