/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	// 在 CKEditor 中集成 CKFinder注意 ckfinder 的路径选择要正确。
	config.language = 'zh-cn'; // 配置语言
	config.toolbar = "Full";
	config.font_names = "宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;"
			+ config.font_names;
	// config.filebrowserBrowseUrl = '../ckfinder/ckfinder.html';
	// config.filebrowserImageBrowseUrl =
	// '../ckfinder/ckfinder.html?type=Images';
	// config.filebrowserFlashBrowseUrl =
	// '../ckfinder/ckfinder.html?type=Flash';
	config.filebrowserUploadUrl = contextPath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
	config.filebrowserImageUploadUrl = contextPath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	config.filebrowserFlashUploadUrl = contextPath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	config.filebrowserWindowWidth = '800';
	config.filebrowserWindowHeight = '500';
	config.toolbar = 'MyToolbar';
	config.toolbar_MyToolbar = [
			{
				name : 'document',
				items : [ 'Source', 'NewPage', 'Preview' ]
			},
			{
				name : 'basicstyles',
				items : [ 'Bold', 'Italic', 'Strike', '-', 'RemoveFormat' ]
			},
			{
				name : 'clipboard',
				items : [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord',
						'-', 'Undo', 'Redo' ]
			},
			{
				name : 'editing',
				items : [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ]
			},
			'/',
			{
				name : 'styles',
				items : [ 'Styles', 'Format','Font' ]
			},
			{
				name : 'paragraph',
				items : [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', 'NumberedList', 'BulletedList', '-', 'Outdent',
							'Indent', '-', 'Blockquote' ]
			},
			{
				name : 'insert',
				items : [ 'Image', 'Flash', 'Table', 'HorizontalRule',
						'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ]
			}, {
				name : 'links',
				items : [ 'Link', 'Unlink', 'Anchor' ]
			}, {
				name : 'tools',
				items : [ 'Maximize', '-', 'About' ]
			} ];
	config.toolbarGroups = [
	                		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
	                		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
	                		{ name: 'links' },
	                		{ name: 'insert' },
	                		{ name: 'forms' },
	                		{ name: 'tools' },
	                		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
	                		{ name: 'others' },
	                		'/',
	                		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
	                		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
	                		{ name: 'styles' },
	                		{ name: 'colors' },
	                		{ name: 'about' }
	                	];

	                	// Remove some buttons provided by the standard plugins, which are
	                	// not needed in the Standard(s) toolbar.
	                	config.removeButtons = 'Underline,Subscript,Superscript';

	                	// Set the most common block elements.
	                	config.format_tags = 'p;h1;h2;h3;pre';

	                	// Simplify the dialog windows.
	                	config.removeDialogTabs = 'image:advanced;link:advanced';
};
