$(function(){
	$(document).on('click', '#register', function(){
		$.ajax({
			url : path + '/employee/entry',
			type : 'POST',
			data : $("#form").serialize(),
			success : function(data){
				$("#content").html(data.message + "<br/>" + data.classs);
			}
		});
	})
	
	
	testPage();
	function testPage()
	{
		var arr = [];
		for(var i = 1; i <= 50; i++){
			arr.push(i);
		}
		var ul = $("<ul></ul>").addClass("pagination fy");
		for(var i = 0; i < arr.length; i++){
			if(i<11){
				var a = $("<a href='javascript:void(0)'>" + arr[i] + "</a>");
				var li = $("<li></li>").append(a);
				ul.append(li);
			}
			else{
				var a = $("<a href='javascript:void(0)'>" + arr[i] + "</a>");
				var li = $("<li></li>").append(a);
				ul.append(li);
			}
		}
		$("<nav></nav>").attr("aria-label","Page navigation").append(ul).appendTo("#page");
	}
	$(document).on('click','#page ul li a',function(){
		var num = parseInt($(this).text());
		if( num <= 6){
			$(this).parent().parent().find("li").css("display","none");
			$(this).parent().parent().find("li:lt("+11+")").css("display","block");
		}else if(num > 6 && num <= 44){
			$(this).parent().parent().find("li").css("display","none");
			$(this).parent().parent().find("li:gt("+(num-7)+")").css("display","block");
			$(this).parent().parent().find("li:gt("+(num+4)+")").css("display","none");
		}else if(num > 44){
			$(this).parent().parent().find("li").css("display","none");
			$(this).parent().parent().find("li:gt("+38+")").css("display","block");
		}
	});

});