<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>connect&&chat@nexus</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js">
/*
http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js
http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js
http://code.jquery.com/jquery-1.7.1.min.js
 */
</script>
<script type="text/javascript">

function sendMessage(){
	reviceMessage();
	$.post("c.cc", {
	action : "say",
	say : $("#myMessageText").val()
	});
	$("#myMessageText").val('');
 	reviceMessage();
}
function reviceMessage(){
	$.post(
		"c.cc",
		{action : "listen"},
		function(data) {
			if(typeof(data) == 'string' && data != ''){
				$("#allMessageText").val($("#allMessageText").val() + data);
				var allMessageText = document.getElementById('allMessageText');
				$("#allMessageText").scrollTop(allMessageText.scrollHeight);
			}
		}
	);
}
function rename(){
	$.post("c.cc", {
		action : "rename",
		rename : $("#myrename").val()
	});
 	reviceMessage();
}
$(document).ready(function() {
	// window.setTimeout(reviceMessage,1);
	window.setInterval(reviceMessage,1000);
	$('#sendbtn').click(function() {
		sendMessage();
	});
	$("#myMessageText").keypress(function(event) {
		if ( event.which == 13 ) {
			sendMessage();
		}
	});
	//rename
	$("#rename").click(function() {
		rename();
	});
	$("#myrename").keypress(function(event) {
		if ( event.which == 13 ) {
			rename();
		}
	});
	//connect
	$("#connect").click(function() {
		$.post("c.cc", {
			action : "connect"
			});
	});
	//connect
	$("#disconnect").click(function() {
		$.post("c.cc", {
			action : "disconnect"
			});
	});
});
</script>
<style type="text/css">
.myMessageText {
	width: 80;
	size: 200;
}
</style>
</head>
<body>
<fmt:setBundle basename="org.caofei.cc.message" />
	<h2>connect&&chat@nexus</h2>
	<div id="allMessage">
		<textarea id="allMessageText" rows="20" cols="100" readonly="readonly"></textarea>
<form action="http://www.google.com/cse" id="cse-search-box" target="_blank">
  <div>
    <input type="hidden" name="cx" value="partner-pub-9875729770788923:2440311077" />
    <input type="hidden" name="ie" value="UTF-8" />
    <input type="text" name="q" size="55" />
    <input type="submit" name="sa" value="Search" />
  </div>
</form>
<script type="text/javascript" src="http://www.google.com/coop/cse/brand?form=cse-search-box&amp;lang=en"></script>
	</div>
	<div id="myMessage">
		text<input id="myMessageText" type="text">
		<input id="sendbtn" type="button" value='<fmt:message key="cc.send"/>'>
		<br><br>
		name<input id="myrename" type="text">
		<input id="rename" type="button" value='<fmt:message key="cc.rename"/>'>
		<br><br>
		<input id="connect" type="button" value='<fmt:message key="cc.connect"/>'>
		<input id="disconnect" type="button" value='<fmt:message key="cc.disconnect"/>'>
	</div>
<script type="text/javascript"><!--
google_ad_client = "ca-pub-9875729770788923";
/* cc */
google_ad_slot = "0408999584";
google_ad_width = 336;
google_ad_height = 280;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-21581713-1']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
</body>
</html>
