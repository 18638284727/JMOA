$(function() {

	$("#adopt").click(function() {
		$("input[name=follow]").val($("#follow").val());
		saveAndSubmitBtn("提交");
		window.location.href = path + "/views/process.jsp?name=开户管理";
	});

	var opinion = $("#opinion").val()

	opinions(opinion, $("#opinions"))

	var inLineOptions1 = $("#inLineOptions1").val();
	var inLineOptions2 = $("#inLineOptions2").val();
	var inLineOptions3 = $("#inLineOptions3").val();

	inLineOptions("inLineOptions1", inLineOptions1);
	inLineOptions("inLineOptions2", inLineOptions2);
	inLineOptions("inLineOptions3", inLineOptions3);

	$(document).on('click', 'img', function() {
		$(this).zoomify();
	});
	
	$("input[name=btn]").each(function(){
		$(this).click(function(){
			var text = $(this).val();
			determineAndSubmitBtn(text, "开户管理");
		});
	});
})