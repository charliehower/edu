var cfg = {};
cfg.grid_load_data_url = contextPath + '/workflow/findWorkflowList.do';
cfg.grid_add_data_url = contextPath + '/workflow/deploy.do';
cfg.grid_edit_data_url = contextPath + '/workflow/deploy.do';
cfg.grid_delete_data_url = contextPath + '/workflow/deleteWorkflowById.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "工作流";
cfg.rowNum= 10;
cfg.dataId= 'deploymentId';
cfg.gridHeight=window.innerHeight-273;//
//cfg.gridHeight= 'auto',

cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}