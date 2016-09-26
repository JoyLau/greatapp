/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

Ext.define('et.view.Header', {
    extend: 'Ext.toolbar.Toolbar' ,
	border: true,
	shadow : 'sides',
	id: 'headerBar',
	autoHeight: true,
	region: 'north',
	style : 'background:#f5f5f5;',
    items: [{
		id: 'top-user',
		hidden: true,
		iconCls: 'yw_btn_view_customer'
	}, '-', '->', '-', {
		text: '退出',
		glyph : 0xf011,
		id: 'header-logout',
		handler: function () {
			setUserLogout();
		}

	}]
}); 
