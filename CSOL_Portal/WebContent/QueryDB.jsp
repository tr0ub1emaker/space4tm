<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose Query Model</title>

<!-- The style of Name and Value in Column
<style type="text/css">

/*此段style应用于Manully的columns内容*/

table.formdata{
    border:1px solid #5F6F7E;
    border-collapse:collapse;
    font-family:Arial;
}
table.formdata caption{       /*标题*/
    text-align:left;
    padding-bottom:6px;
}
table.formdata th{           /*行列标题*/
    border:1px solid #5F6F7E;
    background-color:#E2E2E2;
    color:#000000;
    text-align:left;
    font-weight:normal;
    padding:2px 8px 2px 6px;
    margin:0px;
}
table.formdata td{         /*表格行*/
    margin:0px;
    padding:0px;
    border:1px solid #ABABAB;   /* 单元格边框 */
}
table.formdata input{          /*可输入区域样式*/
    width:80px;
    padding:1px 3px 1px 3px;
    margin:0px;
    border:none;                /* 输入框不要边框 */
    font-family:Arial;
}

</style>
 -->

<script type="text/javascript">

	<!--根据选择的Query mode，使manully或selected的div显示或隐藏-->
	function changeMode() {

		var query_mode = document.getElementById("query_mode").value;

		if (query_mode == "manully") {

			document.getElementById("manually_tables").style.display = "";
			document.getElementById("midhr").style.display = "";
			document.getElementById("selected_tables").style.display = "none";

		} else if (query_mode == "selected") {

			document.getElementById("manually_tables").style.display = "none";
			document.getElementById("midhr").style.display = "none";
			document.getElementById("selected_tables").style.display = "";
		}

	}

	/* 点击add按钮，增加一行Column的输入框 第一种方法 目前不使用
	function closeFunction() {

		var content = document.getElementById("column_tbody").innerHTML;
		alert(content);
		var newInput = "<tr><td><strong><font style='font-family: Calibri; '>Name</font></strong></td><td><input type='text'></td><td><strong><font style='font-family: Calibri; '>Value</font></strong></td><td><input type='text'></td></tr>";
		//alert(newInput);
		content = content + newInput;
		document.getElementById("column_tbody").innerHTML = content;

	}
	 */

	/*点击Add按钮正在使用的函数*/
	/*使用appendchild方法，不会影响之前的输入内容*/
	function addInput() {

		/*模版--分为如下四行：
		<tr style="width: 430px;" id="tr1" name="test">
			<td><strong><font>Name</font></strong></td>
			<td><input type="text" id="n1"></td>
			<td><strong><font>Value</font></strong></td>
			<td><input type="text" id="v1"></td>
		</tr>
		 */

		/*创建所有tr标签*/
		var tr = document.createElement("tr");

		/*第一行td： Name*/
		var td_Name = document.createElement("td");
		var strong1 = document.createElement("strong");
		var font1 = document.createElement("font");
		var text_Name1 = document.createTextNode("Name");

		/*第二行td，为Name的输入框*/
		var td_text = document.createElement("td");
		var input_text = document.createElement("input");

		/*第三行td，Value*/
		var td_Value = document.createElement("td")
		var strong2 = document.createElement("strong");
		var font2 = document.createElement("font");
		var text_Value = document.createTextNode("Value");

		/*第四行td, Value输入框*/
		var td_vtext = document.createElement("td");
		var input_value = document.createElement("input");

		/*delete标签*/
		var td_delete = document.createElement("a");
		var text_delete = document.createTextNode("Delete");

		/*获取tbody中的所有tr*/
		var lc = document.getElementById("column_tbody").getElementsByTagName("tr");

		//获取tbody中最后一个tr（为了获取id）
		var lastTr = document.getElementById("column_tbody").lastChild;

		//idNum： 为第二个及以后tr的id赋值时后面的数据，比如tr2，tr3，tr4… 中的2、3、4
		var idNum;
		//lastId： tbody中最后一个子节点（tr）的id
		var lastId;

		//定义一组字符串，为了拆分lastId
		var strs= new Array();
		var lastNum;
		/*
			这样做的目的是，当用户在添加的过程中删除时，避免再次添加时的赋值重复。
			比如已添加十条，删除了第六条，按照第一种方法，再添加时会按照length大小设置id
			彼时将会把新加行的id设置成10.但此时其实已经有了id为10的一条，
			所以目前使用获取tbody最后一个子节点的id号码去设置下一个id
		*/
		//获取最后一个子节点的id，并解析到id'中的数字
		if (lc.length == 1) {

			lastNum = 2;

		}else {

			lastId = lastTr.getAttribute("id");
			strs = lastId.split("r");
			lastNum = ++strs[1];
			//alert(lastNum);
		}

		/*设置随后添加的标签的id*/
		tr.setAttribute("id", "tr" + lastNum);
		input_text.setAttribute("id", "n" + lastNum);
		input_value.setAttribute("id", "v" + lastNum);

		td_delete.setAttribute("href", "javascript:void(0);");
		td_delete.setAttribute("onclick", "delCol(" + lastNum + ")");

		/*开始装载节点
		 */
		/*Name部分*/
		font1.appendChild(text_Name1);
		strong1.appendChild(font1);
		td_Name.appendChild(strong1);
		tr.appendChild(td_Name);

		td_text.appendChild(input_text);
		tr.appendChild(td_text);

		/*Value部分*/
		font2.appendChild(text_Value);
		strong2.appendChild(font2);
		td_Value.appendChild(strong2);
		tr.appendChild(td_Value);

		td_vtext.appendChild(input_value);
		tr.appendChild(td_vtext);

		td_delete.appendChild(text_delete);
		tr.appendChild(td_delete);

		document.getElementById("column_tbody").appendChild(tr);

	}

	//删除这一行tr标签
	function delCol(id) {
		var trstring = "tr" + id;
		//alert(id);
		document.getElementById(trstring).onclick = function() {
			this.parentNode.removeChild(this);
		}
	}

	<!--  -->
	//以下为selected Mode的js
	//根据用户select的table名，改变下面的Column的list值
	function changeColumn(tableName){
		//alert(tableName);
		document.getElementById("selected_table_gtm");

		//获取path
		var localObj = window.location;
		var contextPath = localObj.pathname.split("/")[1];
		var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
		var server_context=basePath;

		//带有servlet的url
		var url = server_context + "/changetable?table=" + tableName;
		alert(url);
		//如下均为固定
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
				var column = req.responseXML.getElementsByTagName("column");

				alert(column.length);
				var str = new Array();
					for (var i = 0; i < column.length; i++) {
						str[i] = column[i].firstChild.data;
						alert(str[i]);
					}

				}
			}
	}
