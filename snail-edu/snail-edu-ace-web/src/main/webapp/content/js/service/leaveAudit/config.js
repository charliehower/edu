var cfg = {};
cfg.grid_load_data_url = contextPath + '/leave/findLeaveList.do';
cfg.grid_add_data_url = contextPath + '/leave/insertLeave.do';
cfg.grid_edit_data_url = contextPath + '/leave/updateLeave.do';
cfg.grid_delete_data_url = contextPath + '/leave/deleteLeaveByLeaveId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "请假";
cfg.rowNum= 10;
cfg.dataId= 'leaveId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=650;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}