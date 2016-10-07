Ext.define('Desktop.exampool.Choice', {

    extend : 'Ext.form.Panel',
    title : '题目查询',
    frame : true,
    region : 'north',
    labelAlign : 'right',
    collapsible : true,
    height : '15%',
    border : false,
    bodyPadding : 20,
    items : [{
        layout : 'column',
        items : [{
            columnWidth : .3,
            layout : 'form',
            items : [{
                xtype : 'textfield',
                name : 'name',
                fieldLabel : '题目名称',
                labelAlign : "right",
                anchor : '70%'
            }, {
                name : 'updateTimeStart',
                xtype : 'datefield',
                labelAlign : "right",
                fieldLabel : '添加时间',
                format : 'Y-m-d',
                anchor : '70%',
                showToday : true,
                editable : false
            }]

        }, {
            columnWidth : .3,
            layout : 'form',
            items : [{
                xtype : 'combo',
                editable : false,
                mode : 'local',
                fieldLabel : '题目类型',
                labelAlign : "right",
                emptyText : '--请选择--',
                anchor : '60%',
                triggerAction : 'all',
                selectOnFocus : true,
                hiddenName : 'state',
                displayField : 'text',
                valueField : 'id',
                store : new Ext.data.SimpleStore({
                    fields : ['id', 'text'],
                    data : [['0', '公务员试题'],
                        ['1', '驾考试题'],
                        ['2', '自考试题'],
                        ['3', '其他分类']]
                })
            }, {
                name : 'updateTimeEnd',
                xtype : 'datefield',
                fieldLabel : '至',
                labelAlign : "right",
                format : 'Y-m-d',
                anchor : '70%',
                showToday : true,
                editable : false
            }]
        }, {
            columnWidth : .3,
            layout : 'form',
            items : [{
                xtype : 'combo',
                editable : false,
                mode : 'local',
                fieldLabel : '正确答案',
                emptyText : '--请选择--',
                labelAlign : "right",
                anchor : '60%',
                triggerAction : 'all',
                selectOnFocus : true,
                hiddenName : 'state',
                displayField : 'text',
                valueField : 'id',
                store : new Ext.data.SimpleStore({
                    fields : ['id', 'text'],
                    data : [['0', 'A'],
                        ['1', 'B'],
                        ['2', 'C'],
                        ['3', 'D'],
                        ['4', 'E'],
                        ['5', 'F'],
                        ['6', 'G'],
                        ['7', 'H']]
                })
            }]
        }]
    }],
    keys : [{
        key : Ext.EventObject.ENTER,
        fn : function() {
            mygrid.getStore().reload()
        },
        scope : this
    }],
    buttons : [{
        text : '查询',
        handler : function() {
            mygrid.getStore().reload();
        }
    }, {
        text : '清空条件',
        handler : function() {
            queryForm.getForm().reset();
        }
    }]
});



Ext.define('Desktop.exampool.ChoiceGrid', {

    extend : 'Ext.grid.GridPanel',
    border : false,
    stripeRows : true,
    frame : true,
    region : 'center',
    loadMask : {
        msg : "数据加载中,请稍等...."
    },
        columns : [new Ext.grid.RowNumberer(), {
            header : '客户名称',
            dataIndex : 'name',
            sortable : true
        }, {
            header : '客户工作',
            dataIndex : 'job',
            sortable : true
        }, {
            header : '联系电话',
            dataIndex : 'phoneNumber',
            sortable : true,
            renderer : function(v, cellmeta, record) {
                return v
            }
        }],
    bbar: [{
        xtype: 'pagingtoolbar',
        displayMsg: '显示 {0} - {1} 条，共计 {2} 条',
        emptyMsg: "没有数据",
        beforePageText: "当前页",
        afterPageText: "共{0}页",
        displayInfo: true
    }],
    tbar : [{
        text : '添加',
        id : 'addCustomer',
        listeners : {
        }

    }, '-', {
        text : '修改',
        id : 'updateCustomer',
        listeners : {
        }
    }, '-', {
        text : '停用',
        handler : function() {
        },
        listeners : {
        }
    }, '-', {
        text : '启用',
        handler : function() {
        },
        listeners : {
        }
    }, '-', {
        text : '客户项目管理',
        id : 'customerProject',
        listeners : {
        }
    }],
    listeners : {
        // rowdblclick : this.modify,
        // rowcontextmenu : function(g, i, e) {
        //     if (i < 0)
        //         return;
        //     g.getSelectionModel().selectRow(i);
        //     rightClick.showAt(e.getXY());
        // },
        // cellclick : function(grid, rowIndex, columnIndex, e) {
        //     var record = grid.getStore().getAt(rowIndex);
        //     var fieldName = grid.getColumnModel().getDataIndex(columnIndex);
        //     if (fieldName == 'projectName') {
        //         var data = record.get(fieldName);
        //         if (!data || data == '') {
        //             return;
        //         }
        //         data = data.replace(/,/g, ",</br>");
        //         new Ext.ToolTip({
        //             html : data,
        //             title : '详情',
        //             dismissDelay : 0
        //         }).showAt(e.getXY());
        //     }
        // }
    }
});
