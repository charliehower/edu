var cfg = {};
cfg.grid_load_data_url = contextPath + '/salaryImport/findSalaryImportList.do';
cfg.grid_add_data_url = contextPath + '/salaryImport/insertSalaryImport.do';
cfg.grid_edit_data_url = contextPath + '/salaryImport/updateSalaryImport.do';
cfg.grid_delete_data_url = contextPath + '/salaryImport/deleteSalaryImportBySalaryImportId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "工资导入";
cfg.rowNum= 10;
cfg.dataId= 'salaryImportId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;//
//cfg.gridHeight= 'auto',

cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}