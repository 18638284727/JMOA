<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- IE兼容 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 禁止缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>登录页面</title>
<script type="text/javascript" src="${path }/bootstrap/js/jquery-1.12.4.min.js"></script>
<link type="text/css" rel="stylesheet" href="${path }/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${path }/Font-Awesome-3.2.1/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="${path }/bootstrap/css/login.css" />
</head>
<body>
	<div class="container-fluid">
		<div class="logo_box">
			<h3>
				<img src="${path }/assets/images/logo.png">&nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;今迈集团OA办公管理系统
			</h3>
			<form action="${path }/employee/login" id="form" method="post">
				<div class="input_outer">
					<span class="u_user"></span> 
					<input name="logname" id="logname" class="text" 
						   value="输入ID或用户名登录" style="color: #666 !important" 
						   type="text">
				</div>
				<span class="username"></span>
				<div class="input_outer">
					<span class="us_uer"></span> 
					<label class="l-login login_password" style="color: #999; display: block;">输入密码</label> 
					<input name="password" id="password" class="text"
						   style="color: #999 !important; position: absolute; z-index: 100;"
						   value="" type="password">
				</div>
				<span class="password"></span>
				<div class="mb2">
					<a class="act-but submit" href="javascript:void(0);" style="color: #FFFFFF" id="login">登录</a>
				</div>
				<input name="savesid" value="true" id="check-box" class="checkbox"
					   type="checkbox" style="float: left; margin-right: 5px;">
					<span style="color: #999;">记住用户名</span> 
					<a href="${path }/employee/logout" class="login-fgetpwd" style="color: #999">忘记密码？</a>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/js/login.js"></script>
<!-- 可以在外部js文件中使用path作为项目的根路径 -->
<script>var path = '${path}'; </script>
</html>
