$(function() {

	$("#adopt").click(function() {
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=付款管理";
	});

	var opinion = $("#opinion").val()

	opinions(opinion, $("#opinions"))

	var inLineOptions1 = $("#inLineOptions1").val();

	inLineOptions("inLineOptions1", inLineOptions1);

	selected($("#reserve113"), $("#reserve13").val());
	selected($("#inLineOptions12"), $("#inLineOptions2").val());

	$(document).on('click', 'img', function() {
		$(this).zoomify();
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var text = $(this).val();
			determineAndSubmitBtn(text, "付款管理");
		});
	});
})