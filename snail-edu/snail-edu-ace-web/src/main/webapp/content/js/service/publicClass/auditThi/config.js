var cfg = {};
cfg.grid_load_data_url = contextPath + '/publicClass/findPublicClassList.do?auditSecStatus=1';
cfg.grid_add_data_url = contextPath + '/publicClass/insertPublicClass.do';
cfg.grid_edit_data_url = contextPath + '/publicClass/updatePublicClass.do';
cfg.grid_delete_data_url = contextPath + '/publicClass/deletePublicClassByPublicClassId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "公开课";
cfg.rowNum= 10;
cfg.dataId= 'publicClassId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;//
//cfg.gridHeight= 'auto',

cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}