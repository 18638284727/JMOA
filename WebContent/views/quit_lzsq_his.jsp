<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	pageContext.setAttribute("path", request.getContextPath());
	pageContext.setAttribute("page", "quit.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>离职申请历史</title>
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
							<li>办公管理</li>
							<li class="active">离职申请</li>
						</ol>
					</div>
				</div>
				<div class="row ccsq-row">
					<div class="col-lg-12  ccsq-bg">
						<div class="col-lg-12  ccsq-bg">
							<div class="gz">
								<c:if test="${data.message == '驳回' }">
									<input style="margin-top: -20px;" class="g-1" type="button" value="关注" id="follow"/>
								</c:if>
							</div>
							<div class="ywsq">
								<h4>离职申请状态表</h4>
							</div>
							<form method="post" id="form" enctype="multipart/form-data">
							<div class="nryc">
								<div class="input-group1-1">
									<div class="iput">
										<p class="p1">申请人信息</p>
										<span class="t1">姓名：</span><input type="text" name="reserve1" id="reserve1" value="${data.applicant }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span>
										<span class="t1">公司：</span><input type="text" name="reserve2" id="reserve2" value="${applyCompany }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span>
										<span class="t1">部门：</span><input type="text" name="reserve3" id="reserve3" value="${applyDept }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span>
										<span class="t1">职称：</span><input type="text" name="reserve4" id="reserve4" value="${applyManager }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span>
										<span class="t1">申请日期：</span>
										<input type="text" name="apply" id="apply" value="${data.apply }" class="Wdate" 
										   style="padding-left: 5px; width: 140px; height: 30px; border: none; background-color: #f5f5f5;"
										   onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', readOnly: true });"
										   format="yyyy-MM-dd"></br>
									</div>
								</div>
								<div class="jj13">
									<input type="hidden" class="j222" id="inLineOptions1" value="${data.inLineOptions1 }"/> 
									<input type="hidden" class="j222" id="inLineOptions2" value="${data.inLineOptions2 }"/> 
									<input class="ccsq-sp" type="hidden" name="taskId" id="taskId" value="${historyModel.id }">
									<input class="ccsq-sp" type="hidden" name="message" id="message" value="${data.message }">
									<input class="ccsq-sp" type="hidden" name="nextPerson" id="nextPerson" value="${data.nextPerson }">
								</div>
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
									   <span>入职时间：</span><input type="text" name="reserve5" id="reserve5" value="${data.reserve5 }" class="ccsq-jbin" />
									</div>
									<div class="ccsq-xx">
									   <span>预计离职时间：</span><input type="text" name="reserve6" id="reserve6" value="${data.reserve6 }" class="ccsq-jbin" />
									</div>
                                    <div class="ccsq-xx">
									   <span>离职类型：</span>
									   <input type="radio" name="inLineOptions2" id="inlineRadio1" value="合同到期不续签" checked="checked"> 合同到期不续签&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									   <input type="radio" name="inLineOptions2" id="inlineRadio2" value="试用期内解除"> 试用期内解除&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									   <input type="radio" name="inLineOptions2" id="inlineRadio2" value="合同期内辞职"> 合同期内辞职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								    </div>
									<div class="ccsq-xx">
										<span>离职原因：</span>
										<textarea class="ccsq-sqsy" name="reserve7" id="reserve7">${data.reserve7 }</textarea>
								    </div>
								    <div class="ccsq-xx">
										<span>详细说明：</span>
										<textarea class="ccsq-sqsy" name="reserve8" id="reserve8">${data.reserve8 }</textarea>
								    </div>
							    <input type="hidden" name="follow" value="">
								<input type="hidden" value="${data.opinions }" id="opinion" />
								<c:if test="${data.message != '未提交' }">
									<div class="jj13">
										<span>流程：</span><img alt="" src="${path }/activiti/hisImage2?processInstanceId=${historyModel.processInstanceId }" width="300" height="180" style="background-color: #EAEDF2;"></br>
										<div class="hx"></div>
									</div>
								</c:if>
								<div class="ccsq-jtmx">
										<h3>办公用品明细</h3>
											<c:if test="${data.message == '驳回' || data.message == '未提交' }">
											<input class="ccsq-bdan sc " type="button" name="Submit2" value="删除" onclick="DeleteRow(document.getElementById('tabProduct1'),1)" />
											<input class="ccsq-bdan xz "  type="button" name="Submit" value="新增" onclick="AddRow1(document.getElementById('tabProduct1'),1)" />
											</c:if>
										<table width="100%" border="0" cellpadding="0" cellspacing="0" id="tabProduct1">
											<c:choose>
												<c:when test="${data.message == '驳回' }">
													<tr class="ccsq-bdbt">
														<td width="32" align="center" bgcolor="#EFEFEF" Name="Num"><input type="hidden" name="checkbox" value="checkbox" /></td>
														<td width="120" bgcolor="#EFEFEF" Name="Num" EditType="TextBox">序号</td>
														<td width="103" bgcolor="#EFEFEF" Name="Amount" EditType="TextBox">工作交接内容</td>
														<td width="103" bgcolor="#EFEFEF" Name="Amount1" EditType="TextBox">交接形式</td>
														<td width="103" bgcolor="#EFEFEF" Name="Amount2" EditType="TextBox">备注</td>
													</tr>
												</c:when>
												<c:otherwise>
												<tr class="ccsq-bdbt">
													<td width="32" align="center" bgcolor="#EFEFEF" Name="Num"><input type="hidden" name="checkbox" value="checkbox" /></td>
													<td width="120" bgcolor="#EFEFEF" Name="Num">序号</td>
													<td width="103" bgcolor="#EFEFEF" Name="Amount">工作交接内容</td>
													<td width="103" bgcolor="#EFEFEF" Name="Amount1">交接形式</td>
													<td width="103" bgcolor="#EFEFEF" Name="Amount2">备注</td>
												</tr>
												</c:otherwise>
											</c:choose>
										</table>
										<span id="tableData11" style="display:none;">${data.tableData1 }</span>
										<input type="hidden" name="tableData1" id="tableData1" value="" />
									</div>
									<div class="ccsq-jtmx">
										<h3>办公用品明细</h3>
											<c:if test="${data.message == '驳回' || data.message == '未提交' }">
											<input class="ccsq-bdan sc " type="button" name="Submit2" value="删除" onclick="DeleteRow(document.getElementById('tabProduct2'),1)" />
											<input class="ccsq-bdan xz "  type="button" name="Submit" value="新增" onclick="AddRow2(document.getElementById('tabProduct2'),1)" />
											</c:if>
										<table width="100%" border="0" cellpadding="0" cellspacing="0" id="tabProduct2">
											<c:choose>
												<c:when test="${data.message == '驳回' }">
													<tr class="ccsq-bdbt">
														<td width="32" align="center" bgcolor="#EFEFEF" Name="Num"><input type="hidden" name="checkbox" value="checkbox" /></td>
														<td width="120" bgcolor="#EFEFEF" Name="Num" EditType="TextBox">序号</td>
														<td width="103" bgcolor="#EFEFEF" Name="Amount" EditType="TextBox">工作交接内容</td>
														<td width="103" bgcolor="#EFEFEF" Name="Amount1" EditType="TextBox">交接形式</td>
														<td width="103" bgcolor="#EFEFEF" Name="Amount2" EditType="TextBox">备注</td>
													</tr>
												</c:when>
												<c:otherwise>
												<tr class="ccsq-bdbt">
													<td width="32" align="center" bgcolor="#EFEFEF" Name="Num"><input type="hidden" name="checkbox" value="checkbox" /></td>
													<td width="120" bgcolor="#EFEFEF" Name="Num">序号</td>
													<td width="103" bgcolor="#EFEFEF" Name="Amount">工作交接内容</td>
													<td width="103" bgcolor="#EFEFEF" Name="Amount1">交接形式</td>
													<td width="103" bgcolor="#EFEFEF" Name="Amount2">备注</td>
												</tr>
												</c:otherwise>
											</c:choose>
										</table>
										<span id="tableData12" style="display:none;">${data.tableData2 }</span>
										<input type="hidden" name="tableData2" id="tableData2" value="" />
									</div>
								<c:if test="${data.message != '未提交' }">
								<div class="input-group1-2">
										<div class="iput">
											<p class="p1">审批意见</p>
											<div id="opinions"></div>
										</div>
									</div>
									</c:if>
									<c:if test="${data.message == '未提交' }">
									<div class="ccsq-xx">
										<span>申请人： </span> 
										<input class="ccsq-sp" type="text" value="${employee.eName }" disabled /> 
									</div>
								</c:if>	
								<div class="jj13" style="margin-top: 30px;">
									<div class="an">
										<div class="na12">
											<input type="hidden" name="state" value=""/>
											<c:choose>
												<c:when test="${data.message == '审批中' }">
													<div class="dqr">
														<img src="${path }/assets/images/dqr.png" style="margin-left:-600px;"/>审批中....
													</div>
												</c:when>
												<c:when test="${data.message == '完成' }">
													<div class="pas">
												        <img src="${path }/assets/images/pas.png" style="margin-left:-600px;"/>审批通过
													</div>
												</c:when>
												<c:when test="${data.message == '未提交' }">
													<input type="hidden" name="state" value=""/>
													<input class="bc" name="btn" type="button" value="确定" id="save"/>&nbsp;&nbsp; 
													<input class="tj" name="btn"  type="button" value="提 交" id="sub" />
												</c:when>
												<c:otherwise>
													<c:if test="${employee.eName == data.applicant }">
														<input class="an2" type="button" id="adopt" value="重新提交" style="margin-left:200px;"/>&nbsp;&nbsp;
													</c:if>	
													<c:if test="${employee.eName != data.applicant }">
														<input class="an1" type="button" value="已驳回" style="margin-left:200px;"/>&nbsp;&nbsp;
													</c:if>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/js/quit_lzsq_his.js"></script>
<script type="text/javascript" src="${path }/js/base.js"></script>
<!-- 可以在外部js文件中使用path作为项目的根路径 -->
<script>var path = '${path}'; </script>
<script>var page = '${page}'; </script>
<script src="${path }/dist/zoomify.min.js"></script>
</html>