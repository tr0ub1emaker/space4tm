<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	function test(tablename) {

		var url = "http://localhost:8080/CSOL_Portal/testdbservlet?tablename="
				+ tablename;

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

	function getVar() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				 
			}
		}
	}
</script>

</head>
<body>
	<font style="font-family: 微软雅黑;">
								<strong>Envirment &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;:
								</strong>
							</font>
	<select>
		<option selected="selected">Production</option>
		<option></option>
	</select>
	
</body>
</html>