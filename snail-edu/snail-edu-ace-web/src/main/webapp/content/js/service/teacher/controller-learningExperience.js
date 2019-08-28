jQuery(function($) {
	$('#btn-learningExperience-save').on('click',function() {
		var json={};
		var rows=$('#dg-learningExperience').datagrid('getRows');
		json.list=rows;
		json.name=$("input[name='name']").val();
		$.ajax({
			type : "post",
			url : contextPath + "/learningExperience/saveOrUpdateLearningExperience.do",
			data:{jsons:JSON.stringify(json)},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				$('#dg-learningExperience').datagrid('reload');
				bootbox.dialog({
					title:'系统提示',
					message:rst.errorMessage,
					detail:rst.detail,
					buttons: 			
					{
						"success" :
						 {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback": function() {
								
							}
						}
					}
				});
			},
			complete : function(XMLHttpRequest, textStatus) {
				
			},
			error : function() {
			}
		});

	});	
	$('#tab-learningExperience').on('click',function() {
		setTimeout("showgd_learningExperience()",200);
		
	});	
	
});

function showgd_learningExperience(){
	$('#dg-learningExperience').datagrid('selectRow', editIndex).datagrid('beginEdit', -1);
}


