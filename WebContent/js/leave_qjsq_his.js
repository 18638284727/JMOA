$(function(){
	
	$("#adopt").click(function(){
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=请假申请";
	});
	
	var opinion = $("#opinion").val()
	
	opinions(opinion, $("#opinions"))
	
	var inLineOptions1 = $("#inLineOptions1").val();
	var inLineOptions2 = $("#inLineOptions2").val();
	
	if(inLineOptions2 == '年假'){
		$("#nj").css("display", "block");
	}else{
		$("#nj").css("display", "none");
	}

	inLineOptions("inLineOptions1", inLineOptions1);
	inLineOptions("inLineOptions2", inLineOptions2);
	
	$(document).on('click','img',function(){
		$(this).zoomify();
	});
	
	var images = $("#reserve12").val().split(",");
	for(var i = 0; i < images.length; i++){
		$("#preview_picture").append("<img alt='' src='" + path + "/activiti/upload/" + images[i] + "' class='zoomify'>");
	}
	
	$("input[name=inLineOptions2]").each(function(index, item){
		$(this).click(function(){
			if($(this).val() == "年假"){
				$("#nj").css("display", "block");
			}else{
				$("#nj").css("display", "none");
			}
		});
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var text = $(this).val();
			var date1 = new Date($("#reserve5").val());
			var date2 = new Date($("#reserve7").val());
			var curVal = (Date.parse(date2) - Date.parse(date1)) / 1000 / 60 / 60 / 24;
			if(curVal<0){
				alert("开始时间不能大于结束时间...");
			}else{
				determineAndSubmitBtn(text, "请假申请");
			}
		});
	});
})