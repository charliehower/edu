var cfg = {};
cfg.grid_load_data_url = contextPath + '/taskCmcc/findTaskCmccList.do';
cfg.grid_add_data_url = contextPath + '/taskCmcc/insertTaskCmcc.do';
cfg.grid_edit_data_url = contextPath + '/taskCmcc/updateTaskCmcc.do';
cfg.grid_delete_data_url = contextPath + '/taskCmcc/deleteTaskCmccByTaskCmccId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "短信发送";
cfg.rowNum= 10;
cfg.dataId= 'taskId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}