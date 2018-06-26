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
			if(reserve10 != ""){
				$("#nj").css("display", "block");
			}else{
				$("#nj").css("display", "none");
			}
			var images = reserve12.split(",");
			$("#preview_picture").empty()
			for(var i = 0; i < images.length; i++){
				$("#preview_picture").append("<img alt='' src='" + path + "/activiti/upload/" + images[i] + "' class='zoomify'>");
			}
			$("#date").text(reserve9);
		}else if(text.trim() == "提交"){
			submitOperation(id, "");
		}
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var text = $(this).val();
			if(text.trim() == "保存" || text.trim() == "提交"){
				var date1 = new Date($("#reserve5").val());
				var date2 = new Date($("#reserve7").val());
				var curVal = (Date.parse(date2) - Date.parse(date1)) / 1000 / 60 / 60 / 24;
				if(curVal<0){
					alert("开始时间不能大于结束时间...");
				}else{
					saveAndSubmitBtn(text);
				}
			}else{
				determineAndSubmitBtn(text);
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
	
	document.getElementById("reserve5").value = today();
	document.getElementById("reserve6").value = hour();
	document.getElementById("reserve7").value = today();
	document.getElementById("reserve8").value = hour();
	
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
	
	$("#fileName").change(function(){
		var files = this.files;
		$("#preview_picture").empty();
		for(var i =0; i< files.length; i++){
			var fileType = files[i].type.split("/")[0];  
            if(fileType != "image") {  
                alert("请上传图片")  
                return;  
            }  
            var fileSize = Math.round(files[i].size / 1024 / 1024);  
            if(fileSize >= 3) {  
                alert("请上传小于少于3M的图片");  
                return;  
            } 
			preview_picture(files[i]);
		}
	});
	
	$(document).on('click','img',function(){
		$(this).zoomify();
	});
	
	$("input[name=inLineOptions2]").each(function(index, item){
		$(this).click(function(){
			if($(this).val() == "年假"){
				$("#nj").css("display", "block");
			}else{
				$("#nj").css("display", "none");
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

function preview_picture(picture)
{
	var readFile = new FileReader();
	readFile.readAsDataURL(picture);
	readFile.onload = function(e){
		$("#preview_picture").append("<img alt='' src='" + this.result + "' class='zoomify'>");
	}
}

function setCondition(Object)
{
	$("#condition").val($(Object).val())
}
