<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>MyHtml.html</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">

<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

</head>
<script type="text/javascript">
	function getResult(stateVal) {

		//var url = "http://localhost:8080/CSOL_Portal/ajaxservlet?state=" + stateVal;
		
		var localObj = window.location;

		var contextPath = localObj.pathname.split("/")[1];

		var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

		var server_context=basePath;
	
		alert(server_context);
		var url = server_context + "/ajaxservlet?state=" + stateVal;
		alert(url);
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (req) {
			req.open("GET", url, true);
			req.onreadystatechange = complete;
			req.send(null);
		}
	}
	function complete() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				var city = req.responseXML.getElementsByTagName("city");
				//alert(city.length);
				var str = new Array();
				for (var i = 0; i < city.length; i++) {
					str[i] = city[i].firstChild.data;
				}
				//alert(document.getElementById("city"));
				buildSelect(str, document.getElementById("city"));
			}
		}
	}
	function buildSelect(str, sel) {
		sel.options.length = 0;
		for (var i = 0; i < str.length; i++) {
			sel.options[sel.options.length] = new Option(str[i], str[i])
		}
	}
</script>
<body>
	<select name="state" onChange="getResult(this.value)">
		<option value="">Select</option>>
		<option value="zj">ZEHJIANG</option>>
		<option value="zs">JIANGSU</option>>
	</select>
	<select id="city">
		<option value="">CITY</option>
	</select>
</body>
</html>