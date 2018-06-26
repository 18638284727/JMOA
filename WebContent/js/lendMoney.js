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
			var text = $(this).val();
			var lowerMoney = $("#reserve5").val();
			var upperMoney = $("#reserve6").val();
			if(lowerMoney == "0" && upperMoney == "请输入大写金额"){
				alert("请输入金额...");
				return;
			}
			$.ajax({
				url : path + '/activiti/checkMoney',
				type : 'POST',
				data : {"lowerMoney" : lowerMoney, "upperMoney" : upperMoney},
				success : function(data){
					if(data == "false"){
						alert("金额大小写不匹配.....")
					}else{
						if(text.trim() == "保存" || text.trim() == "提交"){
							saveAndSubmitBtn(text);
						}else{
							determineAndSubmitBtn(text);
						}
					}
				}
			});
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
	
	if($("#flag").val() == "fqsq"){
		$("#leave1").attr("class","tab-pane fade")
		$("#leave2").attr("class","tab-pane fade")
		$("#leave3").attr("class","tab-pane fade nryc in active")
		$("#pageInfo1").css("display","none");
		$("#pageInfo2").css("display","none");
		$("#follow").val("关注");
	}
	
});