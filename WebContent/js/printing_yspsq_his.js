$(function(){
	
	$("#adopt").click(function(){
		var tableData1 = GetTableData(document.getElementById('tabProduct1'));
		$("#tableData1").val(JSON.stringify(tableData1));
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=印刷品印刷申请";
	});
	
	var opinion = $("#opinion").val()
	
	opinions(opinion, $("#opinions"))
	
	var inLineOptions1 = $("#inLineOptions1").val();
	
	inLineOptions("inLineOptions1", inLineOptions1);
	
	$(document).on('click','img',function(){
		$(this).zoomify();
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
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var tableData1 = GetTableData(document.getElementById('tabProduct1'));
			$("#tableData1").val(JSON.stringify(tableData1));
			var text = $(this).val();
			determineAndSubmitBtn(text, "印刷品印刷申请");
		});
	});
	
})

//申请时添加行
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

function AddRows2(table, index, item){
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