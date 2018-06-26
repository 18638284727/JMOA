$(function(){
	
	$("#adopt").click(function(){
		var tableData1 = GetTableData(document.getElementById('tabProduct1'));
		$("#tableData1").val(JSON.stringify(tableData1));
		var tableData2 = GetTableData(document.getElementById('tabProduct2'));
		$("#tableData2").val(JSON.stringify(tableData2));
		var tableData3 = GetTableData(document.getElementById('tabProduct3'));
		$("#tableData3").val(JSON.stringify(tableData3));
		var tableData4 = GetTableData(document.getElementById('tabProduct4'));
		$("#tableData4").val(JSON.stringify(tableData4));
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=出差申请";
	});
	
	var opinion = $("#opinion").val()
	
	opinions(opinion, $("#opinions"))
	
	var inLineOptions1 = $("#inLineOptions1").val();
	var inLineOptions2 = $("#inLineOptions2").val();
	
	inLineOptions("inLineOptions1", inLineOptions1);
	inLineOptions("inLineOptions2", inLineOptions2);
	
	$(document).on('click','img',function(){
		$(this).zoomify();
	});
	
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
	
	if($("#tableData11").text() != ""){
		var tableData1 = JSON.parse($("#tableData11").text());
		$.each(tableData1, function(index, item){
			AddRows1(document.getElementById('tabProduct1'), 1, item);
		})
	}
	if($("#tableData12").text() != ""){
		var tableData2 = JSON.parse($("#tableData12").text());
		$.each(tableData2, function(index, item){
			AddRows2(document.getElementById('tabProduct2'), 1, item);
		})
	}
	if($("#tableData13").text() != ""){
		var tableData3 = JSON.parse($("#tableData13").text());
		$.each(tableData3, function(index, item){
			AddRows3(document.getElementById('tabProduct3'), 1, item);
		})
	}
	if($("#tableData14").text() != ""){
		var tableData4 = JSON.parse($("#tableData14").text());
		$.each(tableData4, function(index, item){
			AddRows4(document.getElementById('tabProduct4'), 1, item);
		})
	}
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var tableData1 = GetTableData(document.getElementById('tabProduct1'));
			$("#tableData1").val(JSON.stringify(tableData1));
			var tableData2 = GetTableData(document.getElementById('tabProduct2'));
			$("#tableData2").val(JSON.stringify(tableData2));
			var tableData3 = GetTableData(document.getElementById('tabProduct3'));
			$("#tableData3").val(JSON.stringify(tableData3));
			var tableData4 = GetTableData(document.getElementById('tabProduct4'));
			$("#tableData4").val(JSON.stringify(tableData4));
			var text = $(this).val();
			var date1 = new Date($("#reserve5").val());
			var date2 = new Date($("#reserve7").val());
			var curVal = (Date.parse(date2) - Date.parse(date1)) / 1000 / 60 / 60 / 24;
			if(curVal<0){
				alert("开始时间不能大于结束时间...");
			}else{
				determineAndSubmitBtn(text, "出差申请");
			}
		});
	});
})

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
		'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td>' + item.Num + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td>' + item.Amount2 + '</td>',
		'	<td>' + item.Amount3 + '</td>',
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
		'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td>' + item.Num + '</td>',
		'	<td>' + item.Amount + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td>' + item.Amount2 + '</td>',
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
		'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td>' + item.Num + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td>' + item.Amount2 + '</td>',
		'	<td>' + item.Amount3 + '</td>',
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
		'	<td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td>' + item.Num + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td>' + item.Amount2 + '</td>',
		'	<td>' + item.Amount3 + '</td>',
		'	<td>' + item.Amount4 + '</td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}