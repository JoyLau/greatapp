Ext.define('Ext.ux.plugins.UploadWin',{
	extend: 'Ext.window.Window',
	title: '上传',
	glyph: 0xf0ee,
	alias: 'widget.uploadWin',
	autoScroll: true,
	modal : true,
	animateTarget : Ext.getBody(),
	border: false,
	layout: 'fit',
	width: 700,
	height: 360,
	callback: Ext.emptyFn,
	upload_url: 'attachment/fileUpLoad',
	success_text : '上传成功',
	completeBtnText: '<i class="fa fa-check" aria-hidden="true"></i> 完成',
	file_types: '*.*',
	file_types_description: 'All Files',
	file_upload_limit: 10,
	file_queue_limit: 0,
	requires: ['Ext.ux.plugins.UploadPanel'],
	initComponent: function(){
		this.items = [this.buildPanel()];
		this.callParent();
	},
	buildPanel: function(){
		return Ext.create('Ext.ux.plugins.UploadPanel', {
			upload_url: this.upload_url,
			success_text : this.success_text,
			completeBtnText: this.completeBtnText,
			file_types: this.file_types,
			file_types_description: this.file_types_description,
			file_upload_limit: this.file_upload_limit,
			file_queue_limit: this.file_queue_limit,
			callback: this.callback
		});
	}
});