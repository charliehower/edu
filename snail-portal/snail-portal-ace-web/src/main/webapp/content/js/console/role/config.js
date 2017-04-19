var cfg = {};
cfg.grid_load_data_url = contextPath + '/role/findRoleList.do';
cfg.grid_add_data_url = contextPath + '/role/insertRole.do';
cfg.grid_edit_data_url = contextPath + '/role/updateRole.do';
cfg.grid_delete_data_url = contextPath + '/role/deleteRoleByRoleId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "角色";
cfg.rowNum= 10;
cfg.dataId= 'roleId';
cfg.gridHeight=(window.innerHeight-205);
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=310;
}
//alert(cfg.gridHeight);