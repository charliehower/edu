var cfg = {};
cfg.grid_load_data_url = contextPath + '/docFlowTask/findDocFlowTaskList.do?piUser='+systemUser.users.userId;
cfg.grid_add_data_url = contextPath + '/docFlowTask/insertDocFlowTask.do';
cfg.grid_edit_data_url = contextPath + '/docFlowTask/updateDocFlowTask.do';
cfg.grid_delete_data_url = contextPath + '/docFlowTask/deleteDocFlowTaskByDocFlowTaskId.do';
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