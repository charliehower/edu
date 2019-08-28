var cfg = {};
cfg.grid_load_data_url = contextPath + '/grade/findGradeList.do';
cfg.grid_add_data_url = contextPath + '/grade/insertGrade.do';
cfg.grid_edit_data_url = contextPath + '/grade/updateGrade.do';
cfg.grid_delete_data_url = contextPath + '/grade/deleteGradeByGradeId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "年级";
cfg.rowNum= 10;
cfg.dataId= 'gradeId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}