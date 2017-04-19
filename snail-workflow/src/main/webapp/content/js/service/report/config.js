var cfg = {};
cfg.grid_load_data_url = contextPath + '/report/findReportList.do';
cfg.grid_add_data_url = contextPath + '/report/saveReportAndDetail.do';
cfg.grid_edit_data_url = contextPath + '/report/updateReportAndDetail.do';
cfg.grid_delete_data_url = contextPath + '/report/deleteReportById.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "报表";
cfg.rowNum= 10;
cfg.dataId= 'reportId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;//
//cfg.gridHeight= 'auto',

cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}