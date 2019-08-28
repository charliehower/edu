var cfg = {};
cfg.grid_load_data_url = contextPath + '/repairsUsers/findRepairsUsersList.do';
cfg.grid_add_data_url = contextPath + '/repairsUsers/insertRepairsUsers.do';
cfg.grid_edit_data_url = contextPath + '/repairsUsers/updateRepairsUsers.do';
cfg.grid_delete_data_url = contextPath + '/repairsUsers/deleteRepairsUsersByRepairsUsersId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "维修人员";
cfg.rowNum= 10;
cfg.dataId= 'repairsUsersId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=650;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}