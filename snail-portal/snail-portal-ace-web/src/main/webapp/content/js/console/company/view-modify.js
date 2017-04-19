var modifyWindow;
var modifyForm;
function createModifyWindow(params) {
	if (!modifyWindow) {
		modifyForm = new Ext.form.FormPanel({
			labelWidth : 65,
			baseCls : 'x-plain',
			snail : false,
			layout : {
				type : 'form',
				align : 'stretch'
			},
			defaultType : 'textfield',
			defaults : {
				width : 350
			},
			width : 350,
			autoHeight : true,
			items : [{
				name:'companyId',
				xtype: 'hidden'
				
			}, {
				fieldLabel : "客户名称",
				name : "companyName",
				autoCreate :{
	                  tag : 'input',
	                  type : 'text',
	                  autocomplete : 'off',
	                  maxlength : '20'
	                }
			},{
				name : 'companyType',
				xtype : 'staticDictCombo',
				fieldLabel : '客户类别',
				allowBlank : false,
				emptyText:'请选择',
				dictKey : 'COMPANY_CATEGORY',
				staticDictObjects : parent.staticDictObject
			}, {
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
			},{
				fieldLabel : "公司地址",
				name : "address",
				autoCreate :{
	                  tag : 'input',
	                  type : 'text',
	                  autocomplete : 'off',
	                  maxlength : '30'
	                }
			},{
				fieldLabel : "联系人",
				name : "contact",
				autoCreate :{
	                  tag : 'input',
	                  type : 'text',
	                  autocomplete : 'off',
	                  maxlength : '12'
	                }
			},{
				fieldLabel : "联系电话",
				name : "tel",
				autoCreate :{
	                  tag : 'input',
	                  type : 'text',
	                  autocomplete : 'off',
	                  maxlength : '11'
	                }
			},{
				fieldLabel : "电子邮箱",
				name : "email",
				autoCreate :{
	                  tag : 'input',
	                  type : 'text',
	                  autocomplete : 'off',
	                  maxlength : '30'
	                }
			}]

		});
		modifyWindow = new Ext.Window({
			title : '客户变更',
			layout : 'fit',
			bodyStyle : 'padding:5px;',
			width : 450,
			height : 260,
			minWidth : 300,
			minHeight : 260,
			autoHeight : false,
			closeAction : 'hide',
			plain : true,
			collapsible : true,
			maximizable : true,
			buttonAlign : 'center',
			items : modifyForm,
			buttons : [ {
				text : '保存',
				type : 'submit',
				disabled : false,
				iconCls : 'Save',
				handler : function() {
					t_update(this);
				}
			}, {
				text : '关闭',
				iconCls : 'Erase',
				handler : function() {
					modifyWindow.hide();
				}
			} ]
		});

	}

	modifyWindow.show();
	t_load(params);
}