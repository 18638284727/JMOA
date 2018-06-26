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
			$("#inLineOptions2").val(inLineOptions2);
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
				var text4 = $("#error4").text();
				var text5 = $("#error5").text();
				if(text1 != "金额大小写不匹配......" && text2 != "金额大小写不匹配......" && text3 != "金额大小写不匹配......" && text4 != "金额大小写不匹配......" && text5 != "金额大小写不匹配......"){
					var text = $(this).val();
					if(text.trim() == "保存" || text.trim() == "提交"){
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
	
	$("#reserve4,#reserve5").blur(function(){
		checkMoney($("#reserve4"), $("#reserve5"), $("#error1"));
	});
	
	$("#reserve7,#reserve8").blur(function(){
		checkMoney($("#reserve7"), $("#reserve8"), $("#error2"));
	});
	
	$("#reserve9,#reserve10").blur(function(){
		checkMoney($("#reserve9"), $("#reserve10"), $("#error3"));
	});
	
	$("#reserve11,#reserve12").blur(function(){
		checkMoney($("#reserve11"), $("#reserve12"), $("#error4"));
	});
	
	$("#reserve14,#reserve15").blur(function(){
		checkMoney($("#reserve14"), $("#reserve15"), $("#error5"));
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

function setCondition(Object)
{
	$("#condition").val($(Object).val())
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
	var reserve5 = $("#reserve5").val(); // 合同金额
	var reserve8 = $("#reserve8").val(); // 已付金额
	var reserve10 = $("#reserve10").val(); // 收到发票金额
	var reserve12 = $("#reserve12").val(); // 未付金额
	var reserve15 = $("#reserve15").val(); // 本次应付金额
	if($("#reserve4").val() == "0" && reserve5 == "请输入大写金额"){
		return false;
	}else if($("#reserve7").val() == "0" && reserve8 == "请输入大写金额"){
		return false;
	}else if($("#reserve9").val() == "0" && reserve10 == "请输入大写金额"){
		return false;
	}else if($("#reserve11").val() == "0" && reserve12 == "请输入大写金额"){
		return false;
	}else if($("#reserve14").val() == "0" && reserve15 == "请输入大写金额"){
		return false;
	}
}