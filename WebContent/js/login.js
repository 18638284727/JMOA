$(function(){
	//账号文本框blur事件
	$(document).on('blur', '#logname', function(){
		if(this.value == '') this.value = '输入ID或用户名登录';
		$.ajax({
			url : path + '/employee/checkUsername',
			type : 'POST',
			data : {"logname" : this.value},
			success : function(data){
				if(data == 0){
					$(".username").text("用户名不存在！！！");
				}
			}
		});
	});
	//账号文本框focus事件
	$(document).on('focus', '#logname', function(){
		$(".username").text("");
		if(this.value == '输入ID或用户名登录') this.value = '';
	});
	$('#password').on('keypress',function(event){ 
		//密码框输入时，改变登录链接背景色
		if($("#logname").val() == "输入ID或用户名登录"){
			$("#login").css("background-color", "#ccccce");
		}else{
			$("#login").css("background-color", "#5686fa");
		}
		if(event.keyCode == 13)      
        {  
			if(!$(".username").text() == "用户名不存在！！！" || $(".username").text() == ""){
				//点击enter键提交表单
				$("#form").submit();  
			}
        }  
    });
	//密码文本框blur事件
	$(document).on('blur', '#password', function(){
		if(this.value=='') $('.login_password').show();
	});
	//密码文本框focus事件
	$(document).on('focus', '#password', function(){
		$('.login_password').hide();
		$(".password").text("");
	});
	$(document).on('click', '#login', function(){
		var username = $("#logname").val();
		var password = $("#password").val();
		if(username == "输入ID或用户名登录" && password == ""){
			$(".username").text("请输入账号....");
			$(".password").text("请输入密码....");
		}else if(password == ""){
			$(".password").text("请输入密码....");
		}else{
			if(!$(".username").text() == "用户名不存在！！！" || $(".username").text() == ""){
				//点击enter键提交表单
				$("#form").submit();  
			}
		}
	});
});