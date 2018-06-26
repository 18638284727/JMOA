<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工入职申请</title>
<script type="text/javascript" src="${path }/bootstrap/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<!-- 读取部门信息，显示在下拉框 -->
	<form id="form">
		编号：<input type="text" name="eId"/><br/><br/>
		账号：<input type="text" name="username"/><br/><br/>
		密码：<input type="password" name="password"/><br/><br/>
			 <input type="button" value="注册" id="register"/>
	</form>
	<div id="content"></div>
	<div id="page"></div>
</body>
<script type="text/javascript" src="${path }/js/empForm.js"></script>
<script>var path = '${path}'; </script>
</html>