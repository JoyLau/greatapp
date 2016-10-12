Ext.define('Ext.ux.plugins.UploadWin',{
	extend: 'Ext.window.Window',
	title: '上传图片',
	alias: 'widget.uploadWin',
	autoScroll: true,
	border: false,
	layout: 'fit',
	width: 580,
	height: 360,
	callback: Ext.emptyFn,
	requires: ['Ext.ux.plugins.UploadPanel'],
	initComponent: function(){
		this.items = [this.buildPanel()];
		this.callParent();
	},
	buildPanel: function(){
		var panel = Ext.create('Ext.ux.plugins.UploadPanel',{
			callback: this.callback
		});
		return panel;
	}
})