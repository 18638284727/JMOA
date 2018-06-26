$(function(){
	
	$("#adopt").click(function(){
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=招聘申请";
	});
	
	var opinion = $("#opinion").val()
	
	opinions(opinion, $("#opinions"))
	
	var inLineOptions1 = $("#inLineOptions1").val();
	var inLineOptions2 = $("#inLineOptions2").val();
	
	inLineOptions("inLineOptions1", inLineOptions1);
	inLineOptions("inLineOptions2", inLineOptions2);
	
	$(document).on('click','img',function(){
		$(this).zoomify();
	});
	
	selected($("#reserve111"), $("#reserve11").val())
	selected($("#reserve112"), $("#reserve12").val())
	selected($("#reserve113"), $("#reserve13").val())
	selected($("#reserve114"), $("#reserve14").val())
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var text = $(this).val();
			determineAndSubmitBtn(text, "招聘申请");
		});
	});
})

