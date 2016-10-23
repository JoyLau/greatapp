/**
 * Created by LiuFa on 2016/10/23.
 * static.js.desktop.app.exampool.pcse.multiplechoice
 * greatapp
 */
Ext.define('pcseMultipleChoice.MultipleChoicePanel',{
    extend: 'Ext.panel.Panel',
    id : moduleId+'PCSEMultipleChoicePanel',
    alias: 'widget.PCSEMultipleChoicePanel',
    region: 'north',
    split: true,
    collapsible: true,
    title : 'north',
    items : [{
            xtype : 'button',
            text : '去重',
            handler : removebutton
        }]

});


function removebutton() {
    alert('去重')
}