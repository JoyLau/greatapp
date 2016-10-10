/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

Ext.define('examPoolChoice.ChoiceController', {
    extend: 'Ext.app.Controller',
    stores: ['examPoolChoice.ChoiceStore'],
    models: ['examPoolChoice.ChoiceModel'],
    views : ['examPoolChoice.ChoiceQueryForm','examPoolChoice.ChoiceDetail']
});