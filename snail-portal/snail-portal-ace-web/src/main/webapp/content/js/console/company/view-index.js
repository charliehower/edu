var dataUrl = contextPath + "/company/findCompanyList.do";
var viewPort = null;
var toolBar;
var tabPanel;
var topPanel;
var form1;
var form2;
var grid;
var params = {};
function t_query(button) {
	button.disable();
	grid.store.load({
		callback : function(recorstore, options, success) {
			button.enable();
		}
	});

}
function t_reflash() {
	grid.store.reload();
}
function initToolBar() {
	if (!toolBar) {
		toolBar = new Ext.snailToolBar({
			rightSet : authorConfig,
			snail : true,
			items : [ {
				text : "查询",
				rightCode : 'company.query',
				xtype : "snailButton",
				iconCls : 'View',
				handler : function() {
					t_query(this);
				}
			} ,
			 {
				text : "添加",
				rightCode : 'company.add',
				xtype : "snailButton",
				iconCls : 'Add',
				handler : function() {
					t_add(this);
				}
			},
			
			{
				text : "变更",
				rightCode : 'company.modify',
				xtype : "snailButton",
				iconCls : 'Application',
				handler : function() {
					t_modify(this);
				}
			} ,
			 {
				text : "删除",
				rightCode : 'company.delete',
				xtype : "snailButton",
				iconCls : 'Delete',
				handler : function() {
					t_remove(this);
				}
			}]
		});
	}

	return toolBar;
}
Form1 = Ext.extend(Ext.snailBaseForm, {
	border : false,
	title : '查询',
	layout : 'table',
	autoHeight : true,
	layoutConfig : {
		tableAttrs : {
			cellspacing : "0",
			cellpadding : "2",
			border : 0
		},
		columns : 10
	},
	initComponent : function() {
		if (!this.items) {
			this.items = [ {
				text : "客户名称",
				xtype : "label",
				style:"font-size:12px;margin-right:5px" 
			} , {
				name : "companyName",
				xtype : "textfield"
			},
			{
				text : "客户类别",
				xtype : "label",
				style:"font-size:12px;margin-right:5px;margin-left:5px" 
			},
			{
				name : 'companyType',
				xtype : 'staticDictCombo',
				fieldLabel : '客户类别',
				allowBlank : true,
				emptyText:'请选择',
				dictKey : 'COMPANY_CATEGORY',
				staticDictObjects : parent.staticDictObject
			},{
				text : "所属地区",
				xtype : "label",
				style:"font-size:12px;margin-right:5px;margin-left:5px" 
			},
			{
				xtype: 'treeField',
				name: 'areaCode',
				 autoScroll:true, 
				fieldLabel: '所属地区',
				emptyText:'请选择',
				listWidth:200,
				listHeight:200,
				readOnly:false,
				
				treeRootConfig:{
					text : '中国的省份',
					icon : contextPath + '/content/image/led/control_wheel.png',
					id : '0',
					expand:false,
			        draggable:false 
				},
				dataUrl:contextPath+'/system/selectProvinceTreeList.do'
			}]
		}
		Form1.superclass.initComponent.call(this);
	}
});

