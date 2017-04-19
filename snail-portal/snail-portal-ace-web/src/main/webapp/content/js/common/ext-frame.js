/**
 * 允许GRID表格鼠标选中复制
 */
if (!Ext.grid.GridView.prototype.templates) {
	Ext.grid.GridView.prototype.templates = {};
}
Ext.Ajax.timeout = 5 * 60 * 1000;
Ext.Panel.bodyStyle = "background-color:#ff0000;padding:10 0 10 10";
Ext.grid.GridView.prototype.templates.cell = new Ext.Template(
		'<td class="x-grid3-col x-grid3-cell x-grid3-td-{id} x-selectable {css}"  style="{style}" tabIndex="0" {cellAttr}>',
		'<div class="x-grid3-cell-inner x-grid3-col-{id}" {attr}>{value}</div>',
		'</td>');
Ext.lib.Ajax.getConnectionObject = function() {
	var activeX = [ 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP' ];
	function createXhrObject(transactionId) {
		var http;
		try {
			http = new XMLHttpRequest();
		} catch (e) {
			for ( var i = 0; i < activeX.length; ++i) {
				try {
					http = new ActiveXObject(activeX[i]);
					break;
				} catch (e) {
				}
			}
		} finally {
			return {
				conn : http,
				tId : transactionId
			};
		}
	}

	var o;
	try {
		if (o = createXhrObject(Ext.lib.Ajax.transactionId)) {
			Ext.lib.Ajax.transactionId++;
		}
	} catch (e) {
	} finally {
		return o;
	}
};
/** 处理退格键 */
Ext.getDoc().on(
		'keydown',
		function(e) {
			if (e.getKey() == 8 && e.getTarget().type == 'text'
					&& !e.getTarget().readOnly) {

			} else if (e.getKey() == 8 && e.getTarget().type == 'textarea'
					&& !e.getTarget().readOnly) {

			} else if (e.getKey() == 8) {
				e.preventDefault();
			}

		});

/**
 * 静态参数Store
 * 
 * @class Ext.data.StaticParamStore
 * @extends Ext.data.Store
 */
Ext.data.StaticParamStore = Ext.extend(Ext.data.Store, {
	proxy : new Ext.data.MemoryProxy([]),
	reader : new Ext.data.JsonReader({
		// root : 'list',
		id : 'code'
	// totalProperty : 'totalSize'
	}, [ {
		name : 'code',
		type : 'string',
		mapping : 'CODE'
	}, {
		name : 'name',
		type : 'string',
		mapping : 'NAME'
	} ])
});
// 修复ExtJS3.4中自动关闭下拉树的Bug
Ext.override(Ext.form.ComboBox, {
	onViewClick : function(doFocus) {
		var index = this.view.getSelectedIndexes()[0], s = this.store, r = s
				.getAt(index);
		if (r) {
			this.onSelect(r, index);
		} else if (s.getCount() === 0) {
			this.collapse();
		}
		if (doFocus !== false) {
			this.el.focus();
		}
	}
});
/**
 * 静态参数Combo
 * 
 * @param {}
 *            config
 */
var yearList = [];
var d = new Date();
var curYear = d.getFullYear();
var curDateStr = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDay();
for ( var i = curYear; i >= 2008; i--) {
	yearList.push([ i, i ]);
}
Ext.form.snailDateField = function(config) {
	this.limitDateField = null;
	this.limitType = null;
	Ext.apply(this, config);
	Ext.form.StaticDictCombo.superclass.constructor.call(this);
};
Ext.extend(Ext.form.snailDateField, Ext.form.DateField, {
	format : "Y-m-d",
	initComponent : function() {
		Ext.form.snailDateField.superclass.initComponent.call(this);
	}
});
Ext.form.StaticDictCombo = function(config) {
	this.editable = false;
	this.staticDictObjects = {};
	this.displayField = 'name';
	this.valueField = 'code';
	this.emptyText = '选择属性';
	this.dictKey = '';
	this.hiddenCodes = [];
	this.listWidth = config.width < 100 ? 100 : config.width;
	this.selectOnFocus = false;
	this.dictLoaded = false;
	this.typeAhead = false;
	this.defaultProps = null;
	this.allowBlank = true;
	this.mode = 'local';
	this.forceAll = false;
	this.store = new Ext.data.StaticParamStore({});
	this.triggerAction = 'all';
	Ext.apply(this, config);
	Ext.form.StaticDictCombo.superclass.constructor.call(this);
};
Ext
		.extend(
				Ext.form.StaticDictCombo,
				Ext.form.ComboBox,
				{
					initComponent : function() {
						Ext.form.StaticDictCombo.superclass.initComponent
								.call(this);
					},
					removeHiddenItem : function() {
						if (this.hiddenCodes) {
							for ( var i = 0; i < this.hiddenCodes.length; i++) {
								var r = this.store.getById(this.hiddenCodes[i]);
								this.store.remove(r);
							}
						}
					},
					listeners : {
						'afterrender' : function(combo) {
							if (!this.dictLoaded) {
								if (this.staticDictObjects
										&& this.staticDictObjects[this.dictKey]) {
									combo.store
											.loadData(this.staticDictObjects[this.dictKey]);
								}
								if (combo.allowBlank) {
									combo.store.loadData([ {
										code : '',
										name : '请选择',
										pcode : 'QXZ'
									} ], true);
								}
								this.removeHiddenItem();
								this.dictLoaded = true;
							}
							if (combo.allowBlank && !this.defaultProps) {
								combo.setValue('');
								combo.setRawValue('请选择');
							} else if (this.defaultProps) {
								combo.setValue(this.defaultProps.code);
								combo.setRawValue(this.defaultProps.name);
							} else if ((!combo.allowBlank)
									&& this.staticDictObjects[this.dictKey]
									&& this.staticDictObjects[this.dictKey].length > 0) {
								combo
										.setValue(this.staticDictObjects[this.dictKey][0].code);
								combo
										.setRawValue(this.staticDictObjects[this.dictKey][0].name);
							}
						},
						'specialkey' : function(textfield, event) {

						},
						'beforequery' : function(e) {
							var combo = e.combo;
							combo.collapse();
							if (!e.forceAll) {
								var value = e.query.toUpperCase();
								if (value) {
									combo.store.filterBy(function(record, id) {
										var flag = record.get('name').indexOf(
												value) >= 0;
										var text = record.get('pcode');
										if ((!flag) && text) {
											flag = text.indexOf(value) >= 0;
										}
										return flag;
									});
								} else {
									combo.store.clearFilter();
								}
								combo.onLoad();
								combo.expand();
								return false;
							}
						}
					}
				});
if ('function' !== typeof RegExp.escape) {
	RegExp.escape = function(s) {
		if ('string' !== typeof s) {
			return s;
		}
		return s.replace(/([.*+?^=!:${}()|[\]\/\\])/g, '\\$1');
	};
};
Ext.form.MultiStaticDictCombo = function(config) {
	this.editable = false;
	this.staticDictObjects = {};
	this.displayField = 'name';
	this.valueField = 'code';
	this.checkField = 'checked';
	this.emptyText = '选择属性';
	this.dictKey = '';
	this.hiddenCodes = [];
	this.listWidth = config.width < 100 ? 100 : config.width;
	this.selectOnFocus = false;
	this.dictLoaded = false;
	this.typeAhead = false;
	this.defaultProps = null;
	this.allowBlank = true;
	this.mode = 'local';
	this.forceAll = false;
	this.store = new Ext.data.StaticParamStore({});
	this.triggerAction = 'all';
	this.separator = ',';
	Ext.apply(this, config);
	Ext.form.StaticDictCombo.superclass.constructor.call(this);
};
Ext.extend(Ext.form.MultiStaticDictCombo, Ext.form.ComboBox, {
	initComponent : function() {
		this.tpl = '<tpl for=".">' + '<div class="x-combo-list-item">'
				+ '<img src="' + Ext.BLANK_IMAGE_URL + '" '
				+ 'class="ux-lovcombo-icon ux-lovcombo-icon-' + '{[values.'
				+ this.checkField + '?"checked":"unchecked"' + ']}">'
				+ '<div class="ux-lovcombo-item-text">{'
				+ (this.displayField || 'text') + '}</div>' + '</div>'
				+ '</tpl>';

		Ext.form.MultiStaticDictCombo.superclass.initComponent.call(this);
		if (this.staticDictObjects && this.staticDictObjects[this.dictKey]) {
			this.store.loadData(this.staticDictObjects[this.dictKey]);
		}

	},
	onSelect : function(record, index) {
		if (this.fireEvent('beforeselect', this, record, index) !== false) {
			record.set(this.checkField, !record.get(this.checkField));
			var value = this.getCheckedValue();
			this.setValue(value);
			this.fireEvent('select', this, record, index);
		}
	},
	getCheckedDisplay : function() {
		var re = new RegExp(this.separator, "g");
		return this.getCheckedValue(this.displayField).replace(re,
				this.separator + ' ');
	},
	getCheckedValue : function(field) {
		field = field || this.valueField;
		var c = [];
		var snapshot = this.store.snapshot || this.store.data;
		snapshot.each(function(r) {
			if (r.get(this.checkField)) {
				c.push(r.get(field));
			}
		}, this);
		return c.join(this.separator);
	},
	clearValue : function() {
		this.value = '';
		this.setRawValue(this.value);
		this.store.clearFilter();
		this.store.each(function(r) {
			r.set(this.checkField, false);
		}, this);
		if (this.hiddenField) {
			this.hiddenField.value = '';
		}
		this.applyEmptyText();
	},
	setValue : function(v) {
		if (v) {
			v = '' + v;
			if (v) {
				var valueArray = v.split(',');
				for ( var j = 0; j < valueArray.length; j++) {
					for ( var i = 0; i < this.store.getCount(); i++) {
						var record = this.store.getAt(i);
						if (record.get(this.valueField) == valueArray[j]) {
							record.set(this.checkField, true);
						}
					}
				}
			}
			if (this.valueField) {
				this.store.clearFilter();
				this.value = this.getCheckedValue();
				this.setRawValue(this.getCheckedDisplay());
				if (this.hiddenField) {
					this.hiddenField.value = this.value;
				}
			} else {
				this.value = v;
				this.setRawValue(v);
				if (this.hiddenField) {
					this.hiddenField.value = v;
				}
			}
			if (this.el) {
				this.el.removeClass(this.emptyClass);
			}
		} else {
			this.clearValue();
		}
	}
	/**
	 * Selects all items
	 */
	,
	selectAll : function() {
		this.store.each(function(record) {
			// toggle checked field
			record.set(this.checkField, true);
		}, this);

		this.doQuery(this.allQuery);
		this.setValue(this.getCheckedValue());
	}
	/**
	 * Deselects all items. Synonym for clearValue
	 */
	,
	deselectAll : function() {
		this.clearValue();
	}
});
function rendererStaticDict(value, dictKey, staticDictObjects) {
	try {
		if (!staticDictObjects) {
			staticDictObjects = parent.staticDictObject;
		}
		var name = value;
		if ((value + "") && ("" + value).indexOf(',') < 0) {
			if (staticDictObjects && dictKey && staticDictObjects[dictKey]) {
				for ( var i = 0; i < staticDictObjects[dictKey].length; i++) {
					if (staticDictObjects[dictKey][i].CODE == value) {
						name = staticDictObjects[dictKey][i].NAME;
						break;
					}
				}
			}
		} else {
			if (value) {
				var nameArray = [];
				var v = (value + "").split(',');
				for ( var j = 0; j < v.length; j++) {
					for ( var i = 0; i < staticDictObjects[dictKey].length; i++) {
						if (staticDictObjects[dictKey][i].CODE == v[j]) {
							nameArray.push(staticDictObjects[dictKey][i].NAME);
							break;
						}
					}
				}
				name = nameArray.join(',');
			}
		}
	} catch (err) {
		Ext.Msg.alert("渲染错误", value + ":" + dictKey + ":" + err);
	}
	return name;
}
function redererDateTime(value) {
	return Ext.util.Format.date(value, 'Y-m-d');
}
function doRequestToServer(obj) {
	var limitTime = obj.timeout ? obj.timeout : 30000;
	if (!obj.params) {
		obj.params = {};
	}
	Ext.Ajax.request({
		timeout : limitTime,
		url : obj.url,
		method : obj.method,
		async : obj.async,
		params : obj.params,
		success : obj.callback,
		failure : obj.failurecallback
	});
}
function forEach(obj, callback) {
	if (obj && callback) {
		for ( var prop in obj) {
			if (typeof (obj[prop] != 'function')) {
				// alert(obj[prop]);
				callback(prop, obj[prop]);
			}
		}
	}
};
var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
function validateEmail(addr) {
	return this.email.test(addr);
};
function getLocalFileSize(input) {
	var size = null;
	if (Ext.isIE) {
		var fso = new ActiveXObject('Scripting.FileSystemObject');
		var file = fso.GetFile(files[0].getValue());
		size = file.size;
	} else {
		var file = document.getElementById(input.getFileInputId());
		if (file) {
			size = file.files[0].fileSize;
		}
	}
	return size;
};
Ext.snailPagingToolbar = function(config) {
	this.ownGrid = null;
	this.loadMask = null;
	this.pageSizeList = [];
	this.displayInfo = true;
	Ext.apply(this, config);
	Ext.snailPagingToolbar.superclass.constructor.call(this);
};
Ext.extend(Ext.snailPagingToolbar, Ext.PagingToolbar,
		{
			initComponent : function() {
				if (this.pageSizeList.length == 0) {
					for ( var i = 25; i <= 100; i += 5) {
						this.pageSizeList.push([ i, i ]);
					}
				}
				var toolbar = this;
				this.displayInfo = true;
				this.beforePageText = "第";
				this.afterPageText = "页 共 {0} 页";
				this.displayMsg = '第 {0} 条到 {1} 条记录 共 {2} 条';
				this.emptyMsg = "没有记录";
				if (!this.items) {
					this.items = [ '-', '每页', {
						xtype : 'combo',
						width : 40,
						value : toolbar.pageSize,
						allowBlank : false,
						editable : false,
						triggerAction : 'all',
						displayField : 'pagetext',
						mode : 'local',
						valueField : 'pagesize',
						store : new Ext.data.SimpleStore({
							fields : [ 'pagesize', 'pagetext' ],
							data : toolbar.pageSizeList
						}),
						
						enableKeyEvents : true,
						listeners : {
							'select' : function(e) {
								toolbar.pageSize = parseInt(this.value);
							}
						}
					}, '条', '-', {
						pressed : false,
						enableToggle : true,
						xtype : 'button',
						text : '导出Excel',
						iconCls : 'Table',
						handler : function() {
							if (toolbar.ownGrid) {
								if (toolbar.ownGrid.store.getCount() > 0) {
									toolbar.exportExcelData();
								} else {
									Ext.Msg.alert("提示", "没有任何数据需要导出");
								}
							} else {
								Ext.Msg.alert("提示", "程序开发错误，表格属性未设置");
							}
						}
					} ];
				} else {
					this.items.splice(0, 0, '-', '每页:', {
						xtype : 'numberfield',
						width : 30,
						disabled : false,
						allowDecimals : false,
						maxValue : 100,
						emptyText : toolbar.pageSize,
						enableKeyEvents : true,
						listeners : {
							'change' : function(e) {
								if (this.value > 0) {
									toolbar.pageSize = parseInt(this.value);
									if (toolbar.pageSize > this.maxValue) {
										this.setValue(this.maxValue);
										this.value = this.maxValue;
										toolbar.pageSize = this.maxValue;
									}
								} else {
									this.value = toolbar.pageSize;
								}
							}
						}
					}, '条', '-', {
						pressed : false,
						enableToggle : true,
						xtype : 'button',
						iconCls : 'bexcel',
						handler : function() {
							if (toolbar.ownGrid) {
								if (toolbar.ownGrid.store.getCount() > 0) {
									toolbar.exportExcelData();
								} else {
									Ext.Msg.alert("提示", "没有任何数据需要导出");
								}
							} else {
								Ext.Msg.alert("提示", "程序开发错误，表格属性未设置");
							}
						}
					});
				}
				Ext.snailPagingToolbar.superclass.initComponent.call(this);
			},
			exportExcelData : function() {
				if (!this.loadMask) {
					this.loadMask = new Ext.LoadMask(Ext.getBody(), {
						msg : "数据导出中，请稍候......"
					});
				}
				this.loadMask.show();
				var xls = null;
				try {
					xls = new ActiveXObject("Excel.Application");
				} catch (e) {
					Ext.Msg.alert("提示",
							"要导出该表，您必须安装Excel电子表格软件，同时浏览器须使用“ActiveX 控件”!");
					this.loadMask.hide();
					return;
				}
				xls.visible = false; // 设置excel为不可见
				var xlBook = xls.Workbooks.Add;
				var xlSheet = xlBook.Worksheets(1);
				try {

					var cm = this.ownGrid.getColumnModel();
					var colCount = cm.getColumnCount();
					var temp_obj = [];
					// 只下载没有隐藏的列(isHidden()为true表示隐藏,其他都为显示)
					// 临时数组,存放所有当前显示列的下标
					for ( var i = 0; i < colCount; i++) {
						if (cm.isHidden(i) == true) {
						} else {
							temp_obj.push(i);
						}
					}
					for ( var i = 1; i <= temp_obj.length; i++) {
						// 显示列的列标题
						var title = cm.getColumnHeader(temp_obj[i - 1]);
						if (title.indexOf('<div') != -1) {
							title = '';
						}
						xlSheet.Cells(1, i).Value = title;
						xlSheet.Cells(1, i).HorizontalAlignment = 3;
						xlSheet.Cells(1, i).Font.Bold = true;
						xlSheet.Cells(1, i).Interior.ColorIndex = 5;
						xlSheet.Cells(1, i).Font.ColorIndex = 2;
						xlSheet.Cells(1, i).Borders.LineStyle = 1;
					}
					var store = this.ownGrid.getStore();
					var recordCount = store.getCount();
					var view = this.ownGrid.getView();
					for (i = 1; i <= recordCount; i++) {
						for ( var j = 1; j <= temp_obj.length; j++) {
							// EXCEL数据从第二行开始,故row = i + 1;
							xlSheet.Cells(i + 1, j).NumberFormatLocal = "@";
							xlSheet.Cells(i + 1, j).Font.Name = '宋体';//
							xlSheet.Cells(i + 1, j).Font.Size = 10;// 字体大小
							xlSheet.Cells(i + 1, j).HorizontalAlignment = 2;
							xlSheet.Cells(i + 1, j).Borders.LineStyle = 1;
							xlSheet.Cells(i + 1, j).Value = view.getCell(i - 1,
									temp_obj[j - 1]).innerText;
						}
					}
					xlSheet.Rows(1 + ":" + recordCount).RowHeight = 18;
				} catch (e) {
					Ext.Msg.alert("提示", "导出数据失败，错误信息：" + e);
				}
				this.loadMask.hide();
				Ext.Msg.alert("提示", "当页数据已导出到Excel中");
				xls.visible = true;
				xlSheet.Columns.AutoFit;
				xls.ActiveWindow.Zoom = 100;
				xls.UserControl = true; // 很重要,不能省略,不然会出问题 意思是excel交由用户控制
				xls = null;
				xlBook = null;
				xlSheet = null;
			}
		});
Ext.form.AssociationCombo = function(config) {
	this.recordType = null;
	this.loadDataUrl = null;
	this.selectOnFocus = true;
	this.store = null;
	this.pageSize = 20;
	this.enableKeyEvents = true;
	this.displayField = null;
	this.displayField = 'name';
	this.valueField = 'code';
	this.minChars = 0;
	this.loadingText = '正在查询';
	this.queryParam = 'pcode';
	this.areaCode = null;
	this.queryDelay = 300;
	this.listWidth = 300;
	this.usingLevel = null;
	this.itemSelector = 'div.bigdiv';
	this.typeAhead = false;
	this.listTpl = null;
	this.rowTpl = null;
	this.selected = true;
	this.onSelect = Ext.emptyFn();
	this.hideTrigger = false;
	Ext.apply(this, config);
	Ext.form.AssociationCombo.superclass.constructor.call(this);
};
Ext.extend(Ext.form.AssociationCombo, Ext.form.ComboBox, {
	initComponent : function() {
		Ext.form.AssociationCombo.superclass.initComponent.call(this);
	}
});

/**
 * 疾病联想输入法 返回结果级格式：{totalSize:10,valueResult:[{code:'code',name:'name'}]}
 * 
 * @class Ext.form.DiseaseAssociationCombo
 * @extends Ext.form.ComboBox
 */
Ext.form.DiseaseAssociationCombo = function(config) {
	this.recordType = null;
	this.loadDataUrl = null;
	this.store = null;
	this.pageSize = 20;
	this.minChars = 0;
	this.selectOnFocus = true;
	this.displayField = null;
	this.loadingText = '正在查询';
	this.queryParam = 'pcode';
	this.displayField = 'name';
	this.valueField = 'code';
	this.queryDelay = 300;
	this.listWidth = 300;
	this.itemSelector = 'div.bigdiv';
	this.enableKeyEvents = true;
	this.typeAhead = false;
	this.listTpl = null;
	this.rowTpl = null;
	this.onSelect = Ext.emptyFn();
	this.selected = true;
	this.hideTrigger = false;
	this.year = null;
	this.areaCode = null;
	this.hospitalLevel = null;
	this.state = null;
	this.hospitalNo = null;
	Ext.apply(this, config);
	Ext.form.DiseaseAssociationCombo.superclass.constructor.call(this);
};
Ext.extend(
				Ext.form.DiseaseAssociationCombo,
				Ext.form.ComboBox,
				{
					oldValue : '',
					oldRawValue : '',
					initComponent : function() {
						this.onSelect = function(record, index) {
							this.oldValue = record.id;
							this.oldRawValue = record.get('name');
							this.setValue(record.id);
							this.setRawValue(record.get('name'));
							this.collapse();
							this.selected = true;
							this.fireEvent('select', this, record, index);
						};
						var combo = this;
						if (!combo.recordType) {
							combo.recordType = [ {
								name : 'code',
								type : 'string',
								mapping : 'code'
							}, {
								name : 'name',
								type : 'string',
								mapping : 'name'
							}, {
								name : 'alias',
								type : 'string',
								mapping : 'alias'
							} ];
						}
						;
						if (!combo.store) {
							combo.store = new Ext.data.Store({
								proxy : new Ext.data.snailHttpProxy({
									url : this.loadDataUrl
								}),
								reader : new Ext.data.JsonReader({
									root : 'valueResult',
									totalProperty : 'totalSize',
									id : 'code'
								}, combo.recordType)
							});
						}
						combo.store.on('beforeload', function(store) {
							var pcode = combo.getRawValue().toUpperCase();
							if (pcode == combo.oldRawValue) {
								pcode = '';
							}
							store.baseParams = {
								pcode : pcode,
								limit : combo.pageSize,
								totalRecord : store.getTotalCount(),
								year : combo.year,
								areaCode : combo.areaCode,
								hospitalLevel : combo.hospitalLevel,
								hospitalNo : combo.hospitalNo,
								state : combo.state
							};
						});
						if (!combo.rowTpl) {
							combo.rowTpl = '<div class="classdiva"  style="width:30%">{code}</div>'
									+ '<div class="classdiva"  style="width:65%">{name}</div>';
							// + '<div class="classdiva"
							// style="width:35%">{alias}</div>';
						}
						if (!combo.tpl) {
							combo.tpl = new Ext.XTemplate(
									'<tpl for="."><div class="bigdiv">',
									combo.rowTpl, '</div></tpl>');
						}
						Ext.form.DiseaseAssociationCombo.superclass.initComponent
								.call(this);
					},
					listeners : {
						'focus' : function(combo) {
							if (combo.getValue()) {
								combo.oldValue = combo.getValue();
								combo.oldRawValue = combo.getRawValue();
								combo.selected = true;
							}
						},
						'blur' : function(combo) {
							if (combo.getRawValue() != ''
									&& combo.oldValue != '') {
								combo.setValue(combo.oldValue);
								combo.setRawValue(combo.oldRawValue);
								combo.selected = true;
							} else if (combo.getRawValue() == '') {
								combo.setValue('');
								combo.setRawValue('');
								combo.selected = false;
								combo.oldValue = '';
								combo.oldRawValue = '';
							} else if (combo.oldValue == '') {
								if (combo.getValue() == combo.getRawValue()) {
									combo.setValue('');
									combo.setRawValue('');
									combo.oldValue = '';
									combo.oldRawValue = '';
									combo.selected = false;
								}
							}
						},
						'keypress' : function(combo, event) {
							if (event.getKey() != 13) {
								combo.selected = false;
							}
						},
						'select' : function(combo, record, index) {
							combo.selected = true;
						}
					}
				});
/**
 * 科室联想输入法
 * 返回结果级格式：{totalSize:10,valueResult:[{sectionCode:'code',sectionName:'name'}]}
 * 
 * @class Ext.form.ItemAssociationCombo
 * @extends Ext.form.AssociationCombo
 */
Ext.form.SectionAssociationCombo = Ext
		.extend(
				Ext.form.AssociationCombo,
				{
					minChars : 0,
					oldValue : '',
					oldRawValue : '',
					initComponent : function() {
						this.onSelect = function(record, index) {
							this.oldValue = record.id;
							this.oldRawValue = record.get('name');
							this.setValue(record.id);
							this.setRawValue(record.get('name'));
							this.collapse();
							this.selected = true;
							this.fireEvent('select', this, record, index);
						};
						var combo = this;
						if (!combo.recordType) {
							combo.recordType = [ {
								name : 'code',
								type : 'string',
								mapping : 'code'
							}, {
								name : 'name',
								type : 'string',
								mapping : 'name'
							} ];
						}
						if (!combo.store) {
							combo.store = new Ext.data.Store({
								proxy : new Ext.data.snailHttpProxy({
									url : this.loadDataUrl
								}),
								reader : new Ext.data.JsonReader({
									root : 'valueResult',
									totalProperty : 'totalSize',
									id : 'code'
								}, combo.recordType)
							});
						}
						combo.store.on('beforeload', function(store) {
							var pcode = combo.getRawValue().toUpperCase();
							if (pcode == combo.oldRawValue) {
								pcode = '';
							}
							store.baseParams = {
								totalRecord : store.getTotalCount(),
								areaCode : combo.areaCode,
								pcode : pcode
							};
						});
						if (!combo.rowTpl) {
							combo.rowTpl = '<div class="classdiva"  style="width:25%">{code}</div>'
									+ '<div class="classdiva"  style="width:65%">{name}</div>';
						}
						if (!combo.tpl) {
							combo.tpl = new Ext.XTemplate(
									'<tpl for="."><div class="bigdiv">',
									combo.rowTpl, '</div></tpl>');
						}
						Ext.form.SectionAssociationCombo.superclass.initComponent
								.call(this);
					},
					listeners : {
						'focus' : function(combo) {
							if (combo.getValue()) {
								combo.oldValue = combo.getValue();
								combo.oldRawValue = combo.getRawValue();
							}
						},
						'blur' : function(combo) {
							if (combo.getRawValue() != ''
									&& combo.oldValue != '') {
								combo.setValue(combo.oldValue);
								combo.setRawValue(combo.oldRawValue);
								combo.selected = true;
							} else if (combo.getRawValue() == '') {
								combo.setValue('');
								combo.setRawValue('');
								combo.oldValue = '';
								combo.oldRawValue = '';
								combo.selected = false;
							} else if (combo.oldValue == '') {
								if (combo.getValue() == combo.getRawValue()) {
									combo.setValue('');
									combo.setRawValue('');
									combo.oldValue = '';
									combo.oldRawValue = '';
									combo.selected = false;
								}
							}
						},
						'keypress' : function(combo, event) {
							if (event.getKey() != 13) {
								combo.selected = false;
							}
						},
						'select' : function(combo, record, index) {
							combo.selected = true;
						}
					}
				});
/**
 * 诊疗项目联想输入法，需要复写onSection方法以实现特有动作。
 * 返回结果集格式：{totalSize:10,valueResult:[{sectionCode:'code',sectionName:'name'}]}
 * 
 * @class Ext.form.ItemAssociationCombo
 * @extends Ext.form.AssociationCombo
 */
Ext.form.ItemAssociationCombo = Ext
		.extend(
				Ext.form.AssociationCombo,
				{
					initComponent : function() {
						this.listWidth = 500;
						this.minChars = 2;
						if (!this.onSelect) {
							this.onSelect = function(record, index) {
								this.setValue(record.id);
								this.setRawValue(record.get('medicineName'));
								this.collapse();
								this.fireEvent('select', this, record, index);
							};
						}
						;
						var combo = this;
						if (!combo.recordType) {
							combo.recordType = [ {
								name : 'ids',
								type : 'string',
								mapping : 'ids'
							}, {
								name : 'medicineName',
								type : 'string',
								mapping : 'medicineName'
							}, {
								name : 'medicineCode',
								type : 'string',
								mapping : 'medicineCode'
							}, {
								name : 'medicineClass',
								type : 'string',
								mapping : 'medicineClass'
							}, {
								name : 'medicineType',
								type : 'string',
								mapping : 'medicineType'
							}, {
								name : 'specification',
								type : 'string',
								mapping : 'specification'
							}, {
								name : 'dosageForm',
								type : 'string',
								mapping : 'dosageForm'
							}, {
								name : 'unit',
								type : 'string',
								mapping : 'unit'
							}, {
								name : 'costFee',
								type : 'float',
								mapping : 'costFee'
							}, {
								name : 'scale',
								type : 'float',
								mapping : 'scale'
							}, {
								name : 'usingLevel',
								type : 'string',
								mapping : 'usingLevel'
							}, {
								name : 'isWriteOff',
								type : 'bool',
								mapping : 'isWriteOff'
							} ];
						}
						if (!combo.store) {
							combo.store = new Ext.data.Store({
								proxy : new Ext.data.snailHttpProxy({
									url : this.loadDataUrl
								}),
								reader : new Ext.data.JsonReader({
									root : 'valueResult',
									totalProperty : 'totalSize',
									id : 'ids'
								}, combo.recordType)
							});
						}
						combo.store.on('beforeload', function(store) {
							if (hospitalLevel) {
								store.baseParams = {
									areaCode : combo.areaCode,
									pcode : combo.getRawValue().toUpperCase(),
									usingLevel : hospitalLevel,
									totalRecord : store.getTotalCount()
								};
							} else {
								store.baseParams = {
									areaCode : combo.areaCode,
									pcode : combo.getRawValue().toUpperCase(),
									usingLevel : combo.usingLevel,
									totalRecord : store.getTotalCount()
								};
							}

						});
						if (!combo.rowTpl) {
							combo.rowTpl = '<div class="classdiva"  style="width:40%">{medicineName}</div>'
									+ '<div class="classdiva"  style="width:7%">{medicineClass:this.rendererMediCls}</div>'
									+ '<div class="classdiva"  style="width:7%">{isWriteOff:this.rendererIsWriteOff}</div>'
									+ '<div class="classdiva"  style="width:5%">{scale:this.rendererScale}</div>'
									+ '<div class="classdiva"  style="width:5%">{usingLevel:this.rendererLevel}</div>'
									+ '<div class="classdiva"  style="width:11%">{specification:this.renderOthers}</div>'
									+ '<div class="classdiva"  style="width:10%">{unit:this.renderOthers}</div>'
									+ '<div class="classdiva"  style="width:11%">{dosageForm:this.renderOthers}</div>';
						}
						if (!combo.tpl) {
							combo.tpl = new Ext.XTemplate(
									'<tpl for="."><div class="bigdiv">',
									combo.rowTpl, '</div></tpl>');
						}
						;
						combo.tpl.rendererLevel = function(level) {
							if (level == 0) {
								return '通用';
							}
							return rendererStaticDict(level, 'S201_06');
						};
						combo.tpl.rendererMediCls = function(mediCls) {
							return rendererStaticDict(mediCls,
									'NC_MEDI_CATEGORY');
						};
						combo.tpl.redererMediType = function(mediType) {
							return rendererStaticDict(mediType,
									'NC_MEDICINE_TYPE');
						};
						combo.tpl.rendererIsWriteOff = function(writeOff) {
							if (writeOff) {
								return "保内";
							} else {
								return "保外";
							}
						};
						combo.tpl.rendererScale = function(scale) {
							return scale;
						};
						combo.tpl.renderOthers = function(value) {
							if (value) {
								return value;
							} else {
								return "&nbsp;";
							}
						};
						Ext.form.ItemAssociationCombo.superclass.initComponent
								.call(this);
					},
					listeners : {
						'specialkey' : function(combo, event) {
							if (event.getKey() == 8 && combo.selected) {
								combo.setValue('');
								combo.setRawValue('');
								event.stopEvent();
								event.stopPropagation();
								combo.selected = false;
							}
						},
						'focus' : function(combo) {
							if (combo.getValue() != combo.getRawValue()) {
								combo.selected = true;
							} else {
								combo.selected = false;
							}
						},
						'keypress' : function(combo, event) {
							if (event.getKey() != 13) {
								combo.selected = false;
							}
						},
						'select' : function(combo, record, index) {
							combo.selected = true;
						}
					}
				});
Ext.form.CommonAssociationCombo = Ext.extend(Ext.form.AssociationCombo, {
	minChars : 0,
	oldValue : '',
	oldRawValue : '',
	initComponent : function() {
		this.onSelect = function(record, index) {
			this.fireEvent('select', this, record, index);
			this.oldValue = record.id;
			this.oldRawValue = record.get(combo.displayField);
			this.setValue(record.id);
			this.setRawValue(record.get(combo.displayField));
			this.collapse();
			this.selected = true;
		};
		var combo = this;
		if (!combo.recordType) {
			combo.recordType = [ {
				name : combo.valueField,
				type : 'string'
			}, {
				name : combo.displayField,
				type : 'string'
			} ];
		}
		if (!combo.store) {
			combo.store = new Ext.data.Store({
				proxy : new Ext.data.snailHttpProxy({
					url : this.loadDataUrl
				}),
				reader : new Ext.data.JsonReader({
					root : 'valueResult',
					totalProperty : 'totalSize',
					id : combo.valueField
				}, combo.recordType)
			});
		}
		combo.store.on('beforeload', function(store) {
			var pcode = combo.getRawValue().toUpperCase();
			if (pcode == combo.oldRawValue) {
				pcode = '';
			}
			var baseParam = {
				totalRecord : store.getTotalCount(),
				areaCode : combo.areaCode
			};
			baseParam[combo.queryParam] = pcode;
			if (combo.params) {
				var ss = combo.params.split('&');
				for ( var i = 0; i < ss.length; i++) {
					var s = ss[i].split('=');
					baseParam[s[0]] = s[1];
				}
			}
			if (combo.paramObj) {
				baseParam['notItemType'] = Ext.getCmp(combo.paramObj)
						.getValue();
			}
			store.baseParams = baseParam;
		});
		if (!combo.rowTpl) {
			combo.rowTpl = '<div class="classdiva"  style="width:20%">{'
					+ combo.valueField + '}</div>'
					+ '<div class="classdiva"  style="width:70%">{'
					+ combo.displayField + '}</div>';
		}
		if (!combo.tpl) {
			combo.tpl = new Ext.XTemplate('<tpl for="."><div class="bigdiv">',
					combo.rowTpl, '</div></tpl>');
		}
		Ext.form.CommonAssociationCombo.superclass.initComponent.call(this);
	},
	listeners : {
		'focus' : function(combo) {
			if (combo.getValue()) {
				combo.oldValue = combo.getValue();
				combo.oldRawValue = combo.getRawValue();
				combo.selected = true;
			} else {
				combo.selected = false;
			}
		},
		'blur' : function(combo) {
			if (combo.getRawValue() != '' && combo.oldValue != '') {
				combo.setValue(combo.oldValue);
				combo.setRawValue(combo.oldRawValue);
				combo.selected = true;
			} else if (combo.getRawValue() == '') {
				combo.setValue('');
				combo.setRawValue('');
				combo.oldValue = '';
				combo.oldRawValue = '';
				combo.selected = false;
			} else if (combo.oldValue == '') {
				if (combo.getValue() == combo.getRawValue()) {
					combo.setValue('');
					combo.setRawValue('');
					combo.oldValue = '';
					combo.oldRawValue = '';
					combo.selected = false;
				}
			}
		},
		'keypress' : function(combo, event) {
			if (event.getKey() != 13) {
				combo.selected = false;
			}
		},
		'select' : function(combo, record, index) {
			combo.selected = true;
		}
	}
});
Ext.form.CalcItemAssociationCombo = Ext
		.extend(
				Ext.form.AssociationCombo,
				{
					minChars : 0,
					oldValue : '',
					oldRawValue : '',
					initComponent : function() {
						this.onSelect = function(record, index) {
							this.fireEvent('select', this, record, index);
							this.oldValue = record.id;
							this.oldRawValue = record.get(combo.displayField);
							this.setValue(record.id);
							this.setRawValue(record.get(combo.displayField));
							this.collapse();
							this.selected = true;
						};
						var combo = this;
						if (!combo.recordType) {
							combo.recordType = [ {
								name : combo.valueField,
								type : 'string'
							}, {
								name : combo.displayField,
								type : 'string'
							}, {
								name : 'itemType',
								type : 'string'
							}, {
								name : 'memo',
								type : 'string'
							} ];
						}
						if (!combo.store) {
							combo.store = new Ext.data.Store({
								proxy : new Ext.data.snailHttpProxy({
									url : this.loadDataUrl
								}),
								reader : new Ext.data.JsonReader({
									root : 'valueResult',
									totalProperty : 'totalSize',
									id : combo.valueField
								}, combo.recordType)
							});
						}
						combo.store.on('beforeload', function(store) {
							var pcode = combo.getRawValue().toUpperCase();
							if (pcode == combo.oldRawValue) {
								pcode = '';
							}
							var baseParam = {
								totalRecord : store.getTotalCount(),
								areaCode : combo.areaCode
							};
							baseParam[combo.queryParam] = pcode;
							if (combo.params) {
								var ss = combo.params.split('&');
								for ( var i = 0; i < ss.length; i++) {
									var s = ss[i].split('=');
									baseParam[s[0]] = s[1];
								}
							}
							if (combo.paramObj) {
								baseParam['notItemType'] = Ext.getCmp(
										combo.paramObj).getValue();
							}
							store.baseParams = baseParam;
						});
						if (!combo.rowTpl) {
							combo.rowTpl = '<div class="classdiva" title="{memo}" style="width:65%">{'
									+ combo.displayField
									+ '}</div>'
									+ '<div class="classdiva"  style="width:30%">{itemType:this.renderItemType}</div>';
						}
						if (!combo.tpl) {
							combo.tpl = new Ext.XTemplate(
									'<tpl for="."><div class="bigdiv">',
									combo.rowTpl, '</div></tpl>');
						}
						combo.tpl.renderItemType = function(value) {
							return rendererStaticDict(value, 'S101_99_25');
						};
						Ext.form.CalcItemAssociationCombo.superclass.initComponent
								.call(this);
					},
					listeners : {
						'focus' : function(combo) {
							if (combo.getValue()) {
								combo.oldValue = combo.getValue();
								combo.oldRawValue = combo.getRawValue();
								combo.selected = true;
							} else {
								combo.selected = false;
							}
						},
						'blur' : function(combo) {
							if (combo.getRawValue() != ''
									&& combo.oldValue != '') {
								combo.setValue(combo.oldValue);
								combo.setRawValue(combo.oldRawValue);
								combo.selected = true;
							} else if (combo.getRawValue() == '') {
								combo.setValue('');
								combo.setRawValue('');
								combo.oldValue = '';
								combo.oldRawValue = '';
								combo.selected = false;
							} else if (combo.oldValue == '') {
								if (combo.getValue() == combo.getRawValue()) {
									combo.setValue('');
									combo.setRawValue('');
									combo.oldValue = '';
									combo.oldRawValue = '';
									combo.selected = false;
								}
							}
						},
						'keypress' : function(combo, event) {
							if (event.getKey() != 13) {
								combo.selected = false;
							}
						},
						'select' : function(combo, record, index) {
							combo.selected = true;
						}
					}
				});
/**
 * 行政区划选择项
 */
Ext.form.AreaCodeCombo = function(config) {
	this.loadDataUrl = '';
	this.width = 180;
	this.listWidth = 300;
	this.fieldLabel = '所属地区';
	this.blankText = '选择地区';
	this.mode = 'local';
	this.areaCodeTreeNodeId = '';
	this.triggerAction = 'all';
	this.maxHeight = 300;
	this.selectOnFocus = true;
	this.displayField = 'areaName';
	this.valueField = 'areaCode';
	this.directRedeemArea = [];// 已签订的直补协议地区
	this.maxAreaCodeLength = 12;
	this.editable = false;
	this.selectedClass = '';
	this.onSelect = Ext.emptyFn;
	this.areaTreeRootNode = null;
	this.otherValue = null;
	this.defaultProps = null;
	this.areaTree = null;
	this.treeHtmlNode = null;
	this.tooltip = {
		title : '当前地区',
		text : ''
	};
	this.tooltipObj = null;
	Ext.apply(this, config);
	Ext.form.AreaCodeCombo.superclass.constructor.call(this);
};
Ext
		.extend(
				Ext.form.AreaCodeCombo,
				Ext.form.ComboBox,
				{
					loadTreeNode : function(node) {
						var areaCodeCombo = this;
						var areaCode = node.id;
						if (areaCode == '0') {
							areaCode = this.getValue();
							if (!areaCode) {
								areaCode = areaCodeCombo.defaultProps.areaCode;
							}
						}
						if (node.firstChild.text == 'loading') {
							if (parent.staticAreaData
									&& parent.staticAreaData
											.hasOwnProperty(areaCode)) {// 如果已经存在此节点
								this.appendTreeSubNode(node,
										parent.staticAreaData[areaCode]);
								node.firstChild.remove();// 删除当前节点第一个孩子节点(loading节点)
							} else {
								Ext.Ajax
										.request({
											url : areaCodeCombo.loadDataUrl,
											params : {
												'areaCode' : areaCode
											},
											method : 'GET',
											success : function(v) { // 成功返回
												var areaResult = Ext.decode("("
														+ v.responseText + ")"); // 得到服务器返回的json串，并用json.js解析过对象数组
												if (areaResult.result) {
													var list = areaResult.valueResult;
													parent.staticAreaData[areaCode] = list;
													areaCodeCombo
															.appendTreeSubNode(
																	node, list);
													node.firstChild.remove();// 删除当前节点第一个孩子节点(loading节点)
												} else {
													Ext.Msg.alert("失败提示",
															areaResult.message);
												}
											},
											failure : function() {
												Ext.Msg
														.alert("出错信息",
																"加载树菜单失败");
											}
										// 失败
										});
							}
						}
					},
					appendTreeSubNode : function(node, list) {
						var areaCodeCombo = this;
						for ( var i = 0; i < list.length; i++) {
							var cnode = new Ext.tree.AsyncTreeNode(
									{
										id : list[i].areaCode,// id 为服务器返回id
																// 父节点id
										text : list[i].areaName,// 显示内容为服务器返回id
										// 父节点id
										otherValue : list[i],
										leaf : ((list[i].areaCode.length == 12) || (list[i].areaCode.length == areaCodeCombo.maxAreaCodeLength)) ? true
												: false,
										// false,
										children : [ {// 添加子节点,如果服务器返回tl[i].leaf为ture则孩子节点将无法显示
											text : 'loading',
											iconCls : 'loading',
											leaf : true
										} ]
									// 是否为叶子节点,根据服务器返回内容决定是否为叶子节点
									});
							cnode.on('expand', areaCodeCombo.loadTreeNode,
									areaCodeCombo);// 定义当前节点展开时调用gettree方法,再次异步读取子节点
							node.appendChild(cnode);// 将当前节点添加为待展开节点子节点
						}
					},
					getValue : function() {
						var value = Ext.form.AreaCodeCombo.superclass
								.getValue();
						if ((!value) && this.otherValue) {
							value = this.otherValue.areaCode;
						}
						return value;
					},
					getAreaTreeNodeId : function() {
						var value = new Date().getTime();
						value = value + "_" + (Math.random() * 10000);
						return value;
					},
					initComponent : function() {
						this.areaCodeTreeNodeId = this.getAreaTreeNodeId();
						this.tpl = "<tpl for='.'><div style='height:300px'><div id='"
								+ this.areaCodeTreeNodeId
								+ "'></div></div></tpl>";
						Ext.form.AreaCodeCombo.superclass.initComponent
								.call(this);

					},
					store : new Ext.data.SimpleStore({
						fields : [ 'areaCode', 'areaName' ],
						data : [ [] ]
					}),
					listeners : {
						'expand' : function() {
							var combo = this;
							if (document
									.getElementById(this.areaCodeTreeNodeId).innerHTML == '') {
								this.areaTreeRootNode = new Ext.tree.AsyncTreeNode(
										{
											text : '行政区划'
										});
								this.areaTree = new Ext.tree.TreePanel({
									loader : new Ext.tree.TreeLoader(),
									border : false,
									rootVisible : false,
									root : this.areaTreeRootNode
								});
								this.areaTree
										.on(
												'click',
												function(node, e) {
													combo
															.setValue(node.attributes.otherValue.areaCode);
													combo
															.setRawValue(node.attributes.otherValue.fullAreaName);
													combo.tooltipObj.destroy();
													combo.tooltipObj = new Ext.ToolTip(
															{
																target : combo.id,
																trackMouse : false,
																draggable : false,
																maxWidth : 200,
																minWidth : 100,
																html : '<font  size="2px">'
																		+ node.attributes.otherValue.fullAreaName
																		+ '</font>'
															});
													combo.otherValue = node.attributes.otherValue;
													combo.collapse();
													e.stopEvent();
													combo.fireEvent('select',
															combo, node);
												});
								this.areaTreeRootNode.on('expand',
										this.loadTreeNode, this);// 再次异步读取子节点
								this.areaTree.on('expand', this.loadTreeNode,
										this);// 再次异步读取子节点
								this.areaTree.on('beforeexpandnode', function(
										node, deep, anim) {
									var nextNode = node.nextSibling;
									while (nextNode) {
										nextNode.collapse();
										nextNode = nextNode.nextSibling;
									}
									var preNode = node.previousSibling;
									while (preNode) {
										preNode.collapse();
										preNode = preNode.previousSibling;
									}
								});
								this.areaTree.render(this.areaCodeTreeNodeId);
								if (this.defaultProps.areaCode.length >= 6
										|| (!this.directRedeemArea.length > 0)) {
									var cnode = new Ext.tree.AsyncTreeNode({
										text : this.defaultProps.areaName,
										id : this.defaultProps.areaCode,
										otherValue : this.defaultProps,
										children : [ {// 子节点
											text : 'loading',// 显示文字为loading
											iconCls : 'loading',// 使用图标为loading
											leaf : true
										} ]
									});
									cnode.on('expand', combo.loadTreeNode,
											combo);// 定义当前节点展开时调用gettree方法,再次异步读取子节点
									this.areaTreeRootNode.appendChild(cnode);
									cnode.expand();
								}
								if (this.directRedeemArea
										&& this.directRedeemArea.length > 0) {// 如果签订了直补协议地区
									for ( var i = 0; i < this.directRedeemArea.length; i++) {
										var area = this.directRedeemArea[i];
										var cnode = new Ext.tree.AsyncTreeNode(
												{
													id : area['areaCode'],// id
																			// 为服务器返回id
																			// 父节点id
													text : area['areaName'],// 显示内容为服务器返回id
													otherValue : area,
													leaf : true
												});
										this.areaTreeRootNode
												.appendChild(cnode);// 将当前节点添加为待展开节点子节点
									}
								}
							}

							this.areaTree.root.expand();

						},
						'keyup' : {
							fn : function(combo, event) {
								event.stopEvent();
								var k = event.getKey();
								this.treeHtmlNode = document
										.getElementById(this.areaCodeTreeNodeId).innerHTML;
								if (k == event.BACKSPACE
										|| !event.isSpecialKey()) {
									if (combo.defaultProps != null) {
										combo
												.setValue(combo.defaultProps.areaCode);
										combo
												.setRawValue(combo.defaultProps.areaName);
										combo.otherValue = combo.defaultProps;
										combo.tooltipObj.destroy();
										combo.tooltipObj = new Ext.ToolTip(
												{
													target : combo.id,
													trackMouse : false,
													draggable : false,
													maxWidth : 200,
													minWidth : 100,
													html : '<font  size="2px">'
															+ combo.defaultProps.areaName
															+ '</font>'
												});
									}
								}
							}
						},
						'afterrender' : function(combo) {
							if (!combo.otherValue) {
								combo.setValue(combo.defaultProps.areaCode);
								combo.setRawValue(combo.defaultProps.areaName);
								combo.otherValue = combo.defaultProps;
							} else {
								combo.setValue(combo.otherValue.areaCode);
								combo.setRawValue(combo.otherValue.areaName);
							}
							this.tooltip.text = combo.defaultProps.areaName;
							if (this.tooltip.text) {
								this.tooltipObj = new Ext.ToolTip({
									target : this.id,
									trackMouse : false,
									draggable : false,
									maxWidth : 300,
									minWidth : 100,
									html : '<font  size="2px">'
											+ this.tooltip.text + '</font>'
								});

							}
						}
					}
				});
/**
 * 医疗机构联想输入法
 * 返回结果级格式：{totalSize:10,valueResult:[{ids:1001,areaName:'镇宁县',orgName:'镇宁县人民医院',orgLevel:'3'}]}
 * 
 * @class Ext.form.ItemAssociationCombo
 * @extends Ext.form.AssociationCombo
 */
Ext.form.OrgAssociationCombo = function(config) {
	this.defaultProps = null;
	this.recordType = null;
	this.loadDataUrl = null;
	this.manager = false;
	this.orgType = '';
	this.enableKeyEvents = true;
	this.store = null;
	this.limitLevel = null;
	this.pageSize = 20;
	this.displayField = null;
	this.minChars = 0;
	this.redeemType = null;
	this.triggerAction = 'all';
	this.limitArea = '';
	this.editable = true;
	this.displayField = 'orgName';
	this.valueField = 'ids';
	this.pageTb = null;
	this.tooltip = {};
	this.tooltipObj = null;
	this.loadingText = '正在查询';
	this.queryParam = 'pcode';
	this.queryDelay = 300;
	this.listWidth = 400;
	this.itemSelector = 'div.bigdiv';
	this.listTpl = null;
	this.rowTpl = null;
	this.selected = false;
	this.selectOnFocus = true;
	this.levelParamName = null;
	this.onSelect = Ext.emptyFn();
	this.hideTrigger = false;
	Ext.apply(this, config);
	Ext.form.OrgAssociationCombo.superclass.constructor.call(this);
};
Ext
		.extend(
				Ext.form.OrgAssociationCombo,
				Ext.form.ComboBox,
				{
					oldValue : '',
					oldRawValue : '',
					initComponent : function() {
						this.onSelect = function(record, index) {
							this.tooltipObj.destroy();
							this.tooltipObj = new Ext.ToolTip({
								target : this.id,
								trackMouse : false,
								draggable : false,
								maxWidth : 200,
								minWidth : 100,
								html : '<font  size="2px">名称：'
										+ record.get('orgName')
										+ ',级别:'
										+ rendererStaticDict(record
												.get('orgLevel'), 'S201_06')
										+ '</font>'
							});
							this.oldValue = record.get('ids');
							this.oldRawValue = record.get('orgName');

							this.setValue(record.get('ids'));
							this.setRawValue(record.get('orgName'));
							if (this.levelParamName) {
								if (this.nextSibling()
										&& this.nextSibling().getName() == this.levelParamName) {
									this.nextSibling().setValue(
											record.get('orgLevel'));
								} else if (this.previousSibling()
										&& this.previousSibling().getName() == this.levelParamName) {
									this.previousSibling().setValue(
											record.get('orgLevel'));
								}
							}
							this.fireEvent('select', this, record, index);
							this.collapse();
						};
						var combo = this;
						if (!combo.recordType) {
							combo.recordType = [ {
								name : 'ids',
								type : 'string',
								mapping : 'ids'
							}, {
								name : 'areaName',
								type : 'string',
								mapping : 'areaName'
							}, {
								name : 'orgName',
								type : 'string',
								mapping : 'orgName'
							}, {
								name : 'orgLevel',
								type : 'string',
								mapping : 'orgLevel'
							} ];
						}
						if (!combo.store) {
							combo.store = new Ext.data.Store({
								proxy : new Ext.data.snailHttpProxy({
									url : this.loadDataUrl
								}),
								reader : new Ext.data.JsonReader({
									root : 'valueResult',
									totalProperty : 'totalSize',
									id : 'ids'
								}, combo.recordType)
							});
						}
						combo.store.on('beforeload', function(store) {
							var pcode = combo.getRawValue().toUpperCase();
							if (pcode == combo.oldRawValue) {
								pcode = "";
							}
							store.baseParams = {
								pcode : pcode,
								limitLevel : combo.limitLevel,
								limitArea : combo.limitArea,
								redeemType : combo.redeemType,
								manager : combo.manager,
								orgType : combo.orgType,
								totalRecord : store.getTotalCount()
							};
						});
						if (!combo.rowTpl) {
							combo.rowTpl = '<div class="classdiva"  style="width:55%">{orgName:this.renderOrgName}</div>'
									+ '<div class="classdiva"  style="width:35%">{areaName}</div>'
									+ '<div class="classdiva"  style="width:10%">{orgLevel:this.rendererOrgLevel}</div>';
						}
						if (!combo.tpl) {
							combo.tpl = new Ext.XTemplate(
									'<tpl for="."><div class="bigdiv">',
									combo.rowTpl, '</div></tpl>');
						}
						combo.tpl.renderOrgName = function(name) {
							if (name) {
								return name;
							} else {
								return '&nbsp;';
							}
						};
						combo.tpl.rendererOrgLevel = function(level) {
							return rendererStaticDict(level, 'S201_06');
						};
						Ext.form.OrgAssociationCombo.superclass.initComponent
								.call(this);

					},
					listeners : {
						'afterrender' : function(combo) {
							if (combo.defaultProps) {
								combo.setValue(combo.defaultProps.ids);
								combo.setRawValue(combo.defaultProps.orgName);
								this.tooltipObj = new Ext.ToolTip({
									target : combo.id,
									trackMouse : false,
									draggable : false,
									maxWidth : 200,
									minWidth : 100,
									html : '<font  size="2px">'
											+ combo.defaultProps.orgName
											+ '</font>'
								});
							} else {
								this.tooltipObj = new Ext.ToolTip({
									target : combo.id,
									trackMouse : false,
									draggable : false,
									maxWidth : 200,
									minWidth : 100,
									html : '<font  size="2px">选择医疗机构</font>'
								});
							}
						},
						'focus' : function(combo) {
							if (combo.getValue()) {
								combo.oldValue = combo.getValue();
								combo.oldRawValue = combo.getRawValue();
								combo.selected = true;
							} else {
								combo.selected = false;
							}
						},
						'blur' : function(combo) {
							if (combo.getRawValue() != ''
									&& combo.oldValue != '') {
								combo.setValue(combo.oldValue);
								combo.setRawValue(combo.oldRawValue);
								combo.selected = true;
							} else if (combo.getRawValue() == '') {
								combo.setValue('');
								combo.setRawValue('');
								combo.oldValue = '';
								combo.oldRawValue = '';
								combo.selected = false;
							} else if (combo.oldValue == '') {
								if (combo.getValue() == combo.getRawValue()) {
									combo.setValue('');
									combo.setRawValue('');
									combo.oldValue = '';
									combo.oldRawValue = '';
									combo.selected = false;
								}
							}
						},
						'keypress' : function(combo, event) {
							if (event.getKey() != 13) {
								combo.selected = false;
							}
						},
						'select' : function(combo, record, index) {
							combo.selected = true;
						}
					}
				});
Ext.snailButton = function(config) {
	this.rightCode = null;
	this.hideMode = 'visibility';
	Ext.apply(this, config);
	Ext.snailButton.superclass.constructor.call(this);
};
Ext.extend(Ext.snailButton, Ext.Button, {
	initComponent : function() {
		Ext.snailButton.superclass.initComponent.call(this);
	}
});
Ext.snailToolBar = function(config) {
	this.noRightAction = 'disabled';
	this.layout = 'hbox';
	this.hiddenButton = {};
	this.rightSet = {};
	this.forceLayout = true;
	this.hideMode = 'visibility';
	Ext.apply(this, config);
	Ext.snailButton.superclass.constructor.call(this);
};
Ext.extend(Ext.snailToolBar, Ext.Toolbar, {
	initComponent : function() {
		var buttonArray = this.items;
		if (buttonArray) {
			for ( var i = 0; i < buttonArray.length; i++) {
				if (buttonArray[i].xtype == 'snailButton') {
					if (!(this.rightSet && this.rightSet
							.hasOwnProperty(buttonArray[i].rightCode))) {
						if (buttonArray[i].rightCode
								&& buttonArray[i].rightCode != '-1') {
							if (this.noRightAction == 'disabled') {
								buttonArray[i].disabled = true;
							} else {
								if (i != 0 && buttonArray[i - 1] == '-') {
									buttonArray.splice(i - 1, 2);
								} else if (i + 1 < buttonArray.length
										&& buttonArray[i + 1] == '-') {
									buttonArray.splice(i, 2);
								} else {
									buttonArray.splice(i, 1);
								}

							}
						}
					}
				}
			}
		}
		Ext.snailToolBar.superclass.initComponent.call(this);
	}
});
Ext.grid.snailBaseGrid = function(config) {
	this.loadDataUrl = null;
	this.hasPageToolBar = true;
	Ext.apply(this, config);
	Ext.grid.snailBaseGrid.superclass.constructor.call(this);
};
Ext.extend(Ext.grid.snailBaseGrid, Ext.grid.GridPanel, {
	clicksToEdit : 1,
	columnLines : true,
	loadMask : {
		msg : "正在查询数据，请稍候...."
	},
	initComponent : function() {
		var grid = this;
		if ((!this.bbar) && this.hasPageToolBar) {
			this.bbar = new Ext.snailPagingToolbar({
				ownGrid : grid,
				store : grid.store,
				pageSize : gridDefaultPageSize
			});
		}
		Ext.grid.snailBaseGrid.superclass.initComponent.call(this);
	}
});
/**
 * 基础编辑表格
 * 
 * @param {}
 *            config
 */
Ext.grid.snailBaseEditorGrid = function(config) {
	this.loadDataUrl = null;
	this.editorMap = {};
	this.doDelete = function(deleted) {
		var records = this.getSelectionModel().getSelections();
		var msg = deleted ? "请选择要删除的记录" : "请选择要取消删除的记录";
		if (records.length <= 0) {
			Ext.Msg.alert("提示", msg);
			return;
		}
		for ( var i = 0; i < records.length; i++) {
			records[i].set('localDeleted', deleted);
		}
	};
	Ext.apply(this, config);
	Ext.grid.snailBaseEditorGrid.superclass.constructor.call(this);
};
Ext.extend(Ext.grid.snailBaseEditorGrid, Ext.grid.EditorGridPanel, {
	columnLines : true,
	viewConfig : {
		forceFit : false,
		getRowClass : function(record, index) {
			var c = record.get('localDeleted');
			if (c) {
				return 'x-grid3-row-delete';
			}
			if ((!record.id) || record.id <= 0) {
				return 'x-grid3-row-new';
			}
		}
	}
});
/**
 * 基础数据源
 * 
 * @param {}
 *            config
 */
Ext.data.snailBaseStore = function(config) {
	this.pruneModifiedRecords = true;
	this.deleteRecord = function(record) {
		record.set('localDeleted', true);
	};
	this.recoverRecord = function(record) {
		record.set('localDeleted', false);
	};
	this.getAllDetail = function() {
		var allDetail = [];
		for ( var i = 0, len = detailStore.getCount(); i < len; i++) {// 
			var record = detailStore.getAt(i);
			allDetail.push(record.data);
		}
		return Ext.encode(allDetail);
	};

	this.getModifiedDetail = function(idColName) {
		var detailStore = this;
		var modifiedJson = [];
		var newRecordJson = [];
		var deletedJson = [];
		for ( var i = 0, len = detailStore.getCount(); i < len; i++) {// 
			var record = detailStore.getAt(i);
			if (record.get('localDeleted') && record.get(idColName) > 0) {
				deletedJson.push(record.get(idColName));
			} else if ((record.get(idColName) == null || record.get(idColName) <= 0)
					&& !record.get('localDeleted')) {
				var newRecord = Ext.util.JSON.encode(record.data);
				newRecordJson.push(newRecord);
			} else if (!record.get('localDeleted') && record.get(idColName) > 0) {
				var modifiedRecordOne = getModifyRecords(idColName, record);
				if (modifiedRecordOne != '{}') {
					modifiedJson.push(modifiedRecordOne);
				}
			}

		}
		return Ext.encode([ newRecordJson, modifiedJson, deletedJson ]);

	};
	Ext.apply(this, config);
	Ext.data.snailBaseStore.superclass.constructor.call(this);
};
Ext.extend(Ext.data.snailBaseStore, Ext.data.Store, {
	initComponent : function() {
		Ext.data.snailBaseStore.superclass.initComponent.call(this);
	},
	callback : function(r, option, success) {
		if (success == false) {
			Ext.Msg.alert("提示", "加载数据超时");
		}
	}
});
/**
 * 基础Http proxy
 * 
 * @class Ext.data.snailHttpProxy
 * @extends Ext.data.HttpProxy
 */
Ext.data.snailHttpProxy = Ext.extend(Ext.data.HttpProxy,
		{
			initComponent : function() {
				Ext.data.snailHttpProxy.superclass.initComponent.call(this);
			},
			onRead : function(action, o, response) {
				if (response.status != 200) {
					Ext.Msg.alert('系统提示', response.statusText);
				} else {
					var resp = Ext.decode(response.responseText);
					if (!resp.state) {
						Ext.Msg.alert('系统提示', resp.errorMessage);
					}
				}
				var result;
				try {
					result = o.reader.read(response);
				} catch (e) {
					this.fireEvent('loadexception', this, o, response, e);
					this.fireEvent('exception', this, 'response', action, o,
							response, e);
					o.request.callback.call(o.request.scope, null,
							o.request.arg, false);
					return;
				}
				if (result.success === false) {
					this.fireEvent('loadexception', this, o, response);
					var res = o.reader.readResponse(action, response);
					this.fireEvent('exception', this, 'remote', action, o, res,
							null);
				} else {
					this.fireEvent('load', this, o, o.request.arg);
				}
				o.request.callback.call(o.request.scope, result, o.request.arg,
						result.success);
			},
			listeners : {
				'exception' : function(dataProxy, type, action, options,
						response, arg) {
					if (type == "remote") {
						Ext.Msg.alert("系统提示", "数据已返回，但是返回数据无法识别");
					} else {
						Ext.Msg.alert("系统提示", "加载数据超时");
					}
				}
			}
		});
Ext.data.snailHttpProxySum = Ext.extend(Ext.data.HttpProxy, {
	initComponent : function() {
		Ext.data.snailHttpProxySum.superclass.initComponent.call(this);
	},
	onRead : function(action, o, response) {
		if (response.status != 200) {
			Ext.Msg.alert('加载数据失败', "出错信息：" + response.statusText);
		} else {
			var rst = Ext.decode(response.responseText);

			if (!rst.result) {
				Ext.Msg.alert('加载数据失败', "出错信息：" + resp.message);
			} else {
				if (rst.otherResult
						&& rst.otherResult['REDEEM_QUERY_SUM_RESULT']) {

					forEach(rst.otherResult['REDEEM_QUERY_SUM_RESULT'],
							function(name, value) {
								var extNode = Ext.getCmp(name);
								if (extNode) {
									extNode.setValue(value);
								}
							});
				}
			}
		}
		var result;
		try {
			result = o.reader.read(response);
		} catch (e) {
			this.fireEvent('loadexception', this, o, response, e);
			this.fireEvent('exception', this, 'response', action, o, response,
					e);
			o.request.callback
					.call(o.request.scope, null, o.request.arg, false);
			return;
		}
		if (result.success === false) {
			this.fireEvent('loadexception', this, o, response);
			var res = o.reader.readResponse(action, response);
			this.fireEvent('exception', this, 'remote', action, o, res, null);
		} else {
			this.fireEvent('load', this, o, o.request.arg);
		}
		o.request.callback.call(o.request.scope, result, o.request.arg,
				result.success);
	}
});
/**
 * 基础window
 * 
 * @param {}
 *            config
 */
Ext.snailBaseWindow = function(config) {
	this.dataType = '';
	this.actionType = '1';// 1-新增，2-编辑
	this.saveSuccessAction = '';
	this.modal = true;
	this.postParamName = '';
	this.postPropName = '';
	this.rightNewIds = {};
	this.leftNewIds = {};
	this.successStore = null;
	this.loadDataUrl = null;
	this.saveDataUrl = null;
	this.closeAction = 'hide';
	this.selectedData = null;
	Ext.apply(this, config);
	Ext.snailBaseWindow.superclass.constructor.call(this);
};
Ext
		.extend(
				Ext.snailBaseWindow,
				Ext.Window,
				{
					initComponent : function() {
						var window = this;
						if (!this.tbar) {
							this.tbar = new Ext.Toolbar({
								items : [ {
									text : '关闭',
									width : 60,
									iconCls : 'bexit',
									handler : function() {
										window.hide();
									}
								}, '-', {
									text : '保存',
									width : 60,
									iconCls : 'bsave',
									handler : function() {
										window.save();
									}
								} ]
							});
						}
						Ext.snailBaseWindow.superclass.initComponent.call(this);
					},
					changeButtonStatus : function(status) {
						if (this.getTopToolbar() != null) {
							var buttons = this.getTopToolbar().findByType(
									Ext.Button);
							for ( var i = 0; i < buttons.length; i++) {
								buttons[i].setDisabled(!status);
							}
						}
						if (this.getBottomToolbar() != null) {
							var buttons = this.getBottomToolbar().findByType(
									Ext.Button);
							for ( var i = 0; i < buttons.length; i++) {
								buttons[i].setDisabled(!status);
							}
						}
					},
					removeLeftRow : function() {
						var selected = this.get(0).getSelectionModel()
								.getSelections();
						if (selected && selected.length > 0) {
							for ( var i = 0; i < selected.length; i++) {
								this.addRemoved(selected[i].id);
								this.get(0).store.remove(selected[i]);
								this.get(1).store.add(selected[i]);
							}
						} else {
							Ext.Msg.alert("提示", "请在下方表格中选择要操作的记录行");
						}
					},
					addLeftRole : function() {
						var selected = this.get(1).getSelectionModel()
								.getSelections();
						if (selected && selected.length > 0) {
							for ( var i = 0; i < selected.length; i++) {
								this.addNew(selected[i].id);
								this.get(0).store.add(selected[i]);
								this.get(1).store.remove(selected[i]);
							}
						} else {
							Ext.Msg.alert("提示", "请在下方表格中选择要操作的记录行");
						}
					},
					addRemoved : function(roleIds) {
						var isNew = this.leftNewIds.hasOwnProperty(roleIds);
						if (isNew) {
							delete this.leftNewIds[roleIds];
						} else {
							this.rightNewIds[roleIds] = roleIds;
						}
					},
					addNew : function(roleIds) {
						var isRemoved = this.rightNewIds
								.hasOwnProperty(roleIds);
						if (isRemoved) {
							delete this.rightNewIds[roleIds];
						} else {
							if (this.leftNewIds.hasOwnProperty(roleIds)) {
								Ext.Msg.alert("提示", "该用户已经添加，不需要重复添加");
							} else {
								this.leftNewIds[roleIds] = roleIds;
							}
						}
					},
					initModify : function() {
						this.leftNewIds = {};
						this.rightNewIds = {};
					},
					save : function() {
						var window = this;
						this.changeButtonStatus(false);
						if (((this.valided && this.valided()) || (!this.valided))
								&& this.get(0).valided()) {
							var inputs = this.get(0).getParams();
							if (this.postPropName && this.selectedData) {
								inputs[this.postParamName] = this.selectedData.data[this.postPropName];
							}
							doRequestToServer({
								method : 'POST',
								url : window.saveDataUrl,
								async : false,
								params : inputs,
								callback : function(v) { // 成功返回
									var rst = Ext.decode(v.responseText);
									if (rst.result) {
										Ext.Msg.alert("提示", window.dataType
												+ "信息保存成功。");
										if (window.successStore) {
											window.successStore.load();
										}
										if (window.saveSuccessAction == 'hide') {
											window.hide();
										}
										window.changeButtonStatus(true);
									} else {
										Ext.Msg.alert("提示", window.dataType
												+ "信息保存失败,错误信息：" + rst.message);
										window.changeButtonStatus(true);
									}
								},
								failurecallback : function(response, opts) {
									Ext.Msg.alert("提示", window.dataType
											+ "信息保存失败,错误信息："
											+ response.statusText);
									window.changeButtonStatus(true);
								}
							});
						} else {
							this.changeButtonStatus(true);
						}
					}
				});
/**
 * 基础编辑框
 * 
 * @param {}
 *            config
 */
Ext.snailBaseForm = function(config) {
	this.saveDataUrl = '';
	this.showData = null;
	Ext.apply(this, config);
	Ext.snailBaseForm.superclass.constructor.call(this);
};
Ext.extend(Ext.snailBaseForm, Ext.form.FormPanel, {
	snail : true,
	border : false,
	bodyStyle : 'padding:0px 5px 0px 0px',
	defaults : {
		margins : '0 5 -2 0',
		cmargins : '0, 0, 0, 0'
	},
	initComponent : function() {
		Ext.snailBaseForm.superclass.initComponent.call(this);
	},
	valided : function() {
		if (!this.getForm().isValid()) {
			Ext.Msg.show({
				title : '系统提示',
				msg : '红色下划线输入框中的内容必须输入或输入不合法！',
				icon : Ext.Msg.INFO,
				minWidth : 300,
				buttons : Ext.Msg.OK
			});
			return false;
		}
		return true;
	},
	getParams : function() {
		var params = this.getForm().getValues();
		var hiddens = this.findByType(Ext.form.Hidden);
		for ( var i = 0; i < hiddens.length; i++) {
			params[hiddens[i].name] = hiddens[i].getValue();
		}
		var comboxs = this.findByType(Ext.form.TextField);
		for ( var i = 0; i < comboxs.length; i++) {
			params[comboxs[i].name] = comboxs[i].getValue();
		}
		var checkboxs = this.findByType(Ext.form.Checkbox);
		for ( var i = 0; i < checkboxs.length; i++) {
			params[checkboxs[i].name] = checkboxs[i].getValue();
		}
		return params;
	},
	getInputs : function() {
		var inputs = this.findByType(Ext.form.TextField);
		var hiddens = this.findByType(Ext.form.Hidden);
		for ( var i = 0; i < hiddens.length; i++) {
			inputs.push(hiddens[i]);
		}
		var htmlEdits = this.findByType(Ext.form.HtmlEditor);
		for ( var i = 0; i < htmlEdits.length; i++) {
			inputs.push(htmlEdits[i]);
		}
		var checkboxs = this.findByType(Ext.form.Checkbox);
		for ( var i = 0; i < checkboxs.length; i++) {
			inputs.push(checkboxs[i]);
		}
		return inputs;
	}
});
Ext.form.TreeField = Ext.extend(Ext.form.TriggerField,  {   
    /**  
     * @cfg {Boolean} readOnly  
     * 设置为只读状态  
     *   
     */  
    readOnly : true ,  
    
    /**  
     * @cfg {String} displayField  
     * 用于显示数据的字段名  
     *   
     */  
    displayField : 'text',   
    /**  
     * @cfg {String} valueField  
     * 用于保存真实数据的字段名  
     */  
    valueField : 'id',   
    /**  
     * @cfg {String} hiddenName  
     * 保存真实数据的隐藏域名  
     */  
    hiddenName : null,   
    /**  
     * @cfg {Integer} listWidth  
     * 下拉框的宽度  
     */  
    listWidth : null,   
    /**  
     * @cfg {Integer} minListWidth  
     * 下拉框最小宽度  
     */  
    minListWidth : 50,   
    /**  
     * @cfg {Integer} listHeight  
     * 下拉框高度  
     */  
    listHeight : null,   
    /**  
     * @cfg {Integer} minListHeight  
     * 下拉框最小高度  
     */  
    minListHeight : 50,   
    /**  
     * @cfg {String} dataUrl  
     * 数据地址  
     */  
    dataUrl : null,
    /**  
     * @cfg {Ext.tree.TreePanel} tree  
     * 下拉框中的树  
     */  
    tree : null,   
    /**  
     * @cfg {String} value  
     * 默认值  
     */  
    value : null,   
    /**  
     * @cfg {String} displayValue  
     * 用于显示的默认值  
     */  
    displayValue : null,   
    /**  
     * @cfg {Object} baseParams  
     * 向后台传递的参数集合  
     */  
    baseParams : {},   
    /**  
     * @cfg {Object} treeRootConfig  
     * 树根节点的配置参数  
     */  
    treeRootConfig : {   
     
        },   
    /**  
     * @cfg {String/Object} autoCreate  
     * A DomHelper element spec, or true for a default element spec (defaults to  
     * {tag: "input", type: "text", size: "24", autocomplete: "off"})  
     */  
    defaultAutoCreate : {tag: "input", type: "text", size: "24", autocomplete: "off"},   
  
    initComponent : function(){   
        Ext.form.TreeField.superclass.initComponent.call(this);   
        this.addEvents(   
                'select',   
                'expand',   
                'collapse',   
                'beforeselect'        
        );   
           
    },   
    initList : function(){   
        if(!this.list){   
            var cls = 'x-treefield-list';   
  
            this.list = new Ext.Layer({   
                shadow: this.shadow, cls: [cls, this.listClass].join(' '), constrain:false  
            });   
  
            var lw = this.listWidth || Math.max(this.wrap.getWidth(), this.minListWidth);   
            this.list.setWidth(lw);   
            this.list.swallowEvent('mousewheel');   
               
            this.innerList = this.list.createChild({cls:cls+'-inner'});   
            this.innerList.setWidth(lw - this.list.getsnailWidth('lr'));   
            this.innerList.setHeight(this.listHeight || this.minListHeight);   
            if(!this.tree){   
                this.tree = this.createTree(this.innerList);       
            }   
            this.tree.on('click',this.select,this);   
            this.tree.render();   
        }   
    },   
    onRender : function(ct, position){   
        Ext.form.TreeField.superclass.onRender.call(this, ct, position);   
        if(this.hiddenName){   
            this.hiddenField = this.el.insertSibling({tag:'input',    
                                                     type:'hidden',    
                                                     name: this.hiddenName,    
                                                     id: (this.hiddenId||this.hiddenName)},   
                    'before', true);   
            this.hiddenField.value =   
                this.hiddenValue !== undefined ? this.hiddenValue :   
                this.value !== undefined ? this.value : '';   
            this.el.dom.removeAttribute('name');   
        }   
        if(Ext.isGecko){   
            this.el.dom.setAttribute('autocomplete', 'off');   
        }   
  

        this.initList();   
    },   
    select : function(node, e){
    	e.stopEvent();
        if(this.fireEvent('beforeselect', node, this)!= false){  
            this.onSelect(node);   
            this.fireEvent('select', this, node); 
            
        }   
    },   
    onSelect:function(node){   
        this.setValue(node);   
        this.collapse();   
    },   
    createTree:function(el){   
        var Tree = Ext.tree;   
       
        var tree = new Tree.TreePanel({   
            el:el,   
            autoScroll:true, 
            animate:true,   
            height: 300,
            snail:false,
            containerScroll: true, 
            rootVisible: true,
            dropConfig : {
				appendOnly : true
			},
            loader: new Tree.TreeLoader({   
                dataUrl : this.dataUrl,   
                baseParams : this.baseParams   
            })   
        });   
       
        var root = new Tree.AsyncTreeNode(this.treeRootConfig);   
        tree.setRootNode(root);  
        root.expand(this.treeRootConfig.expand, this.treeRootConfig.expand);
        return tree;   
    },   
  
    getValue : function(){   
        if(this.valueField){   
            return typeof this.value != 'undefined' ? this.value : '';   
        }else{   
            return Ext.form.TreeField.superclass.getValue.call(this);   
        }   
    },   
    setValue : function(node){   
        //if(!node)return;   
        var text,value;   
        if(node && typeof node == 'object'){   
            text = node[this.displayField];   
            value = node[this.valueField || this.displayField];   
        }else{   
            text = node;   
            value = node;   
                   
        }   
        if(this.hiddenField){   
            this.hiddenField.value = value;   
        }   
        Ext.form.TreeField.superclass.setValue.call(this, text);   
        this.value = value;   
    },   
    onResize: function(w, h){   
        Ext.form.TreeField.superclass.onResize.apply(this, arguments);   
        if(this.list && this.listWidth == null){   
            var lw = Math.max(w, this.minListWidth);   
            this.list.setWidth(lw);   
            this.innerList.setWidth(lw - this.list.getsnailWidth('lr'));   
        }   
    },   
    validateBlur : function(){   
        return !this.list || !this.list.isVisible();      
    },   
    onDestroy : function(){   
        if(this.list){   
            this.list.destroy();   
        }   
        if(this.wrap){   
            this.wrap.remove();   
        }   
        Ext.form.TreeField.superclass.onDestroy.call(this);   
    },   
    collapseIf : function(e){   
        if(!e.within(this.wrap) && !e.within(this.list)){   
            this.collapse();   
        }   
    },   
  
    collapse : function(){   
        if(!this.isExpanded()){   
            return;   
        }   
        this.list.hide();   
        Ext.getDoc().un('mousewheel', this.collapseIf, this);   
        Ext.getDoc().un('mousedown', this.collapseIf, this);   
        this.fireEvent('collapse', this);   
    },   
    expand : function(){   
        if(this.isExpanded() || !this.hasFocus){   
            return;   
        }   
        this.onExpand();   
        this.list.alignTo(this.wrap, this.listAlign);   
        this.list.show();   
        Ext.getDoc().on('mousewheel', this.collapseIf, this);   
        Ext.getDoc().on('mousedown', this.collapseIf, this);   
        this.fireEvent('expand', this);   
    },   
    onExpand : function(){   
        var doc = Ext.getDoc();   
        this.on('click',function(){alert(111)},this);   
    },   
    isExpanded : function(){   
        return this.list && this.list.isVisible();   
    },   
    onTriggerClick : function(){   
        if(this.disabled){   
            return;   
        }   
        if(this.isExpanded()){   
            this.collapse();   
        }else {   
            this.onFocus({});   
            this.expand();   
        }   
        this.el.focus();   
    }   
});   

Ext.form.SearchField = function(config) {
	this.recordType = null;
	this.dataUrl = null;
	this.store = null;
	this.pageSize = 20;
	this.minChars = 0;
	this.selectOnFocus = true;
	this.displayField = null;
	this.valueField = null;
	this.listWidth = 300;
	this.itemSelector = 'div.bigdiv';
	this.enableKeyEvents = true;
	this.typeAhead = false;
	this.listTpl = null;
	this.rowTpl = null;
	this.onSelect = Ext.emptyFn();
	this.selected = true;
	this.hideTrigger = false;
	Ext.apply(this, config);
	Ext.form.SearchField.superclass.constructor.call(this);
};
Ext.extend(Ext.form.SearchField,Ext.form.ComboBox,{
					oldValue : '',
					oldRawValue : '',
					initComponent : function() {
						this.onSelect = function(record, index) {
							this.oldValue = record.get(this.valueField);
							this.oldRawValue = record.get(this.displayField);
							this.setValue(record.get(this.valueField));
							this.setRawValue(record.get(this.displayField));
							this.collapse();
							this.selected = true;
							this.fireEvent('select', this, record, index);
						};
						var combo = this;
						if (!combo.recordType) {
							combo.recordType = [ {
								name : this.valueField,
								type : 'string',
								mapping :this.valueField
							}, {
								name : this.displayField,
								type : 'string',
								mapping:this.displayField
							} ];
						}
						;
						if (!combo.store) {
							combo.store = new Ext.data.Store({
								proxy : new Ext.data.snailHttpProxy({
									url : this.dataUrl
								}),
								reader : new Ext.data.JsonReader({
									root : 'list',
									totalProperty : 'allRows',
									id : this.valueField
								}, combo.recordType)
							});
						}
						combo.store.on('beforeload', function(store) {
							var qValue= combo.getRawValue().toUpperCase();
							if (qValue == combo.oldRawValue) {
								qValue = '';
							}
							store.baseParams = {
								name : qValue,
								limit : combo.pageSize,
								totalRecord : store.getTotalCount()
							};
						});
						if (!combo.rowTpl) {
							combo.rowTpl = '<div class="searchField"  style="width:30%">{'+this.valueField+'}</div>'
									+ '<div class="searchField"  style="width:65%">{'+this.displayField+'}</div>';
						}
						if (!combo.tpl) {
							combo.tpl = new Ext.XTemplate(
									'<tpl for="."><div class="bigdiv">',
									combo.rowTpl, '</div></tpl>');
						}
						Ext.form.SearchField.superclass.initComponent
								.call(this);
					},
					listeners : {
						'focus' : function(combo) {
							if (combo.getValue()) {
								combo.oldValue = combo.getValue();
								combo.oldRawValue = combo.getRawValue();
								combo.selected = true;
							}
						},
						'blur' : function(combo) {
							if (combo.getRawValue() != ''&& combo.oldValue != '') {
								combo.setValue(combo.oldValue);
								combo.setRawValue(combo.oldRawValue);
								combo.selected = true;
							} else if (combo.getRawValue() == '') {
								combo.setValue('');
								combo.setRawValue('');
								combo.selected = false;
								combo.oldValue = '';
								combo.oldRawValue = '';
							} else if (combo.oldValue == '') {
								if (combo.getValue() == combo.getRawValue()) {
									combo.setValue('');
									combo.setRawValue('');
									combo.oldValue = '';
									combo.oldRawValue = '';
									combo.selected = false;
								}
							}
						},
						'keypress' : function(combo, event) {
							if (event.getKey() != 13) {
								combo.selected = false;
							}
						},
						'select' : function(combo, record, index) {
							combo.selected = true;
						}
					}
				});
Ext.form.MultipleSearchField = function(config) {
	this.recordType = null;
	this.dataUrl = null;
	this.store = null;
	this.pageSize = 20;
	this.minChars = 0;
	this.selectOnFocus = true;
	this.displayField = null;
	this.valueField = null;
	this.listWidth = 300;
	this.itemSelector = 'div.bigdiv';
	this.enableKeyEvents = true;
	this.typeAhead = false;
	this.listTpl = null;
	this.rowTpl = null;
	this.onSelect = Ext.emptyFn();
	this.selected = true;
	this.hideTrigger = false;
	this.multipleValue="";
	this.multipleRawValue="";
	Ext.apply(this, config);
	Ext.form.MultipleSearchField.superclass.constructor.call(this);
};
Ext.extend(Ext.form.MultipleSearchField,Ext.form.ComboBox,{
	oldValue : '',
	oldRawValue : '',
	initComponent : function() {
		this.onSelect = function(record, index) {
			this.multipleValue=this.multipleValue+record.get(this.valueField)+",";
			this.multipleRawValue=this.multipleRawValue+record.get(this.displayField)+",";
			this.oldValue =this.multipleValue.substring(0, this.multipleValue.length-1);
			this.oldRawValue =this.multipleRawValue.substring(0, this.multipleRawValue.length-1);
			//this.oldValue =record.get(this.valueField);
			//this.oldRawValue =record.get(this.displayField);
			//this.setValue(this.getValue()+","+record.get(this.valueField));
			//this.setRawValue(this.getRawValue()+","+record.get(this.displayField));
			//this.setValue(this.getValue()+","+record.get(this.valueField));
			this.setValue(this.multipleValue.substring(0, this.multipleValue.length-1));
			this.setRawValue(this.multipleRawValue.substring(0, this.multipleRawValue.length-1));
			this.collapse();
			this.selected = true;
			this.fireEvent('select', this, record, index);
		};
		var combo = this;
		if (!combo.recordType) {
			combo.recordType = [ {
				name : this.valueField,
				type : 'string',
				mapping :this.valueField
			}, {
				name : this.displayField,
				type : 'string',
				mapping:this.displayField
			} ];
		}
		;
		if (!combo.store) {
			combo.store = new Ext.data.Store({
				proxy : new Ext.data.snailHttpProxy({
					url : this.dataUrl
				}),
				reader : new Ext.data.JsonReader({
					root : 'list',
					totalProperty : 'allRows',
					id : this.valueField
				}, combo.recordType)
			});
		}
		combo.store.on('beforeload', function(store) {
			var qValue= combo.getRawValue().toUpperCase();
			if (qValue == combo.oldRawValue) {
				qValue = '';
			}
			qValue = '';
			store.baseParams = {
				name : qValue,
				limit : combo.pageSize,
				totalRecord : store.getTotalCount()
			};
		});
		if (!combo.rowTpl) {
			combo.rowTpl = '<div class="searchField"  style="width:30%">{'+this.valueField+'}</div>'
					+ '<div class="searchField"  style="width:65%">{'+this.displayField+'}</div>';
		}
		if (!combo.tpl) {
			combo.tpl = new Ext.XTemplate(
					'<tpl for="."><div class="bigdiv">',
					combo.rowTpl, '</div></tpl>');
		}
		Ext.form.MultipleSearchField.superclass.initComponent
				.call(this);
	},
	listeners : {
		'focus' : function(combo) {
			if (combo.getValue()) {
				combo.oldValue = combo.getValue();
				combo.oldRawValue = combo.getRawValue();
				combo.selected = true;
			}
		},
		'blur' : function(combo) {
			if (combo.getRawValue() != ''&& combo.oldValue != '') {
				combo.setValue(combo.oldValue);
				combo.setRawValue(combo.oldRawValue);
				combo.selected = true;
			} else if (combo.getRawValue() == '') {
				combo.setValue('');
				combo.setRawValue('');
				combo.selected = false;
				combo.oldValue = '';
				combo.oldRawValue = '';
			} else if (combo.oldValue == '') {
				if (combo.getValue() == combo.getRawValue()) {
					combo.setValue('');
					combo.setRawValue('');
					combo.oldValue = '';
					combo.oldRawValue = '';
					combo.selected = false;
				}
			}
			this.multipleValue="";
			this.multipleRawValue="";
		},
		'keypress' : function(combo, event) {
			if (event.getKey() != 13) {
				combo.selected = false;
			}
		},
		'select' : function(combo, record, index) {
			combo.selected = true;
		}
	}
});
Ext.reg('searchField', Ext.form.SearchField);
Ext.reg('multipleSearchField', Ext.form.MultipleSearchField);
Ext.reg('treeField', Ext.form.TreeField);
Ext.reg('snailBaseForm', Ext.snailBaseForm);
Ext.reg('snailHttpProxySum', Ext.data.snailHttpProxySum);
Ext.reg('snailHttpProxy', Ext.data.snailHttpProxy);
Ext.reg('snailBaseStore', Ext.data.snailBaseStore);
Ext.reg('snailBaseEditorGrid', Ext.grid.snailBaseEditorGrid);
Ext.reg('snailButton', Ext.snailButton);
Ext.reg('snailToolBar', Ext.snailToolBar);
Ext.reg('staticParamStore', Ext.data.StaticParamStore);
Ext.reg('staticDictCombo', Ext.form.StaticDictCombo);
Ext.reg('areaCodeCombo', Ext.form.AreaCodeCombo);
Ext.reg('orgCombo', Ext.form.OrgAssociationCombo);
Ext.reg('diseaseCombo', Ext.form.DiseaseAssociationCombo);
Ext.reg('sectionCombo', Ext.form.SectionAssociationCombo);
Ext.reg('associationCombo', Ext.form.AssociationCombo);
Ext.reg('commonCombo', Ext.form.CommonAssociationCombo);
Ext.reg('itemCombo', Ext.form.ItemAssociationCombo);
Ext.reg('snailDateField', Ext.form.snailDateField);
Ext.reg('snailPagingToolbar', Ext.snailPagingToolbar);
Ext.reg("multiStaticDictCombo", Ext.form.MultiStaticDictCombo);
Ext.reg("calcItemAssociationCombo", Ext.form.CalcItemAssociationCombo);

function setFieldStatus(form, status) {
	// alert(form);
	var field = form.getInputs();
	// alert(field);
	for ( var i = 0; i < field.length; i++) {
		field[i].setReadOnly(status);
	}
}
function getParams(formPanel) {
	var params = formPanel.getForm().getValues();
	var hiddens = formPanel.findByType(Ext.form.Hidden);
	for ( var i = 0; i < hiddens.length; i++) {
		params[hiddens[i].name] = hiddens[i].getValue();
	}
	var comboxs = formPanel.findByType(Ext.form.StaticDictCombo);
	for ( var i = 0; i < comboxs.length; i++) {
		params[comboxs[i].name] = comboxs[i].getValue();
	}
	var comboxcomman = formPanel.findByType(Ext.form.ComboBox);
	for ( var i = 0; i < comboxcomman.length; i++) {
		params[comboxcomman[i].name] = comboxcomman[i].getValue();
	}
	var treeField = formPanel.findByType(Ext.form.TreeField);
	for ( var i = 0; i < treeField.length; i++) {
		params[treeField[i].name] = treeField[i].getValue()==null?'':treeField[i].getValue();
	}
	var searchField = formPanel.findByType(Ext.form.SearchField);
	for ( var i = 0; i < searchField.length; i++) {
		params[searchField[i].name] = searchField[i].getValue()==null?'':searchField[i].getValue();
	}
	var snailDateField = formPanel.findByType(Ext.form.snailDateField);
	for ( var i = 0; i < snailDateField.length; i++) {
		params[snailDateField[i].name] = snailDateField[i].getValue()==null?'':snailDateField[i].getValue();
	}
	return params;
}