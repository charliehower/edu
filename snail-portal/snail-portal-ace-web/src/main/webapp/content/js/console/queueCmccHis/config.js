var cfg = {};
cfg.grid_load_data_url = contextPath + '/queueCmccHis/findQueueCmccHisList.do';
cfg.grid_add_data_url = contextPath + '/queueCmccHis/insertQueueCmccHis.do';
cfg.grid_edit_data_url = contextPath + '/queueCmccHis/updateQueueCmccHis.do';
cfg.grid_delete_data_url = contextPath + '/queueCmccHis/deleteQueueCmccHisByQueueCmccHisId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "完成队列";
cfg.rowNum= 10;
cfg.dataId= 'queueId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}