<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css"
	href="${sessionScope.portalContextPath}/content/jquery-easyui-1.3.6/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${sessionScope.portalContextPath}/content/jquery-easyui-1.3.6/themes/icon.css">
<script type="text/javascript"
	src="${sessionScope.portalContextPath}/content/jquery-easyui-1.3.6/gz/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${sessionScope.portalContextPath}/content/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript"
	src="${sessionScope.portalContextPath}/content/js/common/jquery.form.js"></script>
<script type="text/javascript"
	src="${sessionScope.portalContextPath}/content/js/common/authority.js"></script>


<style>

.textbox {
	border: 1px solid #D3D3D3;
	vertical-align: middle;
	/*border-radius: 5px;  css 3标准 */
	/*-moz-border-radius: 5px; /* mozilla */
	/*-webkit-border-radius: 5px;*/
}

.combo {
	display: inline-block;
	white-space: nowrap;
	margin: 0;
	padding: 0;
	border-width: 1px;
	border-style: solid;
	overflow: hidden;
	vertical-align: middle;
	/*border-radius: 5px; /* css 3标准 */
	/*-moz-border-radius: 5px; /* mozilla */
	/*-webkit-border-radius: 5px;*/
}
</style>