var cfg = {};
cfg.grid_load_data_url = contextPath + '/discipline/findDisciplineList.do';
cfg.grid_add_data_url = contextPath + '/discipline/insertDiscipline.do';
cfg.grid_edit_data_url = contextPath + '/discipline/updateDiscipline.do';
cfg.grid_delete_data_url = contextPath + '/discipline/deleteDisciplineByDisciplineId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "学科";
cfg.rowNum= 10;
cfg.dataId= 'disciplineId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}