Ext.data.PageStore = Ext.extend(Ext.data.snailBaseStore, {
	reader : new Ext.data.JsonReader({
		id : 'companyId',
		totalProperty : 'allRows',
		root : 'list'
	}, [ {name:'companyId', type:'string', mapping:'companyId'},//客户编号
	     {name:'companyType', type:'string', mapping:'companyType'},//客户类别
	     {name:'companyName', type:'string', mapping:'companyName'},//客户名称
	     {name:'areaCode', type:'string', mapping:'areaCode'},//客户所属地区编号
	     {name:'areaName', type:'string', mapping:'areaName'},//客户所属地区
	     {name:'address', type:'string', mapping:'address'},//客户地址
	     {name:'content', type:'string', mapping:'content'},//客户简介
	     {name:'contact', type:'string', mapping:'contact'},//联系人
	     {name:'tel', type:'string', mapping:'tel'},//联系电话
	     {name:'email', type:'string', mapping:'email'},//电子邮箱
	     {name:'createTime', type:'string', mapping:'createTime'},//创建时间
	     {name:'createUserId', type:'string', mapping:'createUserId'},//创建人编号
	     {name:'stauts', type:'string', mapping:'stauts'} ]),
	initComponent : function() {
		Ext.data.PageStore.superclass.initComponent.call(this);
	}
});
ViewGrid = function(config) {
	this.dataUrl = null;
	Ext.apply(this, config);
	ViewGrid.superclass.constructor.call(this);
};
Ext.extend(ViewGrid, Ext.grid.GridPanel, {
	//columnLines : true,
	loadMask : {
		msg : "正在查询数据，请稍候...."
	},
	stripeRows : true,
	autoScroll : true,
	initComponent : function() {
		var grid = this;
		var title = "列表";
		this.sm = new Ext.grid.CheckboxSelectionModel({
			single : true
		});
		if (!this.store) {
			this.store = new Ext.data.PageStore({
				proxy : new Ext.data.snailHttpProxy({
					url : grid.dataUrl
				})
			});
		}
		this.bbar = new Ext.snailPagingToolbar({
			ownGrid : grid,
			store : grid.store,
			pageSize : 50
		});
		this.tbar = [ new Ext.Toolbar.TextItem(title), {
			xtype : "tbfill"
		} ];
		if (!this.cm) {
			this.cm = new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(), 
			                                     {
				   header:'客户编号',
				   width: 80,
				   sortable: true,
				   dataIndex: 'companyId'
				},

				{
				   header:'客户类别',
				   width: 80,
				   sortable: true,
				   dataIndex: 'companyType',
				   renderer : function(value) {
						return rendererStaticDict(value, 'COMPANY_CATEGORY');
					}
				},

				{
				   header:'客户名称',
				   width: 150,
				   sortable: true,
				   dataIndex: 'companyName'
				},

				{
				   header:'所属地区',
				   width: 80,
				   sortable: true,
				   dataIndex: 'areaName'
				},

				{
				   header:'客户地址',
				   width: 120,
				   sortable: true,
				   dataIndex: 'address'
				},


				{
				   header:'联系人',
				   width: 80,
				   sortable: true,
				   dataIndex: 'contact'
				},

				{
				   header:'联系电话',
				   width: 120,
				   sortable: true,
				   dataIndex: 'tel'
				},

				{
				   header:'电子邮箱',
				   width: 150,
				   sortable: true,
				   dataIndex: 'email'
				},

				{
				   header:'创建时间',
				   width: 80,
				   sortable: true,
				   dataIndex: 'createTime'
				}]);
		}
		ViewGrid.superclass.initComponent.call(this);

	}
});

Ext.onReady(function() {
	initBodyA();
});
function initTabPanel() {
	form1 = new Form1();
	tabPanel = new Ext.TabPanel({
		title : 'tabPanel',
		snail : true,
		border : false,
		defaults : {
			autoHeight : true,
			bodyStyle : 'padding:2px'
		},
		autoHeight : true,
		listeners : {
			tabchange : function(tp, p) {
				if (p.title == '精确查询') {
					topPanel.setHeight(95);
				} else {
					topPanel.setHeight(95);
				}
				viewPort.doLayout();
			}
		}
	});
	tabPanel.add(form1).show();
	return tabPanel;

}
function initBodyA() {
	initToolBar();
	initTabPanel();
	topPanel = new Ext.Panel({
		region : 'north',
		height : 95
	});
	topPanel.add(tabPanel);
	topPanel.add(toolBar);
	grid = new ViewGrid({
		dataUrl : dataUrl,
		region : 'center'
	});
	grid.getSelectionModel().on('selectionchange', function(sm) {

	});
	viewPort = new Ext.Viewport({
		layout : 'border',
		items : [ topPanel, grid ]
	});
	viewPort.doLayout(true);
	grid.store.on('beforeload', function(store) {
		Ext.apply(params, getParams(form1));
		//alert(Ext.encode(params));
		Ext.apply(params, {
			limit : grid.getBottomToolbar().pageSize,
			totalRecord : store.getTotalCount()
		});
		store.baseParams = params;
	});
	grid.getSelectionModel().on('selectionchange', function(sm) {

	});
	tabPanel.getItem(0).show();
}
