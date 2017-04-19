function addWorkflow(key, name, proxy) {
	$.ajax({
		type : "post",
		url : "/workflow/workflow/startProcessInstanceByKey.do",
		data : {
			key : key,
			proxy : proxy
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			if (rst.state) {
				initWorkflowList();
				var id = rst.response.id;
				console.log(rst);
				addPanel(name, rst.response.formResourceName + '?taskId=' + id,
						true);
			} else {
				alert(rst.errorMessage);
			}

		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});

}
