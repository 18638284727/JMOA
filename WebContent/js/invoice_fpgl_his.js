$(function() {

	$("#adopt").click(function() {
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=发票管理";
	});

	var opinion = $("#opinion").val()

	opinions(opinion, $("#opinions"))

	var inLineOptions1 = $("#inLineOptions1").val();
	var inLineOptions2 = $("#inLineOptions2").val();

	inLineOptions("inLineOptions1", inLineOptions1);
	inLineOptions("inLineOptions2", inLineOptions2);

	$(document).on('click', 'img', function() {
		$(this).zoomify();
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var text = $(this).val();
			determineAndSubmitBtn(text, "发票管理");
		});
	});
})