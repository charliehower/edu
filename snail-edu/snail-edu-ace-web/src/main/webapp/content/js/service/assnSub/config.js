var cfg = {};
cfg.grid_load_data_url = contextPath + '/assnSub/findAssnSubList.do';
cfg.grid_add_data_url = contextPath + '/assnSub/insertAssnSub.do';
cfg.grid_edit_data_url = contextPath + '/assnSub/updateAssnSub.do';
cfg.grid_delete_data_url = contextPath + '/assnSub/deleteAssnSubByAssnSubId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "申请社团学生列表";
cfg.rowNum= 10;
cfg.dataId= 'assnSubId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}