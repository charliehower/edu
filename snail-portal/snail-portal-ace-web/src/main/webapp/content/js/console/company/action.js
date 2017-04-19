function t_add(button) {
	createAddWindow();
}
function t_modify(button) {
	var row = grid.getSelectionModel().getSelected();//
	if (!row) {
		Ext.Msg.show({
			title : '系统提示',
			msg : '请选择要操作的数据！',
			icon : Ext.Msg.INFO,
			minWidth : 300,
			buttons : Ext.Msg.OK
		});
		return;
	}
	createModifyWindow({
		companyId : row.get("companyId")
	});
}
function t_remove(button) {

}
function t_save(button) {
	button.disable();
	Ext.Ajax.request({
		timeout : 10000000,
		url : contextPath + '/company/insertCompany.do',
		method : 'POST',
		async : false,
		params : {
			josnObject : Ext.encode(getParams(addForm))
		},
		success : function(v) {
			button.enable();
			var rst = Ext.decode(v.responseText);
			Ext.Msg.show({
				title : '系统提示',
				msg : rst.errorMessage,
				icon : Ext.Msg.INFO,
				minWidth : 300,
				buttons : Ext.Msg.OK
			});
			if (rst.state) {
				t_reflash();
			}
		},
		failure : function(response, opts) {
			button.enable();
			Ext.Msg.show({
				title : '系统提示',
				msg : response.statusText,
				icon : Ext.Msg.INFO,
				minWidth : 300,
				buttons : Ext.Msg.OK
			});
		}
	})
}
function t_update(button) {
	button.disable();
	Ext.Ajax.request({
		timeout : 10000000,
		url : contextPath + '/company/updateCompany.do',
		method : 'POST',
		async : false,
		params : {
			josnObject : Ext.encode(getParams(modifyForm))
		},
		success : function(v) {
			button.enable();
			var rst = Ext.decode(v.responseText);
			Ext.Msg.show({
				title : '系统提示',
				msg : rst.errorMessage,
				icon : Ext.Msg.INFO,
				minWidth : 300,
				buttons : Ext.Msg.OK
			});
			if (rst.state) {
				t_reflash();
			}
		},
		failure : function(response, opts) {
			button.enable();
			Ext.Msg.show({
				title : '系统提示',
				msg : response.statusText,
				icon : Ext.Msg.INFO,
				minWidth : 300,
				buttons : Ext.Msg.OK
			});
		}
	})
}
function t_load(params) {
	Ext.Ajax.request({
		timeout : 10000000,
		url : contextPath + '/company/selectCompanyByPrimaryKey.do',
		method : 'POST',
		async : false,
		params : params,
		success : function(v) {
			var rst = Ext.decode(v.responseText);
			modifyForm.getForm().setValues(rst.response);
			modifyForm.getForm().findField("areaCode").setRawValue(rst.response.areaName);
	
		},
		failure : function(response, opts) {
			button.enable();
			Ext.Msg.show({
				title : '系统提示',
				msg : response.statusText,
				icon : Ext.Msg.INFO,
				minWidth : 300,
				buttons : Ext.Msg.OK
			});
		}
	})
}

function t_remove(button) {
	var row = grid.getSelectionModel().getSelected();//
	if (!row) {
		Ext.Msg.show({
			title : '系统提示',
			msg : '请选择要操作的数据！',
			icon : Ext.Msg.INFO,
			minWidth : 300,
			buttons : Ext.Msg.OK
		});
		return;
	}
	Ext.MessageBox.confirm('请确认', '您确认删除' + row.get("companyName") + '吗？', function(btn) {
		if (btn == 'yes') {
			t_updateStauts(button,{companyId:row.get("companyId"),struts:'0'});
		}
	});
}
function t_updateStauts(button,params) {
	button.disable();
	Ext.Ajax.request({
				timeout : 10000000,
				url : contextPath
						+ '/company/updateCompanyStautsByPrimaryKey.do',
				method : 'POST',
				async : false,
				params : params,
				success : function(v) {
					button.enable();
					var rst = Ext.decode(v.responseText);
					Ext.Msg.show({
						title : '系统提示',
						msg : rst.errorMessage,
						icon : Ext.Msg.INFO,
						minWidth : 300,
						buttons : Ext.Msg.OK
					})
					if (rst.state) {
						t_reflash();
					}
				},
				failure : function(response, opts) {
					button.enable();
					Ext.Msg.show({
						title : '系统提示',
						msg : response.statusText,
						icon : Ext.Msg.INFO,
						minWidth : 300,
						buttons : Ext.Msg.OK
					});
				}
			})
}