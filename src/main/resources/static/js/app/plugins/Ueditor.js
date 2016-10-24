/**
 * Created by LiuFa on 2016/10/25.
 * static.js.app.plugins
 * greatapp
 */
Ext.define('Ext.ux.Ueditor',{
    extend: 'Ext.form.FieldContainer',
    mixins:{
        field:Ext.form.field.Field
    },
    alias: 'widget.ueditor',//xtype名称
    alternateClassName: 'Ext.form.UEditor',
    ueditorInstance: null,
    initialized: false,
    initComponent: function () {
        var me = this;
        me.addEvents('initialize', 'change'); // 为ueditor添加一个初始化完成的事件
        var id = me.id + '-ueditor';
        me.html = '<script id="' + id + '" type="text/plain" name="' + me.name + '"></script>';
        //调用当前方法的父类方法详见Ext.Base
        me.callParent(arguments);
        me.initField();

        me.on('render', function () {
            var width = me.width - 105;
            var height = me.height - 109;
            var config = {initialFrameWidth: width, initialFrameHeight: height};
            me.ueditorInstance = UE.getEditor(id, config);
            me.ueditorInstance.ready(function () {
                me.initialized = true;
                me.fireEvent('initialize', me);
                me.ueditorInstance.addListener('contentChange', function () {
                    alert(111);
                    me.fireEvent('change', me);
                });

            });
        });
    },
    getValue: function () {
        var me = this,
            value = '';
        if (me.initialized) {
            value = me.ueditorInstance.getContent();
        }
        me.value = value;
        return value;
    },
    setValue: function (value) {
        // alert(value);
        var me = this;
        if (value === null || value === undefined) {
            value = '';
        }else{
            me.isChanged = true;
        }
        if (me.initialized) {
            me.ueditorInstance.setContent(value);
        }
        return me;
    },
    onDestroy:function () {
        this.ueditorInstance.destroy();
    }
});