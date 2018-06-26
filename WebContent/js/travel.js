$(function(){
	
	$(".vertical-nav a[href='/JMOA/javascrip:void(0)']").attr("data-toggle","modal").attr("data-target","#myModal");
	
	ready();
	
	getAllTaskByName(1, "未处理");
	
	$(document).on('click','#home2 tbody td a',function(){
		getData($(this).parent().parent());
		var text = $(this).text();
		if(text.trim() == "撤销"){
			deleteOperation(id)
		}else if(text.trim() == "编辑"){
			$("#date").text(reserve9);
			editOperation();
		}else if(text.trim() == "提交"){
			submitOperation(id, "");
		}
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var tempName = name;
			var tableData1 = GetTableData(document.getElementById('tabProduct1'));
			$("#tableData1").val(JSON.stringify(tableData1))
			var tableData2 = GetTableData(document.getElementById('tabProduct2'));
			$("#tableData2").val(JSON.stringify(tableData2))
			var tableData3 = GetTableData(document.getElementById('tabProduct3'));
			$("#tableData3").val(JSON.stringify(tableData3))
			var tableData4 = GetTableData(document.getElementById('tabProduct4'));
			$("#tableData4").val(JSON.stringify(tableData4))
			var text = $(this).val();
			if(text.trim() == "保存" || text.trim() == "提交"){
				var date1 = new Date($("#reserve5").val());
				var date2 = new Date($("#reserve7").val());
				var curVal = (Date.parse(date2) - Date.parse(date1)) / 1000 / 60 / 60 / 24;
				if(curVal<0){
					alert("开始时间不能大于结束时间...");
				}else{
					name = tempName;
					saveAndSubmitBtn(text);
				}
			}else{
				determineAndSubmitBtn(text);
			}
		});
	});

	$(document).on('click','#home1 tr',function(){
		if($(this).parent().find("tr th:eq(1)").text() == "姓名"){
			return;
		}
		if($(this).find("td:eq(0)").text() == "暂时没有模型数据"){
			return;
		}
		getData(this);
		sendData(page);
	});
	
	$("#search1").click(function(){
		var startTime = $("#stime").val();
		var endTime = $("#etime").val();
		var eName = $("#ename").val();
		var company = $("#company").val();
		var dept = $("#dept").val();
		searchTaskByCondition(1, startTime, endTime, eName, company, dept, "未处理");
		
	});
	
	$("#search2").click(function(){
		var startTime = $("#stime2").val();
		var endTime = $("#etime2").val();
		searchTaskByCondition(1, startTime, endTime, "", "0", "0", "未提交");
	});
	
	document.getElementById("apply").value = today();
	document.getElementById("reserve5").value = today();
	document.getElementById("reserve6").value = hour();
	document.getElementById("reserve7").value = today();
	document.getElementById("reserve8").value = hour();
	
	$("#reserve5,#reserve6,#reserve7,#reserve8").blur(function(){
		var sDay = $("#reserve5").val();
		var sHour = $("#reserve6").val();
		var eDay = $("#reserve7").val();
		var eHour = $("#reserve8").val();
		var sDate = sDay + " " + sHour;
		var eDate = eDay + " " + eHour;
		$("#date").text(formatData(sDate, eDate))
		$("#reserve9").val(formatData(sDate, eDate))
	});
	
	if($("#flag").val() == "fqsq"){
		$("#leave1").attr("class","tab-pane fade")
		$("#leave2").attr("class","tab-pane fade")
		$("#leave3").attr("class","tab-pane fade nryc in active")
		$("#pageInfo1").css("display","none");
		$("#pageInfo2").css("display","none");
		$("#follow").val("关注");
	}
	
	$("#reserve10").keyup(function(e){
		if(e.keyCode >= 48 && e.keyCode <= 57){
			$(this).next().text($(this).val())
		}
	});
});

//添加行
function AddRow1(table, index){
	var newRowEl = [
		'<tr class="ccsq-bdnr">',
		'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td edittype="TextBox">请输入...</td>',
		'	<td>请输入...</td>',
		'	<td>请输入...</td>',
		'	<td>请输入...</td>',
		'	<td>请输入...</td>',
		'	<td>请输入...</td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}
//添加行
function AddRow2(table, index){
	var newRowEl = [
	'<tr class="ccsq-bdnr">',
	'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'</tr>'	
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}
//添加行
function AddRow3(table, index){
	var newRowEl = [
	'<tr class="ccsq-bdnr">',
	'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox"/></td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'</tr>'		
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}
//添加行
function AddRow4(table, index){
	var newRowEl = [
	'<tr class="ccsq-bdnr">',
	'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox"/></td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'	<td>请输入...</td>',
	'</tr>'		
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}

//编辑时自动添加原有的数据行
function AddRows4(table, index, item){
	var newRowEl = [
		'<tr class="ccsq-bdnr">',
		'	<td  align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td edittype="TextBox">' + item.Num + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td>' + item.Amount2 + '</td>',
		'	<td edittype="TextBox">' + item.Amount3 + '</td>',
		'	<td>' + item.Amount4 + '</td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}

//编辑时自动添加原有的数据行
function AddRows1(table, index, item){
	var newRowEl = [
		'<tr class="ccsq-bdnr">',
		'	<td  align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td edittype="TextBox">' + item.Num + '</td>',
		'	<td>' + item.Amount + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td edittype="TextBox">' + item.Amount2 + '</td>',
		'	<td>' + item.Amount3 + '</td>',
		'	<td>' + item.Amount4 + '</td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}

//编辑时自动添加原有的数据行
function AddRows2(table, index, item){
	var newRowEl = [
		'<tr class="ccsq-bdnr">',
		'	<td  align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td edittype="TextBox">' + item.Num + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td>' + item.Amount2 + '</td>',
		'	<td edittype="TextBox">' + item.Amount3 + '</td>',
		'	<td>' + item.Amount4 + '</td>',
		'	<td>' + item.Amount5 + '</td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}

//编辑时自动添加原有的数据行
function AddRows3(table, index, item){
	var newRowEl = [
		'<tr class="ccsq-bdnr">',
		'	<td  align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td edittype="TextBox">' + item.Num + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td>' + item.Amount2 + '</td>',
		'	<td edittype="TextBox">' + item.Amount3 + '</td>',
		'	<td>' + item.Amount4 + '</td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}