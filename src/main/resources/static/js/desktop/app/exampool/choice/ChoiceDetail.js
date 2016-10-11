/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

/**
 * Created by LiuFa on 2016/10/10.
 */
Ext.define('examPoolChoice.ChoiceDetail',{
    extend : 'Ext.panel.Panel',
    alias : 'widget.ChoiceDetail',
    title: '题目详细信息',
    glyph: 0xf05a,
    id: 'exampool-choice-detail',
    frame: true,
    region: 'east',
    collapsible: true,
    split: true,
    minWidth: 150,
    maxWidth: 450
});