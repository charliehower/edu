var cfg = {};
cfg.grid_load_data_url = contextPath + '/student/findStudentList.do';
cfg.grid_add_data_url = contextPath + '/student/insertStudent.do';
cfg.grid_edit_data_url = contextPath + '/student/updateStudent.do';
cfg.grid_delete_data_url = contextPath + '/student/deleteStudentByStudentId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "学生";
cfg.rowNum= 10;
cfg.dataId= 'studentId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}