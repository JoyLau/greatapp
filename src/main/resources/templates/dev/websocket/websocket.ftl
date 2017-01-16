<#assign path=request.contextPath />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <script type="application/javascript" src="${path}/static/js/test/websocket/sockjs.min.js"></script>
    <script type="application/javascript" src="${path}/static/js/test/websocket/stomp.min.js"></script>
    <script type="application/javascript" src="${path}/static/js/test/websocket/jquery.js"></script>
    <title>Spring Boot+WebSocket+广播式</title>
  </head>

  <body onload="disconnect()">
  <noscript><h2 style="color: #ff0000">貌似你的浏览器不支持websocket</h2></noscript>
  <div>
      <div>
          <button id="connect" onclick="connect();">连接</button>
          <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
      </div>
      <div id="conversationDiv">
          <label>输入你的名字</label><input type="text" id="name" />
          <button id="sendName" onclick="sendName();">发送</button>
          <p id="response"></p>
      </div>
  </div>
  </body>
</html>
<script type="application/javascript">
    var stompClient = null;

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        $('#response').html();
    }

    function connect() {
        var socket = new SockJS('/anon/socket'); //1
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/getResponse', function(respnose){ //2
                showResponse(JSON.parse(respnose.body).responseMessage);
            });
        });
    }


    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendName() {
        var name = $('#name').val();
        //3
        stompClient.send("/welcome", {}, JSON.stringify({ 'name': name }));
    }

    function showResponse(message) {
        var response = $("#response");
        response.html(message);
    }
</script>