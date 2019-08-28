var cfg = {};
cfg.grid_load_data_url = contextPath + '/pm/findPmList.do';
cfg.grid_add_data_url = contextPath + '/pm/insertPm.do';
cfg.grid_edit_data_url = contextPath + '/pm/updatePm.do';
cfg.grid_delete_data_url = contextPath + '/pm/deletePmByPmId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "业绩";
cfg.rowNum= 10;
cfg.dataId= 'pmId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;//
//cfg.gridHeight= 'auto',

cfg.jgridEditWinWidth=600;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}