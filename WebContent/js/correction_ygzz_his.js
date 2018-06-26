$(function(){
	
	$("#adopt").click(function(){
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=员工转正申请";
	});
	
	var opinion = $("#opinion").val()
	
	opinions(opinion, $("#opinions"))
	
	var inLineOptions1 = $("#inLineOptions1").val();
	
	inLineOptions("inLineOptions1", inLineOptions1);
	
	$(document).on('click','img',function(){
		$(this).zoomify();
	});

	var tableData1 = JSON.parse($("#tableData1").text());
	$.each(tableData1, function(index, item){
		AddRows1(document.getElementById('tabProduct1'), 1, item);
	});
	
	if($("#tableData2").text() != ""){
		var tableData2 = JSON.parse($("#tableData2").text());
		$.each(tableData2, function(index, item){
			AddRows1(document.getElementById('tabProduct2'), 1, item);
		});
	}
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var tableData1 = GetTableData(document.getElementById('tabProduct1'));
			$("#tableData11").val(JSON.stringify(tableData1));
			var text = $(this).val();
			var date1 = new Date($("#reserve6").val());
			var date2 = new Date($("#reserve7").val());
			var curVal = (Date.parse(date2) - Date.parse(date1)) / 1000 / 60 / 60 / 24;
			if(curVal<0){
				alert("开始时间不能大于结束时间...");
			}else{
				determineAndSubmitBtn(text, "员工转正申请");
			}
		});
	});
	
})

function AddRows1(table, index, item){
	var newRowEl = [
		'<tr class="ccsq-bdnr">',
		'	<td>' + item.Num + '</td>',
		'	<td>' + item.Amount + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td>' + item.Amount2 + '</td>',
		'	<td>' + item.Amount3 + '</td>',
		'	<td>' + item.Amount4 + '</td>',
		'	<td>' + item.Amount5 + '</td>',
		'	<td>' + item.Amount6 + '</td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}