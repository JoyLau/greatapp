Ext.define('et.controller.Menu',{ 
    extend: 'Ext.app.Controller', 
   	stores: ['Menus'],  
    models: ['Menu'],  
    views: ['Menu'],  
    init: function () {  
    //初始化部分，下面是部分是给菜单绑定单击事件，接下来会用，这里先注释  
        this.control({  
            'sxptmenu': {  
              itemmousedown: this.loadMenu
          }
          });
    },
      loadMenu:function(selModel, record){ 
        if (record.get('leaf')) {
        	if(record.get('optype')=='window'){
        		var win= Ext.getCmp(record.get('url'));
        		if(!win){
        			win=Ext.widget(record.get('url'))
        		}
        		win.show();
        	}
        	else{
        		var panel = Ext.getCmp(record.get('id'));
	            if(!panel){  
	                panel ={
	                	id:record.get('url'),
	                    title: record.get('text'), 
	                    xtype:record.get('url'),
	                    closable: true  
	                };
	                this.openTab(panel,record.get('url')); 
	            }else{ 
	                var main = Ext.getCmp("content-panel"); 
	                main.setActiveTab(panel);  
	            } 
        	}
            
        }  },
     openTab : function (panel,id){
        var o = (typeof panel == "string" ? panel : id || panel.id); 
        var main = Ext.getCmp("content-panel"); 
        var tab = main.getComponent(o);       
        if (tab) { 
            main.setActiveTab(tab);  
        } else if(typeof panel!="string"){  
            panel.id = o;  
            var p = main.add(panel);  
            main.setActiveTab(p);  
        }  

    }  

});