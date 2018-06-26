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
			editOperation();
		}else if(text.trim() == "提交"){
			submitOperation(id, "");
		}
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var result = checkMoney2();
			if(result == false){
				alert("请填写金额......");
			}else{
				var text1 = $("#error1").text();
				var text2 = $("#error2").text();
				var text3 = $("#error3").text();
				if(text1 != "金额大小写不匹配......" && text2 != "金额大小写不匹配......" && text3 != "金额大小写不匹配......"){
					var tempName = name;
					var tableData1 = GetTableData(document.getElementById('tabProduct1'));
					$("#tableData1").val(JSON.stringify(tableData1));
					var text = $(this).val();
					if(text.trim() == "保存" || text.trim() == "提交"){
						var date1 = new Date($("#reserve11").val());
						var date2 = new Date($("#reserve13").val());
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
				}else{
					alert("金额大小写不匹配......");
				}
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
	document.getElementById("reserve11").value = today();
	document.getElementById("reserve13").value = today();
	
	var object;
	
	$(document).on('click', 'input[name=uploadAvatar]', function(){
		$("#imageModel").modal('show');
		object = $(this).next();
		fileNames.length = 0;
	});
	
	$("#reserve7,#reserve8").blur(function(){
		checkMoney($("#reserve7"), $("#reserve8"), $("#error2"));
	});
	
	$("#reserve5,#reserve6").blur(function(){
		checkMoney($("#reserve5"), $("#reserve6"), $("#error1"));
	});
	
	$("#reserve9,#reserve10").blur(function(){
		checkMoney($("#reserve9"), $("#reserve10"), $("#error3"));
	});
	
	$("#uploadBtn").click(function(){
		$(object).text(fileNames)
		var formData = new FormData($("#form")[0]);
		$.ajax({
			url : path + '/activiti/multiple',
			type : 'POST',
			data : formData,  
			async: false,  
			cache: false,  
			contentType: false,  
			processData: false,  
			success : function(data){
				var size = $("#img-container div").size();
				$("#img-container div:lt(" + (size - 1) + ")").remove();
			}
		});
	});
	
	if($("#flag").val() == "fqsq"){
		$("#leave1").attr("class","tab-pane fade")
		$("#leave2").attr("class","tab-pane fade")
		$("#leave3").attr("class","tab-pane fade nryc in active")
		$("#pageInfo1").css("display","none");
		$("#pageInfo2").css("display","none");
		$("#follow").val("关注");
	}
});

//删除行
function DeleteRow1(table, index){
	for(var i=table.rows.length - 1; i>0;i--){
		var chkOrder = table.rows[i].cells[0].firstChild;
		var cellsLength = table.rows[i].cells.length;
		var s = $(table.rows[i].cells[cellsLength - 1].lastChild).text();
		if(chkOrder){
			if(chkOrder.type = "CHECKBOX"){
				if(chkOrder.checked){
					//执行删除
					table.deleteRow(i);
				}
			}
		}
	}
}

var count = 1;

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
		'	<td><input name="uploadAvatar" type="button" value="上传发票扫描件"/><span style="display:none;"></span></td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	count++; 
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
		'	<td><input name="uploadAvatar" type="button" value="上传发票扫描件"/><span style="display:none;">' + item.Amount5 + '</span></td>',
		'</tr>'
	].join("");
	$(table).append(newRowEl);
	SetRowCanEdit(table.tBodies[0].lastChild);
	return table.tBodies[0].lastChild;
}

function checkMoney(Object1, Object2, Object3)
{
	var lowerMoney = $(Object1).val();
	var upperMoney = $(Object2).val();
	$.ajax({
		url : path + '/activiti/checkMoney',
		type : 'POST',
		data : {"lowerMoney" : lowerMoney, "upperMoney" : upperMoney},
		success : function(data){
			if(data == "false"){
				$(Object3).text("金额大小写不匹配......");
			}else{
				$(Object3).text("");
			}
		}
	});
}

function checkMoney2()
{
	var reserve6 = $("#reserve6").val(); 
	var reserve8 = $("#reserve8").val(); 
	var reserve10 = $("#reserve10").val(); 
	if( $("#reserve5").val() == "0" && reserve6 == "请输入大写金额"){
		return false;
	}else if( $("#reserve7").val() == "0" && reserve8 == "请输入大写金额"){
		return false;
	}else if( $("#reserve9").val() == "0" && reserve10 == "请输入大写金额"){
		return false;
	}
}