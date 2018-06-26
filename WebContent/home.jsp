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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- IE兼容 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 禁止缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>首页</title>
<script type="text/javascript" src="${path }/bootstrap/js/jquery-1.12.4.min.js"></script>
<link type="text/css" rel="stylesheet" href="${path }/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${path }/Font-Awesome-3.2.1/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="${path }/bootstrap/css/main.css" />
<link href="${path }/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="${path }/assets/css/components.css" rel="stylesheet" type="text/css">
<link href="${path }/assets/css/pages.css" rel="stylesheet" type="text/css">
<link href="${path }/assets/css/responsive.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${path }/date/css/calendar.css">
<script type="text/javascript" src="${path }/bootstrap/js/bootstrap.min.js"></script>
<link type="text/css" href="${path }/nav/css/style.css" rel="stylesheet">
<script type="text/javascript" src="${path }/nav/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${path }/nav/js/verticalnav.js"></script>
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
				<div class="row row-1">
					<div class="col-lg-8  dbsx-bg">
						<ul id="myTab" class="nav nav-tabs">
				  			<li class="glyphicon glyphicon-tasks" aria-hidden="true" id="dbsx"></li>
							<li class="bt-jl"><a href="#home" data-toggle="tab">待我处理工作<span>(2)</span></a></li>
							<li class="bt-jl"><a href="#ios" data-toggle="tab">发起工作状态<span>(2)</span></a></li>
							<li class="bt-jl"><a href="#jmeter" data-toggle="tab">重点关注<span>(3)</span></a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in active" id="home">
								<div class="col-md-12">
									<div class="tile">
										<table class="table table-hover">
											<thead class="bt">
												<tr>
													<th>&nbsp;&nbsp;&nbsp;&nbsp;表单类型</th>
													<th>请求标题</th>
													<th>创建人</th>
													<th>接收日期</th>
												</tr>
											</thead>
											<tbody class="tb">
												<tr>
													<td><span class="icon-angle-right icon-large"></span>用车申请</td>
													<td>车辆派车单</td>
													<td>杨文元</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-angle-right icon-large"></span>用车申请</td>
													<td>车辆派车单</td>
													<td>杨文元</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-angle-right icon-large"></span>用车申请</td>
													<td>车辆派车单</td>
													<td>杨文元</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-angle-right icon-large"></span>用车申请</td>
													<td>车辆派车单</td>
													<td>杨文元</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-angle-right icon-large"></span>用车申请</td>
													<td>车辆派车单</td>
													<td>杨文元</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-angle-right icon-large"></span>用车申请</td>
													<td>车辆派车单</td>
													<td>杨文元</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-angle-right icon-large"></span>用车申请</td>
													<td>车辆派车单</td>
													<td>杨文元</td>
													<td>2018-03-18</td>
												</tr>
											</tbody>
										</table>
										<div>
											<nav aria-label="Page navigation">
												<ul class="pagination fy">
													<li><a href="#" style="border:0">更多......</a></li>
												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="ios">
								<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod
									Touch 和 Apple TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X
									操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
							</div>
							<div class="tab-pane fade" id="jmeter">
								<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="panels panel-default">
							<div class="panel-heading">
								<h4 class="panel-title phb-bt">
									<span class=" icon-calendar icon-large"></span>日程设置
								</h4>
							</div>
							<div id="calendar" class="calendars"></div>
						</div>
					</div>
				</div>
				<div class="row row-1">
					<div class="col-lg-8  dbsx-bg">
						<ul id="myTab" class="nav nav-tabs">
							<li class="icon-volume-up icon-large" id="dbsx"></li>
							<li class="active bt-jl"><a href="#tzgg" data-toggle="tab">通知公告</a></li>
							<li class="bt-jl"><a href="#gszd" data-toggle="tab">公司制度</a></li>
							<li class="bt-jl"><a href="#zhxw" data-toggle="tab">综合新闻</a></li>
							<li class="bt-jl"><a href="#ygfc" data-toggle="tab">员工风采</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in active" id="tzgg">
								<div class="col-md-12">
									<div class="tile">
										<table class="table table-hover">
											<tbody class="tb">
											</tbody>
										</table>
										<div id="pageNav">
											<nav aria-label="Page navigation">
												<ul class="pagination fy">
													<li><a href="${path }/views/notice_bulletin.jsp" style="border:0">更多......</a></li>
												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="gszd">
								<div class="col-md-12">
									<div class="tile">
										<table class="table table-hover">
											<tbody class="tb">
												<tr>
													<td><span class="icon-double-angle-right"></span>2017年中秋国庆节放假通知</td>
													<td>系统管理员</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-double-angle-right"></span>2018年素质拓展训练如期进行！
													</td>
													<td>系统管理员</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-double-angle-right"></span>2017年中秋国庆节放假通知2018年素质拓展训练如期进行！
													</td>
													<td>系统管理员</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-double-angle-right"></span>2017年中秋国庆节放假通知</td>
													<td>系统管理员</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-double-angle-right"></span>2017年中秋国庆节放假通知</td>
													<td>系统管理员</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-double-angle-right"></span>2017年中秋国庆节放假通知</td>
													<td>系统管理员</td>
													<td>2018-03-18</td>
												</tr>
												<tr>
													<td><span class="icon-double-angle-right"></span>2017年中秋国庆节放假通知</td>
													<td>系统管理员</td>
													<td>2018-03-18</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="zhxw">
								<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
							</div>
							<div class="tab-pane fade" id="ygfc">
								<p>Enterprise Java
									Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web
									Logic 等）的 J2EE 上。</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="panels panel-default rl">
							<div class="panel-heading">
								<h4 class="panel-title phb-bt">
									<span class=" icon-trophy icon-large"></span>销售龙虎榜
								</h4>
							</div>
							<div class="panel-body">
								<div class="inbox-widget nicescroll mx-box">
									<a href="#">
										<div class="inbox-item">
											<div id="a" class="inbox-item-img">
												<img src="images/avatar-1.jpg" class="img-circle" alt=""/>
											</div>
											<p id="p1" class="inbox-item-author">Chadengle</p>
											<p id="p2" class="inbox-item-text">Hey! there I'm available...</p>
											<p id="p3" class="inbox-item-date">13:40 PM</p>
										</div>
									</a> 
									<a href="#">
										<div class="inbox-item">
											<div id="a" class="inbox-item-img">
												<img src="images/avatar-1.jpg" class="img-circle" alt=""/>
											</div>
											<p id="p1" class="inbox-item-author">Chadengle</p>
											<p id="p2" class="inbox-item-text">Hey! there I'm available...</p>
											<p id="p3" class="inbox-item-date">13:40 PM</p>
										</div>
									</a> 
									<a href="#">
										<div class="inbox-item">
											<div id="a" class="inbox-item-img">
												<img src="images/avatar-1.jpg" class="img-circle" alt=""/>
											</div>
											<p id="p1" class="inbox-item-author">Chadengle</p>
											<p id="p2" class="inbox-item-text">Hey! there I'm available...</p>
											<p id="p3" class="inbox-item-date">13:40 PM</p>
										</div>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${path }/date/js/calendar.js"></script>
<script src="${path }/js/home.js"></script>
<!-- 可以在外部js文件中使用path作为项目的根路径 -->
<script>var path = '${path}'; </script>
</html>
