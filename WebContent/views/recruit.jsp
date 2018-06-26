<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	pageContext.setAttribute("path", request.getContextPath());
	pageContext.setAttribute("page", "recruit_zpsq.jsp");
	pageContext.setAttribute("name", "招聘申请");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>招聘申请</title>
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

<script type="text/javascript" src="${path }/bootstrap/js/bootstrap.min.js"></script> <!-- 模态框不显示问题 -->
<script type="text/javascript" src="${path }/nav/js/jquery-1.10.1.min.js"></script>
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
							<li>办公管理</li>
							<li class="active">招聘申请</li>
						</ol>
						<div class="gz">
						   <input style="margin-top: -20px;" class="g-1" type="button" value="关注" id="follow"/>
						</div>
					</div>
				</div>
				<div class="row ccsq-row">
					<input type="hidden" id="processModelName" value="${processModel.name }"/>
					<input type="hidden" id="flag" value="<%=request.getParameter("flag") %>"/>
					<div class="col-lg-12  ccsq-bg">
						<ul id="myTab" class="nav nav-tabs ccsq-bt" style="width: 100%;">
							<li class="bt-jl"><a href="javascript:void(0);" data-toggle="tab" id="dw">待我处理工作<span></span></a></li>
							<li class="bt-jl"><a href="javascript:void(0);" data-toggle="tab" id="fqzt">发起工作状态<span></span></a></li>
							<li class="bt-jl"><a href="javascript:void(0);" data-toggle="tab" id="fqsq">发起申请</a></li>
							<li class="bt-jl" id="pageInfo1" style="padding: 20px 0px 0px 990px;"></li>
							<li class="bt-jl" id="pageInfo2" style="padding: 20px 0px 0px 990px;"></li>
						</ul>
						<div id="myTabContent" class="tab-content" style="height: 810px;">
							<div id="leave1">
								<div class="sou">
									<input type="text" class="sso" id="stime" value="开始时间..." onclick="WdatePicker()" /> 
									<input type="text" class="sso" id="etime" value="结束时间..." onclick="WdatePicker()" /> 
									<input type="text" class="sso" id="ename" placeholder="请输入姓名..."/> 
									<select class="sso" id="company">
										<option value="0">按公司搜索</option>
										<option value="金迈衡器">金迈衡器</option>
										<option value="新新园林">新新园林</option>
									</select> 
									<select class="sso" id="dept">
										<option value="0">按部门搜索</option>
										<option value="软件部">软件部</option>
										<option value="销售部">销售部</option>
									</select>
									<input type="button" value="查询" style="width: 90px; height: 35px; margin-left: 10px; background-color: #019ee1; color: #FFFFFF; border: none;" id="search1" />
								</div>
								<div class="col-md-12">
									<div class="tile">
										<table class="table table-hover table-striped" id="home1">
											<thead class="bt">
												<tr>
													<th>&nbsp;&nbsp;&nbsp;&nbsp;序号</th>
													<th>姓名</th>
													<th>公司</th>
													<th>部门</th>
													<th>申请日期</th>
													<th>紧急程度</th>
													<th>审核状态</th>
												</tr>
											</thead>
											<tbody class="tb">

											</tbody>
										</table>
										<div id="pageNav1" style="position: absolute;right:0px;top:690px;">
										</div>
									</div>
								</div>
							</div>
							<div id="leave2">
								<div class="sou">
									<input type="text" class="sso" id="stime2" value="开始时间..." onclick="WdatePicker()" /> 
									<input type="text" class="sso" id="etime2" value="结束时间..." onclick="WdatePicker()" /> 
									<input type="button" value="查询" style="width: 90px; height: 35px; margin-left: 10px; background-color: #019ee1; color: #FFFFFF; border: none;" id="search2"/>
								</div>
								<div class="col-md-12">
									<div class="tile">
										<table class="table table-hover table-striped" id="home2">
											<thead class="bt">
												<tr>
													<th>&nbsp;&nbsp;&nbsp;&nbsp;序号</th>
													<th>姓名</th>
													<th>公司</th>
													<th>部门</th>
													<th>申请日期</th>
													<th>紧急程度</th>
													<th>审核状态</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody class="tb">
											</tbody>
										</table>
										<div id="pageNav2" style="position: absolute; right: 0px; top: 690px;">
											
										</div>
									</div>
								</div>
							</div>
							
							<div id="leave3">
								<form method="post" id="form" enctype="multipart/form-data">
								<input class="ccsq-sp" type="hidden" name="follow" value="关注">
								<div class="ywsq">
									<h4>招聘申请表</h4>
								</div>
								<div class="ccsq-jbxx">基本信息</div>
								<div class="input-group ccsq-clxx">
									<span>姓名：</span><input class="ccsq-jbpt" type="text" value="${employee.eName }" disabled>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>公司：</span><input class="ccsq-jbpt" type="text" value="${employee.dept.company.cName }"  disabled >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>部门：</span><input class="ccsq-jbpt" type="text" value="${employee.dept.dName }"  disabled >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>职位：</span><input class="ccsq-jbpt" type="text" value="${employee.eManager }"  disabled >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>申请日期：</span>
									<input type="text" name="apply" id="apply" class="Wdate" 
										   style="padding-left: 5px; width: 140px; height: 30px; border: none; background-color: #f5f5f5;"
										   onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', readOnly: true });"
										   format="yyyy-MM-dd">
								</div>
								<div class="ccsq-jbxx">招聘信息</div>
								<div class="ccsq-xx">
									<span>紧急程度：</span> 
									<input type="radio" name="inLineOptions1" id="inlineRadio1" value="一般" checked="checked">
									一般&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="radio" name="inLineOptions1" id="inlineRadio2" value="重要">
									重要&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="radio" name="inLineOptions1" id="inlineRadio3" value="紧急">
									紧急
								</div>
								<div class="ccsq-xx">
									<input class="ccsq-sp" type="hidden" name="taskId" id="taskId" value="">
									<input class="ccsq-sp" type="hidden" name="message" id="message" value="">
									<input class="ccsq-sp" type="hidden" name="nextPerson" id="nextPerson" value="">
								</div>
								
								<div class="input-group ccsq-clxx" style="margin-bottom: 30px;">
									<span>申请部门：</span><input class="ccsq-jbpt" style="width: 180px;" type="text" name="reserve1" id="reserve1" value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>需求岗位：</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="ccsq-jbpt" style="width: 180px;" type="text" name="reserve2" id="reserve2" value=""  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>需求人数：</span>&nbsp;&nbsp;&nbsp;<input class="ccsq-jbpt" style="width: 180px;" type="number" name="reserve3" id="reserve3" value="" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div class="input-group ccsq-clxx" style="margin-bottom: 30px;">
									<span>岗位编制：</span><input class="ccsq-jbpt" style="width: 180px;" type="text" name="reserve4" id="reserve4" value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>岗位实有人数：</span><input class="ccsq-jbpt" style="width: 180px;" type="number" name="reserve5" id="reserve5" value=""  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>拟到岗日期：</span><input class="ccsq-jbpt" style="width: 180px;" type="text" name="reserve6" id="reserve6" value="" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div class="ccsq-xx">
									<span>申请理由：</span>
									<input type="checkbox" name="inLineOptions2" id="inlineRadio1" value="新增编制" checked="checked"> 新增编制&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="checkbox" name="inLineOptions2" id="inlineRadio2" value="离职补充"> 离职补充&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="checkbox" name="inLineOptions2" id="inlineRadio3" value="其他"> 其他
								</div>
								<div class="input-group1-1"  style="margin-bottom: 30px;">
								        <div class="iput">
											<p class="p1">需求岗位任职资格条件</p>
											<span>性别：</span>
										    <select style="width:180px;height:30px; border:none; background: #F5F5F5;" name="reserve11" id="reserve11">
											    <option value="不限">不限</option>
											    <option value="男">男</option>
											    <option value="女">女</option>
										    </select>
											<span class="shu">|</span>
											
										    <span>年龄：</span>   
										    <input type="number" name="reserve7" id="reserve7" value="" class="ccsq-jbin" style="width:180px;height:30px; border:none; background: #F5F5F5;">
										    <span class="shu">|</span>
										    
										    <span>婚姻状况：</span>
										    <select style="width:180px;height:30px; border:none; background: #F5F5F5;" name="reserve12" id="reserve12">
											    <option value="不限">不限</option>
											    <option value="已婚">已婚</option>
											    <option value="未婚">未婚</option>
										    </select>
									    </div>
									   <!-- //第二行//-->
									    <div class="iput">
											<span>学历：</span>
										    <select style="width:180px;height:30px; border:none; background: #F5F5F5;"name="reserve13" id="reserve13">
											    <option value="不限">不限</option>
											    <option value="专科">专科</option>
											    <option value="本科">本科</option>
											    <option value="硕士">硕士</option>
										    </select>
											<span class="shu">|</span>
											
										    <span>专业：</span>   
										    <input type="text" name="reserve8" id="reserve8" value="" class="ccsq-jbin" style="width:180px;height:30px; border:none; background: #F5F5F5;">
										    <span class="shu">|</span>
										    
										    <span>经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验：</span>
										    <select style="width:180px;height:30px; border:none; background: #F5F5F5;" name="reserve14" id="reserve14">
											    <option value="不限">不限</option>
											    <option value="1-3年">1-3年</option>
											    <option value="3-5年">3-5年</option>
										    </select>
										    
										    <div class="ccsq-xx" style="margin-left:0px; margin-top: 30px;">
												<span>详细说明：</span>
												<textarea class="ccsq-sqsy" style="width: 860px;" name="reserve9" id="reserve9"></textarea>
									        </div>
									        <div class="ccsq-xx" style="margin-left:0px; margin-top: 30px;">
												<span>工作职责：</span>
												<textarea class="ccsq-sqsy" style="width: 860px;" name="reserve10" id="reserve10"></textarea>
									        </div>
									    </div>
								    </div>
								
								<div class="ccsq-xx">
									<span>申请人： </span> 
									<input class="ccsq-sp" type="text" value="${employee.eName }" disabled /> 
								</div>
								<input type="hidden" name="condition" id="condition" value="">
								<div style="margin-top: 45px; margin-left: 600px;">
									<input type="hidden" name="state" value=""/>
									<input class="bc" name="btn" type="button" value="保存" id="save"/>&nbsp;&nbsp; 
									<input class="tj" name="btn"  type="button" value="提交" id="sub" />
								</div> 
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/js/recruit.js"></script>
<script type="text/javascript" src="${path }/js/base.js"></script>
<!-- 可以在外部js文件中使用path作为项目的根路径 -->
<script>var path = '${path}'; </script>
<script>var page = '${page}'; </script>
<script>var name = '${name}'; </script>
</html>