var cssName = new Object();
cssName['#ac725e'] = 'label-danger';
cssName["#d06b64"] = 'label-danger';
cssName["#f83a22"] = 'label-danger';
cssName["#fa573c"] = 'label-danger';
cssName["#ff7537"] = 'label-danger';

cssName["#ffad46"] = 'label-yellow';
cssName["#42d692"] = 'label-success';
cssName["#16a765"] = 'label-success';
cssName["#7bd148"] = 'label-success';
cssName["#b3dc6c"] = 'label-success';

cssName["#fbe983"] = 'label-yellow';
cssName["#fad165"] = 'label-yellow';

cssName["#92e1c0"] = 'label-info';
cssName["#9fe1e7"] = 'label-info';
cssName["#9fc6e7"] = 'label-info';
cssName["#4986e7"] = 'label-info';

cssName["#9a9cff"] = 'label-purple';
cssName["#b99aff"] = 'label-purple';

cssName["#c2c2c2"] = 'label-grey';
cssName["#cabdbf"] = 'label-grey';

cssName["#cca6ac"] = 'label-grey';
cssName["#f691b2"] = 'label-purple';
cssName["#cd74e6"] = 'label-purple';
cssName["#a47ae2"] = 'label-purple';
cssName["#555"] = 'label-grey';

var cssNameRes = new Object();

