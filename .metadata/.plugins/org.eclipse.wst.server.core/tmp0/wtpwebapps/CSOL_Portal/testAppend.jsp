<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script language="javascript" type="text/jscript">

	function add_input_file(tbfile, index) {
		file_name = "file" + index;
		var tr_file = document.createElement("tr");
		var td_file = document.createElement("td");
		var input_file = document.createElement("input")
		input_file.setAttribute("type", "file")
		input_file.setAttribute("size", "50")
		input_file.setAttribute("name", file_name)

		td_file.appendChild(input_file)
		tr_file.appendChild(td_file)
		tbfile.appendChild(tr_file)
	}
	function add_one_file() {
		var tb_file = document.getElementById("upload_file");
		var count_file = document.getElementById("upload_file").childNodes.length;
		window.alert(steps_nums); 
		add_input_file(tb_file, count_file);
	}

</script>
</head>
<body>
	<div style="text-align: center" style="text-align: center">
		<form>
			<table width="90%">
				<tr>
					<td><BR /> <BR />
						<h5>配置文件上传</h5></td>
				</tr>
				<tr>
					<td>
						<table id="upload_file">
							<tr class="tabletext">
								<td><input type="file" name="job" size="50" /></td>
							</tr>
							<tr class="tabletext">
								<td><input type="file" name='file2' size="50" /></td>
							</tr>
							<tr class="tabletext">
								<td><input type="file" name='file3' size="50" /></td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td align="left"><input type="button" value="新增一行"
						onclick="add_one_file()" /> <input type="submit" value="保 存" /> <input
						name="ciname" value="{{ciname}}" /> <input name="type"
						value="default" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>