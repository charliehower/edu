var cfg = {};
cfg.grid_load_data_url = contextPath + '/dict/findDictList.do';
cfg.grid_add_data_url = contextPath + '/dict/insertDict.do';
cfg.grid_edit_data_url = contextPath + '/dict/updateDict.do';
cfg.grid_delete_data_url = contextPath + '/dict/deleteDictByDictId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "字典";
cfg.rowNum= 10;
cfg.dataId= 'id';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}