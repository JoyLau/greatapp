Ext.define('Ext.ux.plugins.UploadPanel', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.uploadpanel',
    width: 600,
    height: 350,
    prentWinId : 'none',
    upload_url: 'attachment/fileUpLoad',
    columns: [
        {xtype: 'rownumberer'},
        {text: '文件名', width: 200, dataIndex: 'name'},
        {text: '文件类型', width: 70,dataIndex: 'type'},
        {text: '大小', width: 70,dataIndex: 'size',renderer:function(v){
            return Ext.util.Format.fileSize(v);
        }},
        {text: '进度', width: 130, dataIndex: 'percent', renderer: function (v) {
            var stml =
                '<div>' +
                '<div style="border:1px solid #3892d3;height:10px;width:115px;margin:2px 0px 1px 0px;float:left;">' +
                '<div style="float:left;background:#30ff48;width:' + v + '%;height:8px;"><div></div></div>' +
                '</div>' +
                '</div>';
            return stml;
        }
        }, {
            text: '状态', width: 80, dataIndex: 'status', renderer: function (v) {
                if (v == -1) return '等待上传';
                else if (v == -2) return '上传中...';
                else if (v == -3) return '<div style="color:red;">上传失败</div>';
                else if (v == -4) return '上传成功';
                else if (v == -5) return '停止上传';
                else return '';
            }
        }, {
            text: '操作',
            xtype:'actioncolumn',
            align : 'center',
            items: [{
                icon: basePath + '/static/images/desktop/delete.gif',
                tooltip: '移除',
                handler: function (grid, rowIndex, colIndex) {
                    var id = grid.store.getAt(rowIndex).get('id');
                    grid.store.remove(grid.store.getAt(rowIndex));
                }
            }]
        }],
    /*plugins: [
     Ext.create('Ext.grid.plugin.CellEditing', {
     clicksToEdit: 1
     })
     ],*/
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        fields: ['id', 'name', 'type', 'size', 'percent', 'status', 'attachmentId', 'attachmentName']
    }),
    addFileBtnText: '<i class="fa fa-cog fa-spin fa-lg fa-fw"></i> 选择文件',
    uploadBtnText: '<i class="fa fa-upload" aria-hidden="true"></i> 上传',
    removeBtnText: '<i class="fa fa-trash" aria-hidden="true"></i> 移除所有',
    cancelBtnText: '<i class="fa fa-ban" aria-hidden="true"></i> 取消上传',
    completeBtnText: '<i class="fa fa-check" aria-hidden="true"></i> 完成',
    debug: false,
    file_size_limit: 5,//MB
    file_types: '*.*',
    file_types_description: 'All Files',
    file_upload_limit: 10,
    file_queue_limit: 0,
    post_params: {},
    flash_url: basePath + "/static/js/app/plugins/swfupload/swfupload.swf",
    flash9_url: basePath + "/static/js/app/plugins/swfupload/swfupload_fp9.swf",
    initComponent: function () {
        this.dockedItems = [{
            xtype: 'toolbar',
            dock: 'top',
            items: [{
                xtype: 'button',
                itemId: 'addFileBtn',
                id: '_btn_for_swf_',
                text: this.addFileBtnText
            }, {xtype: 'tbseparator'}, {
                xtype: 'button',
                itemId: 'uploadBtn',
                text: this.uploadBtnText,
                scope: this,
                handler: this.onUpload
            }, {xtype: 'tbseparator'}, {
                xtype: 'button',
                itemId: 'removeBtn',
                text: this.removeBtnText,
                scope: this,
                handler: this.onRemoveAll
            }, {xtype: 'tbseparator'}, {
                xtype: 'button',
                itemId: 'cancelBtn',
                disabled: true,
                text: this.cancelBtnText,
                scope: this,
                handler: this.onCancelUpload
            }, {xtype: 'tbseparator'}, {
                xtype: 'button',
                itemId: 'completeBtn',
                text: this.completeBtnText,
                scope: this,
                handler: this.onComplete
            }]
        }];

        this.callParent();
        this.down('button[itemId=addFileBtn]').on({
            afterrender: function (btn) {
                var config = this.getSWFConfig(btn);
                this.swfupload = new SWFUpload(config);
                Ext.get(this.swfupload.movieName).setStyle({
                    position: 'absolute',
                    top: 0,
                    left: -2
                });
            },
            scope: this,
            buffer: 300
        });
    },
    getSWFConfig: function (btn) {
        var me = this;
        var placeHolderId = Ext.id();
        var em = btn.getEl().child('em');
        if (em == null) {
            em = Ext.get(btn.getId() + '-btnWrap');
        }
        em.setStyle({
            position: 'relative',
            display: 'block'
        });
        em.createChild({
            tag: 'div',
            id: placeHolderId
        });
        return {
            debug: me.debug,
            flash_url: me.flash_url,
            flash9_url: me.flash9_url,
            upload_url: me.upload_url,
            file_post_name: 'file',
            post_params: me.post_params || {savePath: 'upload\\'},
            file_size_limit: (me.file_size_limit * 1024),
            file_types: me.file_types,
            file_types_description: me.file_types_description,
            file_upload_limit: me.file_upload_limit,
            file_queue_limit: me.file_queue_limit,
            button_width: em.getWidth(),
            button_height: em.getHeight(),
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_cursor: SWFUpload.CURSOR.HAND,
            button_placeholder_id: placeHolderId,
            custom_settings: {
                scope_handler: me
            },
            swfupload_preload_handler: me.onSwfPreLoad,
            file_queue_error_handler: me.fileQueueFailure,
            swfupload_load_failed_handler: me.swfLoadFailure,
            upload_start_handler: me.onStartUpload,
            upload_progress_handler: me.uploadProcess,
            upload_error_handler: me.uploadFailure,
            upload_success_handler: me.uploadSuccess,
            upload_complete_handler: me.uploadComplete,
            file_queued_handler: me.onFileQueued
        };
    },
    onSwfPreLoad: function () {
        if (!this.support.loading) {
            Ext.Msg.show({
                title: '提示',
                msg: '浏览器Flash Player版本太低,不能使用该上传功能！',
                width: 250,
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
            return false;
        }
    },
    fileQueueFailure: function (file, errorCode, message) {
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED :
                msg('上传文件列表数量超限,不能选择！');
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT :
                msg('文件大小超过限制, 不能选择！');
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE :
                msg('该文件大小为0,不能选择！');
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE :
                msg('该文件类型不允许上传！');
                break;
        }
        function msg(info) {
            Ext.Msg.show({
                title: '提示',
                msg: info,
                width: 350,
                icon: Ext.Msg.WARNING,
                buttons: Ext.Msg.OK
            });
        }
    },
    swfLoadFailure: function () {
        Ext.Msg.show({
            title: '提示',
            msg: 'SWFUpload加载失败！',
            width: 200,
            icon: Ext.Msg.ERROR,
            buttons: Ext.Msg.OK
        });
    },
    onStartUpload: function (file) {
        var me = this.settings.custom_settings.scope_handler;
        me.down('#cancelBtn').setDisabled(false);
    },
    uploadProcess: function (file, bytesLoaded, bytesTotal) {
        var me = this.settings.custom_settings.scope_handler;
        var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
        percent = percent == 100 ? 99 : percent;
        var rec = me.store.getById(file.id);
        rec.set('percent', percent);
        rec.set('status', file.filestatus);
        rec.commit();
    },
    uploadFailure: function (file, errorCode, message) {
        var me = this.settings.custom_settings.scope_handler;
        var rec = me.store.getById(file.id);
        rec.set('percent', 0);
        rec.set('status', file.filestatus);
        rec.commit();
    },
    uploadSuccess: function (file, serverData, responseReceived) {
        var me = this.settings.custom_settings.scope_handler;
        var rec = me.store.getById(file.id);
        var data = Ext.JSON.decode(serverData);
        if (data.success) {
            var atFile = data.atFile;
            rec.set('attachmentId', atFile.attachmentId);
            rec.set('attachmentName', atFile.attachmentName);
            rec.set('percent', 100);
            rec.set('status', file.filestatus);
        } else {
            rec.set('percent', 0);
            rec.set('status', SWFUpload.FILE_STATUS.ERROR);
        }
        rec.commit();
        if (this.getStats().files_queued > 0 && this.uploadStopped == false) {
            this.startUpload();
        } else {
            me.showBtn(me, true);
        }
    },
    onComplete: function () {
        var callback = this.callback;
        var files = this.getSuccessFiles();
        if (callback) {
            callback.call(this, files);
        }
    },
    getSuccessFiles: function () {
        var store = this.store;
        var files = [];
        for (var i = 0; i < store.getCount(); i++) {
            var rec = store.getAt(i);
            if (rec.get('status') == -4) {
                files.push({
                    attachmentId: rec.get('attachmentId'),
                    attachName: rec.get('name')
                });
            }
        }
        return files;
    },
    uploadComplete: function (file) {
        var callback = this.callback;
        if (callback) {
            callback.call(this, file);
        }
    },
    onFileQueued: function (file) {
        var me = this.settings.custom_settings.scope_handler;
        me.store.add({
            id: file.id,
            name: file.name,
            size : file.size,
            type : file.type,
            status: file.filestatus,
            percent: 0
        });

        /*this.customSettings.queue = this.customSettings.queue || [];
        while (this.customSettings.queue.length > 0) {
            this.cancelUpload(this.customSettings.queue.pop(), false);
        }
        this.customSettings.queue.push(file.id);*/
    },
    onUpload: function () {
        if (this.swfupload && this.store.getCount() > 0) {
            if (this.swfupload.getStats().files_queued > 0) {
                this.showBtn(this, false);
                this.swfupload.uploadStopped = false;
                this.swfupload.startUpload();
            }
        }
    },
    showBtn: function (me, bl) {
        me.down('#addFileBtn').setDisabled(!bl);
        me.down('#uploadBtn').setDisabled(!bl);
        me.down('#removeBtn').setDisabled(!bl);
        me.down('#cancelBtn').setDisabled(bl);
        if (bl) {
            me.down('actioncolumn').show();
        } else {
            me.down('actioncolumn').hide();
        }
    },
    onRemoveAll: function () {
        var ds = this.store;
        if(ds.getCount() > 0){
            for (var i = 0; i < ds.getCount(); i++) {
                var record = ds.getAt(i);
                if (record != null) {
                    var id = record.get('id');
                    if (record.get('status') == -2) {
                        this.swfupload.cancelUpload(id, false);
                    }
                }
            }
            ds.removeAll();
            // ds.destroy();
            this.swfupload.uploadStopped = false;
        }
    },
    beforeDestroy: function () {
        var me = this;
        me.store.removeAll();
        Ext.destroy(
            me.placeholder,
            me.ghostPanel
        );
        me.callParent();
    },
    onCancelUpload: function () {
        if (this.swfupload) {
            this.swfupload.uploadStopped = true;
            this.swfupload.stopUpload();
            this.showBtn(this, true);
        }
    }
});