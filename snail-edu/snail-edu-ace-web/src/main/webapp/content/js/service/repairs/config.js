var cfg = {};
cfg.grid_load_data_url = contextPath + '/repairs/findRepairsList.do';
cfg.grid_add_data_url = contextPath + '/repairs/insertRepairs.do';
cfg.grid_edit_data_url = contextPath + '/repairs/updateRepairs.do';
cfg.grid_delete_data_url = contextPath + '/repairs/deleteRepairsByRepairsId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "报修";
cfg.rowNum= 10;
cfg.dataId= 'repairsId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=650;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}