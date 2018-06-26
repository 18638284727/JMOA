$(function(){
	
	$("#adopt").click(function(){
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=特殊发货申请";
	});
	
	var opinion = $("#opinion").val()
	
	opinions(opinion, $("#opinions"))
	
	var inLineOptions1 = $("#inLineOptions1").val();
	
	inLineOptions("inLineOptions1", inLineOptions1);
	
	selected($("#reserve15"), $("#reserve115").val());
	
	$(document).on('click','img',function(){
		$(this).zoomify();
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var text = $(this).val();
			determineAndSubmitBtn(text, "特殊发货申请");
		});
	});
})