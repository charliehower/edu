var cssName = new Object();
var classesId = '';
jQuery(function($) {
	$("#classesId").combobox({
		onChange : function(n, o) {
			classesId = n;
			initEvents();
		}
	});
});
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
				events : [],
				editable : true,
				droppable : true, // this allows things to be dropped
				// onto the calendar !!!
				drop : function(date, allDay) { // this function is
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
	$('input[name=checkDate]').val(end.format("yyyy-MM-dd"));
	$('input[name=start]').val(start.format("yyyy-MM-dd"));
	$('input[name=end]').val(end.format("yyyy-MM-dd"));
	if (ev) {
		// $('textarea[name=title]').val(ev.title);
	}
	var dialog = $("#dialog-message")
			.removeClass('hide')
			.dialog(
					{
						modal : true,
						width : 900,
						height : 500,
						title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i><span id='_title'>每日考核<span></h4></div>",
						title_html : true,
						buttons : [

								{
									html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 保存",
									"class" : "btn btn-info btn-xs",
									id : "btn-submit",
									click : function() {
										if (oper == 'save') {
											insertGrowth(this);
										} else {
											updateGrowth(this, ev.id);
										}
									}
								}, {
									html : html,
									"class" : _class,
									click : function() {
										if (oper == 'save') {
											$(this).dialog("close");
										} else {
											deleteGrowthByGrowthId(ev.id);
										}

									}
								} ]
					});
	if (oper == 'update') {
		console.log(ev.id);
		initForm(ev.id);
	}

}
function initEvents() {

	$.ajax({
		type : "get",
		url : contextPath + "/growth/findGrowthList.do",
		data : {
			start : 0,
			limit : 9999999,
			classesId:classesId
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			var events=$('#calendar').fullCalendar('clientEvents');
			$.each(events, function(i, data) {
				$('#calendar').fullCalendar('removeEvents', data.id);
			});
			$.each(rst.list, function(i, data) {
				$('#calendar').fullCalendar('removeEvents', data.growthId);
				var obj = new Object();
				obj.id = data.growthId;
				obj.title = data.className + " "+data.headerMaster+"  "
						+ (data.totalScore == null ? '0' : +data.totalScore);
				obj.className = 'label-danger';
				obj.start = data.checkDate;
				obj.end = data.checkDate;
				$("#calendar").fullCalendar('renderEvent', obj, true);
				$('#_title').html(data.className);
			});
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}
function updateGrowth(dialog, id) {

	var o = new Object();
	var o = new Object();
	o.growthId = id;
	$.each($('#fm-growth').find('input'), function(n, obj) {
		o[obj.name] = obj.value;
	});
	$.ajax({
		type : "post",
		url : contextPath + "/growth/updateGrowth.do",
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
function insertGrowth(dialog) {
	var o = new Object();
	o.classesId = classesId;
	$.each($('#fm-growth').find('input'), function(n, obj) {
		o[obj.name] = obj.value;
	});
	// alert(JSON.stringify(o));
	$.ajax({
		type : "post",
		url : contextPath + "/growth/insertGrowth.do",
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
function deleteGrowthByGrowthId(id) {
	var o = new Object();
	o.id=id;
	$.ajax({
		type : "post",
		url : contextPath + "/growth/deleteGrowthByGrowthId.do",
		data : {
			jsons : JSON.stringify(o)
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

function initForm(id) {

	$.ajax({
		type : "post",
		url : contextPath + "/growth/selectGrowthByPrimaryKey.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			$('#fm-growth').form('load',rst.response);
			$('#_title').html(rst.response.className);
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});
}
