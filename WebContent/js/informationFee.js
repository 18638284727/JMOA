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
			editOperation2();
		}else if(text.trim() == "提交"){
			submitOperation(id, "");
		}
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var tempName = name;
			var tableData1 = GetTableData(document.getElementById('tabProduct1'));
			$("#tableData1").val(JSON.stringify(tableData1));
			var result = checkMoney2();
			if(result == false){
				alert("请填写金额......");
			}else{
				var text1 = $("#error1").text();
				var text2 = $("#error2").text();
				var text3 = $("#error3").text();
				var text4 = $("#error4").text();
				if(text1 != "金额大小写不匹配......" && text2 != "金额大小写不匹配......" && text3 != "金额大小写不匹配......" && text4 != "金额大小写不匹配......"){
					var text = $(this).val();
					if(text.trim() == "保存" || text.trim() == "提交"){
						name = tempName;
						saveAndSubmitBtn(text);
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
	
	$("#reserve9,#reserve10").blur(function(){
		checkMoney($("#reserve9"), $("#reserve10"), $("#error1"));
	});
	
	$("#reserve13,#reserve14").blur(function(){
		checkMoney($("#reserve13"), $("#reserve14"), $("#error3"));
	});
	
	$("#reserve11,#reserve12").blur(function(){
		checkMoney($("#reserve11"), $("#reserve12"), $("#error2"));
	});
	
	$("#inLineOptions4,#tableData2").blur(function(){
		checkMoney($("#inLineOptions4"), $("#tableData2"), $("#error4"));
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

function editOperation2() // 编辑超链接
{
	$("#leave1").attr("class","tab-pane fade")
	$("#leave2").attr("class","tab-pane fade")
	$("#leave3").attr("class","tab-pane fade nryc in active")
	$("#pageInfo1").css("display","none");
	$("#pageInfo2").css("display","none");
	$("#save").val("确定");
	$("#sub").val("提 交");
	
	//操作单选/复选按钮
	if(undefined != inLineOptions1){
		checkedRadioAndCheckbox("inLineOptions1", inLineOptions1);
	}
	
	if(undefined != tableData1){
		if(tableData1 != ""){
			var tableDatas = JSON.parse(tableData1);
			$(document.getElementById('tabProduct1')).find("tr:gt(0)").remove();
			$.each(tableDatas, function(index, item){
				AddRows1(document.getElementById('tabProduct1'), 1, item);
			})
		}
	}
	
	$("#apply").val(apply);
	
	$("#reserve1").val(reserve1);
	$("#reserve2").val(reserve2);
	$("#reserve3").val(reserve3);
	$("#reserve4").val(reserve4);
	$("#reserve5").val(reserve5);
	$("#reserve6").val(reserve6);
	$("#reserve7").val(reserve7);
	$("#reserve8").val(reserve8);
	$("#reserve9").val(reserve9);
	$("#reserve10").val(reserve10);
	$("#reserve11").val(reserve11);
	$("#reserve12").val(reserve12);
	$("#reserve13").val(reserve13);
	$("#reserve14").val(reserve14);
	$("#reserve15").val(reserve15);
	
	$("#inLineOptions2").val(inLineOptions2);
	$("#inLineOptions3").val(inLineOptions3);
	$("#inLineOptions4").val(inLineOptions4);
	$("#tableData2").val(tableData2);
	$("#tableData3").val(tableData3);
	$("#tableData4").val(tableData4);
	
	$("#taskId").val(id);
	$("#message").val(message);
	$("#nextPerson").val(nextPerson);
	checkFollowTask(id);
}

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
		'	<td  align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>',
		'	<td edittype="TextBox">' + item.Num + '</td>',
		'	<td>' + item.Amount + '</td>',
		'	<td>' + item.Amount1 + '</td>',
		'	<td edittype="TextBox">' + item.Amount2 + '</td>',
		'	<td>' + item.Amount3 + '</td>',
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
	var reserve10 = $("#reserve10").val(); // 合同金额
	var reserve12 = $("#reserve12").val(); // 已付金额
	var reserve14 = $("#reserve14").val(); // 收到发票金额
	var tableData2 = $("#tableData2").val(); // 未付金额
	if(reserve10 == "请输入大写金额" && $("#reserve9").val() == "0"){
		return false;
	}else if(reserve12 == "请输入大写金额" && $("#reserve11").val() == "0"){
		return false;
	}else if(reserve14 == "请输入大写金额" && $("#reserve13").val() == "0"){
		return false;
	}else if(tableData2 == "请输入大写金额" && $("#inLineOptions4").val() == "0"){
		return false;
	}
}