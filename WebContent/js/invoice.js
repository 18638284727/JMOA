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
				if(text1 != "金额大小写不匹配......" && text2 != "金额大小写不匹配......"){
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
	
	$("#reserve11,#reserve12").blur(function(){
		checkMoney($("#reserve11"), $("#reserve12"), $("#error2"));
	});
	
	$("#reserve13,#reserve14").blur(function(){
		checkMoney($("#reserve13"), $("#reserve14"), $("#error1"));
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
	var reserve12 = $("#reserve12").val(); 
	var reserve14 = $("#reserve14").val(); 
	if($("#reserve11").val() == "0" && reserve12 == "请输入大写金额"){
		return false;
	}else if($("#reserve13").val() == "0" && reserve14 == "请输入大写金额"){
		return false;
	}
}