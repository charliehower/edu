var cfg = {};
cfg.grid_load_data_url = contextPath + '/classes/findClassesList.do';
cfg.grid_add_data_url = contextPath + '/classes/insertClasses.do';
cfg.grid_edit_data_url = contextPath + '/classes/updateClasses.do';
cfg.grid_delete_data_url = contextPath + '/classes/deleteClassesByClassesId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "班级";
cfg.rowNum= 10;
cfg.dataId= 'classesId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}