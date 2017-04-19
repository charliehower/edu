var cfg = {};
cfg.grid_load_data_url = contextPath + '/location/findLocationList.do';
cfg.grid_add_data_url = contextPath + '/location/insertLocation.do';
cfg.grid_edit_data_url = contextPath + '/location/updateLocation.do';
cfg.grid_delete_data_url = contextPath + '/location/deleteLocationByLocationId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "位置";
cfg.rowNum= 10;
cfg.dataId= 'locationId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}