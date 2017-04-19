<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<title>消息推送模式与内容测试</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta http-equiv="Pragma" content="no-cache">
	<script type="text/javascript" src="<%=basePath %>content/pushlet_js/js-pushlet-client.js"></script>
    <script type="text/javascript">
     	var subscriptionId = null;
       	// 加入或离开
		function joinLeave(what) {
			if (what == 'joinStream') {
			  p_join_listen(null, 'stream');
			} else if (what == 'joinPull') {
			  p_join_listen(null, 'pull');
			} else if (what == 'joinPoll') {
			  p_join_listen(null, 'poll');
		    } else if (what == 'leave') {
			  p_leave();
			  subscriptionId = null;
			  displayData('无消息或离开状态');
		    }
		}
		// 产生消息
		function onData(event) {
			// Write the event as HTML table into content Element
	  		// p_debug(flag, "pushlet-app", 'event received subject=' + event.getSubject() );
			displayData(event.toString());
		}
		// 主题订阅成功
		function onSubscribeAck(event) {
			subscriptionId = event.get('p_sid');
			displayControl(event.toString());
			displayData('订阅成功，等待消息');
    	}
    	// Ack refresh
		function onRefreshAck(event) {
			displayData(event.toString());
    	}
	   	// Heartbeat on data channel
		function onHeartbeat(event) {
			displayData(event.toString());
    	}
		// 主题取消订阅成功
		function onUnsubscribeAck(event) {
			subscriptionId = null;
			displayControl(event.toString());
			displayData('取消订阅成功，无消息');
  		}
		// 捕获其他返回信息
		function onEvent(event) {
			displayControl(event.toString());
		}
		// 订阅主题
		function subscribeUnsubscribe(subject) {
		    if (p_getSessionId() == null) {
		      alert('请先选择加入模式');
		      return;
		    }
		    if (subject == 'UNSUBSCRIBE') {
		       // Unsubscribe all
			   p_unsubscribe();
			} else if (subject != '') {
				// Unsubscribe first if already subscribed
				if (subscriptionId != null) {
					p_unsubscribe(subscriptionId);
				}
				p_subscribe(subject, 'mylabel');
			}
 		}
		// 选择调试
		function setDebug(value) {
			if (value == 'none') {
				p_setDebug(false);
			} else if (value == 'full') {
				p_setDebug(true);
			} else if (value == 'net') {
				p_setDebug(false);
				p_setNetDebug(true);
			}
 		}
 		function displayData(aString) {
 		  document.dataEventDisplay.event.value = aString;
		}
   		function displayControl(aString) {
 		  document.controlEventDisplay.event.value = aString;
		}
	</script>
</head>
<body>
  <h2>消息推送测试</h2>
  <p>通过以下表单控制消息服务</p>
  <table border="0" cellpadding="0" cellspacing="0" >
    <tr>
    <td>
       <h3>加入/离开</h3>
     </td>
     <td>
       <h3>主题</h3>
     </td>
      <td>
       <h3>调试窗口</h3>
     </td>
    </tr>
    <tr>
    <td>
       <form name="joinForm">
         <select name="joinField" onChange="joinLeave(this.value)">
           <option selected value="" >请选择</option>
           <option value="joinStream">加入 (stream mode)</option>
           <option value="joinPull">加入 (pull mode)</option>
           <option value="joinPoll">加入 (poll mode)</option>
           <option value="leave">离开</option>
         </select>
       </form>
     </td>
     <td>
       <form name="subscribeForm">
         <select name="selectField" onChange="subscribeUnsubscribe(this.value)">
           <option selected value="" >选择主题</option>
           <option value="/system">系统信息</option>
           <option value="/temperature">天气情况</option>
           <option value="/stocks">网络连接</option>
           <option value="/stocks,/temperature">多主题订阅</option>
           <option value="/">所有消息</option>
          <option value="UNSUBSCRIBE">卸载主题</option>
          </select>
       </form>
     </td>
      <td>
        <form name="debugForm">
          <select name="selectField" onChange="setDebug(this.value)">
           <option selected value="none" >无调试</option>
           <option value="full">所有调试</option>
           <option value="net">进调试网络</option>
          </select>
        </form>
     </td>
    </tr>
  </table>
<h3>控制和监控</h3>
<p>
以下是事件监听情况，你需要先加入才能看到效果
</p>
<form name="controlEventDisplay">
  <table border="2" bordercolor="white" cellpadding="0" cellspacing="0" >
    <tr>
     <td>
       <textarea cols="40" rows="8" name="event">
        等待消息
       </textarea>
     </td>
    </tr>
  </table>
</form>
<h3>消息监控</h3>
<p>
以下是事件产生的消息内容，你需要选择主题后才能看到消息内容
</p>
<form name="dataEventDisplay">
  <table border="2" bordercolor="white" cellpadding="0" cellspacing="0" >
    <tr>
     <td>
       <textarea cols="60" rows="10" name="event">
        没有消息
       </textarea>
     </td>
    </tr>
  </table>
</form>
<script type="text/javascript">p_embed()</script>
</body>
</html>