cssNameRes['label-danger']='#f83a22';
cssNameRes['label-yellow']='#ffad46';
cssNameRes['label-success']='#16a765';
cssNameRes['label-info']='#9fc6e7';
cssNameRes['label-grey']='#cabdbf';
cssNameRes['label-yellow']='#ffad46';
cssNameRes['label-yellow']='#ffad46';
var category ='';
jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	$('#date-timepicker1').datetimepicker().next().on(ace.click_event,
			function() {
				$(this).prev().focus();
			});
	$('#date-timepicker2').datetimepicker().next().on(ace.click_event,
			function() {
				$(this).prev().focus();
			});
	$('.date-picker').datepicker({
		autoclose : true,
		todayHighlight : true
	})
	$('#simple-colorpicker-1').ace_colorpicker();
	$('textarea.limited').inputlimiter({
		remText : '%n character%s remaining...',
		limitText : 'max allowed : %n.'
	});
	initDepUserList();
});
var calendar = {};
jQuery(function($) {

	/*
	 * initialize the external events
	 * -----------------------------------------------------------------
	 */

	$('#external-events div.external-event').each(function() {

		// create an Event Object
		// (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
		// it doesn't need to have a start or end
		var eventObject = {
			title : $.trim($(this).text())
		// use the element's text as the event title
		// title:'fdsa'
		};

		// store the Event Object in the DOM element so we can get to it later
		$(this).data('eventObject', eventObject);

		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex : 999,
			revert : true, // will cause the event to go back to its
			revertDuration : 0
		// original position after the drag
		});

	});

	/*
	 * initialize the calendar
	 * -----------------------------------------------------------------
	 */

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();

	calendar = $('#calendar').fullCalendar(
			{
				// isRTL: true,
				buttonText : {
					prev : '<i class="ace-icon fa fa-chevron-left"></i>',
					next : '<i class="ace-icon fa fa-chevron-right"></i>'
				},

				header : {
					left : 'prev,next today',
					center : 'title',
					right : 'month,agendaWeek,agendaDay'
				},
				events : function(start, end, timezone, callback) {

				},
				defaultView:'agendaWeek',
				editable : true,
				eventDrop : function(event, dayDelta, minuteDelta, allDay,
						revertFunc) {
					console.log("=========eventDrop==========");
					updateForEv(event,revertFunc);

				},

				eventResize : function(event, dayDelta, minuteDelta,
						revertFunc, jsEvent, ui, view) {
					console.log("=========eventResize==========");
				
					updateForEv(event,revertFunc);
				},
				drop : function(date, allDay) { // this function is
					console.log("=========drop==========");
					console.log(date);
					console.log(allDay);
					// called when something
					// is dropped

					// retrieve the dropped element's stored Event
					// Object
					var originalEventObject = $(this).data('eventObject');
					var $extraEventClass = $(this).attr('data-class');

					// we need to copy it, so that multiple events don't
					// have a reference to the same object
					var copiedEventObject = $.extend({}, originalEventObject);

					// assign it the date that was reported
					copiedEventObject.start = date;
					copiedEventObject.allDay = allDay;
					if ($extraEventClass)
						copiedEventObject['className'] = [ $extraEventClass ];

					// render the event on the calendar
					// the last `true` argument determines if the event
					// "sticks"
					// (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
					$('#calendar').fullCalendar('renderEvent',
							copiedEventObject, true);

					// is the "remove after drop" checkbox checked?
					if ($('#drop-remove').is(':checked')) {
						// if so, remove the element from the "Draggable
						// Events" list
						$(this).remove();
					}

				},
				selectable : true,
				selectHelper : true,
				select : function(start, end, allDay) {
					saveOrUpdate(calendar, 'save', start, end);
					/*
					 * bootbox.prompt("新的日程:", function(title) { if (title !==
					 * '') { calendar.fullCalendar('renderEvent', { title:
					 * title, start: start, end: end, allDay: allDay }, true //
					 * make the event "stick" ); } });
					 */
					calendar.fullCalendar('unselect', 'save');
				},
				eventClick : function(calEvent, jsEvent, view) {
					console.log(calEvent);
					console.log(jsEvent);
					saveOrUpdate(calendar, 'update', calEvent.start,
							calEvent.end, calEvent);

				}

			});

	initEvents();

})
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
function uuid() {
	var s = [];
	var hexDigits = "0123456789abcdef";
	for (var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	}
	s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
	s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the
	// clock_seq_hi_and_reserved
	// to 01
	s[8] = s[13] = s[18] = s[23] = "-";

	var uuid = s.join("");
	return uuid;
}
function saveOrUpdate(calendar, oper, start, end, ev) {
	console.log(ev);
	if (!start || !end) {
		start = ev.start;
		end = ev.start;
	}
	var html = "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消";
	var _class = "btn btn-default btn-xs";
	if (oper == 'save') {
		_class = "btn btn-default btn-xs";
		html = "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消";
	} else {
		_class = "btn btn-danger btn-xs";
		html = "<i class='ace-icon fa fa-trash-o bigger-110'></i>&nbsp; 删除";
	}

	$('#date-timepicker1').val(start.format("yyyy-MM-dd hh:mm:ss"));
	$('#date-timepicker2').val(end.format("yyyy-MM-dd hh:mm:ss"));
	if (ev) {
		$('textarea[name=title]').val(ev.title);
		$('textarea[name=content]').val(ev.content);
		
		var bg=cssNameRes[ev.className[0]];
		console.log(bg);
		$('select[name=backgroundColor]').val(bg);
		$("select[name=backgroundColor]").find("option[text='"+bg+"']").attr("selected",true);
	}
	var dialog = $("#dialog-message")
			.removeClass('hide')
			.dialog(
					{
						modal : true,
						width : 400,
						height : 450,
						title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>场地申请</h4></div>",
						title_html : true,
						buttons : [

								{
									html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 保存",
									"class" : "btn btn-info btn-xs",
									id : "btn-submit",
									click : function() {
										if (oper == 'save') {
											insertSiteApp(this);
										} else {
											updateSiteApp(this, ev.id);
										}
									}
								}, {
									html : html,
									"class" : _class,
									click : function() {
										if (oper == 'save') {
											$(this).dialog("close");
										} else {
											deleteSiteAppBySiteAppId(ev.id);
										}

									}
								} ]
					});
	if (oper == 'save') {
		$('textarea[name=title]').val("");
	}

}
function initEvents(_category) {
	if(_category){
		category=_category;
	}
	$.ajax({
		type : "post",
		url : contextPath + "/siteApp/findSiteAppList.do",
		data : {
			limit : 99999,
			totalRecord : 9999,
			page : 1,
			category : category
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			$('#calendar').fullCalendar('removeEvents');
			$.each(rst.list, function(i, data) {
				$('#calendar').fullCalendar('removeEvents', data.id);
				var obj = new Object();
				obj.id = data.id;
				obj.category = data.category;
				obj.content = data.content;
				obj.title = data.title;
				obj.className =data.className;
				obj.start = data.start;
				obj.end = data.end;
				obj.allDay = false;
				$("#calendar").fullCalendar('renderEvent', obj, true);
			});
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}
function initDepUserList() {
	/*
	 * $.ajax({ type : "post", url : contextPath +
	 * "/siteApp/selectDepUserListByDepId.do", data : { }, beforeSend :
	 * function(XMLHttpRequest) { }, success : function(rst, textStatus) { var
	 * html=new Array(); $.each(rst.list, function(i, data) { html.push("<div
	 * class='layout-user'><a href='javascript:initEvents(\"");
	 * html.push(data.id); html.push("\""); html.push(")'>");
	 * html.push(data.name); html.push("</a></div>"); });
	 * $("#users").html(html.join("")); //alert(html.join("")); }, complete :
	 * function(XMLHttpRequest, textStatus) { }, error : function() { } });
	 */

}
function updateSiteApp(dialog, id) {
	var o = new Object();
	o.id = id;
	o.title = $('textarea[name=title]').val();
	o.content = $('textarea[name=content]').val();
	o.start = $('input[name=start]').val();
	o.end = $('input[name=end]').val();
	o.className = cssName[$('select[name=backgroundColor]').val()];
	o.allDay = false;
	if (o.title == '') {
		alert("标题不能为空！");
		return;
	}
	if (o.start == '') {
		alert("开始时间不能为空！");
		return;
	}
	if (o.end == '') {
		alert("截止时间不能为空！");
		return;
	}
	$.ajax({
		type : "post",
		url : contextPath + "/siteApp/updateSiteApp.do",
		data : {
			jsons : JSON.stringify(o)
		},
		beforeSend : function(XMLHttpRequest) {
			style_ajax_button('btn-submit', true);
		},
		success : function(rst, textStatus) {
			initEvents();
			$(dialog).dialog("close");
			alert(rst.errorMessage);
			style_ajax_button('btn-submit', false);
		},
		complete : function(XMLHttpRequest, textStatus) {
			style_ajax_button('btn-submit', false);
		},
		error : function() {
			style_ajax_button('btn-submit', true);
		}
	});
}
function insertSiteApp(dialog) {
	console.log(calendar.events);
	var o = new Object();
	o.title = $('textarea[name=title]').val();
	o.start = $('input[name=start]').val();
	o.end = $('input[name=end]').val();
	o.content = $('textarea[name=content]').val();
	o.className = cssName[$('select[name=backgroundColor]').val()];
	o.allDay = false;
	o.category =category;
	if (o.category==null||o.category == '') {
		alert("请选择场地！");
		return;
	}
	if (o.title == '') {
		alert("标题不能为空！");
		return;
	}
	if (o.start == '') {
		alert("开始时间不能为空！");
		return;
	}
	if (o.end == '') {
		alert("截止时间不能为空！");
		return;
	}
	$.ajax({
		type : "post",
		url : contextPath + "/siteApp/insertSiteApp.do",
		data : {
			jsons : JSON.stringify(o)
		},
		beforeSend : function(XMLHttpRequest) {
			style_ajax_button('btn-submit', true);
		},
		success : function(rst, textStatus) {
			initEvents();
			$(dialog).dialog("close");
			alert(rst.errorMessage);
			style_ajax_button('btn-submit', false);
		},
		complete : function(XMLHttpRequest, textStatus) {
			style_ajax_button('btn-submit', false);
		},
		error : function() {
			style_ajax_button('btn-submit', true);
		}
	});
}
function deleteSiteAppBySiteAppId(id) {

	$.ajax({
		type : "post",
		url : contextPath + "/siteApp/deleteSiteAppBySiteAppId.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			$('#calendar').fullCalendar('removeEvents', id);
			$("#dialog-message").dialog("close");
			initEvents();
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}

jQuery(function($) {
	/*
	 * $('#tree-dept').tree( { checkbox : false, url : contextPath +
	 * '/users/selectGroupDepTreeByPid.do', onBeforeExpand : function(node,
	 * param) { $('#tree-dept').tree('options').url = contextPath +
	 * '/users/selectGroupDepTreeByPid.do?pid=' + node.id; }, onClick :
	 * function(node) { initEvents(node.id) } });
	 */
	var DataSourceTree = function(options) {
		this.url = options.url;
	}

	DataSourceTree.prototype.data = function(options, callback) {
		var self = this;
		var $data = null;

		var param = null;

		if (!("name" in options) && !("type" in options)) {
			param = ""; // load the first level
		} else if ("type" in options && options.type == "folder") {
			if ("additionalParameters" in options
					&& "children" in options.additionalParameters) {
				param = options.additionalParameters["id"];
			}
		}

		if (true) {
			$.ajax({
				url : this.url,
				data : 'pid=' + param,
				type : 'POST',
				dataType : 'json',
				success : function(response) {
					// console.log(response);
					if (response.status == "OK")
						callback({
							data : response.data
						})
				},
				error : function(response) {
					console.log(response);
				}
			})
		}
	};

	$('#tree-dept')
			.ace_tree(
					{
						dataSource : new DataSourceTree({
							url : contextPath
									+ '/siteApp/selectSiteTreeByPid.do'
						}),
						multiSelect : false,
						loadingHTML : '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>',
						'open-icon' : 'ace-icon tree-minus',
						'close-icon' : 'ace-icon tree-plus',
						'selectable' : true,
						'selected-icon' : 'ace-icon fa fa-check',
						'unselected-icon' : 'ace-icon fa fa-times'
					});
	$('#tree-dept').on('selected', function(evt, data) {

		console.log(JSON.stringify(data));
		category=data['info'][0]['id'];
		var flag=data['info'][0]['additionalParameters'].src;
		var msg=flag=="0"?"周一至周五":"周一至周日";
		$("#_title").html(data['info'][0]['name']);
		$("#_subtitle").html(msg+data['info'][0]['additionalParameters'].href);
		initEvents(data['info'][0]['id']);
	})
});
function updateForEv(event,revertFunc){

	var o = new Object();
	o.id = event.id;
	o.title = event.title;
	o.content = event.content;
	o.start = event.start.format("yyyy-MM-dd hh:mm:ss");
	o.end = event.end.format("yyyy-MM-dd hh:mm:ss");
	o.className =event.className[0];
	o.allDay = false;
	console.log(o);
	if (!confirm("确定要变更吗?")) {
        revertFunc();
    }else{
    	$.ajax({
			type : "post",
			url : contextPath + "/siteApp/updateSiteApp.do",
			data : {
				jsons : JSON.stringify(o)
			},
			beforeSend : function(XMLHttpRequest) {
				
			},
			success : function(rst, textStatus) {
				initEvents();
				
			},
			complete : function(XMLHttpRequest, textStatus) {
				
			},
			error : function() {
				
			}
		});
    }
}