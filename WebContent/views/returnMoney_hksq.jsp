<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>回款管理</title>
<script type="text/javascript" src="${path }/bootstrap/js/jquery-1.12.4.min.js"></script>
<link type="text/css" rel="stylesheet" href="${path }/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${path }/Font-Awesome-3.2.1/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="${path }/bootstrap/css/main.css" />
<link type="text/css" rel="stylesheet" href="${path }/bootstrap/css/ccsq.css" />

<link href="${path }/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="${path }/assets/css/components.css" rel="stylesheet" type="text/css">
<link href="${path }/assets/css/pages.css" rel="stylesheet" type="text/css">
<link href="${path }/assets/css/responsive.css" rel="stylesheet" type="text/css">

<link type="text/css" href="${path }/nav/css/style.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="${path }/bootstrap/css/four-status-spxq.css" />

<script type="text/javascript" src="${path }/nav/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${path }/bootstrap/js/bootstrap.min.js"></script> <!-- 模态框不显示问题 -->
<script type="text/javascript" src="${path }/nav/js/verticalnav.js"></script>
<script type="text/javascript" src="${path }/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
#home table td{vertical-align: middle;}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(".vertical-nav").verticalnav({
			speed : 400,
			align : "left"
		});
	});
</script>
</head>
<body>
	<div class="container-fluid">	
		<div class="row bg">
			<div class="col-lg-1 left-navigation">
				<div class="logo">
					<h3>
						<img src="${path }/assets/images/logo-1.png">今迈办公系统
					</h3>
				</div>
				<ul class="vertical-nav">
					<c:forEach items="${nodes }" var="n">
						<shiro:hasAnyRoles name = "${n.roles }">
							<li role="presentation"><a href="${path }/${n.jUrl }"><span class="${n.jIcon }"></span>${n.jName }</a>
								<ul>
									<c:forEach items="${n.children }" var="c">
										<li><a href="${path }/${c.jUrl }">${c.jName }</a>
									</c:forEach>
								</ul>
							</li>
						</shiro:hasAnyRoles>
					</c:forEach>
					<%-- 实现角色不同的操作，则使用<shiro:hasPermission name="角色:操作"></shiro:hasPermission>判断 --%>
				</ul>
				<div class="modal fade" id="myModal">
					<div class="modal-dialog" style="color:#666666;">
						<div class="modal-content">
							<div class="modal-header" style="text-align: center;">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title">模型信息</h4>
							</div>
							<div class="modal-body">
								<div class="tile">
									<div style="padding-left: 75px; padding-bottom: 20px; font-size: 14px; width: 100%;">
										模型名称：<input type="text" value="" id="name" style="background-color: #f5f5f5; border: none; width: 250px; height: 30px;" /></br>
										<div style="margin-top: 20px;">
										</div>
										模型标识：<input type="text" value="" id="key" style="background-color: #f5f5f5; border: none; width: 250px; height: 30px;" /></br>
										<div style="margin-top: 20px;">
										</div>
										模型描述：<input type="text" value="" id="desc" style="background-color: #f5f5f5; border: none; width: 250px; height: 30px;" /></br>
										<div style="margin-top: 20px;">
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button type="button" class="btn btn-primary" id="ok">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-11">
				<div class="row">
					<div class="col-lg-8 header">
						<div>
							<div class="input-group hd-left"> 		
						    </div>
						</div>
					</div>
					<div class="col-lg-4 header">
						<div class="hd-right">
							<ul>
								<li>
									<span><img src="${path }/images/${employee.eImage }"></span>
									${employee.eName }，欢迎您！
								</li>
								<li>
									<a href="${path }/employee/logout">
										<span class="glyphicon glyphicon-off" aria-hidden="true"></span>退出
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb ccsq-dh">
							<li><a href="#">主页</a></li>
							<li>财务管理</li>
							<li class="active">回款管理</li>
						</ol>
					</div>
				</div>
				<div class="row ccsq-row">
					<div class="col-lg-12  ccsq-bg">
						<div class="col-lg-12  ccsq-bg">
							<div class="gz"></div>
							<div class="ywsq">
								<h4>待回款详情单</h4>
							</div>
							<div class="nryc">
								<div class="input-group1-1">
									<div class="iput">
										<p class="p1">申请人信息</p>
										<span class="t1">姓名：</span><input type="text" name="reserve1" id="reserve1" value="${data.applicant }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span>
										<span class="t1">公司：</span><input type="text" name="reserve2" id="reserve2" value="${applyCompany }"  disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span>
										<span class="t1">部门：</span><input type="text" name="reserve3" id="reserve3" value="${applyDept }"  disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span>
										<span class="t1">职称：</span><input type="text" name="reserve4" id="reserve4" value="${applyManager }"  disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span>
										<span class="t1">申请日期：</span><input type="text" name="apply" value="${data.apply }" disabled class="i-1" /></br>
									</div>
								</div>
								<div class="ccsq-jbxx">账单信息</div>
								<div class="input-group ccsq-clxx" style="margin-bottom: 30px;" >
								   <div style="padding-right: 100px; float: left;"><span>合同名称：</span><input class="ccsq-jbpt-1"  type="text" name="company" value="" disabled="disabed"></div>
								   <span>客户名称：</span><input class="ccsq-jbpt-1" type="text" name="userName" value="" disabled="disabed">
							    </div>
								<div class="input-group ccsq-clxx" style="margin-bottom: 30px;" >
									   <div style="padding-right: 100px; float: left;">
									   	<span>合同金额：</span>
									   	<input class="ccsq-jbpt-1" style="width: 140px;" type="text" name="userName" value="" disabled="disabled">元
									    <input class="ccsq-jbpt-1" style="width: 212px; margin-left: 10px;" type="text" name="userName" value="请输入大写金额" disabled="disabled">
									   </div>
									   <span>已回金额：</span>
									    <input class="ccsq-jbpt-1" style="width: 140px;" type="text" name="userName" value="" disabled="disabled">元
									    <input class="ccsq-jbpt-1" style="width: 212px; margin-left: 10px;" type="text" name="userName" value="请输入大写金额" disabled="disabled">
								    </div>
								<div class="input-group ccsq-clxx" style="margin-bottom: 30px;" >
									   <div style="padding-right: 100px; float: left;"><span>签订日期：</span><input class="ccsq-jbpt-1"  type="text" name="company" value="" disabled="disabed"></div>
									   <span>业&nbsp;&nbsp;务&nbsp;&nbsp;员：</span><input class="ccsq-jbpt-1" type="text" name="userName" value="" disabled="disabed">
								    </div>
								<div class="input-group ccsq-clxx" style="margin-bottom: 30px;" >
									   <div style="padding-right: 100px; float: left;"><span>批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：</span><input class="ccsq-jbpt-1"  type="text" name="company" value="" disabled="disabed"></div>
									   <span>金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</span>
									   <input class="ccsq-jbpt-1" style="width: 140px;" type="text" name="userName" value="" disabled="disabled">元
									    <input class="ccsq-jbpt-1" style="width: 212px; margin-left: 10px;" type="text" name="userName" value="请输入大写金额" disabled="disabled">
								    </div>
								 <div class="input-group ccsq-clxx" style="margin-bottom: 30px;" >
									   <div style="padding-right: 100px; float: left;">
									   	<span>计划回款日期：</span><input disabled="disabled" type="text" name="end_time" id="end_time" class="Wdate" placeholder="2018-03-29" style="padding-left: 5px;width: 380px;height: 30px;border: none;background-color: #f5f5f5;" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', readOnly: true ,maxDate:'#F{$dp.$D(\'end_time\')}'});" format="yyyy-MM-dd"></div>
								   		<%
								   			String flag = request.getParameter("flag");
								   			if(!flag.equals("n")){
								   		%>
											<div style="clear:both;"></div>
									   		<span>业务经理补助详情：</span><input type="text" class="j2222" value="尾款于2018-05-06打入公司账户。" disabled /> </div>					   				
								   		<%
								   			}
								   		%>
									   		
								    </div>
								 <div class="input-group-1 ccsq-clxx">
									   <p class="p1">操作</p>
									   <div style="padding-right: 100px; float:left;">
										    <span>申请原因：</span>
								            <select style="width:380px;height:30px; border:none; background: #f5f5f5;">
											    <option value="one">未回款</option>
								                <option value="two">已回款</option>
										    </select>
										</div>
										<div style="float:left;">
										    <span>实际回款日期：</span><input type="text" name="end_time" id="end_time" class="Wdate" placeholder="" style="padding-left: 5px;width: 380px;height: 30px;border: none;background-color: #f5f5f5;" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', readOnly: true ,maxDate:'#F{$dp.$D(\'end_time\')}'});" format="yyyy-MM-dd">
								        </div>
								     </div>
								     
									<div class="jj13" style="margin-top: 80px;">
										<div class="input-group1-11">
											<input type="text" class="qz" name="opinion" placeholder="签字意见" value="" />
										</div>
										<div style="margin-top: 45px;margin-left: 550px;">
											<input class="bc" type="button" value="完成"/>&nbsp;&nbsp;
									        <input class="an1" style="margin-left: 0px;" type="button" value="取消" />
										</div>
									</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript" src="${path }/js/returnMoney_hksq.js"></script>
<!-- 可以在外部js文件中使用path作为项目的根路径 -->
<script>var path = '${path}'; </script>
</html>