</script>

</head>
<body>

	<!-- 选择Envirment和QueryMode部分 -->
	<div id="out_choose_mode"
		style="width: 1000px; margin: auto auto; padding-top: 100px;">

		<div id="in_choose_mode" style="width: 700px; text-align: left;">
			<form>
				<table>
					<tr>
						<td><font style="font-family: verdana;"> <strong>Envirment
									&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;: </strong>
						</font></td>
						<td><font style="">&nbsp;&nbsp; <!-- Envirment Select -->
								<select id="select_env" style="width: 150px;">
									<option value="pro" selected="selected">Production</option>
									<option value="psub">PSUB</option>
									<option value="sit">SIT</option>
									<option value="dev">DEV</option>
							</select>
						</font></td>
					</tr>
					<tr>
						<td><font style="font-family: verdana;">
							<strong>Query Mode &nbsp;&nbsp;&nbsp;&nbsp;: </strong>
						</font></td>
						<td><font>&nbsp;&nbsp; <!-- Query Mode --> <select
								id="query_mode" style="width: 150px;">
									<option value="manully" selected="selected">Manully</option>
									<option value="selected">Selected</option>
							</select>
						</font> <input type="button" value="SUBMIT" onclick="changeMode()">
						</td>
					</tr>

				</table>
			</form>
		</div>
	</div>

	<center>
		<hr width="1000px" />
	</center>

	<!-- manully block -->
	<div id="" style="width: 1000px; margin: auto auto; padding-top: 20px;">
		<div id="manually_tables"
			style="width: 700px; text-align: left; display: none;">
			<form>
				<table>
					<tr>
						<td><font style="font-family: verdana;"> <strong>Schema
									&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
							</strong>
						</font></td>
						<td><font>&nbsp;&nbsp; <!-- Schema input--> <input
								type="text" id="manully_schema">
						</font></td>
					</tr>
					<tr>
						<td><font style="font-family: verdana;"> <strong>Table
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									: </strong>
						</font></td>
						<td><font>&nbsp;&nbsp; <!-- Table input--> <input
								type="text" id="manully_table">
						</font></td>
					</tr>
				</table>
				<br />

				<!-- Manully 部分的Column条件 -->
				<table>

					<tr>
						<td style="width: 100px;" valign="top"><font
							style="font-family: Calibri;"> <strong>Columns: </strong>
						</font></td>
						<td>
							<table class="formdata" id="column_properity">
								<tbody id="column_tbody">
									<tr style="width: 430px;" id="tr1" name="test">
										<td><strong><font>Name</font></strong></td>
										<td><input type="text" id="n1"></td>
										<td><strong><font>Value</font></strong></td>
										<td><input type="text" id="v1"></td>
									</tr>
								</tbody>
							</table>
						</td>

						<!-- Add和Submit两个按钮 -->
						<td valign="top"><input type="button" value="Add"
							onclick="addInput()"> <input type="button" value="Submit">
						</td>
					</tr>
				</table>


			</form>
			<br> <br>

		</div>
	</div>

	<center>
		<hr id="midhr" width="1000px" style="display: none" />
	</center>

	<!-- Selected block -->
	<div id="" style="width: 1000px; margin: auto auto;">
		<div id="selected_tables"
			style="width: 700px; text-align: left; display: none;">

			<form>
				<table>
					<tr>
						<td><font style="font-family: verdana;"> <strong>Schema
									&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
							</strong>
						</font></td>
						<td><font>&nbsp;&nbsp;
							<!-- Schema select-->
							<select id="selected_schema" style="width: 150px;">
								<option selected="selected">GTM</option>
							</select>
						</font></td>
					</tr>
					<tr>
						<td><font style="font-family: verdana;"> <strong>Table
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									: </strong>
						</font></td>
						<td><font>&nbsp;&nbsp;
							<!-- Table select-->
							<select id="selected_table_gtm" style="width:150px;" onchange="changeColumn(this.value)">
								<option selected="selected" value="BLI_INVOICE_LINE">BLI_INVOICE_LINE</option>
								<option value="BP_REF">BP_REF</option>
								<option value="BSE_CONTRACT">BSE_CONTRACT</option>
								<option value="BSE_FEATURE">BSE_FEATURE</option>
								<option value="BSE_INST_HARDWARE">BSE_INST_HARDWARE</option>
								<option value="BSE_LINE_ITEM">BSE_LINE_ITEM</option>
								<option value="BSE_MAINT_AGREE">BSE_MAINT_AGREE</option>
								<option value="BSE_MANUFACTURING">BSE_MANUFACTURING</option>
								<option value="BSE_ORDER">BSE_ORDER</option>
								<option value="BSE_ORDER_SHIP">BSE_ORDER_SHIP</option>
								<option value="BSE_SHIPMENT">BSE_SHIPMENT</option>
								<option value="BSE_SOFTWARE_SUB">BSE_SOFTWARE_SUB</option>
								<option value="CARRIER">CARRIER</option>
								<option value="CD_EVENTPARM">CD_EVENTPARM</option>
								<option value="CD_TRANSLATE">CD_TRANSLATE</option>
								<option value="CD_TXN">CD_TXN</option>
								<option value="CD_TXNEVENT">CD_TXNEVENT</option>
								<option value="COMPONENT">COMPONENT</option>
								<option value="COMPONENT_SHIPMENT">COMPONENT_SHIPMENT</option>
								<option value="COSRPT_EVENTPARM">COSRPT_EVENTPARM</option>
								<option value="COSRPT_TRANSLATE">COSRPT_TRANSLATE</option>
								<option value="COSRPT_TXN">COSRPT_TXN</option>
								<option value="COSRPT_TXNEVENT">COSRPT_TXNEVENT</option>
								<option value="COUNTRY_REF">COUNTRY_REF</option>
								<option value="CROSS_REF_ROLE">CROSS_REF_ROLE</option>
								<option value="CUST_ADDR">CUST_ADDR</option>
								<option value="CUST_LINE_ITEM">CUST_LINE_ITEM</option>
								<option value="CUST_ORDER">CUST_ORDER</option>
								<option value="CUSTOMER">CUSTOMER</option>
								<option value="DERIVED_LINE_SEG">DERIVED_LINE_SEG</option>
								<option value="DLS_SERIAL">DLS_SERIAL</option>
								<option value="ERROR_LOGGER">ERROR_LOGGER</option>
								<option value="FMS_REF">FMS_REF</option>
								<option value="FMS_REF_LOAD_HIST">FMS_REF_LOAD_HIST</option>
								<option value="GROUP_PERMISSIONS">GROUP_PERMISSIONS</option>
								<option value="GROUPS">GROUPS</option>
								<option value="HOLIDAY_REF">HOLIDAY_REF</option>
								<option value="INVOICE">INVOICE</option>
								<option value="INVOICE_LINE_ITEM">INVOICE_LINE_ITEM</option>
								<option value="INVOICE_SOURCE">INVOICE_SOURCE</option>
								<option value="IOS_CHANGE_LOG">IOS_CHANGE_LOG</option>
								<option value="ITEM_IDENTITY">ITEM_IDENTITY</option>
								<option value="KNA1">KNA1</option>
								<option value="LOOKUP">LOOKUP</option>
								<option value="OSAM_CTRY_GEO_REF">OSAM_CTRY_GEO_REF</option>
								<option value="OSAM_GEO_REF">OSAM_GEO_REF</option>
								<option value="PERMISSION">PERMISSION</option>
								<option value="PLANT_ORDER">PLANT_ORDER</option>
								<option value="PLANT_ORDER_SHIP">PLANT_ORDER_SHIP</option>
								<option value="PROFILE">PROFILE</option>
								<option value="PROFILE_COUNTRY">PROFILE_COUNTRY</option>
								<option value="PURGE_HISTORY">PURGE_HISTORY</option>
								<option value="RESERVED_ITEMS">RESERVED_ITEMS</option>
								<option value="ROLE">ROLE</option>
								<option value="ROLE_GROUPS">ROLE_GROUPS</option>
								<option value="SCHEDULE">SCHEDULE</option>
								<option value="SERV_BILLINGS">SERV_BILLINGS</option>
								<option value="SHIP_LINE_ITEM">SHIP_LINE_ITEM</option>
								<option value="SHIP_STATUS_SUMRY">SHIP_STATUS_SUMRY</option>
								<option value="STATSDB">STATSDB</option>
								<option value="UI_ENTITLE">UI_ENTITLE</option>
								<option value="UI_ENTITLE_CLAUSE">UI_ENTITLE_CLAUSE</option>
							</select>

						</font></td>
					</tr>
				</table>
				<br />
				<table>

					<tr>
						<td style="width: 100px;" valign="top"><font
							style="font-family: Calibri;"> <strong>Columns: </strong>
						</font></td>
						<td>
							<table class="formdata" id="column_properity">
								<tbody id="column_tbody">
									<tr style="width: 430px;" id="tr1">
										<td><strong><font>Name</font></strong></td>
										<td>
											<select style="width:150px;">
												<option>id</option>
												<option>name</option>
											</select>
										</td>
										<td><strong><font>Value</font></strong></td>
										<td><input type="text" id="v1"></td>
									</tr>
								</tbody>
							</table>
						</td>


						

						<!-- Add and Submit buttons -->
						<td valign="top">
							<input type="button" value="Add" onclick="">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="Submit">
						</td>
					</tr>
				</table>




			</form>

		</div>

	</div>


</body>
</html>
