$(function(){
	
	$(".vertical-nav a[href='/JMOA/javascrip:void(0)']").attr("data-toggle","modal").attr("data-target","#myModal");
	
	$("#leave1").css("display", "block");
	$("#leave2").css("display", "none");
	$("#leave3").css("display", "none");
	$("#leave4").css("display", "none");
	$("#pageInfo1").css("display","block");
	$("#pageInfo2").css("display","none");
	$("#pageInfo3").css("display","none");
	$("#pageInfo4").css("display","none");
	
	$("#return").click(function(){
		$("#leave1").css("display", "block");
		$("#leave2").css("display", "none");
		$("#leave3").css("display", "none");
		$("#leave4").css("display", "none");
		$("#pageInfo1").css("display","block");
		$("#pageInfo2").css("display","none");
		$("#pageInfo3").css("display","none");
		$("#pageInfo4").css("display","none");
	});
	
	$("#confirm").click(function(){
		$("#leave1").css("display", "none");
		$("#leave2").css("display", "block");
		$("#leave3").css("display", "none");
		$("#leave4").css("display", "none");
		$("#pageInfo1").css("display","none");
		$("#pageInfo2").css("display","block");
		$("#pageInfo3").css("display","none");
		$("#pageInfo4").css("display","none");
	});

	$("#back").click(function(){
		$("#leave1").css("display", "none");
		$("#leave2").css("display", "none");
		$("#leave3").css("display", "block");
		$("#leave4").css("display", "none");
		$("#pageInfo1").css("display","none");
		$("#pageInfo2").css("display","none");
		$("#pageInfo3").css("display","block");
		$("#pageInfo4").css("display","none");
	});

	$("#delay").click(function(){
		$("#leave1").css("display", "none");
		$("#leave2").css("display", "none");
		$("#leave3").css("display", "none");
		$("#leave4").css("display", "block");
		$("#pageInfo1").css("display","none");
		$("#pageInfo2").css("display","none");
		$("#pageInfo3").css("display","none");
		$("#pageInfo4").css("display","block");
	});
	
	$("#home1 tr").click(function(){
		window.location.href = path + "/views/returnMoney_hksq.jsp?flag=n";
	});
	
	$("#home2 tr").click(function(){
		window.location.href = path + "/views/returnMoney_hksq.jsp?flag=y";
	});
	
	$("#home3 tr").click(function(){
		window.location.href = path + "/views/returnMoney_hksq_his_n.jsp";
	});
	
	$("#home4 tr").click(function(){
		window.location.href = path + "/views/returnMoney_hksq_his_y.jsp";
	});
});