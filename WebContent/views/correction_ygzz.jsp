<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	pageContext.setAttribute("path", request.getContextPath());
	pageContext.setAttribute("page", "correction.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工转正申请</title>
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
							<li class="active">员工转正申请</li>
						</ol>
					</div>
				</div>
				<div class="row ccsq-row">
					<div class="col-lg-12  ccsq-bg">
						<div class="col-lg-12  ccsq-bg">
							<div class="gz">
								<input style="margin-top: -20px;" class="g-1" type="button" value="关注" id="follow"/>
							</div>
							<div class="ywsq">
								<h4>员工转正审批表</h4>
							</div>
							<div class="nryc">
								<div class="input-group1-1">
									<div class="iput">
										<p class="p1">申请人信息</p>
										<span class="t1">姓名：</span><input type="text" name="userName" value="${data.applicant }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span> <span class="t1">公司：</span><input type="text" name="company" value="${applyCompany }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span> <span class="t1">部门：</span><input type="text" name="department" value="${applyDept }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span> <span class="t1">职称：</span><input type="text" name="manager" value="${applyManager }" disabled class="i-1" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="shu">|</span> <span class="t1">申请日期：</span><input type="text" name="applicationDate" value="${data.apply }" disabled class="i-1" /></br>
									</div>
								</div>
								
								<div class="jj13">
									<p class="p1">自我评价</p>
									<span>紧急程度：</span><input class="j222" type="text" value="${data.inLineOptions1 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
									<div class="hx"></div>
									<span>试用岗位：</span><input class="j222" type="text" value="${data.reserve5 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
									<div class="hx"></div>
									<span>试用期间：</span><input class="j222" type="text" value="${data.reserve6 } 至 ${data.reserve7 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
									<div class="hx"></div>
									<span>试用薪资：</span><input class="j222" type="text" value="${data.reserve8 }元" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
									<div class="hx"></div>
									<span>期望薪资：</span><input class="j222" type="text" value="${data.reserve9 }元" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
									<div class="hx"></div>
									<span>自我工作评述：</span>
									<textarea class="ccsq-sqsy" style="width: 860px; background-color: #fff; margin-top: 8px;" placeholder="" disabled>${data.reserve10 }</textarea>
									<div class="hx"></div>
									<span>流程：</span><img alt="" src="${path }/activiti/hisImage1?processInstanceId=${processInstanceId }" width="300" height="180" style="background-color:#EAEDF2;"></br>
									<div class="hx"></div>
								</div>
								<div class="ccsq-jtmx">
											<h3>自我评价</h3>
											<table width="100%" border="0" cellpadding="0" cellspacing="0" id="tabProduct1">
												<tr class="ccsq-btt">
				                                        <th>工作业绩（20分）</th>
				                                        <th>执行能力（15分）</th>
				                                        <th>沟通协调能力（15分）</th>
				                                        <th>业务知识（15分）</th>
				                                        <th>责任感（10分）</th>
				                                        <th>服务意识（15分）</th>
				                                        <th>团队精神（10分）</th>
				                                        <th>得分</th>
				                                    </tr>
											</table>
											<span id="tableData1" style="display:none;">${data.tableData1 }</span>
                                       </div>
								<div class="jj13">
									<span>自我评价结果：</span><input class="j222" type="text" value="${data.inLineOptions2 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
									<div class="hx"></div>
								</div>
								<div class="input-group1-2">
									<div class="iput">
										<p class="p1">审批意见</p>
										<input type="hidden" value="${data.opinions }" id="opinion" />
										<div id="opinions"></div>
									</div>
								</div>
								
								<form method="post" id="form">
								<div style="text-align: center; font-size: 15px; font-weight: bold; color: #019ee1; margin-bottom: 20px;">
                                       	<span>用人部门主管评价</span>
                                       </div>
                                 <span id="tableData2" name="tableData2" style="display:none;">${data.tableData2 }</span>
                                 <c:if test="${employee.eName == nextPerson }">
								<div class="ccsq-jtmx">
											<table width="100%" border="0" cellpadding="0" cellspacing="0" id="tabProduct2">
												<tr class="ccsq-bdbt">
													<td width="700" bgcolor="#EFEFEF" Name="Num" EditType="TextBox">工作业绩（20分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount" EditType="TextBox">执行能力（15分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount1" EditType="TextBox">沟通协调能力（15分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount2" EditType="TextBox">业务知识（15分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount3" EditType="TextBox">责任感（10分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount4" EditType="TextBox">服务意识（15分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount5" EditType="TextBox">团队精神（10分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount6" EditType="TextBox">得分</td>
												</tr>
											</table>
											<input type="hidden" id="td2"  name="tableData2" value="">
                                       </div>
								<div class="ccsq-xx">
										<span>主管评价结果：</span>
										<input type="radio" name="inLineOptions3" value="优秀90-100" checked="checked"> 优秀90-100&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="inLineOptions3" value="良好80-89"> 良好80-89&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="inLineOptions3" value="一般65-79"> 一般65-79&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="inLineOptions3" value="差65以下"> 差65以下&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="hidden" id="inLineOptions3" value="${data.inLineOptions3 }">
									   </div>
								<div class="ccsq-xx">
										<span>转正情况：</span>
										<input type="radio" name="inLineOptions4" value="提前转正" checked="checked"> 提前转正&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="inLineOptions4" value="按期转正"> 按期转正&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="inLineOptions4" value="试用期不合格，不拟聘用"> 试用期不合格，不拟聘用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  	<input type="hidden" id="inLineOptions4" value="${data.inLineOptions4 }">
									   </div>
									   <div class="ccsq-xx">
									   <span>岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</span><input type="text" name="reserve11" id="reserve11" class="ccsq-jbin" value="${data.reserve11 }"/>&nbsp; <!--<button type="button" style="width: 70px;height: 30px;background-color: #019ee1;color: #FFFFFF;border: none;">浏览</button>-->
									</div>
									<div class="ccsq-xx">
									   <span>工资标准：</span><input type="number" name="reserve12" id="reserve12" class="ccsq-jbin" value="${data.reserve12 }"/>&nbsp;元 <!--<button type="button" style="width: 70px;height: 30px;background-color: #019ee1;color: #FFFFFF;border: none;">浏览</button>-->
									</div>
									<div class="ccsq-xx">
										<span>执行日期：</span><input type="text" name="reserve13" id="reserve13" value="${data.reserve13 }" class="Wdate" style="padding-left: 5px;width: 180px;height: 30px;border: none;background-color: #f5f5f5;" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd', readOnly: true });" format="yyyy-MM-dd">
				                    </div>
				                    <div class="ccsq-xx">
					                    <span>部门意见：</span>
										<textarea class="ccsq-sqsy" name="reserve14" id="reserve14" style="width: 860px; background-color: #f5f5f5; margin-top: 8px;" placeholder="" >${data.reserve14 }</textarea>
										<div class="hx"></div>
									</div>
                                 </c:if>
								<c:if test="${employee.eName != nextPerson }">
                                 	<div class="ccsq-jtmx">
											<table width="100%" border="0" cellpadding="0" cellspacing="0" id="tabProduct2">
												<tr class="ccsq-bdbt">
													<td width="700" bgcolor="#EFEFEF" Name="Num">工作业绩（20分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount">执行能力（15分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount1">沟通协调能力（15分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount2">业务知识（15分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount3">责任感（10分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount4">服务意识（15分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount5">团队精神（10分）</td>
													<td width="700" bgcolor="#EFEFEF" Name="Amount6">得分</td>
												</tr>
											</table>
                                       </div>
									    <div class="jj13">
											<span>主管评价结果：</span><input class="j222" type="text" value="${data.inLineOptions3 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
											<div class="hx"></div>
									</div> <div class="jj13">
											<span>转正情况：</span><input class="j222" type="text" value="${data.inLineOptions4 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
											<div class="hx"></div>
									</div>
									  <div class="jj13">
											<span>岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</span><input class="j222" type="text" value="${data.reserve11 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
											<div class="hx"></div>
									</div>
									   <div class="jj13">
											<span>工资标准：</span><input class="j222" type="text" value="${data.reserve12 }&nbsp;元" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
											<div class="hx"></div>
									</div>
									  <div class="jj13">
											<span>执行日期：</span><input class="j222" type="text" value="${data.reserve13 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
											<div class="hx"></div>
									</div>
									  <div class="jj13">
											<span>部门意见：</span><input class="j222" type="text" value="${data.reserve14 }" disabled />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br>
											<div class="hx"></div>
									</div>
                                 </c:if>
								<div class="jj13" style="margin-top: 30px;">
									<div class="input-group1-11">
										<input type="hidden" name="follow" value="">
										<input type="hidden" name="taskId" value="${taskId }">
										<input type="text" class="qz" name="opinion" placeholder="签字意见" value=""/>
									</div>
									<div class="an">
										<div class="na12">
											<input type="hidden" name="text" id="text" value="">
												<input class="an1" type="button" id="back" value="退回" /> 
												<input class="an2" type="button" id="adopt" value="同意" />&nbsp;&nbsp;
												<button type="button" class="an3" id="forward">转发</button>
										</div>
									</div>
								</div>
								</form>
							</div>
							<!--Modal-->
							<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">人员选择</h4>
										</div>
										<div class="modal-body" style="height: auto;">
											<!--模态框内容-->
											<nav id="mysidebarmenu" class="amazonmenu" style="margin-top: 15px;">
												<div class="sou">
													<select class="sso" id="company">
													</select>
													<select class="sso" id="dept">
													</select>
													<select class="sso" id="emp">
													</select>
												</div>
												 已选择：<input type="text" disabled="disabled" id="txtHobby" style="margin:20px 0px; width: 500px; height: 40px; padding-left: 5px; background-color: #fff;"/>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
													<button type="button" id="confirm" class="btn btn-primary">确认转发</button>
												</div>
											</nav>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/js/correction_ygzz.js"></script>
<script type="text/javascript" src="${path }/js/base.js"></script>
<!-- 可以在外部js文件中使用path作为项目的根路径 -->
<script>var path = '${path}'; </script>
<script>var page = '${page}'; </script>
<script src="${path }/dist/zoomify.min.js"></script>
</html>