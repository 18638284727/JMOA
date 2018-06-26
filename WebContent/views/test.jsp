<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="${path }/activiti/multiple" method="post" enctype="multipart/form-data">
		<input type="file" name="fileName" multiple="multiple">
		<input type="submit" value="上传">
	</form>
	<table border="1" cellpadding="20" cellspacing="0">
		<thead>
			<tr>
				<th>表头1</th>
				<th>表头2</th>
				<th>表头3</th>
				<th>表头4</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>内容11</td>
				<td>内容12</td>
				<td>内容13</td>
				<td>内容14</td>
			</tr>
		</tbody>
		<tbody>
			<tr>
				<td>内容21</td>
				<td>内容22</td>
				<td>内容23</td>
				<td>内容24</td>
			</tr>
		</tbody>
	</table>

</body>
</html>