var cfg = {};
cfg.grid_load_data_url = contextPath + '/dwmReport/findDwmReportList.do?categoryId=3';
cfg.grid_add_data_url = contextPath + '/dwmReport/insertDwmReport.do';
cfg.grid_edit_data_url = contextPath + '/dwmReport/updateDwmReport.do';
cfg.grid_delete_data_url = contextPath + '/dwmReport/deleteDwmReportByDwmReportId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "部门总结";
cfg.rowNum= 10;
cfg.dataId= 'dwmReportId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;//
//cfg.gridHeight= 'auto',

cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}