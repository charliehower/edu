<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>resources</title>
</head>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet" href="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<style type="text/css">
		.excel{ background-color:#999; font-size:13px;}
		.excel td{ background-color:#fff; white-space:nowrap;}
		.excel th{ background-color:#E7E7E7; font-weight:normal;}
	</style>
<body>
	<div class="page-content">
	<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form action="#" id="fm-search">
					上级资源： 
							<input id="cc1" name="parentResourcesId" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/resources/selectResourcesTreeList.do?id=01',method:'get',animate: true,
                lines:true," style='width:200px;line-height: 30px;height: 30px;'>
							<a href="javascript:clearQparams()">清除</a>
						资源名称： <input name="resourcesName" type="text" maxlength="20"
							style="width: 200px;"/>
								<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/resources/findResourcesList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/resources/insertResources.do">
							<i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/resources/updateResources.do">
							<i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/resources/deleteResourcesByResourcesId.do">
							<i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-import"
							authority="${pageContext.request.contextPath}/resources/insertResources.do">
							Excel导入<i
								class="ace-icon glyphicon glyphicon-upload  align-top bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	<div class="easyui-layout" id="cc" style="width:100%;height:300px;">
	
		

		<div data-options="region:'center',title:'',iconCls:'icon-ok'" id="easyui-center">
			<table id="grid-table"></table>
	
			<div id="grid-pager"></div>
		</div>
		<div id="cc-west" class="easyui-west" data-options="region:'west',split:true" title="我的树" style="width:200px;">
		 <ul id="tt" class="easyui-tree" data-options="
               url: '${pageContext.request.contextPath}/resources/selectResourcesTreeList.do?id=01',
                method: 'get',
                animate: true,
                lines:true,
                onContextMenu: function(e,node){
                    e.preventDefault();
                    $(this).tree('select',node.target);
                    autotreeq(node);
                    $('#mm').menu('show',{
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            "></ul>
		
		</div>
		<!--  
		<div data-options="region:'south',split:true" style="height:50px;"></div>
        <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
        -->
</div>
	</div>

<div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="treeappend()" data-options="iconCls:'icon-add'">添加</div>
        <div onclick="treeedit()" data-options="iconCls:'icon-edit'">编辑</div>
        <div onclick="treeremove()" data-options="iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div onclick="treereload()" data-options="iconCls:'icon-refresh'">刷新</div>
        <div onclick="expand()">展开</div>
        <div onclick="collapse()">收回</div>
    </div>
    <script type="text/javascript">
       
    </script>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/console/resources/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/console/resources/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/console/resources/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/console/resources/view.js"></script>

	<jsp:include page="../../common/footer-2.jsp" />

<script type="text/javascript">
window.onresize = function () {
	setTimeout("autoResize()",100);
}
function autoResize(){
	jQuery('.panel-tool').find('a').on( 'click', function(e) {
		setTimeout("autoResize()",1000);
	});
	var h=window.innerHeight-150;
	$('#cc').layout('resize', {
		width:'100%',
		height:h
	});
	$('#cc').css("height",h);
	$(cfg.grid_selector).jqGrid('setGridHeight', h-60);
	var display=$('#cc-west').css('display');
	console.log(display)
	if(display=='none'){
		$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	}else{
		$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width()-200);
	}
	console.log('autoResize:'+h);
}
jQuery(function($) {
	jQuery('.layout-button-left').on( 'click', function(e) {
		setTimeout("autoResize()",1000);
	});
	
});

</script>

<div id="dialog-message" class="hide">
		
		<div id="uploader">
    <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
</div>
	
 <div style="margin:5px">

		           <a href="rs.xls" style="color:red">下载模板</a>.<br>
		      
		
 </div>



		</div>
<script
		src="${pageContext.request.contextPath}/content/js/console/resources/upload.js"></script>
		<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/plupload.full.min.js"></script>
			<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="${sessionScope.portalContextPath}/content/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
</body>
</html>