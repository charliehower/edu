var cfg = {};
cfg.grid_load_data_url = contextPath + '/quit/findQuitList.do';
cfg.grid_add_data_url = contextPath + '/quit/insertQuit.do';
cfg.grid_edit_data_url = contextPath + '/quit/saveOrUpdateQuit.do';
cfg.grid_delete_data_url = contextPath + '/quit/deleteQuitByQuitId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "教职工";
cfg.rowNum= 10;
cfg.dataId= 'teacherId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;//
//cfg.gridHeight= 'auto',

cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}