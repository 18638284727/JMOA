$(function(){
	$("#back").click(function(){
		var tableData2 = GetTableData(document.getElementById('tabProduct2'));
		$("#td2").val(JSON.stringify(tableData2));
		$("input[name=follow]").val($("#follow").val());
		$("input[name=opinion]").val() == "" ? $("input[name=opinion]").val('暂无意见') : $("input[name=opinion]").val($("input[name=opinion]").val());
		$("#text").val("退回");
		completeAllTask($("#form"));
	});
	
	$("#adopt").click(function(){
		var tableData2 = GetTableData(document.getElementById('tabProduct2'));
		$("#td2").val(JSON.stringify(tableData2));
		$("input[name=follow]").val($("#follow").val())
		$("input[name=opinion]").val() == "" ? $("input[name=opinion]").val('暂无意见') : $("input[name=opinion]").val($("input[name=opinion]").val());
		$("#text").val("同意");
		completeAllTask($("#form"));
	});
	
	$("#forward").click(function(){
		$("#myModal2").modal('show');
	});
	
	var opinion = $("#opinion").val()
	
	opinions(opinion, $("#opinions"))
	
	$(document).on('click','img',function(){
		$(this).zoomify();
	});
	
	var tableData1 = JSON.parse($("#tableData1").text());
	$.each(tableData1, function(index, item){
		AddRows1(document.getElementById('tabProduct1'), 1, item);
	})
	
	if($("#tableData2").text() == ""){
		AddRows2(document.getElementById('tabProduct2'), 1);
	}else{
		var tableData2 = JSON.parse($("#tableData2").text());
		$.each(tableData2, function(index, item){
			AddRows1(document.getElementById('tabProduct2'), 1, item);
		})
	}
	
	if(document.getElementById("reserve13").value == ""){
		document.getElementById("reserve13").value = today();
	}else{
		document.getElementById("reserve13").value = document.getElementById("reserve13").value;
	}
	
	
	inLineOptions("inLineOptions3", $("#inLineOptions3").val());
	inLineOptions("inLineOptions4", $("#inLineOptions4").val());
	
	
	
	
	
	
	
	
	
	var taskId;
	
	$("#myModal2").on('show.bs.modal', function(e){
		taskId = $("input[name=taskId]").val();
		$.ajax({
			url : path + '/company/getAllCompany',
			type : 'POST',
			success : function(data){
				$("#company").empty()
				$("#company").append("<option value='0'>请选择公司...</option>");
				$("#dept").append("<option value='0'>请选择部门...</option>");
				$("#emp").append("<option value='0'>请选择员工...</option>");
				$.each(data, function(index, item){
					$("#company").append("<option value='" + item.cId + "'>" + item.cName + "</option>");
				})
			}
		});
	})
	
	$(document).on('change', '#company', function(){
		var value = $(this).val()
		$("#dept").empty()
		$("#dept").append("<option value='0'>请选择部门...</option>");
		$("#emp").empty()
		$("#emp").append("<option value='0'>请选择员工...</option>");
		if(value != 0){
			$.ajax({
				url : path + '/company/getDepartmentByCid',
				type : 'POST',
				data : {"cId" : value},
				success : function(data){
					$.each(data, function(index, item){
						$("#dept").append("<option value='" + item.dId + "'>" + item.dName + "</option>");
					})
				}
			});
		}
	})
	
	$(document).on('change', '#dept', function(){
		var value = $(this).val()
		$("#emp").empty();
		$("#emp").append("<option value='0'>请选择员工...</option>");
		if(value != 0){
			$.ajax({
				url : path + '/company/getEmployeeByDid',
				type : 'POST',
				data : {"dId" : value},
				success : function(data){
					$.each(data, function(index, item){
						$("#emp").append("<option value='" + item.eId + "'>" + item.eName + "</option>");
					})
				}
			});
		}
	})
	
	$(document).on('change', '#emp', function(){
		var value = $(this).find("option:selected").text()
		if(value != 0){
			$("#txtHobby").val(value);
		}else{
			$("#txtHobby").val("");
		}
	})
	
	$(document).on('click', '#confirm', function(){
		$.ajax({
			url : path + '/activiti/updateAssignee',
			type : 'POST',
			data : {"taskId" : taskId, "assignee" : $("#txtHobby").val()},
			success : function(data){
				if(data == "success"){
					window.location.href = path + "/views/" + page;
				}
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

function AddRows2(table, index){
	var newRowEl = [
		'<tr class="ccsq-bdnr">',
		'	<td> 20 </td>',
		'	<td> 15 </td>',
		'	<td> 15 </td>',
		'	<td> 15 </td>',
		'	<td> 10 </td>',
		'	<td> 15 </td>',
		'	<td> 10 </td>',
		'	<td> 100 </td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}