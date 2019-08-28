var cfg = {};
cfg.grid_load_data_url = contextPath + '/docFlow/findDocFlowList.do?status=0';
cfg.grid_add_data_url = contextPath + '/docFlow/insertDocFlow.do';
cfg.grid_edit_data_url = contextPath + '/docFlow/updateDocFlow.do';
cfg.grid_delete_data_url = contextPath + '/docFlow/deleteDocFlowByDocFlowId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "公文";
cfg.rowNum= 10;
cfg.dataId= 'id';
cfg.gridHeight=window.innerHeight - layoutTopHeight;//
//cfg.gridHeight= 'auto',

cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}