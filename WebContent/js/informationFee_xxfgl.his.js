$(function(){
	
	$("#adopt").click(function(){
		var tableData1 = GetTableData(document.getElementById('tabProduct1'));
		$("#tableData1").val(JSON.stringify(tableData1));
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=信息费管理";
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
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var tableData1 = GetTableData(document.getElementById('tabProduct1'));
			$("#tableData1").val(JSON.stringify(tableData1));
			var text = $(this).val();
			determineAndSubmitBtn(text, "信息费管理");
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
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}